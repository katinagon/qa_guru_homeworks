package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class SecondSelenideTests {
    @Test
    @DisplayName("Загрузка страницы Enterprize")
    void loadTheEnterprizePageTest() {
        open("https://github.com/");
        $(byTagAndText("button","Solutions")).hover();
        $(byTagAndText("a","Enterprises")).click();
        $("h1[id=hero-section-brand-heading]").shouldHave(text("The AI-powered\n" +
                "developer platform"));
    }

    @Test
    @DisplayName("Drag And Drop")
    void programDragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a header").shouldHave(text("A"));
        $("#column-b header").shouldHave(text("B"));
        actions().clickAndHold($("#column-a")).moveToElement($("#column-b")).release().perform();
        $("#column-a header").shouldHave(text("B"));
        $("#column-b header").shouldHave(text("A"));
    }
    /* При  $("#column-a").dragAndDrop($("#column-b")); будет ошибка комплиляции.
       С помощью $("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b")); тест пройдет.*/
}
