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
    public void saucesOpen() {
        MainPage page = new MainPage(driver);
        page.clickOnSaucesButton();
        assertTrue(page.isCategorySelected("Соусы"));
    }

    @Test
    public void bunsOpen() {
        MainPage page = new MainPage(driver);
        page.clickOnSaucesButton();
        page.clickOnBunsButton();
        assertTrue(page.isCategorySelected("Булки"));
    }

    @Test
    public void fillingsOpen() {
        MainPage page = new MainPage(driver);
        page.clickOnFillingsButton();
        assertTrue(page.isCategorySelected("Начинки"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
