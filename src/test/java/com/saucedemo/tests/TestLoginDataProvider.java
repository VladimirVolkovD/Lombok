package com.saucedemo.tests;

import com.saucedemo.User;
import com.saucedemo.pages.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLoginDataProvider extends BaseTest {

    @DataProvider(name = "Correct data")
    public Object[][] inputCorrectData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "PRODUCTS"},
                {"problem_user", "secret_sauce",  "PRODUCTS"},
                {"performance_glitch_user", "secret_sauce",  "PRODUCTS"}
        };
    }

    @Test(dataProvider = "Correct data")
    public void testLoginCorrectData(String username, String password, String expectedResult) {
        System.out.println("Test correct data");
        loginPage.openPage();
        loginPage.isPageOpen();
        ProductsListPage productsPage = loginPage.login(new User(username, password));
        productsPage.waitProductPageLoading();
        productsPage.isPageOpen();
        Assert.assertEquals(productsPage.getPageTitle(), reader.getProductsPageTitle(), expectedResult);
    }

    @DataProvider(name = "Incorrect data")
    public Object[][] inputIncorrectData() {
        return new Object[][] {
                {"locked_out_user", "secret_sauce",  "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"qwer", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "1111", "Epic sadface: Username and password do not match any user in this service"},
                {"qwer", "1111", "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"},
                {"     ", "     ", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Incorrect data")
    public void testLoginIncorrectData(String username, String password, String expectedResult) {
        System.out.println("Test incorrect data");
        loginPage.openPage();
        loginPage.isPageOpen();
        loginPage.login(new User(username, password));
        loginPage.waitMessageFailedLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), expectedResult);
    }
}
