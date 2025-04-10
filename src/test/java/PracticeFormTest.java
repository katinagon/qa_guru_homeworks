import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }
    @Test
    @DisplayName("Успешное заполнение всех полей формы")
    public void fillAllRegistrationForm() {
        File img = new File("src/test/resources/img.png");

        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("ivan@test.com");
        $("label[for=gender-radio-1]").click();
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

        $("#uploadPicture").uploadFile(img); // Загрузка картинки

        $("#currentAddress").setValue("Test Address 1/2");

        // Выбор state и city из селектов
        $("#state").scrollIntoView(true).click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-0").click();

        $(".text-right").click(); // Отправка формы

        // Проверки
        $("tbody").$("tr",0).$("td",1).shouldHave(text("Ivan Ivanov"));
        $("tbody").$("tr",1).$("td",1).shouldHave(text("ivan@test.com"));
        $("tbody").$("tr",2).$("td",1).shouldHave(text("Male"));
        $("tbody").$("tr",3).$("td",1).shouldHave(text("9991234590"));
        $("tbody").$("tr",4).$("td",1).shouldHave(text("14 July,1995"));
        $("tbody").$("tr",5).$("td",1).shouldHave(text("Computer Science, Physics"));
        $("tbody").$("tr",6).$("td",1).shouldHave(text("Reading, Music"));
        $("tbody").$("tr",7).$("td",1).shouldHave(text("img.png"));
        $("tbody").$("tr",8).$("td",1).shouldHave(text("Test Address 1/2"));
        $("tbody").$("tr",9).$("td",1).shouldHave(text("Haryana Karnal"));
    }
}
