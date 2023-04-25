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

public class CheckStickersTest {

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

        String[] boxTitle = {"box-most-popular", "box-campaigns", "box-latest-products"};
        for (final String box : boxTitle) {
        int productsItems = driver.findElements(By.xpath(".//div[@class='middle']//div[@id='" + box + "']" +
                "//li[contains(@class, 'product')]")).size();

        for (int i = 1; i <= productsItems; i++) {
            WebElement product = driver.findElement(By.xpath(".//div[@class='middle']//div[@id='" + box + "']" +
                    "//li[contains(@class, 'product')][" + i + "]"));
            int stickerItems = product.findElements(By.xpath(".//div[@class='image-wrapper']/div[contains(@class,'sticker')]")).size();
            if (stickerItems != 1) {
                System.out.println("У товара больше одного стикера или стикер отсутствует");
            } else {
                String stickerName = product.findElement(By.xpath(".//div[contains(@class,'sticker')]")).getText();
                System.out.println("У товара есть стикер " + stickerName);
            }
        }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
