package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Computer {
    static WebDriver driver;
    public static void waitUntilElementIsClickable(By by, int time){
    WebDriverWait wait = new WebDriverWait(driver, time);
    wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public static void sleep1(int n)
    {
        try {
            Thread.sleep(n*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void clickable(By by)
    {
        driver.findElement(by).click();
    }
    public static String getText(By by)
    {
       return driver.findElement(by).getText();
    }
    @AfterTest
    public static void closeTest()
    {
        driver.close();
    }
    @BeforeTest()
    public static void chromeHomePage(){
        System.setProperty("webdriver.chrome.driver", "C:\\soft\\chromedriver.exe");
        driver = new ChromeDriver();     //creating chrome driver object to open google chrome browser
        driver.manage().window().maximize();   //maximizing screen
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //giving implicity wait
        driver.get("https://demo.nopcommerce.com/");   //open the URL
    }
@Test
    public static void main(String[] args) {
        chromeHomePage();
        //reusable  object to click computers
        clickable(By.xpath("//a[text()=\"Computers \"]"));
        // calling object to click desktop
        clickable(By.xpath("//a[text()=\" Desktops \"]"));
        //reusable object for sleep wait
        sleep1(3);
        //
        String expectedText = "Build  own computer";
       String achualText = getText(By.xpath("//a[text()=\"Build your own computer\"]"));
        //
        sleep1(3);
        Assert.assertEquals(achualText,expectedText,"Expected doesnt match Achual");
        closeTest();

    }
}
