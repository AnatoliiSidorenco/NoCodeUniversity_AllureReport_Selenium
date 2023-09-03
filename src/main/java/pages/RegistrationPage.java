package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wait.Wait;

public class RegistrationPage extends BasePage {

    Wait wait;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2")
    private WebElement signUpFormHeadingElement;

    @Step("Verification that the registration form is displayed.")
    public boolean signUpFormIsVisible() {
        return signUpFormHeadingElement.isDisplayed();
    }

}
