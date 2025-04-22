package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetStoreApiTest {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private int createdPetId;
    
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
    
    @Test(priority = 1)
    public void testCreatePet() {
        String requestBody = "{ \"id\": 12345, \"name\": \"doggie\", \"status\": \"available\" }";
        
        Response response = given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/pet")
        .then()
            .statusCode(200)
            .body("id", equalTo(12345))
            .body("name", equalTo("doggie"))
            .body("status", equalTo("available"))
            .extract().response();
        
        createdPetId = response.jsonPath().getInt("id");
    }
    
    @Test(priority = 2)
    public void testGetPetById() {
        given()
            .pathParam("petId", createdPetId)
        .when()
            .get("/pet/{petId}")
        .then()
            .statusCode(200)
            .body("id", equalTo(createdPetId))
            .body("name", equalTo("doggie"));
    }
    
    @Test(priority = 3)
    public void testUpdatePet() {
        String requestBody = "{ \"id\": " + createdPetId + ", \"name\": \"updated-doggie\", \"status\": \"sold\" }";
        
        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .put("/pet")
        .then()
            .statusCode(200)
            .body("id", equalTo(createdPetId))
            .body("name", equalTo("updated-doggie"))
            .body("status", equalTo("sold"));
    }
    
    @Test(priority = 4)
    public void testDeletePet() {
        given()
            .pathParam("petId", createdPetId)
        .when()
            .delete("/pet/{petId}")
        .then()
            .statusCode(200);
    }
    
    @Test(priority = 5)
    public void testGetDeletedPet() {
        given()
            .pathParam("petId", createdPetId)
        .when()
            .get("/pet/{petId}")
        .then()
            .statusCode(404);
    }
}