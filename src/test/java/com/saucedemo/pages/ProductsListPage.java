package com.saucedemo.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProductsListPage extends BasePage {

    @FindBys({
            @FindBy (css = ".inventory_item")
    })
    public List<WebElement> productsList;
    @FindBy(tagName = "select")
    public WebElement select;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuButton;
    @FindBy(css = ".shopping_cart_link")
    public WebElement cartLink;
    @FindBy(css = ".shopping_cart_badge")
    public WebElement cartBadge;

    private By addToCartButton = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
    private By productName = By.cssSelector(".inventory_item_name");
    private By productDesc = By.cssSelector(".inventory_item_desc");
    private By price = By.cssSelector(".inventory_item_price");

    public ProductsListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public ProductsListPage isPageOpen() {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".inventory_item"), 6));
        return this;
    }

    public SideMenu clickMenuButton() {
        menuButton.click();
        return new SideMenu(driver);
    }

    public CartPage clickCartLink() {
        cartLink.click();
        return new CartPage(driver);
    }

    public String numberCartBadge() {
        return cartBadge.getText();
    }

    public WebElement getSelectedProduct(int index) {
        return productsList.get(index);
    }

    public WebElement getAddToCart(int index) {
        return getSelectedProduct(index).findElement(addToCartButton);
    }

    public ProductsListPage clickAddToCart(int index) {
        System.out.printf("Added to cart item with index {}", index);
        getAddToCart(index).click();
        return this;
    }

    public String getName(int index) {
        return getSelectedProduct(index).findElement(productName).getText();
    }

    public String getDescription(int index) {
        return getSelectedProduct(index).findElement(productDesc).getText();
    }

    public String getPrice(int index) {
        return getSelectedProduct(index).findElement(price).getText();
    }

    public Select getSelect() {
        return new Select(select);
    }

    public void selectValue(String value) {
        getSelect().selectByVisibleText(value);
    }

    public List<String> getProductsNames() {
        List<String> names = new ArrayList<>();
        for (WebElement product: productsList) {
            names.add(product.findElement(productName).getText());
        }
        return names;
    }

    public List<Double> getProductsPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement product: productsList) {
            prices.add(Double.parseDouble(product.findElement(price).getText().replace("$", "")));
        }
        return prices;
    }

    public void waitProductPageLoading() {
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
    }

    PropertyReader reader = new PropertyReader();
    public void waitPageTitleLoading() {

        wait.until(ExpectedConditions.textToBe(title, reader.getProductsPageTitle()));
    }
}

