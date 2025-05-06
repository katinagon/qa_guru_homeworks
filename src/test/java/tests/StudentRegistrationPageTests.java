package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.StudentRegistrationPage;

import java.util.stream.Stream;

import static tests.TestDataDemoQA.*;

public class StudentRegistrationPageTests extends TestBaseDemoQA {
    StudentRegistrationPage studentRegistrationPage = new StudentRegistrationPage();

    @Test
    @DisplayName("Успешная отправка формы регистрации со всеми заполненными полями")
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

    @Test
    @DisplayName("Успешная отправка формы регистрации с обязательными полями")
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

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при незаполненном имени")
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

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при незаполненной фамилии")
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

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при всех незаполненных обязательных полях")
    public void unsuccessSubmitRegFormWithoutRequiredFieldsTest() {
        studentRegistrationPage.openPage()
                .submitForm();
        studentRegistrationPage.checkInvalidFilledInput("firstNameInput")
                .checkInvalidFilledInput("lastNameInput")
                .checkInvalidFilledInput("userNumberInput")
                .checkEmptyRadioButton();
    }

    @ParameterizedTest(name="Ошибка валидации поля Mobile при вводе номера \"{0}\" меньше 10 цифр")
    @ValueSource(strings = {"9", "999123", "999123457"})
    public void mobileFieldErrorWhenNumberLessThan10DigitsTest(String phoneNumber) {
        studentRegistrationPage.openPage()
                .setUserNumber(phoneNumber)
                .submitForm();
        studentRegistrationPage.checkInvalidFilledInput("userNumberInput");
    }

    @ParameterizedTest(name="Успешная валидации поля First Name с текстом \"{0}\"")
    @CsvSource(value = {"Игорь", "Mike", "美"})
    public void successValidationFirstNameFieldTest(String firstName) {
        studentRegistrationPage.openPage()
                .setFirstName(firstName)
                .submitForm();
        studentRegistrationPage.checkValidFilledInput("firstNameInput", firstName);
    }

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

    @ParameterizedTest(name="Ошибка валидации поля Email при некорректном значении \"{0}\"")
    @MethodSource
    public void emailFieldErrorWithIncorrectMailTest(String email) {
        studentRegistrationPage.openPage()
                .setUserEmail(email)
                .submitForm();
        studentRegistrationPage.checkInvalidFilledInput("userEmailInput");
    }
}
