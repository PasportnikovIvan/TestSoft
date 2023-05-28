package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvancedSearch {
    WebDriver driver;

    public AdvancedSearch(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#search-options > button")
    WebElement searchOptions;
    @FindBy(css = "#advanced-search-link")
    WebElement advancedSearchButton;
    @FindBy(css = "#all-words")
    WebElement allWords;
    @FindBy(css = "#least-words")
    WebElement leastWords;
    @FindBy(css = "#facet-start-year")
    WebElement startYear;
    @FindBy(css = "#facet-end-year")
    WebElement endYear;
    @FindBy(css = "#submit-advanced-search")
    WebElement submitSearchButton;
    @FindBy(css = "button.cc-button:nth-child(1)")
    private WebElement acceptCookiesButton;


    public WebElement getAdvancedSearchButton(){
        return advancedSearchButton;
    }
    public WebElement getSearchOptions(){
        return searchOptions;
    }
    public WebElement getAllWords(){
        return allWords;
    }

    public WebElement getLeastWords() {
        return leastWords;
    }
    public WebElement getStartYear() {
        return startYear;
    }
    public WebElement getEndYear() {
        return endYear;
    }
    public WebElement getSubmitSeachButton(){
        return submitSearchButton;
    }

    public void acceptCookies(){
        acceptCookiesButton.click();
    }
}

