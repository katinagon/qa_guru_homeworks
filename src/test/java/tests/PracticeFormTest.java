package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    @DisplayName("Успешное заполнение всех полей формы")
    public void fillAllRegistrationFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("ivan@test.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9991234590");
        $("#dateOfBirthInput").click();

        // Выбор даты на календаре
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1995");
        $$("div.react-datepicker__day").findBy(text("14")).click();

        // Выбор предметов
        $("#subjectsInput").setValue("co");
        $("#react-select-2-option-0").click();
        $("#subjectsInput").setValue("p");
        $("#react-select-2-option-0").click();

        // Выбор хобби
        $("label[for=hobbies-checkbox-2]").click();
        $("label[for=hobbies-checkbox-3]").click();

        $("#uploadPicture").uploadFromClasspath("img.png"); // Загрузка картинки

        $("#currentAddress").setValue("Test Address 1/2");

        // Выбор state и city из селектов
        $("#state").scrollIntoView(true).click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-0").click();

        $(".text-right").click(); // Отправка формы

        // Проверки
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Ivan Ivanov"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("ivan@test.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9991234590"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("14 July,1995"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Computer Science, Physics"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading, Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("img.png"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Test Address 1/2"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Haryana Karnal"));
    }
}
