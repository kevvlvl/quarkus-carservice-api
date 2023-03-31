package com.kevvlvl.quarkus.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import com.kevvlvl.quarkus.service.MakesService;
import io.quarkus.vertx.web.Route;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EndpointRoutes {

    private final MakesService service;

    public EndpointRoutes(MakesService service) {
        this.service = service;
    }

    @Route(methods = Route.HttpMethod.GET, path = "/make", type = Route.HandlerType.BLOCKING, produces = "application/json")
    public Multi<MakeDto> allMakes() {

        try {
            return Multi.createFrom().items(service.getMakes().stream());
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Route(methods = Route.HttpMethod.GET, path = "/models", type = Route.HandlerType.BLOCKING, produces = "application/json")
    public Multi<ModelDto> allModels() {

        try {
            return Multi
                    .createFrom()
                    .items(service.getModels().stream());
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}