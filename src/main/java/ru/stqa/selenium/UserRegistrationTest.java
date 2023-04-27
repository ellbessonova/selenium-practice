package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

import static java.time.Duration.ofSeconds;

public class UserRegistrationTest {

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
        driver.get("http://localhost/litecart");

        String email = "test" + LocalDate.now() + "@list.ru";
        String password = "123qwe";

        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
        driver.findElement(By.name("firstname")).sendKeys("Ivan");
        driver.findElement(By.name("lastname")).sendKeys("Ivanov");
        driver.findElement(By.name("address1")).sendKeys("Park Avenue");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("New York");

        Select country = new Select(driver.findElement(By.name("country_code")));
        country.selectByVisibleText("United States");

        Select state = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        state.selectByVisibleText("New York");

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
