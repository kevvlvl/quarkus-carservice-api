package com.kevvlvl.quarkus.endpoint;

import com.kevvlvl.quarkus.dto.MakeDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MakesEndpointTest {

    @Test
    public void getAllMakesAndExpectResult() {

        JsonPath jsonPath = given()
                .when().get("/make")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .extract().body().jsonPath();

        List<MakeDto> makes = jsonPath.getList("", MakeDto.class);
    }
}
