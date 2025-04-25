package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.Input;
import pages.components.RadioButton;
import pages.components.ResultTable;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            subjectSelectPoint = $("#react-select-2-option-0"),
            hobbiesReadingCheckBox = $("label[for=hobbies-checkbox-2]"),
            hobbiesMusicCheckBox = $("label[for=hobbies-checkbox-3]"),
            uploadPictureBtn = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelect = $("#state"),
            stateSelectPoint = $("#react-select-3-option-2"),
            citySelect = $("#city"),
            citySelectPoint = $("#react-select-4-option-0"),
            submitBtn = $(".text-right");

    private final String image = "img.png";
    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultTable resultTable = new ResultTable();
    private final Input input = new Input();
    private final RadioButton radioButton = new RadioButton();

    public StudentRegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public StudentRegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public StudentRegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public StudentRegistrationPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public StudentRegistrationPage setGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public StudentRegistrationPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    public StudentRegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public StudentRegistrationPage selectSubjects(String subjectName) {
        subjectsInput.setValue(subjectName);
        subjectSelectPoint.click();
        return this;
    }

    public StudentRegistrationPage setHobbiesCheckBox() {
        hobbiesReadingCheckBox.click();
        hobbiesMusicCheckBox.click();
        return this;
    }

    public StudentRegistrationPage selectPicture() {
        uploadPictureBtn.uploadFromClasspath(image);
        return this;
    }

    public StudentRegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public StudentRegistrationPage setStateAndCity() {
        stateSelect.scrollIntoView(true).click();
        stateSelectPoint.click();
        citySelect.click();
        citySelectPoint.click();
        return this;
    }

    public StudentRegistrationPage submitForm() {
        submitBtn.scrollIntoView(true).click();
        return this;
    }

    public StudentRegistrationPage checkResult(String key, String value) {
        resultTable.checkTableResult(key, value);
        return this;
    }

    public StudentRegistrationPage checkEmptyInput(String nameInput) {
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
        }
        return this;
    }

    public StudentRegistrationPage checkiFilledInput(String nameInput, String value) {
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

    public StudentRegistrationPage checkEmptyRadioButton() {
        radioButton.checkInvalidRadioButton(genderWrapper);
        return this;
    }

    public StudentRegistrationPage checkFilledRadioButton(String value) {
        radioButton.checkValidRadioButton(genderWrapper, value);
        return this;
    }
}
