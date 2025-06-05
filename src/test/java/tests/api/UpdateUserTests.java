package tests.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

@Owner("goncharova-ek")
@DisplayName("Тесты на обновление пользователя")
public class UpdateUserTests extends ReqresTestBase {
    String userId;

    @BeforeEach
    public void getUserIdTests() {
        userId =
                given()
                        .header("x-api-key", apiKey)
                        .contentType(JSON)
                        .body("{\"name\": \"morpheus\", \"job\": \"leader\"}")
                .when()
                        .post(baseURI + usersEP)
                .then()
                        .extract()
                        .path("id");
    }

    @DisplayName("Успешное обновление пользователя через PUT")
    @Test
    public void successfulUpdateUserViaPutTest() {
        String userData = "{\"name\": \"fred\", \"job\": \"artist\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
        .when()
                .put(baseURI + usersEP + userId)
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("fred"))
                .body("job", is("artist"))
                .body("updatedAt", not(emptyOrNullString()));
    }

    @DisplayName("Успешное обновление имени пользователя через PUT")
    @Test
    public void successfulUpdateUserNameViaPutTest() {
        String userData = "{\"name\": \"mike\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
        .when()
                .put(baseURI + usersEP + userId)
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("mike"))
                .body("updatedAt", not(emptyOrNullString()));
    }

    @DisplayName("Успешное обновление работы пользователя через PUT")
    @Test
    public void successfulUpdateUserJobViaPutTest() {
        String userData = "{\"job\": \"QA\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
        .when()
                .put(baseURI + usersEP + userId)
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("QA"))
                .body("updatedAt", not(emptyOrNullString()));
    }

    @DisplayName("Успешное обновление пользователя через PATCH")
    @Test
    public void successfulUpdateUserViaPatchTest() {
        String userData = "{\"name\": \"anna\", \"job\": \"teacher\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
        .when()
                .patch(baseURI + usersEP + userId)
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("anna"))
                .body("job", is("teacher"))
                .body("updatedAt", not(emptyOrNullString()));
    }

    @DisplayName("Успешное обновление имени пользователя через PATCH")
    @Test
    public void successfulUpdateUserNameViaPatchTest() {
        String userData = "{\"name\": \"leon\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
        .when()
                .patch(baseURI + usersEP + userId)
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("leon"))
                .body("updatedAt", not(emptyOrNullString()));
    }

    @DisplayName("Успешное обновление работы пользователя через PATCH")
    @Test
    public void successfulUpdateUserJobViaPatchTest() {
        String userData = "{\"job\": \"manager\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
        .when()
                .patch(baseURI + usersEP + userId)
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("manager"))
                .body("updatedAt", not(emptyOrNullString()));
    }
}
