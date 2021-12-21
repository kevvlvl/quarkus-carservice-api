package com.kevvlvl.quarkus.dto;

import java.math.BigDecimal;

public record ModelDto(int id, String model, BigDecimal msrp) { }