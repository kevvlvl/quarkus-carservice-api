package com.kevvlvl.quarkus.service;

import com.kevvlvl.quarkus.ApiTestProfile;
import com.kevvlvl.quarkus.MakeStub;
import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import com.kevvlvl.quarkus.redis.RedisService;
import com.kevvlvl.quarkus.repository.MakeRepository;
import com.kevvlvl.quarkus.repository.ModelRepository;
import io.quarkus.redis.client.RedisClient;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@QuarkusTest
@TestProfile(ApiTestProfile.class)
public class MakesServiceTest {

    @Inject
    MakesService makesService;

    @InjectMock
    RedisClient redisClient;

    @InjectMock
    RedisService redisService;

    @InjectMock
    MakeRepository makeRepository;

    @InjectMock
    ModelRepository modelRepository;

    @Test
    public void getAllMakesWithUnavailableDataFromRedisAndAvailableFromDb() {

        Mockito.when(redisService.get(anyString())).thenReturn(null);
        Mockito.when(makeRepository.listAll()).thenReturn(MakeStub.getMakes());

        List<MakeDto> makeDtos = makesService.getMakes();
        Assertions.assertNotNull(makeDtos);
    }

    @Test
    public void getAllModelsWithUnavailableDataFromRedisAndAvailableFromDb() {

        Mockito.when(redisService.get(anyString())).thenReturn(null);

        List<ModelDto> modelDtos = makesService.getModels();
        Assertions.assertNotNull(modelDtos);
    }
}
