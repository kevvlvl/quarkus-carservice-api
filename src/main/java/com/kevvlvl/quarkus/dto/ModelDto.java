package com.kevvlvl.quarkus.dto;

import java.math.BigDecimal;

public class ModelDto {

    private int id;
    private String model;
    private BigDecimal msrp;

    public ModelDto(int id, String model, BigDecimal msrp) {
        this.id = id;
        this.model = model;
        this.msrp = msrp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }
}
