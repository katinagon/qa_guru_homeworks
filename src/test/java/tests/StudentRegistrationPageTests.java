package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.StudentRegistrationPage;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static tests.TestDataDemoQA.*;

@Tag("registration_form")
@DisplayName("Тесты формы регистрации студента")
public class StudentRegistrationPageTests extends TestBaseDemoQA {
    StudentRegistrationPage studentRegistrationPage = new StudentRegistrationPage();

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "name", "Test: " + UUID.randomUUID()
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.holdBrowserOpen = false;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @AfterEach
    public void afterEach() {
        Attach.screenshotAs("Скриншот результата");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }

    @Tag("submit_form")
    @Feature("Форма регистрации студента")
    @Story("Успешная отправка формы")
    @Owner("goncharova-ek")
    @DisplayName("Успешная отправка формы регистрации со всеми заполненными полями")
    @Test
    public void successSubmitRegFormWithAllFieldsTest() {
        studentRegistrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(calendarDay, calendarMonth, calendarYear)
                .selectSubjects(subject)
                .setHobbiesCheckBox()
                .selectPicture()
                .setAddress(address)
                .setStateAndCity()
                .submitForm();
        studentRegistrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", calendarDay + " " + calendarMonth + ","
                    + calendarYear)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", image)
                .checkResult("Address", address)
                .checkResult("State and City", stateSelectPoint + " " + citySelectPoint);
    }

    @Tag("submit_form")
    @Feature("Форма регистрации студента")
    @Story("Успешная отправка формы")
    @Owner("goncharova-ek")
    @DisplayName("Успешная отправка формы регистрации с обязательными полями")
    @Test
    public void successSubmitRegFormWithRequiredFieldsTest() {
        studentRegistrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submitForm();
        studentRegistrationPage.checkResult("Student Name",firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);
    }

    @Tag("submit_form")
    @Feature("Форма регистрации студента")
    @Story("Неуспешная отправка формы")
    @Owner("goncharova-ek")
    @DisplayName("Неуспешная отправка формы регистрации при незаполненном имени")
    @Test
    public void unsuccessSubmitRegFormWithoutFirstNameTest() {
        studentRegistrationPage.openPage()
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submitForm();
        studentRegistrationPage.checkInvalidFilledInput("firstNameInput")
                .checkValidFilledInput("lastNameInput", lastName)
                .checkFilledRadioButton(gender)
                .checkValidFilledInput("userNumberInput", phoneNumber);
    }

    @Tag("submit_form")
    @Feature("Форма регистрации студента")
    @Story("Неуспешная отправка формы")
    @Owner("goncharova-ek")
    @DisplayName("Неуспешная отправка формы регистрации при незаполненной фамилии")
    @Test
    public void unsuccessSubmitRegFormWithoutLastNameTest() {
        studentRegistrationPage.openPage()
                .setFirstName(firstName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submitForm();
        studentRegistrationPage.checkInvalidFilledInput("lastNameInput")
                .checkValidFilledInput("firstNameInput", firstName)
                .checkFilledRadioButton(gender)
                .checkValidFilledInput("userNumberInput", phoneNumber);
    }

    @Tag("submit_form")
    @Feature("Форма регистрации студента")
    @Story("Неуспешная отправка формы")
    @Owner("goncharova-ek")
    @DisplayName("Неуспешная отправка формы регистрации при всех незаполненных обязательных полях")
    @Test
    public void unsuccessSubmitRegFormWithoutRequiredFieldsTest() {
        studentRegistrationPage.openPage()
                .submitForm();
        studentRegistrationPage.checkInvalidFilledInput("firstNameInput")
                .checkInvalidFilledInput("lastNameInput")
                .checkInvalidFilledInput("userNumberInput")
                .checkEmptyRadioButton();
    }

    @Tag("validation_form")
    @Feature("Форма регистрации студента")
    @Story("Валидация полей")
    @Owner("goncharova-ek")
    @ParameterizedTest(name="Ошибка валидации поля Mobile при вводе номера \"{0}\" меньше 10 цифр")
    @ValueSource(strings = {"9", "999123", "999123457"})
    public void mobileFieldErrorWhenNumberLessThan10DigitsTest(String phoneNumber) {
        studentRegistrationPage.openPage()
                .setUserNumber(phoneNumber)
                .submitForm();
        studentRegistrationPage.checkInvalidFilledInput("userNumberInput");
    }

    @Tag("validation_form")
    @Feature("Форма регистрации студента")
    @Story("Валидация полей")
    @Owner("goncharova-ek")
    @ParameterizedTest(name="Успешная валидации поля First Name с текстом \"{0}\"")
    @CsvSource(value = {"Игорь", "Mike", "美"})
    public void successValidationFirstNameFieldTest(String firstName) {
        studentRegistrationPage.openPage()
                .setFirstName(firstName)
                .submitForm();
        studentRegistrationPage.checkValidFilledInput("firstNameInput", firstName);
    }

    @Tag("validation_form")
    @Feature("Форма регистрации студента")
    @Story("Валидация полей")
    @Owner("goncharova-ek")
    @ParameterizedTest(name="Успешная валидации поля Last Name с текстом \"{0}\"")
    @CsvSource(value = {"Иванов", "Smith", "赵"})
    public void successValidationLastNameFieldTest(String lastName) {
        studentRegistrationPage.openPage()
                .setLastName(lastName)
                .submitForm();
        studentRegistrationPage.checkValidFilledInput("lastNameInput", lastName);
    }

    static Stream<String> emailFieldErrorWithIncorrectMailTest() {
        return Stream.of("q2qmail.ru", "q2q@mailru", "q2q?@mail.ru", "q2q @mail.ru", " ");
    }

    @Tag("validation_form")
    @ParameterizedTest(name="Ошибка валидации поля Email при некорректном значении \"{0}\"")
    @MethodSource
    public void emailFieldErrorWithIncorrectMailTest(String email) {
        studentRegistrationPage.openPage()
                .setUserEmail(email)
                .submitForm();
        studentRegistrationPage.checkInvalidFilledInput("userEmailInput");
    }
}
