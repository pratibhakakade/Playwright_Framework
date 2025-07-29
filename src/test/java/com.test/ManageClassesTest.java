package com.test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.pages.ManageClassesPage;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.UserData;

import java.io.IOException;

public class ManageClassesTest extends BaseTest{

    public ManageClassesTest() throws IOException {
    }

    @Test(priority = 1)
    public void verifyAddCourseTest(){
        loginPage.login(user.adminUsername, user.adminPassword);
        manageClassesPage.verifyAddCourseFunctionality();
    }
    @Test(priority = 2)
    public void verifyPageElementsTest(){
        manageClassesPage.verifyAllPageElements();
    }

    @Test(priority = 3)
    public void verifyDownloadCourseTest(){
        manageClassesPage.verifyDownloadFunctionality();
    }


    @Test(priority = 4)
            public void verifyRequiredFieldsTest(){
        manageClassesPage.verifyErrorMessageforRequiredFields();
        PlaywrightAssertions.assertThat(manageClassesPage.getErrorMessageforRequiredFields()).containsText("Please fill all the fields");
        manageClassesPage.cancelButtonTest().click();
    }

    @Test(priority = 5)
            public void verifySortByNameTest(){
        manageClassesPage.verifySortByName();
    }

    @Test(priority = 6)
            public void verifySortByPriceTest(){
        manageClassesPage.verifySortByPrice();
    }

    @Test(priority = 7)
            public void verifySortByInstructorTest(){
        manageClassesPage.verifySortByInstructorName();
    }

    @Test(priority = 8)
            public void verifyAddingExistingCourseTest(){
        manageClassesPage.verifyCourseIsAlreadyPresent();
        PlaywrightAssertions.assertThat(manageClassesPage.getCourseAlreayExisitError()).containsText("Course with this name already exists");
        manageClassesPage.cancelButtonTest().click();
    }

    @Test(priority = 9)
    public void verifyDeleteCourseTest() throws InterruptedException {
        manageClassesPage.verifyDeleteFunctionality();
    }

    UserData user = JsonReader.readUserData(".//src//resources//user.json");
}
