package com.kevvlvl.quarkus.service;

import com.kevvlvl.quarkus.model.Make;
import com.kevvlvl.quarkus.model.Model;

import java.util.List;

public interface MakesService {

    List<Make> getMakes();
    List<Model> getModels();
}
