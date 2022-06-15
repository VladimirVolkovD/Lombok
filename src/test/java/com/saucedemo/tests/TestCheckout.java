package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCheckout extends BaseTest {

    @Test //Проверка кнопки "Cancel".
    public void testCancelButton() {
        System.out.println("Test Cancel button");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.loginWithDefaultUser();
        productsPage.isPageOpen();
        CartPage cartPage = productsPage.clickCartLink();
        cartPage.isPageOpen();
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.isPageOpen();
        checkoutPage.clickCancelButton();
        cartPage.waitCartPageLoading();
        Assert.assertEquals(cartPage.getPageTitle(),reader.getCartPageTitle());
    }

    @Test //Проверка кнопки "Continue".
    public void testContinueButton() {
        System.out.println("Test Continue button");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.loginWithDefaultUser();
        productsPage.isPageOpen();
        CartPage cartPage = productsPage.clickCartLink();
        cartPage.isPageOpen();
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.isPageOpen();
        checkoutPage.clickContinueButton();
        checkoutPage.waitMessageLoading();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test //Проверка ссылки "Cart".
    public void testCartLink() {
        System.out.println("Test cart link");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.loginWithDefaultUser();
        productsPage.isPageOpen();
        CartPage cartPage = productsPage.clickCartLink();
        cartPage.isPageOpen();
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.isPageOpen();
        checkoutPage.clickCartLink();
        cartPage.waitCartPageLoading();
        Assert.assertEquals(cartPage.getPageTitle(), reader.getCartPageTitle());
    }

    @Test //Проверка нажатия кнопки "Continue" при незаполненном поле "Zip/Postal Code".
    public void testEmptyPostalCode() {
        System.out.println("Test empty postal code");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.loginWithDefaultUser();
        productsPage.isPageOpen();
        CartPage cartPage = productsPage.clickCartLink();
        cartPage.isPageOpen();
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.isPageOpen();
        checkoutPage.setFirstName("qwerty").setLastName("abcd").clickContinueButton();
        checkoutPage.waitErrorMessage();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
    }
}
