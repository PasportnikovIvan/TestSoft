package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenPage {

    WebDriver driver;

    @FindBy(css = "#header > button.pillow-btn.open-menu")
    WebElement burgerButton;
    @FindBy(css = "#header > div.panel-menu > div > div.auth.flyout > a")
    WebElement signInButton;
    @FindBy(css = "#logo > picture > img")
    WebElement returningButton;



    public OpenPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitPage(){
        driver.get("https://link.springer.com/");
    }

    public void signIn(){
        burgerButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
    }

    public WebElement getReturningButton(){
        return returningButton;
    }
}

