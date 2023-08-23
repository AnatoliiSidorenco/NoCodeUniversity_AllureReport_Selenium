package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wait.Wait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class AuthorizationPage extends BasePage {

    Wait wait;

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "sw-form-capture-email-input")
    private WebElement emailInputSignInElement;
    @FindBy(id = "sw-form-password-input")
    private WebElement passwordInputSignInElement;
    @FindBy(xpath = "(//i)[1]")
    private WebElement eyeIconPasswordSignInForm;
    @FindBy(css = "a[href='/forgot-password']")
    private WebElement forgotPasswordLinkElement;
    @FindBy(id = "sw-sign-in-submit-btn")
    private WebElement signInButtonSignInFormElement;
    @FindBy(id = "sw-go-to-sign-up-btn")
    private WebElement signUpButtonSignInFormElement;
    @FindBy(css = "[class='error-message login-error d-block']")
    private WebElement errorMessageSignInElement;


    public void waitForLoading() {
        wait = new Wait(driver);
        wait.forVisibility(emailInputSignInElement);
    }

    @Step("Fill Input 'Email' with valid data")
    public void fillInputEmailSignInForm(User user) {
        emailInputSignInElement.sendKeys(user.getEmail());
    }

    @Step("Fill Input 'Password' with valid data")
    public void fillInputPasswordSignInForm(User user) {
        passwordInputSignInElement.sendKeys(user.getPassword());
    }

    @Step("Push the button 'Sign in'")
    public void clickSignInButtonInSignInForm() {
        signInButtonSignInFormElement.click();
    }
    @Step("Push the button 'Sign up'")
    public void clickSignUpButtonInSignInForm() {
        signUpButtonSignInFormElement.click();
    }
    @Step("Click on 'Forgot password' link")
    public void clickForgotPasswordLink() {
        forgotPasswordLinkElement.click();
    }
    @Step("Click on 'Eye' in password field")
    public void clickOnEyeInPasswordFieldSignInForm() {
        eyeIconPasswordSignInForm.click();
    }
    @Step("Verification that Password is hidden by dots")
    public boolean passwordCheckMaskedSignInForm() {
        String text = "password";
        String textAttribute = passwordInputSignInElement.getAttribute("type");
        return text.equals(textAttribute);
    }
    @Step("Verification that Password is not hidden by dots")
    public boolean passwordCheckNotMaskedSignInForm() {
        String text = "text";
        String textAttribute = passwordInputSignInElement.getAttribute("type");
        return text.equals(textAttribute);
    }
    @Step("Verification that password copying is not possible.")
    public boolean isCopyingDisabledSignInForm(String password) throws IOException, UnsupportedFlavorException {

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);

        passwordInputSignInElement.sendKeys(password);
        passwordInputSignInElement.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        String copiedText = (String) contents.getTransferData(DataFlavor.stringFlavor);

        return copiedText == null || copiedText.isEmpty();
    }
    @Step("Verification that password cutting is not possible.")
    public boolean isCuttingDisabledSignInForm(String password) throws IOException, UnsupportedFlavorException {

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
        passwordInputSignInElement.sendKeys(password);
        passwordInputSignInElement.sendKeys(Keys.CONTROL + "x");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        String carvedText = (String) contents.getTransferData(DataFlavor.stringFlavor);

        return carvedText == null || carvedText.isEmpty();
    }

    public boolean emailInSignInFormIsVisible() {

        return emailInputSignInElement.isDisplayed();
    }
@Step("Above the email field appears notice 'Invalid email or password'")
    public String errorMessageSignInForm() {
        wait.forVisibility(errorMessageSignInElement);
        return errorMessageSignInElement.getText();
    }


}
