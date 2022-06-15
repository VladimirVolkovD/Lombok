package com.saucedemo.pages;

import com.saucedemo.User;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;

@Log4j2
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    public WebElement userNameField;
    @FindBy(id = "password")
    public WebElement passwordField;
    @FindBy(id = "login-button")
    public WebElement loginButton;
    @FindBy(xpath = "//h3")
    public WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public LoginPage isPageOpen() {
        wait.until(ExpectedConditions.urlToBe(reader.getLoginUrl()));
        return this;
    }

    PropertyReader reader = new PropertyReader();

    public void openPage() {
        log.info("Navigate to the {}", reader.getLoginUrl());
        driver.get(reader.getLoginUrl());
    }

    public String Url() {
        return driver.getCurrentUrl();
    }

    public LoginPage setUserName(String userName) {
        log.info("Set {}  like userName for login", userName);
        userNameField.sendKeys(userName);
        return this;
    }

    public LoginPage setPassword(String password) {
        log.info("Set {} like password for login", password);
        passwordField.sendKeys(password + "23");
        return this;
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public ProductsListPage clickLogin() {
        loginButton.click();
        return new ProductsListPage(driver);
    }

    public ProductsListPage login(User user) {
        return setUserName(user.getUsername())
                .setPassword(user.getPassword())
                .clickLogin();
    }

    public ProductsListPage loginWithDefaultUser() {
        return login(new User(reader.getUsername(), reader.getPassword()));
    }

    public void waitMessageEmptyPassword() {
        wait.until(ExpectedConditions.textToBe(By.xpath("//h3"), "Epic sadface: Password is required"));
    }

    public void waitMessageLockedOutUser() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public void waitMessageEmptyUser() {
        wait.until(ExpectedConditions.textToBe(By.xpath("//h3"), "Epic sadface: Username is required"));
    }

    public void waitMessageFailedLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3")));
    }
}
