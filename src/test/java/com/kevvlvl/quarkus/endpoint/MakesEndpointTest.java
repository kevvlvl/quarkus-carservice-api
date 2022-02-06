package com.kevvlvl.quarkus.endpoint;

import com.kevvlvl.quarkus.ApiTestProfile;
import com.kevvlvl.quarkus.MakeStub;
import com.kevvlvl.quarkus.dto.MakeDto;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestProfile(ApiTestProfile.class)
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

        // add AssertJ and assertions
        assertThat(makes).containsAll(MakeStub.getMakeDtos());
    }
}
