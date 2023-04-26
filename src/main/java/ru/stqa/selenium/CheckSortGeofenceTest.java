package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class CheckSortGeofenceTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        wait = new WebDriverWait(driver, ofSeconds(10));
    }

    @Test
    public void test() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        int count = driver.findElements(By.cssSelector("td#content table.dataTable tr.row")).size();
        for(int i = 1; i <= count; i++) {
            WebElement table = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
            WebElement countriesName = table.findElement(By.xpath("./tr[@class='row']["+ i+ "]/td[3]/a"));
            countriesName.click();
            WebElement zonesTable = driver.findElement(By.cssSelector("table#table-zones.dataTable tbody"));
            List<WebElement> zonesName = zonesTable.findElements(By.xpath(".//tr/td[3]//option[@selected='selected']"));
            List<String> zonesList = new ArrayList<>();
            for (WebElement element : zonesName) {
                zonesList.add(element.getAttribute("textContent"));
            }
            List<String> sortedZonesList = new ArrayList<>(zonesList);
            Collections.sort(sortedZonesList);
            if (sortedZonesList.equals(zonesList)) {
                System.out.println("Зоны расположены в алфавитном порядке");
            }
            else {
                System.out.println("Зоны расположены не в алфавитном порядке");
            }
            driver.findElement(By.cssSelector("td#content p button[name=cancel]")).click();
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
