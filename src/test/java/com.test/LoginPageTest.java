package com.test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.pages.BasePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.pages.LoginPage;
import utils.JsonReader;
import utils.UserData;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginPageTest extends BaseTest{

    public LoginPageTest() throws IOException {
    }

    @Test(priority = 1)
    public void verifyEmptyStringErrorTest(){
        loginPage.verifyEmptyStringError();
        PlaywrightAssertions.assertThat(loginPage.getEmailPasswordError())
                .containsText("Email and Password is required");
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentialsTest(){
        loginPage.verifyLoginWithInvalidCredentials(user.username, user.InvalidPass);
        PlaywrightAssertions.assertThat(loginPage.getEmailPasswordNotMatchError())
                .containsText("Email and Password Doesn't match");
    }
    @Test(priority  =3)
    public void verifyEmptyPasswordErrorTest(){
        loginPage.verifyEmptyPasswordError(user.username);
        PlaywrightAssertions.assertThat(loginPage.getEmptyPasswordError())
                .containsText("Password is required");
    }

    @Test(priority = 4)
    public void verifyConactLinksTest(){
        loginPage.verifyContactLinks();
    }

    @Test(priority =5 )
    public void VerifyLoginTest(){
        loginPage.login(user.username, user.password);
        PlaywrightAssertions.assertThat(loginPage.getLoginWelcomeMessage())
                .containsText("Welcome");
        }

    UserData user = JsonReader.readUserData( ".//src//resources//user.json");
    }

