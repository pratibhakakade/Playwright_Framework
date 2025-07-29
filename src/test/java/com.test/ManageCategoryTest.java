package com.test;

import org.testng.annotations.Test;
import utils.JsonReader;
import utils.UserData;

import java.io.IOException;

public class ManageCategoryTest extends BaseTest{

    public ManageCategoryTest() throws IOException {
    }
    String CategoryName = "Java Automation PK";
    String updatedCategoryName = "Java Automation PK updated";

    @Test(priority = 1)
    public void verifyAddCategoryTest(){
        loginPage.login(user.adminUsername, user.adminPassword);
        manageCategoryPage.verifyAddNewCategory(CategoryName);
    }

    @Test(priority = 2)
   public void verifyEditCategoryTest(){
        String updatedCategoryName = "Java Automation PK updated";
       manageCategoryPage.verifyEditCategory(CategoryName, updatedCategoryName);
    }

    @Test(priority = 3)
    public void verifyDeleteCategoryTest() {
        manageCategoryPage.verifyDeleteCategory(updatedCategoryName);
   }


    UserData user = JsonReader.readUserData(".//src//resources//user.json");
}
