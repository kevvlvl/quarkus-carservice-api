package com.kevvlvl.quarkus.endpoint;

import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import com.kevvlvl.quarkus.service.MakesService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/make")
public class MakesEndpoint {

    private final MakesService service;

    public MakesEndpoint(MakesService service) {
        this.service = service;
    }

    @GET
    public List<MakeDto> allMakes() {
        return service.getMakes();
    }

    @GET
    @Path("/models")
    public List<ModelDto> allModels() {
        return service.getModels();
    }
}
