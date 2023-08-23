package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wait.Wait;

public class TermsPage extends BasePage {
    Wait wait;
    public TermsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h1")
    private WebElement termsElement;

    public String checkTerms(){
       return termsElement.getText();
    }
}
