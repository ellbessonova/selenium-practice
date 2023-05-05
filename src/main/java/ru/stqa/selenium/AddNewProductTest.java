package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Random;

import static java.time.Duration.ofSeconds;

public class AddNewProductTest {

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
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        Random rand = new Random();
        String random = String.valueOf(rand.nextInt(1000));
        String newProductName = "goose" + random;

        String path = "./src/test/resources/goose.jpg";
        Path filePath = Paths.get(path);
        String absolutePath = filePath.normalize().toAbsolutePath().toString();

        driver.findElement(By.partialLinkText("Add New Product")).click();
        driver.findElement(By.cssSelector("input[name=status][value='1']"));
        driver.findElement(By.name("name[en]")).sendKeys(newProductName);
        driver.findElement(By.name("code")).sendKeys(random);
        driver.findElement(By.name("quantity")).sendKeys("40");
        driver.findElement(By.name("new_images[]")).sendKeys(absolutePath);
        driver.findElement(By.name("date_valid_from")).sendKeys(LocalDate.now().toString());
        driver.findElement(By.name("date_valid_to")).sendKeys(LocalDate.now().plusMonths(3).toString());

        driver.findElement(By.linkText("Information")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("manufacturer_id")));
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("keywords")).sendKeys("gooseTest");
        driver.findElement(By.name("short_description[en]")).sendKeys("Geese are big birds.");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Geese are big birds. " +
                "Their neck is not long, on strong orange legs there are membranes for swimming. " +
                "Along the edges of a strong beak, geese have small and sharp teeth, with which they pinch grass. " +
                "Geese feel great both on land and in water.");
        driver.findElement(By.name("head_title[en]")).sendKeys("White Goose");
        driver.findElement(By.name("meta_description[en]")).sendKeys("White Goose");

        driver.findElement(By.linkText("Prices")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("purchase_price")));
        driver.findElement(By.name("purchase_price")).sendKeys("10");
        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByVisibleText("US Dollars");
        driver.findElement(By.name("prices[USD]")).sendKeys("4");
        driver.findElement(By.name("prices[EUR]")).sendKeys("6");

        driver.findElement(By.name("save")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(newProductName)));

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
