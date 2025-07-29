package com.test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{

    public RegistrationTest(){

    }

    @Test
    public void verifyRegistrationTest(){
        loginPage.registerNewUser();
        PlaywrightAssertions.assertThat(loginPage.getSignUpMessage()).containsText("Signup successfully");
    }
}
