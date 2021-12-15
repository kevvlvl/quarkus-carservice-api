package com.kevvlvl.quarkus.service;

import com.kevvlvl.quarkus.model.Make;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MakesServiceImpl implements MakesService {

    @Override
    public List<Make> getMakes() {
        return null;
    }
}
