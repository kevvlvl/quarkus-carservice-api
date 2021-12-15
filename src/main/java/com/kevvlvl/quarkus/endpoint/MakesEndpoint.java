package com.kevvlvl.quarkus.endpoint;

import com.kevvlvl.quarkus.model.Make;
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
    public List<Make> allMakes() {
        return service.getMakes();
    }
}
