package tests.api;

import io.qameta.allure.Owner;
import model.lombok.UserRequestBodyModel;
import model.lombok.UserResponseBodyModel;
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
                given(createUserRequestSpec)
                        .body(userData)
                .when()
                        .post()
                .then()
                        .spec(createUserResponseSpec)
                        .extract().as(UserResponseBodyModel.class)
        );
        userId = response.getId();
    }

    @DisplayName("Успешное удаление пользователя")
    @Test
    public void successfulUpdateUserViaPutTest() {
        step("Отправляем запрос", () ->
                given(deleteUserRequestSpec(userId))
                .when()
                        .delete()
                .then()
                        .spec(deleteUserResponseSpec)
        );
    }
}
