package com.saucedemo.tests;

import com.saucedemo.User;
import com.saucedemo.pages.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRetry extends BaseTest {

    @Test(timeOut = 5530, retryAnalyzer = Retry.class)
    public void testRetry() {
        System.out.println("Test retry");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.login(new User("performance_glitch_user", reader.getPassword()));
        productsPage.waitProductPageLoading();
        productsPage.isPageOpen();
        Assert.assertEquals(productsPage.getPageTitle(), reader.getProductsPageTitle());
    }
}
