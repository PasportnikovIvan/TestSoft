package selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageOMTest {
    WebDriver driver;
    OpenPage page;
    LogIn logIn;
    AdvancedSearch avSearch;
    Article article;
    static List<String> articleNames = new ArrayList<>();
    static List<String> articleDOIs = new ArrayList<>();
    static List<String> articlePublishingDates = new ArrayList<>();


    @BeforeEach
    public void setUp(){
        driver = new EdgeDriver();
        page = new OpenPage(driver);
        logIn = new LogIn(driver);
        avSearch = new AdvancedSearch(driver);
        article = new Article(driver);
    }

    @Test
    public void moveToForm(){
        //prihlaseni
        page.visitPage();
        page.signIn();
        logIn.getMailElement().sendKeys("paspoiva@fel.cvut.cz");
        logIn.getPasswordElement().sendKeys("Vanekpenek1234");
        logIn.getLogInButton().click();
        //hledani
        avSearch.getSearchOptions().click();
        avSearch.getAdvancedSearchButton().click();
        avSearch.acceptCookies();
        avSearch.getAllWords().sendKeys("Page Object Model");
        avSearch.getLeastWords().sendKeys("Selenium Testing");
        avSearch.getStartYear().sendKeys("2023");
        avSearch.getEndYear().sendKeys("2023");
        avSearch.getSubmitSeachButton().click();
        //cteni clanku a ukladani dat

        article.getArticleButton().click();
        for (int i = 1; i <= 4; i++) {
            article.openNthResultInNewTab(i);
        }

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (int i = 1; i <= 4; i++) {
            driver.switchTo().window(tabs.get(i));
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            articlePublishingDates.add(article.getPublishedDate());
            articleDOIs.add(article.getDOI());
            articleNames.add(article.getTitle());
        }

        //comparing the web page
        for (int i = 0; i < 4; i++) {
            page.visitPage();
            avSearch.getSearchOptions().click();
            avSearch.getAdvancedSearchButton().click();
            avSearch.getAllWords().sendKeys(articleNames.get(i));
            avSearch.getSubmitSeachButton().click();
            article.searchForArticles();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            article.openNthResult(1);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            Assertions.assertEquals(articlePublishingDates.get(i), article.getPublishedDate());
            Assertions.assertEquals(articleDOIs.get(i), article.getDOI());
            page.getReturningButton().click();
        }
    }
}

