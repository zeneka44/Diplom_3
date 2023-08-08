package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class MainPage {
    private WebDriver driver;
    private final WebDriverWait wait;

    private By loginHeaderButton = xpath("//p[text()='Личный Кабинет']");
    private By loginMainButton = xpath("//button[text()='Войти в аккаунт']");
    private By createOrderButton = xpath("//button[text()='Оформить заказ']");
    private By bunsMainButton = xpath("//span[@class='text text_type_main-default'][text()='Булки']");
    private By saucesMainButton = xpath("//span[@class='text text_type_main-default'][text()='Соусы']");
    private By fillingsMainButton = xpath("//span[@class='text text_type_main-default'][text()='Начинки']");
    private By tab = cssSelector("[class*='tab']");
    private By selectedTab = cssSelector("[class*='current'] span");
    private String ingredientsHeader = "//h2[text()='%s']";


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ofSeconds(10));
    }
    public void findElementAndClick(By locator) {
        WebElement button = driver.findElement(locator);
        button.click();
    }

    public void clickOnLoginHeaderButton() {
        findElementAndClick(loginHeaderButton);
    }

    public void clickOnLoginMainButton() {
        findElementAndClick(loginMainButton);
    }

    public boolean createOrderIsDisplayed() {
        WebElement button = driver.findElement(createOrderButton);
        return driver.findElement(createOrderButton).isDisplayed();
    }

    public void clickOnBunsButton() {
        findElementAndClick(bunsMainButton);
    }

    public void clickOnSaucesButton() {
        findElementAndClick(saucesMainButton);
    }

    public void clickOnFillingsButton() {
        findElementAndClick(fillingsMainButton);
    }

    public boolean isCategorySelected(String category) {
        WebElement tabElement = driver.findElement(tab);
        WebElement header = driver.findElement(xpath(format(ingredientsHeader, category)));
        wait.until((ExpectedCondition<Boolean>) driver -> tabElement.getLocation().getY() + tabElement.getSize().getHeight() == header.getLocation().getY());
        return driver.findElement(selectedTab).getText().equals(category);
    }
}
