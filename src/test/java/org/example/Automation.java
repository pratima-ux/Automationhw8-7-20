package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Automation
{
   static WebDriver driver;
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\soft\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class=\"ico-register\"]")).click();
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){e.printStackTrace();}

        driver.findElement(By.id("FirstName")).sendKeys("Priti");

        driver.findElement(By.xpath("//input[@name=\"LastName\"]")).sendKeys("Patel");



       driver.close();
    }
}
