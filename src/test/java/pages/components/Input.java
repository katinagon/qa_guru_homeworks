package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;

public class Input {

    public void checkValidInput(SelenideElement input) {
        input.shouldHave(cssValue("border-color", "rgb(40, 167, 69)"))
                .shouldHave(cssValue("background-image", "url(\"data:image/svg+xml,%3csvg " +
                        "xmlns='http://www.w3.org/2000/svg' width='8' height='8' viewBox='0 0 8 8'%3e%3cpath " +
                        "fill='%2328a745' d='M2.3 6.73L.6 4.53c-.4-1.04.46-1.4 1.1-.8l1.1 1.4 3.4-3.8c.6-.63 " +
                        "1.6-.27 1.2.7l-4 4.6c-.43.5-.8.4-1.1.1z'/%3e%3c/svg%3e\")"));
    }

    public void checkInvalidInput(SelenideElement input) {
        input.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"))
                .shouldHave(cssValue("background-image", "url(\"data:image/svg+xml,%3csvg " +
                        "xmlns='http://www.w3.org/2000/svg' width='12' height='12' fill='none' stroke='%23dc3545' " +
                        "viewBox='0 0 12 12'%3e%3ccircle cx='6' cy='6' r='4.5'/%3e%3cpath stroke-linejoin='round' " +
                        "d='M5.8 3.6h.4L6 6.5z'/%3e%3ccircle cx='6' cy='8.2' r='.6' fill='%23dc3545' " +
                        "stroke='none'/%3e%3c/svg%3e\")"));
    }
}
