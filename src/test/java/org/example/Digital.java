package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Digital {


    static WebDriver driver;
public static void main(String[] args) {
          //setting up chromedriver path
        System.setProperty("webdriver.chrome.driver", "C:\\soft\\chromedriver.exe");
        driver = new ChromeDriver();//creating chrome driver object to open google chrome browser
        driver.manage().window().maximize();    //maximizing screen
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://demo.nopcommerce.com/");  //open the URL
       driver.findElement(By.xpath("//a[text()=\"Computers \"]")).click();  //click on link for computers
        driver.findElement(By.xpath("//a[text()=\" Desktops \"]")).click();//click on link for desktop
    //click on link for Digital storm vanquish
        driver.findElement(By.xpath("//a[text()=\"Digital Storm VANQUISH 3 Custom Performance PC\"]")).click();
           // click on Email a friend button
        driver.findElement(By.xpath("//input[@value=\"Email a friend\"]")).click();
           //click on send email button
        driver.findElement(By.xpath("//input[@name=\"send-email\"]")).click();
              //user will see enter friend email error
       String pageText= driver.findElement(By.xpath("//span[@id=\"FriendEmail-error\"]")).getText();

        System.out.println(pageText);
              // user will see enter your email error
        String pageText1= driver.findElement(By.xpath("//span[@id=\"YourEmailAddress-error\"]")).getText();
        System.out.println(pageText1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
