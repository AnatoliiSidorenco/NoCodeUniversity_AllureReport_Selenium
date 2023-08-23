package tests.e2e;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;
import tests.BaseTest;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class AuthorizationPositiveTest extends BaseTest {
    StartPage startPage;
    AuthorizationPage authorizationPage;
    HomePage homePage;
    ForgotPasswordPage forgotPasswordPage;

    RegistrationPage registrationPage;

    @Test @DisplayName("Authorization as a teacher")
    @Description("Trying to sign in with valid data")
    public void successAuthorizationAsTeacherTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputEmailSignInForm(validTeacher);
        authorizationPage.fillInputPasswordSignInForm(validTeacher);
        authorizationPage.clickSignInButtonInSignInForm();

        homePage = new HomePage(app.driver);
        homePage.waitForLoading();

        assertTrue(homePage.getTextForTeacher().contains("As a teacher"));
    }

    @Test @DisplayName("Authorization as a student")
    @Description("Trying to sign in with valid data")
    public void successAuthorizationAsStudentTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputEmailSignInForm(validStudent);
        authorizationPage.fillInputPasswordSignInForm(validStudent);
        authorizationPage.clickSignInButtonInSignInForm();

        homePage = new HomePage(app.driver);
        homePage.waitForLoading();

        assertTrue(homePage.getTextForStudent().contains("As a student"));
    }

    @Test @DisplayName("Recovery password")
    @Description("When you click on the 'Forgot password' link, the system navigates you to the 'Forgot password' page where you'll see a form to fill out.")
    public void checkForgotPasswordTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.clickForgotPasswordLink();

        forgotPasswordPage = new ForgotPasswordPage(app.driver);
        assertTrue("Recovery password form is not displayed",forgotPasswordPage.recoverPasswordButton());
    }

    @Test @DisplayName("Password is hidden")
    @Description("Password field displays masked characters (dots) instead of the actual password")
    public void passwordInvisibilitySignInFormTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputPasswordSignInForm(validStudent);

        assertTrue(authorizationPage.passwordCheckMaskedSignInForm());
    }

    @Test @DisplayName("Password is visible")
    @Description("If you click on crossed eye symbol, displays actual password")
    public void passwordVisibilitySignInFormTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.fillInputPasswordSignInForm(validStudent);
        authorizationPage.clickOnEyeInPasswordFieldSignInForm();

        assertTrue(authorizationPage.passwordCheckNotMaskedSignInForm());
    }

    @Test @DisplayName("Copy is disabled")
    @Description("Impossible to copy password, if it masked")
    public void disabledCopyForHiddenPasswordSignInFormTest() throws IOException, UnsupportedFlavorException {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();

        assertTrue(authorizationPage.isCopyingDisabledSignInForm(validStudentPassword));
    }

    @Test @DisplayName("Cut is disabled")
    @Description("Impossible to cut password, if it masked")
    public void disabledCutForHiddenPasswordSignInFormTest() throws IOException, UnsupportedFlavorException {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();

        assertTrue(authorizationPage.isCuttingDisabledSignInForm(validStudentPassword));
    }

    @Test @DisplayName("Sign Up button is enabled")
    @Description("After clicking the 'Sign Up' button,the system navigates to the 'Registration' page ")
    public void checkSignUpButtonInSignInFormTest() {
        startPage = new StartPage(app.driver);
        startPage.clickHeaderSignInButton();

        authorizationPage = new AuthorizationPage(app.driver);
        authorizationPage.waitForLoading();
        authorizationPage.clickSignUpButtonInSignInForm();

        registrationPage = new RegistrationPage(app.driver);
        assertTrue(registrationPage.signUpFormIsVisible());
    }
}
