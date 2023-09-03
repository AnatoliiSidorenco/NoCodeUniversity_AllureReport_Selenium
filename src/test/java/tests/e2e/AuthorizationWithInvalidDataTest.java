package tests.e2e;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.AuthorizationPage;
import pages.StartPage;
import tests.BaseTest;

import static org.junit.Assert.assertEquals;

public class AuthorizationWithInvalidDataTest extends BaseTest {
    StartPage startPage;
    AuthorizationPage authorizationPage;

    @Test
    @DisplayName("Authorization as a Non-Existed user")
    @Description("Attempt to sign in, with data of not previously registered user")
    public void signInWithNonExistedUserTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputEmailSignInForm(nonRegisteredUser);
        authorizationPage.fillInputPasswordSignInForm(nonRegisteredUser);
        authorizationPage.clickSignInButtonInSignInForm();

        assertEquals("Invalid email or password", authorizationPage.errorMessageSignInForm());
    }

    @Test
    @DisplayName("Authorization with invalid Email")
    @Description("Attempt to sign in, while inputting in the email field invalid data")
    public void signInWithInvalidEmail() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputEmailSignInForm(invalidUser);
        authorizationPage.fillInputPasswordSignInForm(validTeacher);
        authorizationPage.clickSignInButtonInSignInForm();

        assertEquals("Invalid email or password", authorizationPage.errorMessageSignInForm());
    }

    @Test
    @DisplayName("Authorization with empty field Email")
    @Description("Attempt to sign in, while inputting in the password field invalid data")
    public void signInWithInvalidPassword() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputEmailSignInForm(validTeacher);
        authorizationPage.fillInputPasswordSignInForm(invalidUser);
        authorizationPage.clickSignInButtonInSignInForm();

        assertEquals("Invalid email or password", authorizationPage.errorMessageSignInForm());
    }
}
