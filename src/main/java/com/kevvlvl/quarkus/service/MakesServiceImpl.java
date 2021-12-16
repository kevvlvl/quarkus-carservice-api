package com.kevvlvl.quarkus.service;

import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;
import com.kevvlvl.quarkus.model.Make;
import com.kevvlvl.quarkus.model.Model;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class MakesServiceImpl implements MakesService {

    @Override
    public List<MakeDto> getMakes() {
        List<Make> makes = Make.listAll();
        return makes.stream()
                .map(m -> new MakeDto(m.getId(), m.getName(), m.getCountryOfOrigin()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ModelDto> getModels() {
        List<Model> models = Model.listAll();
        return models.stream()
                .map(m -> new ModelDto(m.getId(), m.getModel(), m.getMsrp()))
                .collect(Collectors.toList());
    }
}
