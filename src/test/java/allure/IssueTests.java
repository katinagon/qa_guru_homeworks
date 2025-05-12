package allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Issue тесты")
public class IssueTests {
    private static final String REPOSITORY = "qa-guru/knowledge-base";

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Feature("Issue в репозитории")
    @Story("Отображение Issue")
    @Owner("goncharova-ek")
    @DisplayName("Проверка Issue с помощью чистого Selenide")
    @Test
    public void findIssueTest() {
        open("https://github.com/");

        $(".search-input-container").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();

        $("[href='/" + REPOSITORY + "']").click();
        $("[data-content=Issues]").shouldHave(text("Issues"));
    }

    @Feature("Issue в репозитории")
    @Story("Отображение Issue")
    @Owner("goncharova-ek")
    @DisplayName("Проверка Issue с помощью Lambda шагов")
    @Test
    public void findIssueLambdaStepTest() {
        step("Открываем главную страницу GitHub", () ->
                open("https://github.com"));

        step("Ищем репозиторий", () -> {
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });

        step("Кликаем по репозиторию", () ->
                $("[href='/" + REPOSITORY + "']").click());

        step("Проверяем название Issue в репозитории", () ->
            $("[data-content=Issues]").shouldHave(text("Issues")));
    }

    @Feature("Issue в репозитории")
    @Story("Отображение Issue")
    @Owner("goncharova-ek")
    @DisplayName("Проверка Issue с помощью шагов с аннотацией @Step")
    @Test
    public void findIssueWithAnnotatedStepTest() {
        GitHubSteps steps = new GitHubSteps();

        steps.openGitHubPage()
                .searchForRepository(REPOSITORY)
                .clickOnRepository(REPOSITORY)
                .checkIssue();
    }
}
