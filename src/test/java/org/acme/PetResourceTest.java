package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


@QuarkusTest
public class PetResourceTest {

    @Test
    public void testGetPetEndpoint() {
        given()
                .when().get("/v1/pets/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllPetEndpoint() {
        given()
                .when().get("/v1/pets")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetPetByNameEndpoint() {
        given()
                .when().get("/v1/pets/getByName/Boola")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetPetByAgeEndpoint() {
        given()
                .when().get("/v1/pets/getByAge/4")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCreatePetEndpoint() {
        Map<String, Object> map = new HashMap<>();
        map.put("petType", "Dog");
        map.put("petName", "Lacy");
        map.put("petAge", "3");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map)
                .when().post("/v1/pets")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdatePetEndpoint() {
        Map<String, Object> map = new HashMap<>();
        map.put("petType", "Dog");
        map.put("petName", "LacyNew");
        map.put("petAge", "3");

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map)
                .when().put("/v1/pets/1")
                .then()
                .statusCode(200);
    }


    @Test
    public void testDeletePetEndpoint() {
        given()
                .when().delete("/v1/pets/1")
                .then()
                .statusCode(200);
    }


}


