import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;

public class TestConstructor extends TestBase {
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    public void bunsOpen() {
        new MainPage(driver).clickOnSaucesButton();
        new MainPage(driver).clickOnBunsButton();
        assertTrue(new MainPage(driver).bunsHeaderIsDisplayed());
    }
    @Test
    public void saucesOpen() {
        new MainPage(driver).clickOnSaucesButton();
        assertTrue(new MainPage(driver).saucesHeaderIsDisplayed());
    }
    @Test
    public void fillingsOpen() {
        new MainPage(driver).clickOnFillingsButton();
        assertTrue(new MainPage(driver).fillingsHeaderIsDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
