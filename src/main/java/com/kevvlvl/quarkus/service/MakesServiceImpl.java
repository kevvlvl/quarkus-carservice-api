package com.kevvlvl.quarkus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import com.kevvlvl.quarkus.model.Make;
import com.kevvlvl.quarkus.model.Model;
import com.kevvlvl.quarkus.redis.RedisService;
import com.kevvlvl.quarkus.repository.MakeRepository;
import com.kevvlvl.quarkus.repository.ModelRepository;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class MakesServiceImpl implements MakesService {

    private static final Logger LOG = Logger.getLogger(MakesServiceImpl.class);
    private static final String REDIS_MODELS_KEY = "car-models";
    private static final String REDIS_MAKES_KEY = "car-makes";

    private final RedisService redisService;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;

    @Inject
    public MakesServiceImpl(RedisService redisService,
                            MakeRepository makeRepository,
                            ModelRepository modelRepository) {
        this.redisService = redisService;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<MakeDto> getMakes() {

        List<MakeDto> makeDtos = null;

        try {
            makeDtos = deserializeFromRedis(redisService.get(REDIS_MAKES_KEY), MakeDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(makeDtos == null || makeDtos.isEmpty()) {

            LOG.info("Makes not found in Redis. Query DB");

            List<Make> makes = makeRepository.listAll();

            makeDtos = makes.stream()
                    .map(m -> new MakeDto(m.getId(), m.getName(), m.getCountryOfOrigin()))
                    .collect(Collectors.toList());

            writeRedis(REDIS_MAKES_KEY, makeDtos);
        }

        return makeDtos;
    }

    @Override
    public List<ModelDto> getModels() {

        List<ModelDto> modelDtos = null;
        try {
            modelDtos = deserializeFromRedis(redisService.get(REDIS_MODELS_KEY), ModelDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(modelDtos == null || modelDtos.isEmpty()) {

            LOG.info("Models not found in Redis. Query DB");

            List<Model> models = modelRepository.listAll();
            modelDtos = models.stream()
                    .map(m -> new ModelDto(m.getId(), m.getModel(), m.getMsrp()))
                    .collect(Collectors.toList());

            writeRedis(REDIS_MODELS_KEY, modelDtos);
        }

        return modelDtos;
    }

    private void writeRedis(String key, List<?> data) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            LOG.info("About to write to Redis");
            redisService.set(key, mapper.writeValueAsString(data));
            LOG.info("Successfully wrote to Redis");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static<T> List<T> deserializeFromRedis(String str, Class<T> dataClass) throws JsonProcessingException {

        LOG.info("Deserialize following string = " + str);

        if(str != null && !str.isBlank()) {
            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, dataClass);

            LOG.info("Deserialized data from Redis");
            return mapper.readValue(str, type);
        }
        else {
            return null;
        }
    }
}
