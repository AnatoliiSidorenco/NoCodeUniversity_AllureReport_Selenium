package tests.e2e;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.AuthorizationPage;
import pages.StartPage;
import tests.BaseTest;

import static org.junit.Assert.assertEquals;

public class AuthorizationWithEmptyFieldsTest extends BaseTest {

    StartPage startPage;
    AuthorizationPage authorizationPage;

    @Test @DisplayName("Authorization with Empty fields")
    @Description("Attempt to sign in, while leaving all fields empty")
    public void signInWithEmptyFieldsTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.clickSignInButtonInSignInForm();

        assertEquals("Invalid email or password", authorizationPage.errorMessageSignInForm());
    }

    @Test @DisplayName("Authorization with empty field Email")
    @Description("Attempt to sign in, while leaving the email field empty")
    public void signInWithEmptyFieldEmailTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputPasswordSignInForm(validTeacher);
        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.clickSignInButtonInSignInForm();

        assertEquals("Invalid email or password", authorizationPage.errorMessageSignInForm());
    }

    @Test @DisplayName("Authorization with empty field Password")
    @Description("Attempt to sign in, while leaving the password field empty")
    public void signInWithEmptyFieldPasswordTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputEmailSignInForm(validTeacher);
        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.clickSignInButtonInSignInForm();

        assertEquals("Invalid email or password", authorizationPage.errorMessageSignInForm());
    }
}
