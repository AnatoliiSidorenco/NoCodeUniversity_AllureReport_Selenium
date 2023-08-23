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
import java.util.ArrayList;

public class RegistrationPage extends BasePage {

    Wait wait;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2")
    private WebElement signUpFormHeadingElement;
    @FindBy(css = "[class='filter-option-inner-inner']")
    private WebElement selectRoleFieldElement;
    @FindBy(id = "bs-select-1-0")
    private WebElement roleTeacherElement;

    @FindBy(id = "bs-select-1-1")
    private WebElement roleStudentElement;

    @FindBy(id = "sw-form-capture-full_name-input")
    private WebElement fullNameInputSignUpElement;

    @FindBy(id = "sw-form-capture-email-input")
    private WebElement emailInputSignUpElement;

    @FindBy(id = "sw-form-password-input")
    private WebElement passwordInputSignUpElement;

    @FindBy(xpath = "(//i)[1]")
    private WebElement crossedEyeIconSignUpForm;

    @FindBy(css = "[class='checkmark position-relative sw-checkbox']")
    private WebElement checkBoxInSignUpFormElement;

    @FindBy(xpath = "//a[contains(text(),'Terms')]")
    private WebElement termsInSignUpFormElement;

    @FindBy(xpath = "//a[contains(text(),'Privacy Policy')]")
    private WebElement privacyPolicyInSignUpFormElement;

    @FindBy(id = "sw-sign-up-submit-btn")
    private WebElement signUpButtonSignUpFormElement;

    @FindBy(id = "sw-go-to-sign-in-btn")
    private WebElement signInButtonSignUpFormElement;

    @FindBy(css = "[failure existing notification]")
    private WebElement FAILURE_EXISTING_NOTIFICATION_WebElement;

    @FindBy(css = "[class='error-message required-errors d-block']")
    private WebElement errorMessageForEmptyFieldsSignUpFormWebElement;

    @FindBy(xpath = "//div[@class='error-message other-errors d-flex']")
    private WebElement errorMessageSignUpExistedUserWebElement;
    @Step("Verification that the registration form is displayed.")
    public boolean signUpFormIsVisible() {

        return signUpFormHeadingElement.isDisplayed();
    }

    public void waitForLoading() {
        wait = new Wait(driver);
        wait.forVisibility(selectRoleFieldElement);
    }

    public void selectTeacherInDropdownInSignUpForm() {
        selectRoleFieldElement.click();
        roleTeacherElement.click();
    }

    public void selectStudentInDropdownInSignUpForm() {
        selectRoleFieldElement.click();
        roleStudentElement.click();
    }

    public void fillInputFullNameSignUpForm(User user) {
        fullNameInputSignUpElement.sendKeys(user.getFullName());
    }

    public void fillInputEmailSignUpForm(User user) {
        emailInputSignUpElement.sendKeys(user.getEmail());
    }

    public void fillInputPasswordSignUpForm(User user) {
        passwordInputSignUpElement.sendKeys(user.getPassword());
    }

    public void fillInputPasswordWithQuantity(String password) {
        passwordInputSignUpElement.sendKeys(password);
    }

    public void selectCheckBoxSignUpForm() {
        checkBoxInSignUpFormElement.click();
    }

    public void clickSignUpButtonInSignUpForm() {
        signUpButtonSignUpFormElement.click();
    }

    public boolean passwordCheckMaskedSignUpForm() {
        String text = "password";
        String textAttribute = passwordInputSignUpElement.getAttribute("type");
        return text.equals(textAttribute);
    }

    public boolean passwordCheckNotMaskedSignUpForm() {
        String text = "text";
        String textAttribute = passwordInputSignUpElement.getAttribute("type");
        return text.equals(textAttribute);
    }


    public void clickTerms() {
        termsInSignUpFormElement.click();
    }

    public void clickPrivacyPolicy() {
        privacyPolicyInSignUpFormElement.click();
    }

    public void switchToWindow() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void clickCrossedEye() {
        crossedEyeIconSignUpForm.click();
    }

    public boolean isCopyingDisabledSignUpForm(String password) throws IOException, UnsupportedFlavorException {

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
        passwordInputSignUpElement.sendKeys(password);
        passwordInputSignUpElement.sendKeys(Keys.chord(Keys.CONTROL, "c"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        String copiedText = (String) contents.getTransferData(DataFlavor.stringFlavor);

        return copiedText == null || copiedText.isEmpty();
    }

    public boolean isCuttingDisabledSignUpForm(String password) throws IOException, UnsupportedFlavorException {

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
        passwordInputSignUpElement.sendKeys(password);
        passwordInputSignUpElement.sendKeys(Keys.CONTROL + "x");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        String carvedText = (String) contents.getTransferData(DataFlavor.stringFlavor);

        return carvedText == null || carvedText.isEmpty();
    }

    public void clickSignInButtonSignUpForm() {
        signInButtonSignUpFormElement.click();
    }

    public String FAILURE_EXISTING_NOTIFICATION_SignUpForm() {
        wait.forVisibility(FAILURE_EXISTING_NOTIFICATION_WebElement);
        return FAILURE_EXISTING_NOTIFICATION_WebElement.getText();
    }

    public String errorMessageEmptyFieldsSignUpForm() {
        wait.forVisibility(errorMessageForEmptyFieldsSignUpFormWebElement);
        return errorMessageForEmptyFieldsSignUpFormWebElement.getText();
    }

    public String errorMessageSignUpWithExistedUser() {
        wait.forVisibility(errorMessageSignUpExistedUserWebElement);
        return errorMessageSignUpExistedUserWebElement.getText();
    }

}
