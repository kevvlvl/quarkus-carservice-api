package com.kevvlvl.quarkus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Future;
import io.vertx.redis.client.Response;

import java.util.List;

public interface MakesService {

    List<MakeDto> getMakes() throws JsonProcessingException;
    List<ModelDto> getModels() throws JsonProcessingException;
}
