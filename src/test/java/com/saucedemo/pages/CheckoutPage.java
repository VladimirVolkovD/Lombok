package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    @FindBy(id = "cancel")
    public WebElement cancelButton;
    @FindBy(id = "continue")
    public WebElement continueButton;
    @FindBy(className = "shopping_cart_link")
    public WebElement cartLink;
    @FindBy(id = "first-name")
    public WebElement firstNameField;
    @FindBy(id = "last-name")
    public WebElement lastNameField;
    @FindBy(xpath = "//h3")
    public WebElement errorMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public CheckoutPage isPageOpen() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".form_group")));
        return this;
    }

    public CartPage clickCancelButton() {
        cancelButton.click();
        return new CartPage(driver);
    }

    public CartPage clickContinueButton() {
        continueButton.click();
        return new CartPage(driver);
    }

    public CartPage clickCartLink() {
        cartLink.click();
        return new CartPage(driver);
    }

    public CheckoutPage setFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    public CheckoutPage setLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void waitCheckoutPageLoading() {
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-one.html"));
    }

    public void waitMessageLoading() {
        wait.until(ExpectedConditions.textToBe(By.xpath("//h3"), "Error: First Name is required"));
    }

    public void waitErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3")));
    }
}
