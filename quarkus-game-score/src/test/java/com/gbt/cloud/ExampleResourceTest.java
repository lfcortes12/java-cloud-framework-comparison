package com.gbt.cloud;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/game-score/key")
          .then()
             .statusCode(200)
             .body(is("Quarkus Game Score DEV"));
    }

}