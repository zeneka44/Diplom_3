package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class RegistrationPage {
    private WebDriver driver;
    private By nameField = xpath("//label[text()='Имя']/parent::*/input");
    private By emailField = xpath("//label[text()='Email']/parent::*/input");
    private By passwordField = xpath("//label[text()='Пароль']/parent::*/input");
    private String inputError = "//p[text()='%s' and contains(@class, 'input__error')]";
    private By registerButton = cssSelector("form button");
    private By loginButtonLink = xpath("//a[text()='Войти']");
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
    }
    public RegistrationPage fillRegistrationData(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        return this;
    }
    public void clickOnLoginButtonLink() {
        WebElement element = driver.findElement(loginButtonLink);
        element.click();
    }
    public boolean isErrorDisplayed(String error) {
        return driver.findElement(xpath(format(inputError, error))).isDisplayed();
    }

}
