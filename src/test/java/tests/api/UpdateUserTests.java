package tests.api;

import io.qameta.allure.Owner;
import models.UserRequestBodyModel;
import models.UserResponseBodyModel;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateUserSpec.*;

@Tags({
        @Tag("all_api"),
        @Tag("update_user_api")
})
@Owner("goncharova-ek")
@DisplayName("Тесты на обновление пользователя")
public class UpdateUserTests extends ReqresTestBase {
    String userId;

    @BeforeEach
    public void getUserIdTests() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setName("fred");
        userData.setJob("artist");

        UserResponseBodyModel response = step("Создание пользователя перед тестом", () ->
                given(createUserRequestSpec)
                        .body(userData)
                        .when()
                        .post(usersEP)
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(UserResponseBodyModel.class)
        );
        userId = response.getId();
    }

    @DisplayName("Успешное обновление пользователя через PUT")
    @Test
    public void successfulUpdateUserViaPutTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setName("fred");
        userData.setJob("artist");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(updateUserRequestSpec)
                        .body(userData)
                        .when()
                        .put(usersEP + userId)
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем name в ответе", () ->
                assertEquals("fred", response.getName()));

        step("Проверяем job в ответе", () ->
                assertEquals("artist", response.getJob()));

        step("Проверяем updatedAt в ответе", () ->
                assertThat(response.getUpdatedAt(), not(emptyOrNullString())));
    }

    @DisplayName("Успешное обновление имени пользователя через PUT")
    @Test
    public void successfulUpdateUserNameViaPutTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setName("mike");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(updateUserRequestSpec)
                        .body(userData)
                        .when()
                        .put(usersEP + userId)
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем name в ответе", () ->
                assertEquals("mike", response.getName()));

        step("Проверяем updatedAt в ответе", () ->
                assertThat(response.getUpdatedAt(), not(emptyOrNullString())));
    }

    @DisplayName("Успешное обновление работы пользователя через PUT")
    @Test
    public void successfulUpdateUserJobViaPutTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setJob("QA");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(updateUserRequestSpec)
                        .body(userData)
                        .when()
                        .put(usersEP + userId)
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем name в ответе", () ->
                assertEquals("QA", response.getJob()));

        step("Проверяем updatedAt в ответе", () ->
                assertThat(response.getUpdatedAt(), not(emptyOrNullString())));
    }

    @DisplayName("Успешное обновление пользователя через PATCH")
    @Test
    public void successfulUpdateUserViaPatchTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setName("anna");
        userData.setJob("teacher");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(updateUserRequestSpec)
                        .body(userData)
                        .when()
                        .patch(usersEP + userId)
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем name в ответе", () ->
                assertEquals("anna", response.getName()));

        step("Проверяем job в ответе", () ->
                assertEquals("teacher", response.getJob()));

        step("Проверяем updatedAt в ответе", () ->
                assertThat(response.getUpdatedAt(), not(emptyOrNullString())));
    }

    @DisplayName("Успешное обновление имени пользователя через PATCH")
    @Test
    public void successfulUpdateUserNameViaPatchTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setName("leon");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(updateUserRequestSpec)
                        .body(userData)
                        .when()
                        .patch(usersEP + userId)
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем name в ответе", () ->
                assertEquals("leon", response.getName()));

        step("Проверяем updatedAt в ответе", () ->
                assertThat(response.getUpdatedAt(), not(emptyOrNullString())));
    }

    @DisplayName("Успешное обновление работы пользователя через PATCH")
    @Test
    public void successfulUpdateUserJobViaPatchTest() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setJob("manager");

        UserResponseBodyModel response = step("Отправляем запрос", () ->
                given(updateUserRequestSpec)
                        .body(userData)
                        .when()
                        .patch(usersEP + userId)
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UserResponseBodyModel.class)
        );

        step("Проверяем job в ответе", () ->
                assertEquals("manager", response.getJob()));

        step("Проверяем updatedAt в ответе", () ->
                assertThat(response.getUpdatedAt(), not(emptyOrNullString())));
    }
}
