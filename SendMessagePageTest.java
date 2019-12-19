package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SendMessagePage;
import java.util.concurrent.TimeUnit;

public class SendMessagePageTest {
    private WebDriver driver;
    private SendMessagePage sendMessagePage;
    private LoginPage loginPage;
    Logger logger = Logger.getLogger(SendMessagePageTest.class);

    @BeforeTest
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver", "D:/DZ/src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://mail.ru");
        loginPage = new LoginPage(driver);
        sendMessagePage = new SendMessagePage(driver);
        loginPage.enterLogin("prokopchik.vityusha@mail.ru");
        loginPage.clickEnterButton();
        loginPage.enterPass("Asusx54c");
        loginPage.clickEnterButton();
        logger.info("Открыли брауез, перешли на сайт маил.ру и залогинились");


    }



    @Test
    public void sendingAMessageToAGroupOfUsers(){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        sendMessagePage.createNewMessage();
        sendMessagePage.setDestination();
        sendMessagePage.sendMessage();
        Assert.assertTrue(sendMessagePage.sendMessageLink());
        logger.info("Отправили сообщения....");
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
        logger.info("Закрыли брауезер и пьем кофе");
    }
}
