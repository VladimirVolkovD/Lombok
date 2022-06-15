package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBys({
            @FindBy(css = ".cart_item_label")
    })
    public List<WebElement> productList;
    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;
    @FindBy(id = "checkout")
    public WebElement checkoutButton;
    @FindBy(xpath = "//button[text()='Remove']")
    public WebElement removeButton;
    @FindBy(css = ".inventory_item_desc")
    public WebElement desc;
    @FindBy(css = ".inventory_item_price")
    public WebElement price;

    private By productName = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public CartPage isPageOpen() {
        wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.id("continue-shopping")),
                ExpectedConditions.visibilityOfElementLocated(By.id("checkout"))));
        return this;
    }

    public List<String> getProductNamesList() {
        List<String> names = new ArrayList<>();
        for (WebElement product : productList) {
            names.add(product.findElement(productName).getText());
        }
        return names;
    }

    public ProductsListPage clickContinueShoppingButton() {
       continueShoppingButton.click();
        return new ProductsListPage(driver);
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public CartPage clickRemoveButton(int productNumberInList) {
        productList.get(productNumberInList);
        removeButton.click();
        return this;
    }

    public String getName() {
        return driver.findElement(productName).getText();
    }

    public String getDescription() {
        return desc.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public void waitCartPageLoading() {
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/cart.html"));
    }
}
