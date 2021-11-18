package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class PetTypeResourceTest {
    @Test
    public void testGetPetTypeEndpoint() {
        given()
                .when().get("/v1/petTypes/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllPetTypesEndpoint() {
        given()
                .when().get("/v1/petTypes")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCreatePetTypeEndpoint() {
        Map<String, Object> map = new HashMap<>();
        map.put("petTypeName", "Bird");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map)
                .when().post("/v1/petTypes")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdatePetTypeEndpoint() {
        Map<String, Object> map = new HashMap<>();
        map.put("petTypeName", "DogNew");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map)
                .when().put("/v1/petTypes/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeletePetTypeEndpoint() {
        given()
                .when().delete("/v1/petTypes/1")
                .then()
                .statusCode(200);
    }
}
