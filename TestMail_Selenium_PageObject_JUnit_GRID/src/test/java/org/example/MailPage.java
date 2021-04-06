package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class MailPage {

    @FindBy(xpath = "//*[contains(@class, 'mail-ComposeButton-Text')]")
    private WebElement writeBtn;

    @FindBy(xpath = "//*[contains(@class, 'composeYabbles')]")
    private WebElement addressBox;

    @FindBy(xpath = "//*[contains(@class, 'composeTextField ComposeSubject-TextField')]")
    private WebElement subjectBox;

    @FindBy(xpath = "//*[contains(@class, 'cke_wysiwyg_div cke_reset')]")
    private WebElement contentBox;

    @FindBy(xpath = "//*[contains(@class, 'ComposeControlPanelButton-Button_action')]")
    private WebElement sendButton;

    public WebDriver driver;

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public int getNumberOfMailBySubject() {
        /** This part of the code is waiting for all messages to be loaded.*/
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'Simbirsoft Тестовое задание')]")));
        /** ------------------------------------------------------------- */

        List<WebElement> inboxEmailsByItem = driver.findElements(
                By.xpath("//*[contains(text(), 'Simbirsoft Тестовое задание')]"));
        return inboxEmailsByItem.size();

    }
    @Step("Нажатие кнопки 'Написать'")
    public void clickWriteBtn() {
        writeBtn.click();
    }

    @Step("Нажатие кнопки 'Отправить'")
    public void clickSendBtn() {
        sendButton.click();
    }

    @Step("Вставка почтового адреса {mailAddress} в поле ввода")
    public void inputAddress(String mailAddress) {
        addressBox.sendKeys(mailAddress);
    }

    @Step("Вставка темы письма {subject} в поле ввода")
    public void inputSubject(String subject) {
        subjectBox.sendKeys(subject);
    }

    @Step("Вставка сообщения пиьсма в поле ввода")
    public void inputContent(String content) {
        contentBox.sendKeys(content);
    }

    public void writeMail(String mailAddress, String subject, String content) {
        clickWriteBtn();
        inputAddress(mailAddress);
        inputSubject(subject);
        inputContent(content);
        clickSendBtn();
    }
}
