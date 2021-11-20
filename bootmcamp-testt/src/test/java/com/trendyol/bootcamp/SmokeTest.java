package com.trendyol.bootcamp;


import com.sun.org.omg.CORBA.ExceptionDescription;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**

 1-browseri açtım
 2-trendyol.com' gittim
 * popupo kapatmak
 3-secarhbar' telefon yazdım
 4-entera bastim
 5-sayfanın üst kisminda telefon yazdiğimi kontol ettim
 *
 * click
 * input
 * scroll
 * url'e gider
 * hover yapabilir
 * surukle birak(click)
 * speech recognition
 * read getText()
 *
 *
 */


public class SmokeTest {
       WebDriver webDriver;


    @BeforeMethod

    public  void startUp(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        webDriver=new ChromeDriver(options);

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("https://www.trendyol.com");
        //Manuel olarak bir bekleme süresi ekledik
        //Thread.sleep(2000);

        WebElement closeButton=webDriver.findElement(By.id("Rating-Review"));
        closeButton.click();


    }


    @Test
    public void shouldSearch() throws InterruptedException {

        //İLK YÖNTEM
        //WebElement searchBox=webDriver.findElement(By.className("search-box"));
        //searchBox.sendKeys("telefon",Keys.ENTER);

        //İKİNCİ YÖNTEM
        webDriver.findElement(By.className("search-box")).sendKeys("telefon");
        webDriver.findElement(By.className("search-box")).sendKeys(Keys.ENTER);
        String resultText=webDriver.findElement(By.cssSelector(".dscrptn>h1")).getText();
        assertEquals(resultText,"telefon");

        Thread.sleep(2000);

    }
    @Test
    public void shouldDisplayRecommendationsOnSearchBox(){



        //İLK YÖNTEM
        //WebElement searchBox=webDriver.findElement(By.className("search-box"));
        //searchBox.sendKeys("telefon",Keys.ENTER);

        //İKİNCİ YÖNTEM
        webDriver.findElement(By.className("search-box")).sendKeys("telefon");
        boolean searchRecommendations=webDriver.findElement(By.className("suggestion-title")).isDisplayed();
        assertEquals(searchRecommendations,true);

    }

    @Test

    public void shouldLogin() {

        webDriver.findElement(By.className("account-user")).click();
        webDriver.findElement(By.id("login-email")).sendKeys("yunuskaratest1@outlook.com");
        webDriver.findElement(By.id("login-password-input")).sendKeys("samsun12345");
        webDriver.findElement(By.className("submit")).click();
        WebDriverWait wait=new WebDriverWait(webDriver,10);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("component-list")));


        String accounButtonText=webDriver.findElement(By.className("account-user")).getText();
        assertEquals(accounButtonText,"Hesabım");



    }
    @AfterMethod

    public void tearDown(){
        webDriver.quit();
    }


}
