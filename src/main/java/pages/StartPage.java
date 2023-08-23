package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wait.Wait;

public class StartPage extends BasePage {

    public StartPage(WebDriver driver) {
        super(driver);
    }

    Wait wait;
    @FindBy(xpath = "(//a[@data-element='button'])[1]")
    private WebElement headerSignInElement;

    @FindBy(xpath = "(//a[@data-element='button'])[2]")
    private WebElement headerSignUpElement;

    @Step("Push the button 'Sign In' in Header ")
    public void clickHeaderSignInButton() {
        wait = new Wait(driver);
        wait.forVisibility(headerSignInElement);
        headerSignInElement.click();
    }

    public void clickHeaderSignUpButton() {
        wait = new Wait(driver);
        wait.forVisibility(headerSignUpElement);
        headerSignUpElement.click();
    }
}
