import api.client.UserClient;
import api.data.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;

public class TestProfilePage extends TestBase {
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
        driver.get("https://stellarburgers.nomoreparties.site/login");
        new LoginPage(driver).fillLoginData(email, password).clickOnLoginButton();
        new MainPage(driver).clickOnLoginHeaderButton();
    }

    @After
    public void tearDown() {
        if (UserClient.login(email, password).statusCode() == 200) {
            UserClient.delete(email, password);
        }
    }

    @Test
    public void openConstructorFromProfile() {
        new ProfilePage(driver).clickOnConstructorButton();
        assertTrue(new MainPage(driver).createOrderIsDisplayed());
    }

    @Test
    public void openConstructorByStellarLogoButton() {
        new ProfilePage(driver).clickOnStellarBurgerLogo();
        assertTrue(new MainPage(driver).createOrderIsDisplayed());
    }

    @Test
    public void logOut() {
        new ProfilePage(driver).clickOnLogOutButton();
        assertTrue(new LoginPage(driver).isLoaded());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
