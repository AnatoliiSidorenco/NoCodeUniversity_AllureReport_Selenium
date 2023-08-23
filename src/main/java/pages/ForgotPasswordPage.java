package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wait.Wait;

public class ForgotPasswordPage extends BasePage {

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[type='button']")
    private WebElement recoverPasswordButtonElement;
    @Step("Verification that Button 'Recover password' is visible")
    public boolean recoverPasswordButton(){
        Wait wait = new Wait(driver);
        wait.forVisibility(recoverPasswordButtonElement);
        return recoverPasswordButtonElement.isDisplayed();
    }
}
