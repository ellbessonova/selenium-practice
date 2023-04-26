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

public class CheckSortCountriesAndGeofence {

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
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        WebElement table = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
        List<WebElement> countriesName = table.findElements(By.xpath("./tr[@class='row']/td[5]/a"));
        List<String> countriesList = new ArrayList<>();
        for (WebElement element : countriesName) {
            countriesList.add(element.getAttribute("textContent"));
        }
        List<String> sortedList = new ArrayList<>(countriesList);
        Collections.sort(sortedList);
       if (sortedList.equals(countriesList)) {
           System.out.println("Страны расположены в алфавитном порядке");
       }
       else {
           System.out.println("Страны расположены не в алфавитном порядке");
       }

       List<WebElement> timeZones = table.findElements(By.xpath("./tr[@class='row']/td[6]"));
       List<String> timeZonesList = new ArrayList<>();
       for (WebElement element : timeZones) {
           timeZonesList.add(element.getText());
       }
       int i = 1;
       for (String zone : timeZonesList) {
           if (!zone.equals("0")) {
               WebElement locator = driver.findElement(By.cssSelector("td#content table.dataTable tbody"));
               WebElement countryName = locator.findElement(By.xpath("./tr[@class='row'][" + i + "]/td[5]/a"));
               countryName.click();
               WebElement tableZones = driver.findElement(By.cssSelector("table#table-zones.dataTable tbody"));
               List<WebElement> zonesName = tableZones.findElements(By.xpath("./tr/td[3]"));
               List<String> zonesList = new ArrayList<>();
               for (WebElement element : zonesName) {
                   zonesList.add(element.getAttribute("textContent"));
               }
               zonesList.remove(zonesList.size()-1);
               List<String> sortedZonesList = new ArrayList<>(zonesList);
               Collections.sort(sortedZonesList);
               if (sortedZonesList.equals(zonesList)) {
                   System.out.println("Геозоны расположены в алфавитном порядке");
               }
               else {
                   System.out.println("Геозоны расположены не в алфавитном порядке");
               }
               driver.findElement(By.cssSelector("td#content p button[name=cancel]")).click();
           }
           i++;
       }

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
