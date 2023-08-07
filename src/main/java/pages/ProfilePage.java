package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;

public class ProfilePage {
    private WebDriver driver;
    private final WebDriverWait wait;

    private By profileHeaderButton = xpath("//a[text()='Профиль']");
    private By constructorButton = xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");
    private By stellarBurgerLogo = xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private By logOutButton = xpath("//button[text()='Выход']");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ofSeconds(10));
    }

    public boolean profileHeaderIsDisplayed() {
        WebElement button = driver.findElement(profileHeaderButton);
        return driver.findElement(profileHeaderButton).isDisplayed();
    }

    public void clickOnConstructorButton() {
        WebElement element = driver.findElement(constructorButton);
        element.click();
    }

    public void clickOnStellarBurgerLogo() {
        WebElement element = driver.findElement(stellarBurgerLogo);
        element.click();
    }

    public void clickOnLogOutButton() {
        WebElement element = driver.findElement(logOutButton);
        element.click();
    }

}
