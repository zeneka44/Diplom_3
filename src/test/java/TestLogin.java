import api.client.UserClient;
import api.data.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;

public class TestLogin extends TestBase {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;

    @Before
    public void setup() {
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.random().hex(6);
        UserClient.create(new User(email, password, name));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
    }

    @After
    public void tearDown() {
        if (UserClient.login(email, password).statusCode() == 200) {
            UserClient.delete(email, password);
        }
    }

    @Test
    public void loginByMainButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        new MainPage(driver).clickOnLoginMainButton();
        new LoginPage(driver).fillLoginData(email, password).clickOnLoginButton();
        assertTrue(new MainPage(driver).createOrderIsDisplayed());
    }

    @Test
    public void loginByHeaderButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        new MainPage(driver).clickOnLoginHeaderButton();
        new LoginPage(driver).fillLoginData(email, password).clickOnLoginButton();
        assertTrue(new MainPage(driver).createOrderIsDisplayed());
    }

    @Test
    public void loginOnRegistrationPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        new RegistrationPage(driver).clickOnLoginButtonLink();
        new LoginPage(driver).fillLoginData(email, password).clickOnLoginButton();
        assertTrue(new MainPage(driver).createOrderIsDisplayed());
    }

    @Test
    public void loginOnForgotPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        new ForgotPasswordPage(driver).clickOnLoginButtonLink();
        new LoginPage(driver).fillLoginData(email, password).clickOnLoginButton();
        assertTrue(new MainPage(driver).createOrderIsDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
