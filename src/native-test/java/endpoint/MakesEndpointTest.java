package endpoint;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MakesEndpointTest {

    @Test
    public void getAllMakesAndExpectResult() {

        given()
            .when().get("/make")
            .then()
            .statusCode(HttpStatus.SC_OK);
    }
}
