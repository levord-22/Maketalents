package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class MailPage {

    public WebDriver driver;
    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[contains(text(), 'Написать')]")
    private WebElement writeBtn;

    @FindBy(xpath = "//*[contains(@class, 'composeYabbles')]")
    private WebElement addressBox;

    @FindBy(xpath = "//*[contains(@class, 'composeTextField ComposeSubject-TextField')]")
    private WebElement subjectBox;

    @FindBy(xpath = "//*[contains(@class, 'cke_wysiwyg_div cke_reset')]")
    private WebElement contentBox;

    @FindBy(xpath = "//*[contains(@class, 'ComposeControlPanelButton-Button_action')]")
    private WebElement sendButton;


    public int getNumberOfMailsBySubject(){

        List<WebElement> inboxEmailsByItem = driver.findElements(By.xpath("//*[contains(text(), 'Simbirsoft Тестовое задание')]"));
        return inboxEmailsByItem.size();
    }

    public void clickWriteBtn() {
        writeBtn.click(); }

    public void clickSendBtn() {
        sendButton.click(); }

    public void inputAddress(String mailAddress) {
        addressBox.sendKeys(mailAddress); }

    public void inputSubject(String subject) {
        subjectBox.sendKeys(subject); }

    public void inputContent(String content) {
        contentBox.sendKeys(content); }

    public void writeMail(String mailAddress, String subject, String content) {
        clickWriteBtn();

        driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);

        inputAddress(mailAddress);
        inputSubject(subject);
        inputContent(content);
        clickSendBtn();

    }
}
