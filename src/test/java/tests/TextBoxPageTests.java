package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxPageTests extends TestBaseDemoQA {
    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    @DisplayName("Успешное заполнение текстовой формы со всеми заполненными полями")
    public void successSubmitTextBoxWithAllFieldsTest() {
        textBoxPage.openPage()
                .setUserName("Alex")
                .setUserEmail("alex@egorov.com")
                .setCurrentAddress("Some street 1")
                .setPermanentAddress("Another street 1")
                .submitForm();
        textBoxPage.checkOutputField("outputName", "Alex")
                .checkOutputField("outputEmail", "alex@egorov.com")
                .checkOutputField("outputCurrentAddress", "Some street 1")
                .checkOutputField("outputPermanentAddress", "Another street 1");
    }
}
