package com.kevvlvl.quarkus.dto;

public class MakeDto {

    private int id;
    private String name;
    private String countryOfOrigin;

    public MakeDto(int id, String name, String countryOfOrigin) {
        this.id = id;
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
    }

    public MakeDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
}
