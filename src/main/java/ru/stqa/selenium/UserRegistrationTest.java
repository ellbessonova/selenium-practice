package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

import static java.time.Duration.ofSeconds;

public class UserRegistrationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        wait = new WebDriverWait(driver, ofSeconds(10));
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("http://localhost/litecart");

        String email = "test" + LocalDate.now() + "@list.ru";
        String password = "123qwe";

        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
        driver.findElement(By.name("firstname")).sendKeys("Ivan");
        driver.findElement(By.name("lastname")).sendKeys("Ivanov");
        driver.findElement(By.name("address1")).sendKeys("Park Avenue");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("New York");

        driver.findElement(By.cssSelector("[id ^= select2-country_code]")).click();
        driver.findElement(By.cssSelector(".select2-results__option[id $= US]")).click();
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name=zone_code] option[value=NY]")));
        WebElement element = driver.findElement(By.xpath(".//select[@name = 'zone_code']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].selectedIndex = 3 ; arguments[0].dispatchEvent(new Event('change'))", element);

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("777888");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);

        driver.findElement(By.name("create_account")).click();

        driver.findElement(By.cssSelector("div#box-account.box li:last-child a")).click();

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("div#box-account.box li:last-child a")).click();

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
