package com.saucedemo.tests;

import com.saucedemo.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import utils.PropertyReader;

@Listeners(TestListener.class)
public class BaseTest {

    private WebDriver driver;
    protected LoginPage loginPage;
    protected PropertyReader reader;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        reader = new PropertyReader();
        System.out.println("-----Before Method-----");
    }

    @AfterMethod(alwaysRun=true)
    public void closeBrowser() {
        driver.quit();
        System.out.println("-----After Method-----");
    }

    @BeforeClass
    void beforeClass() {
        System.out.println("-----Before Class-----");
    }

    @AfterClass
    void afterClass() {
        System.out.println("-----After Class-----");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("-----Before Test-----");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("-----After Test-----");
    }

    @BeforeSuite
    public void beforeSuit() {
        System.out.println("-----Before Suit-----");
    }

    @AfterSuite
    public void afterSuit() {
        System.out.println("-----After Suit-----");
    }
}
