package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Objects;

public class Article {
    WebDriver driver;
    @FindBy(css = "#content-type-facet > ol > li:nth-child(3)")
    WebElement articleButton;
    @FindBy(css = "#article-info-content > div > div:nth-child(2) > ul.c-bibliographic-information__list > li.c-bibliographic-information__list-item.c-bibliographic-information__list-item--doi > p > span.c-bibliographic-information__value")
    private WebElement DOI;

    @FindBy(css = "#main-content > main > article > div.c-article-header > header > h1")
    private WebElement title;

    @FindBy(css = "#article-info-content > div > div:nth-child(2) > ul.c-bibliographic-information__list > li:nth-child(2) > p > span.c-bibliographic-information__value > time")
    private WebElement publishedDate;

    public Article(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebElement getArticleButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content-type-facet > ol > li:nth-child(3)")));

        // Scroll to the element using JavascriptExecutor
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();", articleButton);

        return articleButton;
    }

    private boolean isElementPresent(String cssSelector) {
        try {
            // Attempt to find the element using the given locator
            driver.findElement(new By.ByCssSelector(cssSelector));
            return true; // Element found, so it exists
        } catch (Exception e) {
            return false; // Element not found, so it does not exist
        }
    }


    public void searchForArticles(){
        for (int i = 1; i <= 12; i++) {
            String cssSelector = String.format("#content-type-facet > ol:nth-child(2) > " +
                    "li:nth-child(%d) > a:nth-child(1) > span:nth-child(2)", i);
            if (isElementPresent(cssSelector)){
                WebElement span = driver.findElement(new By.ByCssSelector(cssSelector));
                if (Objects.equals(span.getText(), "Article")){
                    span.click();
                    return;
                }
            }
            else {
                return;
            }
        }
    }


    public void openNthResultInNewTab(int n){ //n = number of the article
        String cssSelector;
        cssSelector = String.format("li.no-access:nth-child(%d) > h2:nth-child(4) > a:nth-child(1)", n);
        if (!(driver.findElements(By.cssSelector(cssSelector)).size() > 0)){
            cssSelector = String.format("#results-list > li:nth-child(%d) > h2:nth-child(3) > a:nth-child(1)", n);
        }

        WebElement searchResultLink = driver.findElement(new By.ByCssSelector(cssSelector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchResultLink);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchResultLink));

        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).click(searchResultLink).keyUp(Keys.CONTROL).build().perform();
    }

    public void openNthResult(int n){
        String cssSelector;
        cssSelector = String.format("#results-list > li:nth-child(1) > h2 > a", n);
        if (!(driver.findElements(By.cssSelector(cssSelector)).size() > 0)){
            cssSelector = String.format("#results-list > li:nth-child(1) > h2 > a", n);
        }

        WebElement searchResultLink = driver.findElement(new By.ByCssSelector(cssSelector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchResultLink);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchResultLink));

        searchResultLink.click();
    }


    public String getDOI(){
        return DOI.getText();
    }
    public String getTitle(){
        return title.getText();
    }
    public String getPublishedDate(){
        return publishedDate.getText();
    }
}

