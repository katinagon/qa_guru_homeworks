package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationPage;

import static tests.TestData.*;

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
        studentRegistrationPage.checkEmptyInput("firstNameInput")
                .checkiFilledInput("lastNameInput", lastName)
                .checkFilledRadioButton(gender)
                .checkiFilledInput("userNumberInput", phoneNumber);
    }

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при незаполненной фамилии")
    public void unsuccessSubmitRegFormWithoutLastNameTest() {
        studentRegistrationPage.openPage()
                .setFirstName(firstName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submitForm();
        studentRegistrationPage.checkEmptyInput("lastNameInput")
                .checkiFilledInput("firstNameInput", firstName)
                .checkFilledRadioButton(gender)
                .checkiFilledInput("userNumberInput", phoneNumber);
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
