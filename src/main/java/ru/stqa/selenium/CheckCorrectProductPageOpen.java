package ru.stqa.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class CheckCorrectProductPageOpen {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        wait = new WebDriverWait(driver, ofSeconds(10));
    }

    @Test
    public void test() {
        driver.get("http://localhost/litecart");

        WebElement product = driver.findElement(By.cssSelector("div#box-campaigns.box li.product:first-child"));

        String productNameMainPage = product.findElement(By.cssSelector("div.name")).getText();

        String productPriceRegularMainPage = product.findElement(By.cssSelector("div.price-wrapper s.regular-price")).getText();
        String productPriceCampaignsMainPage = product.findElement(By.cssSelector("div.price-wrapper strong.campaign-price")).getText();

        String colorRegularMainPage = product.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String textRegularMainPage = product.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-line");
        String textSizeRegularMainPage = product.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");

        String colorCampaignMainPage = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String textCampaignMainPage = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        String textSizeCampaignMainPage = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        product.click();

        String productNameItemPage = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();

        WebElement productItemPrice = driver.findElement(By.cssSelector("div.information div.price-wrapper"));

        String productPriceRegularItemPage = productItemPrice.findElement(By.cssSelector("s.regular-price")).getText();
        String productPriceCampaignsItemPage = productItemPrice.findElement(By.cssSelector("strong.campaign-price")).getText();

        String colorRegularItemPage = productItemPrice.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String textRegularItemPage = productItemPrice.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration-color");
        String textSizeRegularItemPage = productItemPrice.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");

        String colorCampaignItemPage = productItemPrice.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String textCampaignItemPage = productItemPrice.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        String textSizeCampaignItemPage = productItemPrice.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        Assert.assertEquals(productNameMainPage, productNameItemPage);
        Assert.assertEquals(productPriceRegularMainPage, productPriceRegularItemPage);
        Assert.assertEquals(productPriceCampaignsMainPage, productPriceCampaignsItemPage);

        //проверка на серый цвет и что цена зачеркнута на странице продукта
        String[] colorGrey = {colorRegularMainPage, colorRegularItemPage, textRegularItemPage};
        for (String checkGreyColor : colorGrey) {
            String checkGreyColorOnPage = checkGreyColor.replaceAll("[a-z() ]+", "");
            String[] value = checkGreyColorOnPage.split(",");
            Boolean greyColor = Integer.parseInt(value[0]) == Integer.parseInt(value[1]) && Integer.parseInt(value[1]) == Integer.parseInt(value[2]);
            Assert.assertTrue(greyColor);
        }

        //проверка на красный цвет
        String[] colorRed = {colorCampaignMainPage, colorCampaignItemPage};
        for (String checkRedColor : colorRed) {
            String checkRedColorOnPage = checkRedColor.replaceAll("[a-z() ]+", "");
            String[] val = checkRedColorOnPage.split(",");
            Boolean redColor = Integer.parseInt(val[1]) == 0 && Integer.parseInt(val[2]) == 0;
            Assert.assertTrue(redColor);
        }

        //проверка, что цена зачеркнута на главной странице
        Assert.assertEquals("line-through", textRegularMainPage);

        //проверка, что акционная цена жирная
        Assert.assertTrue(textCampaignMainPage.equals("700") || textCampaignMainPage.equals("900"));
        Assert.assertTrue(textCampaignItemPage.equals("700") || textCampaignItemPage.equals("900"));

        //проверка, что акционная цена крупнее, чем обычная
        String[] sizeRegular = {textSizeRegularMainPage, textSizeRegularItemPage};
        String[] sizeCampaign = {textSizeCampaignMainPage, textSizeCampaignItemPage};
        for (String checkSizeRegular : sizeRegular) {
            for (String checkSizeCampaign : sizeCampaign) {
                Float checkSizeR = Float.valueOf(checkSizeRegular.replaceAll("[^0-9.]", ""));
                Float checkSizeC = Float.valueOf(checkSizeCampaign.replaceAll("[^0-9.]", ""));
                Assert.assertTrue(checkSizeC > checkSizeR);
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
