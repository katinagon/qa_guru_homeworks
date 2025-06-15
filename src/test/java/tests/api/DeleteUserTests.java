package tests.api;

import io.qameta.allure.Owner;
import models.UserRequestBodyModel;
import models.UserResponseBodyModel;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static specs.CreateUserSpec.*;


@Tags({
        @Tag("all_api"),
        @Tag("delete_user_api")
})
@Owner("goncharova-ek")
@DisplayName("Тесты на удаление пользователя")
public class DeleteUserTests extends ReqresTestBase {
    String userId;

    @BeforeEach
    public void getUserIdTests() {
        UserRequestBodyModel userData = new UserRequestBodyModel();
        userData.setName("fred");
        userData.setJob("artist");

        UserResponseBodyModel response = step("Создание пользователя перед тестом", () ->
                given(baseRequestSpec)
                        .body(userData)
                        .when()
                        .post(usersEP)
                        .then()
                        .spec(baseResponseSpec(201))
                        .extract().as(UserResponseBodyModel.class)
        );
        userId = response.getId();
    }

    @DisplayName("Успешное удаление пользователя")
    @Test
    public void successfulUpdateUserViaPutTest() {
        step("Отправляем запрос", () ->
                given(baseRequestSpec)
                        .when()
                        .delete(usersEP + userId)
                        .then()
                        .spec(baseResponseSpec(204))
        );
    }
}
