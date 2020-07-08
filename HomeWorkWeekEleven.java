package org.example;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;  //imported classes
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import javax.management.StringValueExp;
import java.util.concurrent.TimeUnit;
public class HomeWorkWeekEleven {
    static WebDriver driver;
    //created method to give wait untill element clickable
    public static void waitUntilElementIsClickable(By by, int time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public static void waitUntilElementVisible(By by, int time){   //created method to give wait untill element visible
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfAllElements());
    }
    public static void sleep1(int n) // try and catch reusable method
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
    } //created method to click element
    public static String getText(By by)
    {
        return driver.findElement(by).getText();
    } //created method to get text
    @AfterMethod    //created metod runs after all methods
    public static void closeTest()
    {
        driver.close();
    }
    @BeforeMethod   //created before method as preconditon
    public static void chromeHomePage(){
        System.setProperty("webdriver.chrome.driver", "C:\\soft\\chromedriver.exe");
        driver = new ChromeDriver();     //creating chrome driver object to open google chrome browser
        driver.manage().window().maximize();   //maximizing screen
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //giving implicity wait
        driver.get("https://demo.nopcommerce.com/");   //open the URL
    }                    //created method for timestamp
    public static long timeStamp(){
        return System.currentTimeMillis();


    }                            // created method to select value from dropdown
    public static void selectFromDropDownByValue(By by, String value)
    {
        Select sel = new Select(driver.findElement(by));
        sel.selectByValue(value);
    }                 //created methos to select dropdown value
    public static void selectFromDropDownByText(By by, String text)
    {
        Select sel = new Select(driver.findElement(by));
        sel.selectByVisibleText(text);
    }                 //created method to select index from dropdown
    public static void selectFromDropDownByIndex(By by, int num)
    {
        Select sel = new Select(driver.findElement(by));
        sel.selectByIndex(num);
    }                 //created method for sendkey
    public static void sendkeyElement(By by,String data)
    {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(data);
    }
    @Test (priority = 0 )           //first requrement
    public void userShouldBeRegisterScuccessfullymain() {

        clickable(By.xpath("//a[text()=\"Register\"]"));
        waitUntilElementIsClickable(By.id("register-button"),10);

        clickable(By.xpath("//input[@value=\"M\"]"));//Gennder radio button reg
        ////putting text in Firstname textbox
        sendkeyElement(By.id("FirstName"),"Parva");
        //putting text in Lastname in the text box
        sendkeyElement(By.xpath("//input[@name=\"LastName\"]"),"Patel");
        //choose date 3 from dropdown
        selectFromDropDownByIndex(By.xpath("//select[@name=\"DateOfBirthDay\"]"),3);
        //choose January month from dropdown
        selectFromDropDownByText(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),"January");
        //choose 2000 as Year from
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthYear\"]"),"2000");
        //entering email by using object
        sendkeyElement(By.xpath("//input[@id=\"Email\"]"),"test+"+timeStamp()+"@gmail.com");
        //enterin company name by using method
        sendkeyElement(By.xpath("//input[@name=\"Company\"]"),"xyz ltd");
        //unchecking  on checkbox
        clickable(By.xpath("//input[@type=\"checkbox\"]"));
        //input password
        sendkeyElement(By.xpath("//input[@id=\"Password\"]"),"Password");
        //input confirmation password
        sendkeyElement(By.xpath("//input[@id=\"ConfirmPassword\"]"),"Password");
        //clicking on Regester button
        clickable(By.xpath("//input[@name=\"register-button\"]"));
        //stored expected value in string
        String expectedText = "Your registration completed";
        //stored actual value in variable
        System.out.println(expectedText);
        String achualText = getText(By.xpath("//div[text()=\"Your registration completed\"]"));
        System.out.println(achualText);
        Assert.assertEquals(achualText,expectedText); sleep1(5);  //checking achual vs expected
        clickable(By.xpath("//input[@name=\"register-continue\"]"));//press continue button
        waitUntilElementIsClickable(By.xpath("//created method to give wait untill element visible"),10);


        // userShouldBeAbleToReferToFriendSuccessfully();
    }

    //creating another method for another reqirement
    @Test (priority = 1)
    public  void userShouldBeAbleToReferToFriendSuccessfully()
    {

        clickable(By.xpath("//a[text()=\"Computers \"]"));//click on computer
        sleep1(5);
        clickable(By.xpath("//a[text()=\" Desktops \"]"));//click on desktop
        sleep1(3);                          //click on digital storm
        clickable(By.xpath("//a[text()=\"Digital Storm VANQUISH 3 Custom Performance PC\"]"));
        //click on email a friend
        clickable(By.xpath("//input[@value=\"Email a friend\"]")); sleep1(3);
        //enters friends email add
        sendkeyElement(By.xpath("//input[@class=\"friend-email\"]"),"friend@gmail.com");
        //enter massage in msg box
        waitUntilElementVisible(By.xpath("//input[@class=\"your-email\"]"),20);
        sendkeyElement(By.xpath("//textarea[@class=\"your-email\"]"),"I am reffering this product to you");
        //click on send email button
        clickable(By.xpath("//input[@class=\"button-1 send-email-a-friend-button\"]"));
        String expectedText = "Your message has been sent";  //stored expected value in variable
        //stored actual value in variable
        String achualText = getText(By.xpath("//div[@class=\"result\"]"));
        sleep1(10);                                         //checking if expected is = achual
        Assert.assertEquals(achualText,expectedText);
    }
    @Test (priority = 2)
    public void userShouldBeAbleToSeeSelectedProductInShoppingCart()
    {
        clickable(By.linkText("Books"));  //click on books option
        sleep1(3);
        //click on firstprizepies add to cart button
        clickable(By.xpath("//input[contains(@onclick,\"38/1/1\")]")); sleep1(3);

        // waitUntilElementIsClickable(By.id("//input[contains(@onclick,\"38/1/1\")]"),3);
        //click on pride and prejudice add to cart button
        clickable(By.xpath("//input[contains(@onclick,\"39/1/1\")]"));sleep1(3);
        //click on green bar cross button
        clickable(By.xpath("//span[@title=\"Close\"]")); sleep1(3);
        //put explicit wait
        waitUntilElementIsClickable(By.xpath("//span[@class=\"cart-label\"]"),3);
        // click on shopping cart button
        clickable(By.xpath("//span[@class=\"cart-label\"]"));
        //checking if expected is = achual
        String expectedText = "First Prize pies";
        System.out.println(expectedText);
        //stored actual value in variable
        String achualText  = getText(By.xpath("//tbody/tr[1]/td[4]/a"));////stored actual value in variable
        System.out.println(achualText);
        Assert.assertEquals(achualText ,expectedText);   //checking if expected is = achual
        sleep1(10);
        String expectedText3 = "Pride and Prejudice";   //stored expected value in variable
        System.out.println(expectedText3);
        String achualText3= getText(By.xpath("//tbody/tr[2]/td[4]/a"));
        System.out.println(achualText3);
        Assert.assertEquals(achualText3,expectedText3);   ////checking if expected is = achual


    }
}

