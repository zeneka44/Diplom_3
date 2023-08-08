package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;

public class ForgotPasswordPage {
    private WebDriver driver;
    private final WebDriverWait wait;
    private By loginButtonLink = xpath("//a[text()='Войти']");
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ofSeconds(10));
    }
    public void clickOnLoginButtonLink() {
        WebElement element = driver.findElement(loginButtonLink);
        element.click();
    }

}
