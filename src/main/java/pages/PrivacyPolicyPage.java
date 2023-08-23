package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivacyPolicyPage extends BasePage {
    public PrivacyPolicyPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h1")
    private WebElement privacyPolicyElement;
    public String checkPrivacyPolicyElement(){
        return privacyPolicyElement.getText();
    }
}
