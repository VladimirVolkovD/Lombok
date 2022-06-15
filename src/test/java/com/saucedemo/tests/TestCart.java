package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCart extends BaseTest {

    @Test  //Проверка кнопки "Continue Shopping".
    public void testContinueShoppingButton() {
        System.out.println("Test ContinueShopping button");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.loginWithDefaultUser();
        productsPage.isPageOpen();
        CartPage cartPage = productsPage.clickCartLink();
        cartPage.isPageOpen();
        cartPage.clickContinueShoppingButton();
        productsPage.waitProductPageLoading();
        Assert.assertEquals(productsPage.getPageTitle(), reader.getProductsPageTitle());
    }

    @Test //Проверка кнопки "Checkout".
    public void testCheckoutButton() {
        System.out.println("Test Checkout button");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.loginWithDefaultUser();
        productsPage.isPageOpen();
        CartPage cartPage = productsPage.clickCartLink();
        cartPage.isPageOpen();
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.waitCheckoutPageLoading();
        checkoutPage.isPageOpen();
        Assert.assertEquals(checkoutPage.getPageTitle(), reader.getCheckoutPageTitle());
    }

    @Test  //Проверка возможности добавления товара в корзину и его удаления.
    public void testAddRemoveItem() {
        System.out.println("Test add and remove item");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.loginWithDefaultUser();
        productsPage.isPageOpen();

        String expectedProductName = productsPage.getName(5);
        String expectedDescription = productsPage.getDescription(5);
        String expectedPrice = productsPage.getPrice(5);

        productsPage.clickAddToCart(5);
        Assert.assertEquals(productsPage.numberCartBadge(), "1"); //Проверка отображения количества товаров в корзине.
        CartPage cartPage = productsPage.clickCartLink();
        cartPage.waitCartPageLoading();
        cartPage.isPageOpen();
        Assert.assertEquals(cartPage.getName(), expectedProductName); //Проверка правильности отображения названия товара.
        Assert.assertEquals(cartPage.getDescription(), expectedDescription); //Проверка правильности отображения описания товара.
        Assert.assertEquals(cartPage.getPrice(), expectedPrice); //Проверка правильности отображения цены товара.
        cartPage.clickRemoveButton(0);
        Assert.assertEquals(cartPage.getProductNamesList().size(), 0); //Проверка на отсутствие информации о товаре после его удаления.
    }
}
