package com.kevvlvl.quarkus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevvlvl.quarkus.ApiTestProfile;
import com.kevvlvl.quarkus.MakeStub;
import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import com.kevvlvl.quarkus.repository.MakeRepository;
import com.kevvlvl.quarkus.repository.ModelRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
@TestProfile(ApiTestProfile.class)
public class MakesServiceTest {

    @Inject
    MakesService makesService;

    @InjectMock
    MakeRepository makeRepository;

    @InjectMock
    ModelRepository modelRepository;

    @Test
    public void getAllMakesWithUnavailableDataFromRedisAndAvailableFromDb() throws JsonProcessingException {

        // TODO: ideally mock Redis calls..
        Mockito.when(makeRepository.listAll()).thenReturn(MakeStub.getMakes());

        List<MakeDto> makeDtos = makesService.getMakes();
        Assertions.assertNotNull(makeDtos);
    }

    @Test
    public void getAllModelsWithUnavailableDataFromRedisAndAvailableFromDb() throws JsonProcessingException {

        // TODO: ideally mock Redis calls..
        Mockito.when(modelRepository.listAll()).thenReturn(MakeStub.getModels());

        List<ModelDto> modelDtos = makesService.getModels();
        Assertions.assertNotNull(modelDtos);
    }
}
