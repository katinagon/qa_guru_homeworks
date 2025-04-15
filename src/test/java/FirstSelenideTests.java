import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Condition.text;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstSelenideTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @DisplayName("Провека наличия кода на странице Soft assertions")
    public void findCodeForJUnitInSoftAssertionsTest() {
        // Открываем страницу Selenide в Github
        open("https://github.com/selenide/selenide");
        // Переходим в раздел Wiki проекта
        $$("ul li a").findBy(text("Wiki")).click();
        // Открываем 3 доп. пункта списка
        $(".wiki-more-pages-link button").click();
        // Проверям, что есть пункт SoftAssertions
        $$("[data-filterable-for=wiki-pages-filter] li a")
                .findBy(text("SoftAssertions"))
                .shouldHave(href("/selenide/selenide/wiki/SoftAssertions"))
                .click();
        // Проверка что есть пример для кода JUnit5
        $$("h4.heading-element").findBy(text("JUnit5")) // находим заголовок по тексту JUnit5
                .closest(".markdown-heading") // находим ближайшего предка уровнем выше
                .sibling(0) // следующий элемент в DOM за .markdown-heading
                .$("pre")
                .shouldHave(text("""
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");
                        
                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }
                        """));
    }
}
