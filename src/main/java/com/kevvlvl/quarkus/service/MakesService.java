package com.kevvlvl.quarkus.service;

import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.dto.ModelDto;

import java.util.List;

public interface MakesService {

    List<MakeDto> getMakes();
    List<ModelDto> getModels();
}
