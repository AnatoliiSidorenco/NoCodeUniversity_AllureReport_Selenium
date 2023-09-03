package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wait.Wait;

public class HomePage extends BasePage {
    Wait wait;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[contains(text(),'As a teacher')]")
    private WebElement welcomeSectionForTeacherElement;

    @FindBy(xpath = "//p[contains(text(),'As a student')]")
    private WebElement welcomeSectionForStudentElement;

    @FindBy(xpath = "(//img)[2]")
    private WebElement welcomeElement;

    @FindBy(css = "[class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-17qbyv7']")
    private WebElement teacherProfileIconElement;

    @FindBy(css = "[class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-17qbyv7']")
    private WebElement studentProfileIconElement;

    @FindBy(xpath = "(//*[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters css-vsdmmi'])[2]")
    private WebElement signOutStudentElement;

    @FindBy(xpath = "(//*[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters css-vsdmmi'])[2]")
    private WebElement signOutTeacherElement;


    public void waitForLoading() {
        wait = new Wait(driver);
        wait.forVisibility(welcomeElement);
    }

    @Step("The validation result: displayed text from the teacher's page")
    public String getTextForTeacher() {

        return welcomeSectionForTeacherElement.getText();
    }

    @Step("The validation result: displayed text from the student's page")
    public String getTextForStudent() {
        return welcomeSectionForStudentElement.getText();
    }

    public void selectStudentSignOut() {
        wait = new Wait(driver);
        studentProfileIconElement.click();
        wait.forVisibility(signOutStudentElement);
        signOutStudentElement.click();
    }

    public void selectTeacherSignOut() {
        wait = new Wait(driver);
        teacherProfileIconElement.click();
        wait.forVisibility(signOutTeacherElement);
        signOutTeacherElement.click();
    }
}
