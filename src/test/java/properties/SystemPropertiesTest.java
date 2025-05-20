package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTest {
    @Test
    @Tag("chrome")
    void testInChrome() {
        String browser = System.getProperty("browser", "chrome");
        System.out.println(browser);
    }

    @Test
    @Tag("firefox")
    void testInFirefox() {
        String browser = System.getProperty("browser", "firefox");
        System.out.println(browser);
    }

    @Test
    @Tag("opera")
    void testInOpera() {
        String browser = System.getProperty("browser", "opera");
        System.out.println(browser);
    }
}
