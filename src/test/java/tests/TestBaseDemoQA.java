package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseDemoQA {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser.version", "136.0");
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }
}
