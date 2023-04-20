package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class CheckHeadersTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        wait = new WebDriverWait(driver, ofSeconds(10));
    }

    @Test
    public void test() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        int menuItems = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size();
        for (int i = 1; i <= menuItems; i++) {
            WebElement element = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-'][" + i + "]"));
            element.click();
            WebElement newElement = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-'][" + i + "]"));
            int docsItems = newElement.findElements(By.xpath("//ul[@class='docs']/li[@id]")).size();
            if (docsItems >= 1) {
                for (int j = 1; j <= docsItems; j++) {
                    WebElement newElementDocs = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-'][" + i + "]"));
                    WebElement docsElement = newElementDocs.findElement(By.xpath("//ul[@class='docs']/li[@id][" + j + "]"));
                    docsElement.click();
                    driver.findElement(By.xpath("//td[@id='content']/h1")).isDisplayed();
                }
            }
            else {
                driver.findElement(By.xpath("//td[@id='content']/h1")).isDisplayed();
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}