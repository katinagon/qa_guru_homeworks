package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    private final SelenideElement userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitBtn = $("#submit"),
            outputName = $("#output #name"),
            outputEmail = $("#output #email"),
            outputCurrentAddress = $("#output #currentAddress"),
            outputPermanentAddress = $("#output #permanentAddress");

    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage setUserName(String userName) {
        userNameInput.setValue(userName);
        return this;
    }

    public TextBoxPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress) {
        permanentAddressInput.setValue(permanentAddress);
        return this;
    }

    public TextBoxPage submitForm() {
        submitBtn.click();
        return this;
    }

    public TextBoxPage checkOutputField(String outputField, String value) {

        switch (outputField) {
            case "outputName":
                outputName.shouldHave(text(value));
                break;
            case "outputEmail":
                outputEmail.shouldHave(text(value));
                break;
            case "outputCurrentAddress":
                outputCurrentAddress.shouldHave(text(value));
                break;
            case "outputPermanentAddress":
                outputPermanentAddress.shouldHave(text(value));
                break;
        }
        return this;
    }

}
