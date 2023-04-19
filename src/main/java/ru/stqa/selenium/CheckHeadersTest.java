package ru.stqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    public void test(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.xpath("//span[text()='Appearence']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Template')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Logotype']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Logotype')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Catalog']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Catalog')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Product Groups']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Product Groups')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Option Groups']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Option Groups')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Manufacturers']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Manufacturers')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Suppliers']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Suppliers')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Delivery Statuses']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Delivery Statuses')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Sold Out Statuses']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Sold Out Statuses')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Quantity Units']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Quantity Units')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='CSV Import/Export']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'CSV Import/Export')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Countries']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Countries')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Currencies']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Currencies')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Customers']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Customers')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='CSV Import/Export']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'CSV Import/Export')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Newsletter']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Newsletter')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Geo Zones']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Geo Zones')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Languages']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Languages')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Storage Encoding']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Storage Encoding')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Modules']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Job Modules')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Customer']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Customer Modules')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Shipping']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Shipping Modules')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Payment']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Payment Modules')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Order Total']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Order Total Modules')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Order Success']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Order Success Modules')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Order Action']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Order Action Modules')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Orders']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Orders')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Order Statuses']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Order Statuses')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Pages']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Pages')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Reports']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Monthly Sales')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Most Sold Products']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Most Sold Products')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Most Shopping Customers']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Most Shopping Customers')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Settings']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Settings')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Defaults']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Settings')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='General']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Settings')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Listings']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Settings')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Images']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Settings')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Checkout']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Settings')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Advanced']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Settings')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Security']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Settings')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Slides']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Slides')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Tax']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Tax Classes')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Tax Rates']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Tax Rates')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Translations']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Search Translations')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Scan Files']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Scan Files For Translations')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='CSV Import/Export']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'CSV Import/Export')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='Users']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'Users')]")).isDisplayed();
        driver.findElement(By.xpath("//span[text()='vQmods']")).click();
        driver.findElement(By.xpath("//h1[contains(text(), 'vQmods')]")).isDisplayed();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
