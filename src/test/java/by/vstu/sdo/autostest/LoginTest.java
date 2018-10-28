package by.vstu.sdo.autostest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    private static WebDriver driver;

    private static void logout() {
        WebElement menuUser = driver.findElement(By.cssSelector(".menubar"));
        menuUser.click();
        WebElement logoutButton = driver.findElement(By.id("actionmenuaction-6"));
        logoutButton.click();
    }
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://sdo.vstu.by/login/index.php");
    }

    @Test
    public void positiveTestLogin() {
        WebElement login = driver.findElement(By.id("username"));
        login.sendKeys("StepanovEA");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("StepanovEA");

        WebElement form = driver.findElement(By.id("login"));
        form.submit();

        WebElement userName = driver.findElement(By.cssSelector(".usertext"));
        Assert.assertEquals("Егор Андреевич Степанов", userName.getText());

        logout();
    }

    @Test
    public void negativeTestLogin() {
        WebElement login = driver.findElement(By.id("username"));
        login.sendKeys("qweqwe");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("asdasd");

        WebElement form = driver.findElement(By.id("login"));
        form.submit();

        WebElement error = driver.findElement(By.cssSelector(".error"));
        Assert.assertEquals("Неверный логин или пароль, попробуйте заново.", error.getText());
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

