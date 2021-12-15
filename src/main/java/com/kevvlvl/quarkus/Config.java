package com.kevvlvl.quarkus;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "app")
public interface Config {

    @WithName("port")
    int port();

}