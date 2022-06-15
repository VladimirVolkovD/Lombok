package com.saucedemo.tests;

import com.saucedemo.User;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParameters extends BaseTest {

    @Test
    @Parameters({"username", "password", "expectedResult"})
    public void testParameters(@Optional("") String username,
                           @Optional("11111") String password,
                           @Optional("Epic sadface: Username is required") String expectedResult) {
        System.out.println("Test parameters");
        loginPage.openPage();
        loginPage.isPageOpen();
        loginPage.login(new User(username, password));
        loginPage.waitMessageFailedLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), expectedResult);
    }
}
