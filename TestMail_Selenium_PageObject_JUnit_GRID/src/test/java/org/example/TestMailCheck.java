package org.example;

import io.qameta.allure.Description;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestMailCheck {

    public static LoginPage loginPage;
    public static WebDriver driver;
    public static MailPage mailPage;

    @BeforeClass
    public static void setup() throws MalformedURLException {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://192.168.1.146:4444/wd/hub"), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
        driver.get( "https://passport.yandex.ru/auth?from=mail&origin=hostroot_homer_auth_ru&retpath=" +
                "https%3A%2F%2Fmail.yandex.ru%2F&backpath=https%3A%2F%2Fmail.yandex.ru%3Fnoretpath%3D1");

        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
    }

    @Test
    @Description(value = "Тест проверяет вхождение по паролю и логину на страницу почты")
    public void loginTest() {
        loginPage.inputLogin("killsomegad");
        loginPage.clickLoginBtn();
        loginPage.inputPasswd("**************");
        loginPage.clickLoginBtn();
        mailPage.writeMail("killsomegad@yandex.ru", "Simbirsoft Тестовое задание. Ivanov",
                String.valueOf(mailPage.getNumberOfMailBySubject()));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}