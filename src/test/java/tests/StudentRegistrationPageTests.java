package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationPage;

import static tests.TestData.*;

public class StudentRegistrationPageTests extends TestBaseDemoQA {
    StudentRegistrationPage studentRegistrationPage = new StudentRegistrationPage();
    TestData testData = new TestData();

    @Test
    @DisplayName("Успешная отправка формы регистрации со всеми заполненными полями")
    public void successSubmitRegFormWithAllFieldsTest() {
        studentRegistrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(testData.phoneNumber)
                .setDateOfBirth(calendarDay, calendarMonth, calendarYear)
                .selectSubjects(subject)
                .setHobbiesCheckBox()
                .selectPicture()
                .setAddress(testData.address)
                .setStateAndCity()
                .submitForm();
        studentRegistrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", testData.phoneNumber)
                .checkResult("Date of Birth", calendarDay + " " + calendarMonth + ","
                    + calendarYear)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", image)
                .checkResult("Address", testData.address)
                .checkResult("State and City", stateSelectPoint + " " + citySelectPoint);
    }

    @Test
    @DisplayName("Успешная отправка формы регистрации с обязательными полями")
    public void successSubmitRegFormWithRequiredFieldsTest() {
        studentRegistrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(gender)
                .setUserNumber(testData.phoneNumber)
                .submitForm();
        studentRegistrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", testData.phoneNumber);
    }

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при незаполненном имени")
    public void unsuccessSubmitRegFormWithoutFirstNameTest() {
        studentRegistrationPage.openPage()
                .setLastName(testData.lastName)
                .setGender(gender)
                .setUserNumber(testData.phoneNumber)
                .submitForm();
        studentRegistrationPage.checkEmptyInput("firstNameInput")
                .checkiFilledInput("lastNameInput", testData.lastName)
                .checkFilledRadioButton(gender)
                .checkiFilledInput("userNumberInput", testData.phoneNumber);
    }

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при незаполненной фамилии")
    public void unsuccessSubmitRegFormWithoutLastNameTest() {
        studentRegistrationPage.openPage()
                .setFirstName(testData.firstName)
                .setGender(gender)
                .setUserNumber(testData.phoneNumber)
                .submitForm();
        studentRegistrationPage.checkEmptyInput("lastNameInput")
                .checkiFilledInput("firstNameInput", testData.firstName)
                .checkFilledRadioButton(gender)
                .checkiFilledInput("userNumberInput", testData.phoneNumber);
    }

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при всех незаполненных обязательных полях")
    public void unsuccessSubmitRegFormWithoutRequiredFieldsTest() {
        studentRegistrationPage.openPage()
                .submitForm();
        studentRegistrationPage.checkEmptyInput("firstNameInput")
                .checkEmptyInput("lastNameInput")
                .checkEmptyInput("userNumberInput")
                .checkEmptyRadioButton();
    }
}
