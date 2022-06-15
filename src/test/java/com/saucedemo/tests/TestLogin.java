package com.saucedemo.tests;

import com.saucedemo.User;
import com.saucedemo.pages.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {

    @Test
    public void testSuccessLogin() {
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.loginWithDefaultUser();
        productsPage.waitPageTitleLoading();
        productsPage.isPageOpen();
        Assert.assertEquals(productsPage.getPageTitle(),reader.getProductsPageTitle());
    }

    @Test
    public void testEmptyPassword() {
        System.out.println("Test empty password");
        loginPage.openPage();
        loginPage.isPageOpen();
        loginPage.setUserName(reader.getUsername()).setPassword("").clickLogin();
        loginPage.waitMessageEmptyPassword();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
    public void testFailedLogin() {
        System.out.println("Test failed login");
        loginPage.openPage();
        loginPage.isPageOpen();
        loginPage.login(new User("aaaa", "1111"));
        loginPage.waitMessageFailedLogin();
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testEmptyUserName() {
        System.out.println("Test empty username");
        loginPage.openPage();
        loginPage.isPageOpen();
        loginPage.login(new User("", reader.getPassword()));
        loginPage.waitMessageEmptyUser();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test
    public void testLockedOutUser() {
        System.out.println("Test locked out user");
        loginPage.openPage();
        loginPage.isPageOpen();
        loginPage.login(new User("locked_out_user", reader.getPassword()));
        loginPage.waitMessageLockedOutUser();
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void testPerformanceGlitchUser() {
        System.out.println("Test performance glitch user");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.login(new User("performance_glitch_user", reader.getPassword()));
        productsPage.waitProductPageLoading();
        productsPage.isPageOpen();
        Assert.assertEquals(productsPage.getPageTitle(), reader.getProductsPageTitle());
    }
}
