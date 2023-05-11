package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;

public class CartTest {

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
        driver.get("http://localhost/litecart");

        for (int i = 1; i < 4; i++) {
            List<WebElement> products = driver.findElements(By.cssSelector(".product a.link"));
            products.get(0).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add_cart_product")));
            if (driver.findElements(By.cssSelector("td.options")).size() != 0) {
                new Select(driver.findElement(By.name("options[Size]"))).selectByVisibleText("Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            wait.until(ExpectedConditions.textToBe(By.cssSelector("#cart-wrapper .quantity"), String.valueOf(i)));
            driver.findElement(By.linkText("Home")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("box-most-popular")));
        }

        driver.findElement(By.cssSelector("div#cart a.link")).click();

        int count = driver.findElements(By.cssSelector("li.shortcut")).size();
        for (int i = count; i > 1; i--) {
            driver.findElement(By.cssSelector("li.shortcut")).click();
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("table.dataTable td.item"), i));
        }
        driver.findElement(By.name("remove_cart_item")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("table.dataTable"))));

    }

        @After
        public void stop () {
            driver.quit();
            driver = null;
        }
    }
