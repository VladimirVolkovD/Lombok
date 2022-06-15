package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SideMenu extends BasePage{

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutLink;

    public SideMenu(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public SideMenu isPageOpen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bm-menu-wrap")));
        return this;
    }

    public LoginPage clickLogoutLink() {
        logoutLink.click();
        return new LoginPage(driver);
    }
}
