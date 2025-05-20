package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseDemoQA {
    private static final String SELENOID_URL = System.getProperty("selenoid.url");
    private static final String SELENOID_LOGIN = System.getProperty("selenoid.login");;
    private static final String SELENOID_PASSWORD = System.getProperty("selenoid.password");

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser.version", "136.0");
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");
        Configuration.remote = "https://" + SELENOID_LOGIN + ":" + SELENOID_PASSWORD + "@" + SELENOID_URL + "/wd/hub";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }
}
