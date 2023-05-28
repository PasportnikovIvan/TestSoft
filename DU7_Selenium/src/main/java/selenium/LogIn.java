package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogIn {
    WebDriver driver;
    public LogIn(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#login-box-email")
    static
    WebElement mail;
    @FindBy(css = "#login-box-pw")
    WebElement password;
    @FindBy(css = "#login-box > div > div.form-submit > button")
    WebElement logInButton;

    public WebElement getMailElement(){
        return mail;
    }

    public WebElement getPasswordElement(){
        return password;
    }

    public WebElement getLogInButton(){
        return logInButton;
    }
}
