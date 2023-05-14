package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;

public class CheckBrowserLogs {

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
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        int countItem = driver.findElements(By.xpath(".//table[@class='dataTable']//td[3]/img")).size();
        for (int i=1; i<=countItem; i++) {
            WebElement currentItem = driver.findElement(By.xpath("(.//table[@class='dataTable']//td[3]/a[contains(@href, 'product')])[" + i + "]"));
            currentItem.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            driver.findElement(By.name("cancel")).click();

            List<LogEntry> logList = driver.manage().logs().get("browser").getAll();
            if (logList.size()!= 0) {
                for (LogEntry l : logList)
                    System.out.println(l);
            }
            else
                System.out.println("На странице сообщения отсутствуют");
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
