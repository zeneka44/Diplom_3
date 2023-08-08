package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By registerButton = xpath("//a[@href='/register' and text()='Зарегистрироваться']");
    private By emailField = xpath("//label[text()='Email']/parent::*/input");
    private By passwordField = xpath("//label[text()='Пароль']/parent::*/input");
    private By loginButton = xpath("//button[text()='Войти']");


    private final By loginFormHeader = xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, ofSeconds(10));
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickOnRegisterButton() {
        WebElement element = driver.findElement(registerButton);
        scrollTo(element);
        element.click();
    }

    public LoginPage fillLoginData(String email, String password) {
        setEmail(email);
        setPassword(password);
        return this;
    }

    public void clickOnLoginButton() {
        WebElement element = driver.findElement(loginButton);
        element.click();
    }


    public boolean isLoaded() {
        return driver.findElement(loginFormHeader).isDisplayed();
    }

    private void scrollTo(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}
