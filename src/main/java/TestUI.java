/**
 * Created by Anastasia on 25.01.2018.
 */

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class TestUI extends AssertExtension {

    private static FirefoxDriver driver;

    @BeforeClass
    public static void Init () {
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);  //ожидание загрузки страницы
        driver.get("http://demoqa.com/");

    }

    @Test
    public void ClickOnTopNavigation () {   //Переход по верхней панели навигации

        String [] expectedResult={"Demoqa | Just another WordPress site",
                "About us | Demoqa",
                "Services | Demoqa",
                "Draggable | Demoqa",
                "Tabs | Demoqa",
                "Blog | Demoqa | Just another WordPress site",
                "Contact | Demoqa"};

        String [] actialResult = new String[7];

        WebElement buttonHome = driver.findElement(By.id("menu-item-38"));
        buttonHome.click();
        actialResult[0]=driver.getTitle();

        WebElement buttonAboutUs = driver.findElement(By.id("menu-item-158"));
        buttonAboutUs.click();
        actialResult[1]=driver.getTitle();

        WebElement buttonServices = driver.findElement(By.id("menu-item-155"));
        buttonServices.click();
        actialResult[2]=driver.getTitle();

        WebElement buttonDemo = driver.findElement(By.id("menu-item-66"));
        buttonDemo.click();
        WebElement buttonDraggable = driver.findElement(By.id("menu-item-73"));
        buttonDraggable.click();
        actialResult[3]=driver.getTitle();

        WebElement buttonDemo2 = driver.findElement(By.id("menu-item-66"));
        buttonDemo2.click();
        WebElement buttonTabs = driver.findElement(By.id("menu-item-153"));
        buttonTabs.click();
        actialResult[4]=driver.getTitle();

        WebElement buttonBlog = driver.findElement(By.id("menu-item-65"));
        buttonBlog.click();
        actialResult[5]=driver.getTitle();

        WebElement buttonContact = driver.findElement(By.id("menu-item-64"));
        buttonContact.click();
        actialResult[6]=driver.getTitle();

        Assert.assertArrayEquals(expectedResult,actialResult);
    }

    @Test
    public void SendMessageAllFields () throws InterruptedException {  //Отправка сообщения из раздела "Contact" с заполнением всех полей


        WebElement buttonContact = driver.findElement(By.id("menu-item-64"));
        assertIsElementPresent( buttonContact);
        buttonContact.click();

        WebElement fillName = driver.findElement(By.xpath("//p[1]//span//input"));
        assertIsElementPresent( fillName);
        fillName.sendKeys("Anastasia");

        WebElement fillEmail = driver.findElement(By.xpath("//p[2]//span//input"));
        assertIsElementPresent( fillEmail);
        fillEmail.sendKeys("anastasia@yandex.ru");

        WebElement fillSubject = driver.findElement(By.xpath("//p[3]//span//input"));
        assertIsElementPresent( fillSubject);
        fillSubject.sendKeys("My Message");

        WebElement fillMessage = driver.findElement(By.xpath("//p[4]//span//textarea"));
        assertIsElementPresent( fillMessage);
        fillMessage.sendKeys("This message");

        WebElement buttonSend = driver.findElement(By.xpath("//p[5]//input"));
        assertIsElementPresent( buttonSend);
        buttonSend.click();

        Thread.sleep(10000);
        WebElement message = driver.findElement(By.xpath("//article//div/div//form//div[2]"));

        assertIsElementPresent(message);
        assertData(message, "Your message was sent successfully. Thanks.");
    }


    @Test
    public void SendMessageWithoutEmail () throws InterruptedException {  //Отправка сообщения из раздела "Contact" без заполнения обязательного поля "Your Email"

        WebElement buttonContact = driver.findElement(By.id("menu-item-64"));
        assertIsElementPresent( buttonContact);
        buttonContact.click();

        WebElement fillName = driver.findElement(By.xpath("//p[1]//span//input"));
        assertIsElementPresent( fillName);
        fillName.sendKeys("Anastasia");

        WebElement fillSubject = driver.findElement(By.xpath("//p[3]//span//input"));
        assertIsElementPresent( fillSubject);
        fillSubject.sendKeys("My Message");

        WebElement fillMessage = driver.findElement(By.xpath("//p[4]//span//textarea"));
        assertIsElementPresent( fillMessage);
        fillMessage.sendKeys("This message");

        WebElement buttonSend = driver.findElement(By.xpath("//p[5]//input"));
        assertIsElementPresent( buttonSend);
        buttonSend.click();

        Thread.sleep(5000);
        WebElement message1 = driver.findElement(By.xpath("//article//div//div//form//div[2]"));

        assertIsElementPresent(message1);
        assertData(message1, "Validation errors occurred. Please confirm the fields and submit it again.");

        WebElement message2 = driver.findElement(By.xpath("//p[2]//span//span"));

        assertIsElementPresent(message2);
        assertData(message2, "Please fill the required field.");
    }


    @AfterClass
    public static void Termination () {

        //driver.quit();
    }
}