package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byText;

public class RadioButton {

    public void checkValidRadioButton(SelenideElement radioButton, String text) {
        radioButton.$(byText(text)).shouldHave(cssValue("border-color", "rgb(40, 167, 69)"));
    }

    public void checkInvalidRadioButton(SelenideElement radioButton) {
        radioButton.$(byText("Male")).shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
        radioButton.$(byText("Female")).shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
        radioButton.$(byText("Other")).shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
    }
}
