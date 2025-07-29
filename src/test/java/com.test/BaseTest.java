package com.test;

import com.microsoft.playwright.*;
import com.pages.*;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {

    Playwright playwright;
    Browser browser;
    Page page;
    Page newPage;
    BrowserContext context;
    protected LoginPage loginPage;
    protected Properties prop;
    protected BasePage basePage;
    protected HomePage homePage;
    protected ManageClassesPage manageClassesPage;
    protected ManageCategoryPage manageCategoryPage;

    @Parameters("browserName")
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("chrome")String browserName) throws FileNotFoundException {
       basePage = new BasePage();
       prop = basePage.init_prop();
        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }
        page = basePage.initBrowser(prop);

        context = page.context();
        loginPage = new LoginPage(page);
        homePage = new HomePage(page);
        manageClassesPage = new ManageClassesPage(page);
        manageCategoryPage = new ManageCategoryPage(page);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
      //  browser.close();
       context.close();
       page.close();
    }
}
