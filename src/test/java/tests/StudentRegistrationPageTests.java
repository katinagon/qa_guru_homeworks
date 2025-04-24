package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationPage;

public class StudentRegistrationPageTests extends TestBaseDemoQA {
    StudentRegistrationPage studentRegistrationPage = new StudentRegistrationPage();

    @Test
    @DisplayName("Успешная отправка формы регистрации со всеми заполненными полями")
    public void successSubmitRegFormWithAllFieldsTest() {
        studentRegistrationPage.openPage()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setUserEmail("ivan@test.com")
                .setGender("Male")
                .setUserNumber("9991234590")
                .setDateOfBirth("14", "July", "1995")
                .selectSubjects("Physics")
                .setHobbiesCheckBox()
                .selectPicture()
                .setAddress("Test Address 1/2")
                .setStateAndCity()
                .submitForm();
        studentRegistrationPage.checkResult("Student Name", "Ivan Ivanov")
                .checkResult("Student Email", "ivan@test.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9991234590")
                .checkResult("Date of Birth", "14 July,1995")
                .checkResult("Subjects", "Physics")
                .checkResult("Hobbies", "Reading, Music")
                .checkResult("Picture", "img.png")
                .checkResult("Address", "Test Address 1/2")
                .checkResult("State and City", "Haryana Karnal");
    }

    @Test
    @DisplayName("Успешная отправка формы регистрации с обязательными полями")
    public void successSubmitRegFormWithRequiredFieldsTest() {
        studentRegistrationPage.openPage()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setGender("Male")
                .setUserNumber("9991234590")
                .submitForm();
        studentRegistrationPage.checkResult("Student Name", "Ivan Ivanov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9991234590");
    }

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при незаполненном имени")
    public void unsuccessSubmitRegFormWithoutFirstNameTest() {
        studentRegistrationPage.openPage()
                .setLastName("Ivanov")
                .setGender("Male")
                .setUserNumber("9991234590")
                .submitForm();
        studentRegistrationPage.checkEmptyInput("firstNameInput")
                .checkiFilledInput("lastNameInput", "Ivanov")
                .checkFilledRadioButton("Male")
                .checkiFilledInput("userNumberInput", "9991234590");
    }

    @Test
    @DisplayName("Неуспешная отправка формы регистрации при незаполненной фамилии")
    public void unsuccessSubmitRegFormWithoutLastNameTest() {
        studentRegistrationPage.openPage()
                .setFirstName("Ivan")
                .setGender("Male")
                .setUserNumber("9991234590")
                .submitForm();
        studentRegistrationPage.checkEmptyInput("lastNameInput")
                .checkiFilledInput("firstNameInput", "Ivan")
                .checkFilledRadioButton("Male")
                .checkiFilledInput("userNumberInput", "9991234590");
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
