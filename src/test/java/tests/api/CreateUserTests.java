package tests.api;

import io.qameta.allure.Owner;
import models.UserRequestBodyModel;
import models.UserResponseBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateUserSpec.*;

@Tags({
        @Tag("all_api"),
        @Tag("create_user_api")
})
@Owner("goncharova-ek")
@DisplayName("Тесты на создание пользователя")
public class CreateUserTests extends ReqresTestBase {

    @DisplayName("Успешное создание пользователя")
    @Test
    public void successfulCreateUserTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(baseRequestSpec)
                        .body(userData)
                        .when()
                        .post(usersEP)
                        .then()
                        .spec(baseResponseSpec(201))
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем name в ответе", () ->
                assertEquals("morpheus", response.getName()));

        step("Проверяем job в ответе", () ->
                assertEquals("leader", response.getJob()));

        step("Проверяем id в ответе", () ->
                assertThat(response.getId(), not(emptyOrNullString())));

        step("Проверяем createdAt в ответе", () ->
                assertThat(response.getCreatedAt(), not(emptyOrNullString())));
    }

    @DisplayName("Успешное создание пользователя без имени в запросе")
    @Test
    public void successfulCreateUserWithoutNameTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setJob("leader");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(baseRequestSpec)
                        .body(userData)
                        .when()
                        .post(usersEP)
                        .then()
                        .spec(baseResponseSpec(201))
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем job в ответе", () ->
                assertEquals("leader", response.getJob()));

        step("Проверяем id в ответе", () ->
                assertThat(response.getId(), not(emptyOrNullString())));

        step("Проверяем createdAt в ответе", () ->
                assertThat(response.getCreatedAt(), not(emptyOrNullString())));
    }

    @DisplayName("Успешное создание пользователя без работы в запросе")
    @Test
    public void successfulCreateUserWithoutJobTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setName("morpheus");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(baseRequestSpec)
                        .body(userData)
                        .when()
                        .post(usersEP)
                        .then()
                        .spec(baseResponseSpec(201))
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем name в ответе", () ->
                assertEquals("morpheus", response.getName()));

        step("Проверяем id в ответе", () ->
                assertThat(response.getId(), not(emptyOrNullString())));

        step("Проверяем createdAt в ответе", () ->
                assertThat(response.getCreatedAt(), not(emptyOrNullString())));
    }
}
