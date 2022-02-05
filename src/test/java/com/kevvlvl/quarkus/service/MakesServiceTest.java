package com.kevvlvl.quarkus.service;

import com.kevvlvl.quarkus.ApiTestProfile;
import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import com.kevvlvl.quarkus.model.Make;
import com.kevvlvl.quarkus.model.Model;
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

import java.math.BigDecimal;
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
        Mockito.when(makeRepository.listAll()).thenReturn(getMakeStub());

        List<MakeDto> makeDtos = makesService.getMakes();
        Assertions.assertNotNull(makeDtos);
    }

    @Test
    public void getAllModelsWithUnavailableDataFromRedisAndAvailableFromDb() {

        Mockito.when(redisService.get(anyString())).thenReturn(null);

        List<ModelDto> modelDtos = makesService.getModels();
        Assertions.assertNotNull(modelDtos);
    }

    private List<Make> getMakeStub() {

        Make make1 = new Make();
        make1.setId(111);
        make1.setName("Ford");
        make1.setCountryOfOrigin("USA");

        Make make2 = new Make();
        make2.setId(222);
        make2.setName("Honda");
        make2.setCountryOfOrigin("Japan");

        return List.of(make1, make2);
    }

    private List<Model> getModelStub() {

        Model model1 = new Model();
        model1.setId(888);
        model1.setMake(111);
        model1.setModel("Mustang");
        model1.setMsrp(new BigDecimal("40000"));

        Model model2 = new Model();
        model2.setId(999);
        model2.setMake(222);
        model2.setModel("Civic");
        model2.setMsrp(new BigDecimal("20000"));

        return List.of(model1, model2);
    }
}
