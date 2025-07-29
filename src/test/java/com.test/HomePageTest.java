package com.test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.UserData;

import java.io.IOException;

public class HomePageTest extends BaseTest{

    public HomePageTest() throws IOException {
    }


    @Test(priority =1)
    public void verifyCourseListTest(){
        loginPage.login(user.username, user.password);
        homePage.verifyCourseList();
    }

    @Test(priority = 2)
    public void VerifyAddToCartTest(){
        homePage.verfiyAddToCartFunctionality();
    }

    @Test(priority = 3)
    public void verifyRemoveFromCartHomeTest(){
        homePage.verfiyAddToCartFunctionality();
        homePage.verifyRemoveFromCartOnHomePage();
    }

    @Test(priority =4)
    public void verifyCartPageTest(){
        homePage.verfiyAddToCartFunctionality();
        homePage.verifyEnrollNowFunctionality();

    }
    @Test(priority = 5)
    public void verifyErrorMessageForEnrollTest(){
        homePage.verifyErrorMsgForEnrollNowFunctionality();
    }

    UserData user = JsonReader.readUserData(".//src//resources//user.json");
}

