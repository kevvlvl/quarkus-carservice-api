package com.kevvlvl.quarkus;

import com.kevvlvl.quarkus.dto.MakeDto;
import com.kevvlvl.quarkus.model.Make;
import com.kevvlvl.quarkus.model.Model;

import java.math.BigDecimal;
import java.util.List;

public class MakeStub {

    public static List<Make> getMakes() {

        Make make1 = new Make();
        make1.setId(1);
        make1.setName("Ford");
        make1.setCountryOfOrigin("USA");

        Make make2 = new Make();
        make2.setId(2);
        make2.setName("Honda");
        make2.setCountryOfOrigin("Japan");

        return List.of(make1, make2);
    }

    public static List<MakeDto> getMakeDtos() {

        return List.of(
                new MakeDto(1, "Ford", "USA"),
                new MakeDto(2, "Honda", "Japan"));
    }

    public static List<Model> getModels() {

        Model model1 = new Model();
        model1.setId(888);
        model1.setMake(111);
        model1.setModel("Mustang");
        model1.setMsrp(new BigDecimal("40000"));

        Model model2 = new Model();
        model2.setId(999);
        model2.setMake(222);
        model2.setModel("Civic");
        model2.setMsrp(new BigDecimal("20000"));

        return List.of(model1, model2);
    }
}
