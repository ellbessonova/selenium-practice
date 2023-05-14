package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class CheckOpenNewWindow {

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
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.partialLinkText("Add New Country")).click();

        List<WebElement> links = driver.findElements(By.cssSelector("form td a:not(#address-format-hint)"));
        for (WebElement link : links) {
            link.click();
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            JavascriptExecutor js;
            js = (JavascriptExecutor) driver;
            js.executeScript("window.close()");
            ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(0));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
