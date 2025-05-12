package allure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubSteps {
    @Step("Открываем главную страницу GitHub")
    public GitHubSteps openGitHubPage() {
        open("https://github.com");
        return this;
    }

    @Step("Ищем репозиторий")
    public GitHubSteps searchForRepository(String repositoryName) {
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys(repositoryName);
        $("#query-builder-test").submit();
        return this;
    }

    @Step("Кликаем по репозиторию")
    public GitHubSteps clickOnRepository(String repositoryName) {
        $("[href='/" + repositoryName + "']").click();
        return this;
    }

    @Step("Проверяем название Issue в репозитории")
    public GitHubSteps checkIssue() {
        $("[data-content=Issues]").shouldHave(text("Issues"));
        return this;
    }
}
