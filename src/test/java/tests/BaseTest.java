package tests;

import io.qameta.allure.Attachment;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.User;

public class BaseTest {
    public static ApplicationManager app = new ApplicationManager();

    @Before
    public void setUp() {
        app.init();
    }

    @Rule
    public TestWatcher screenShotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {

            makeScreenshotOnFailure();
            app.driver.close();
            app.driver.quit();
        }
        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            makeScreenshotOnFailure();
            app.driver.close();
            app.driver.quit();
        }
        @Override
        protected void succeeded(Description description) {
            app.driver.close();
            app.driver.quit();
        }
        @Attachment
        public byte[] makeScreenshotOnFailure() {
            return ((TakesScreenshot) app.driver).getScreenshotAs(OutputType.BYTES);
        }
    };
    public void pause(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String validTeacherEmail = "xavi@gmail.com";
    public static String validStudentEmail = "malik@example.com";
    public static String validStudentPassword = "123456";
    public static String validTeacherPassword = "123456";
    public static String nonRegisteredEmail = "rox@example.com";
    public static String nonRegisteredPassword = "12AB#m4";
    public static String inValidUserEmail = "malikexample.com";
    public static String inValidUserPassword = "ιδιωτικός";


    public static User validTeacher = new User(validTeacherEmail, validTeacherPassword);
    public static User validStudent = new User(validStudentEmail, validStudentPassword);
    public static User nonRegisteredUser = new User(nonRegisteredEmail, nonRegisteredPassword);
    public static User invalidUser = new User(inValidUserEmail, inValidUserPassword);

}
