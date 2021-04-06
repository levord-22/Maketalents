package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(xpath = "//*[contains(@id, 'passp-field-login')]")
    private WebElement loginField;

    @FindBy(xpath = "//*[contains(@class, 'passp-button passp-sign-in-button')]")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[contains(@id, 'passp-field-passwd')]")
    private WebElement passwdField;

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Вставка логина {login} в поле ввода")
    public void inputLogin(String login) {
        System.out.println(loginField.getText());
        loginField.sendKeys(login);
    }
    @Step("Вставка пароля {passwd} в поле ввода")
    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd);
    }

    @Step("Нажатие кнопки 'Ввести'")
    public void clickLoginBtn() {
        loginBtn.click();
    }
}
