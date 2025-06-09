package tests.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Owner("goncharova-ek")
@DisplayName("Тесты на удаление пользователя")
public class DeleteUserTests extends ReqresTestBase {
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

    @DisplayName("Успешное удаление пользователя")
    @Test
    public void successfulUpdateUserViaPutTest() {

        given()
                .header("x-api-key", apiKey)
                .log().uri()
        .when()
                .delete(baseURI + usersEP + userId)
        .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
