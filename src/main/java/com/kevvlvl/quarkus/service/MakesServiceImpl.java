package com.kevvlvl.quarkus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import com.kevvlvl.quarkus.model.Make;
import com.kevvlvl.quarkus.model.Model;
import com.kevvlvl.quarkus.repository.MakeRepository;
import com.kevvlvl.quarkus.repository.ModelRepository;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@ApplicationScoped
public class MakesServiceImpl implements MakesService {

    private static final Logger LOG = Logger.getLogger(MakesServiceImpl.class);

    private static final String REDIS_MODELS_KEY = "car-models";
    private static final String REDIS_MAKES_KEY = "car-makes";

    private ReactiveKeyCommands<String> keys;
    private ValueCommands<String, String> valueCmd;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;

    @Inject
    public MakesServiceImpl(RedisDataSource redis,
                            ReactiveRedisDataSource reactive,
                            MakeRepository makeRepository,
                            ModelRepository modelRepository) {

        keys = reactive.key();
        valueCmd = redis.value(String.class);
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<MakeDto> getMakes() throws JsonProcessingException {

        String val = valueCmd.get(REDIS_MAKES_KEY);
        List<MakeDto> list = deserializeFromRedis(val, MakeDto.class);

        if (list == null || list.isEmpty()) {

            LOG.info("Makes not found in Redis. Query DB");

            List<Make> makes = makeRepository.listAll();

            list = makes.stream()
                    .map(m -> new MakeDto(m.getId(), m.getName(), m.getCountryOfOrigin()))
                    .collect(Collectors.toList());

            writeRedis(REDIS_MAKES_KEY, list);
        }

        return list;
    }

    @Override
    public List<ModelDto> getModels() throws JsonProcessingException {

        String val = valueCmd.get(REDIS_MODELS_KEY);
        List<ModelDto> list = deserializeFromRedis(val, ModelDto.class);

        if (list == null || list.isEmpty()) {

            LOG.info("Models not found in Redis. Query DB");

            List<Model> models = modelRepository.listAll();

            list = models.stream()
                        .map(m -> new ModelDto(m.getId(), m.getModel(), m.getMsrp()))
                        .collect(Collectors.toList());

            writeRedis(REDIS_MODELS_KEY, list);
        }

        return list;
    }

    private void writeRedis(String key, List<?> data) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            LOG.info("About to write to Redis");
            this.valueCmd.setex(key, TimeUnit.MINUTES.toSeconds(5), mapper.writeValueAsString(data));
            LOG.info("Successfully wrote to Redis");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static <T> List<T> deserializeFromRedis(String str, Class<T> dataClass) throws JsonProcessingException {

        LOG.info("Deserialize following string = " + str);

        if (str != null && !str.isBlank()) {
            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, dataClass);

            LOG.info("Deserialized data from Redis");
            return mapper.readValue(str, type);
        } else {
            return null;
        }
    }
}
