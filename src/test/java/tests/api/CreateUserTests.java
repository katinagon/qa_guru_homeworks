package tests.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

@Owner("goncharova-ek")
@DisplayName("Тесты на создание пользователя")
public class CreateUserTests extends ReqresTestBase {
    String userId;

    @DisplayName("Успешное создание пользователя")
    @Test
    public void successfulCreateUserTest() {
        String userData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
        .when()
                .post(baseURI + usersEP)
        .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", not(emptyOrNullString()))
                .body("createdAt", not(emptyOrNullString()));
    }

    @DisplayName("Успешное создание пользователя без имени в запросе")
    @Test
    public void successfulCreateUserWithoutNameTest() {
        String userData = "{\"job\": \"leader\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
                .when()
                .post(baseURI + usersEP)
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("job", is("leader"))
                .body("id", not(emptyOrNullString()))
                .body("createdAt", not(emptyOrNullString()));
    }

    @DisplayName("Успешное создание пользователя без работы в запросе")
    @Test
    public void successfulCreateUserWithoutJobTest() {
        String userData = "{\"name\": \"morpheus\"}";

        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .body(userData)
                .log().uri()
                .when()
                .post(baseURI + usersEP)
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("id", not(emptyOrNullString()))
                .body("createdAt", not(emptyOrNullString()));
    }
}
