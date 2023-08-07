package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;

public class MainPage {
    private WebDriver driver;
    private final WebDriverWait wait;

    private By loginHeaderButton = xpath("//p[text()='Личный Кабинет']");
    private By loginMainButton = xpath("//button[text()='Войти в аккаунт']");
    private By createOrderButton = xpath("//button[text()='Оформить заказ']");
    private By bunsMainButton = xpath("//span[@class='text text_type_main-default'][text()='Булки']");
    private By bunsHeader = xpath("//span[text()='Булки']");
    private By saucesMainButton = xpath("//span[@class='text text_type_main-default'][text()='Соусы']");
    private By saucesHeader = xpath("//span[text()='Соусы']");
    private By fillingsMainButton = xpath("//span[@class='text text_type_main-default'][text()='Начинки']");
    private By fillingsHeader = xpath("//span[text()='Начинки']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ofSeconds(10));
    }

    public void clickOnLoginHeaderButton() {
        WebElement button = driver.findElement(loginHeaderButton);
        driver.findElement(loginHeaderButton).click();
    }

    public void clickOnLoginMainButton() {
        WebElement button = driver.findElement(loginMainButton);
        driver.findElement(loginMainButton).click();
    }

    public boolean createOrderIsDisplayed() {
        WebElement button = driver.findElement(createOrderButton);
        return driver.findElement(createOrderButton).isDisplayed();
    }

    public void clickOnBunsButton() {
        WebElement button = driver.findElement(bunsMainButton);
        driver.findElement(bunsMainButton).click();
    }

    public boolean bunsHeaderIsDisplayed() {
        WebElement button = driver.findElement(bunsHeader);
        return driver.findElement(bunsHeader).isDisplayed();
    }

    public void clickOnSaucesButton() {
        WebElement button = driver.findElement(saucesMainButton);
        driver.findElement(saucesMainButton).click();
    }

    public boolean saucesHeaderIsDisplayed() {
        WebElement button = driver.findElement(saucesHeader);
        return driver.findElement(saucesHeader).isDisplayed();
    }

    public void clickOnFillingsButton() {
        WebElement button = driver.findElement(fillingsMainButton);
        driver.findElement(fillingsMainButton).click();
    }

    public boolean fillingsHeaderIsDisplayed() {
        WebElement button = driver.findElement(fillingsHeader);
        return driver.findElement(fillingsHeader).isDisplayed();
    }
}
