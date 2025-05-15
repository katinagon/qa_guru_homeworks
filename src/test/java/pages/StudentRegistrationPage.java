package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.Input;
import pages.components.RadioButton;
import pages.components.ResultTable;
import tests.TestDataDemoQA;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.TestDataDemoQA.*;

public class StudentRegistrationPage {
    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultTable resultTable = new ResultTable();
    private final Input input = new Input();
    private final RadioButton radioButton = new RadioButton();

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            subjectSelectPoint = $("#react-select-2-option-0"),
            hobbiesCheckBox = $(byText(hobby)),
            uploadPictureBtn = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelect = $("#state"),
            stateSelectPoint = $(byText(TestDataDemoQA.stateSelectPoint)),
            citySelect = $("#city"),
            citySelectPoint = $(byText(TestDataDemoQA.citySelectPoint)),
            submitBtn = $(".text-right");

    @Step("Открываем главную страницу формы")
    public StudentRegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Устанавливаем значение в поле First Name")
    public StudentRegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Устанавливаем значение в поле Last Name")
    public StudentRegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Устанавливаем значение в поле Email")
    public StudentRegistrationPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    @Step("Устанавливаем значение в поле Gender")
    public StudentRegistrationPage setGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    @Step("Устанавливаем значение в поле Mobile")
    public StudentRegistrationPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    @Step("Устанавливаем дату в поле Date of Birth")
    public StudentRegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Выбираем предметы")
    public StudentRegistrationPage selectSubjects(String subjectName) {
        subjectsInput.setValue(subjectName);
        subjectSelectPoint.click();
        return this;
    }

    @Step("Выбираем хобби")
    public StudentRegistrationPage setHobbiesCheckBox() {
        hobbiesCheckBox.click();
        return this;
    }

    @Step("Загружаем файл")
    public StudentRegistrationPage selectPicture() {
        uploadPictureBtn.uploadFromClasspath(image);
        return this;
    }

    @Step("Устанавливаем значение в поле Address")
    public StudentRegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    @Step("Выбираем штат и город")
    public StudentRegistrationPage setStateAndCity() {
        stateSelect.scrollIntoView(true).click();
        stateSelectPoint.click();
        citySelect.click();
        citySelectPoint.click();
        return this;
    }

    @Step("Отправляем форму")
    public StudentRegistrationPage submitForm() {
        submitBtn.scrollIntoView(true).click();
        return this;
    }

    @Step("Проверка таблицы")
    public StudentRegistrationPage checkResult(String key, String value) {
        resultTable.checkTableResult(key, value);
        return this;
    }

    @Step("Проверка валидации некорректно заполненного поля")
    public StudentRegistrationPage checkInvalidFilledInput(String nameInput) {
        switch (nameInput) {
            case "firstNameInput":
                input.checkInvalidInput(firstNameInput);
                break;
            case "lastNameInput":
                input.checkInvalidInput(lastNameInput);
                break;
            case "userNumberInput":
                input.checkInvalidInput(userNumberInput);
                break;
            case "userEmailInput":
                input.checkInvalidInput(userEmailInput);
                break;
        }
        return this;
    }

    @Step("Проверка валидации корректно заполненного поля")
    public StudentRegistrationPage checkValidFilledInput(String nameInput, String value) {
        switch (nameInput) {
            case "firstNameInput":
                firstNameInput.shouldHave(exactValue(value));
                input.checkValidInput(firstNameInput);
                break;
            case "lastNameInput":
                lastNameInput.shouldHave(exactValue(value));
                input.checkValidInput(lastNameInput);
                break;
            case "userNumberInput":
                userNumberInput.shouldHave(exactValue(value));
                input.checkValidInput(userNumberInput);
                break;
            case "emailInput":
                userEmailInput.shouldHave(exactValue(value));
                input.checkValidInput(userEmailInput);
                break;
            case "calendarInput":
                calendarInput.shouldHave(exactValue(value));
                input.checkValidInput(calendarInput);
                break;
            case "addressInput":
                addressInput.shouldHave(exactValue(value));
                input.checkValidInput(addressInput);
                break;
        }
        return this;
    }

    @Step("Проверка выключенной радио-кнопки")
    public StudentRegistrationPage checkEmptyRadioButton() {
        radioButton.checkInvalidRadioButton(genderWrapper);
        return this;
    }

    @Step("Проверка включенной радио-кнопки")
    public StudentRegistrationPage checkFilledRadioButton(String value) {
        radioButton.checkValidRadioButton(genderWrapper, value);
        return this;
    }
}
