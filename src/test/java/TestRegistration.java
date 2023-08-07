import api.client.UserClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RegistrationPage;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;

public class TestRegistration extends TestBase {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;

    @Before
    public void setup() {
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.random().hex(6);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @After
    public void tearDown() {
        if (UserClient.login(email, password).statusCode() == 200) {
            UserClient.delete(email, password);
        }
    }

    @Test
    public void registrationValidUser() {
        new RegistrationPage(driver)
                .fillRegistrationData(name, email, password)
                .clickRegisterButton();
        assertTrue(new LoginPage(driver).isLoaded());
    }

    @Test
    public void registrationInvalidUser() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage
                .fillRegistrationData(name, email, faker.random().hex(5))
                .clickRegisterButton();
        assertTrue(registrationPage.isErrorDisplayed("Некорректный пароль"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
