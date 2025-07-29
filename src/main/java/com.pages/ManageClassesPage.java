package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Path;

import static java.lang.Thread.sleep;

public class ManageClassesPage extends BasePage{

    public ManageClassesPage(Page page){
        super();
        this.page = page;
    }

    private String manageCoursesMenu = "//a[normalize-space()='Manage Courses']";
    private String manageCategoryMenu = "//a[normalize-space()='Manage Categories']";
    private String manageLink = "//span[normalize-space()='Manage']";
    private String searchInput = "//input[@name='searchText']";
    private String sortByDropdown = "//select[@name='sortField']";
    private String downloadIcon = "//img[@alt='download']";
    private String deleteButton = "//button[@class='action-btn delete-btn']";
    private String addNewCourseButton = "//button[normalize-space()='Add New Course']";
    private String uploadFileButton = "//input[@type='file']";
    private String courseName = "//input[@name='name']";
    private String courseDescription = "//textarea[@name='description']";
    private String instructorName = "#instructorNameId";
    private String coursePrice = "#price";
    private String startDate = "//input[@name='startDate']";
    private String endDate = "//input[@name='endDate']";
    private String permanentCheckbox = "#isPermanent";
    private String selectCategory = "//div[normalize-space()='Select Category']";
    private String selectCategoryOption = "//button[normalize-space()='Python']";
    private String selectCategoryOption1 = "//button[normalize-space()='Playwright By MS']";
    private String saveButton = "//button[normalize-space()='Save']";
    private String cancelButton = "//button[normalize-space()='Cancel']";
    private String closeButton = "//button[@class='btn-close']";
    private String addCoursePopup = "//div[@class='modal-content']";
    private String courseErrorMessage = "//h2[normalize-space()='Please fill all the fields']";
    private String recordRows = "//table[@class='courses-table table table-borderless']//tr";
    private String deleteCourseButton = "//button[normalize-space()='Delete Courses']";
    private String activateCourseButton = "//button[normalize-space()='Activate Courses']";
    private String deactivateCourseButton = "//button[normalize-space()='Deactivate Courses']";
    private String loadMoreButton = "//button[normalize-space()='Load More']";


    public void verifyAllPageElements(){
        page.locator(manageLink).click();
        page.waitForSelector(manageCoursesMenu).click();
        page.locator(searchInput).isVisible();
        page.locator(sortByDropdown).isVisible();
        page.locator(addNewCourseButton).isVisible();
        page.locator(deleteCourseButton).isVisible();
        page.locator(activateCourseButton).isVisible();
        page.locator(deactivateCourseButton).isVisible();
        page.locator(loadMoreButton).isVisible();

    }
    public void verifySortByName(){
        page.locator(manageLink).click();
        page.waitForSelector(manageCoursesMenu);
        page.locator(manageCoursesMenu).click();
        page.locator(sortByDropdown).selectOption("Name");
    }

    public void verifySortByInstructorName(){
        page.locator(manageLink).click();
        page.waitForSelector(manageCoursesMenu);
        page.locator(manageCoursesMenu).click();
        page.locator(sortByDropdown).selectOption("Instructor Name");
    }

    public void verifySortByPrice(){
        page.locator(manageLink).click();
        page.waitForSelector(manageCoursesMenu);
        page.locator(manageCoursesMenu).click();
        page.locator(sortByDropdown).selectOption("Price");
    }


    public void verifyAddCourseFunctionality(){
        page.locator(manageLink).click();
        page.locator(manageCoursesMenu).click();
        page.locator(addNewCourseButton).click();
        page.locator(addCoursePopup).click();
           page.locator(uploadFileButton).setInputFiles(Path.of("C://Users//PratibhaKakade//IdeaProjects//PlayWright_Framework//src//resources//Playwright.png"));
            page.locator(courseName).fill("Test Playwright Course PK");
            page.locator(courseDescription).fill("This is a test course for playwright automation testing.");
            page.locator(instructorName).fill("Pratibha K");
            page.locator(instructorName).dblclick();
            page.locator(coursePrice).fill("2000");
            page.locator(startDate).fill("08/28/2025");
            page.locator(endDate).fill("09/28/2025");
            page.locator(selectCategory).click();
            page.locator(selectCategoryOption1).click();
            page.locator(saveButton).click();

    }

    public void verifyDownloadFunctionality(){
        page.locator(manageLink).click();
        page.waitForSelector(manageCoursesMenu);
        page.locator(manageCoursesMenu).click();
        page.locator(searchInput).fill("Test Playwright Course PK");
        page.locator(downloadIcon).click();
        page.goBack();
    }

    public void verifyDeleteFunctionality() throws InterruptedException {
        page.locator(manageLink).click();
        page.waitForSelector(manageCoursesMenu);
        page.locator(manageCoursesMenu).click();
        page.waitForSelector(searchInput);
        page.locator(searchInput).fill("Test Playwright Course PK");
        sleep(2000);
        page.locator(deleteButton).click();
    }

    public void verifyErrorMessageforRequiredFields(){
        page.locator(manageLink).click();
        page.waitForSelector(manageCoursesMenu);
        page.locator(manageCoursesMenu).click();
        page.locator(addNewCourseButton).click();
        page.locator(saveButton).click();
    }

    public Locator getErrorMessageforRequiredFields() {
        return page.locator(courseErrorMessage);
    }

    public Locator cancelButtonTest(){
        return page.locator(cancelButton);
    }

    public void verifyCourseIsAlreadyPresent(){
        page.locator(manageLink).click();
        page.locator(manageCoursesMenu).click();
        page.locator(addNewCourseButton).click();
        page.locator(addCoursePopup).click();
        page.locator(uploadFileButton).setInputFiles(Path.of("C://Users//PratibhaKakade//IdeaProjects//PlayWright_Framework//src//resources//Playwright.png"));
        page.locator(courseName).fill("Selenium For Web Automation");
        page.locator(courseDescription).fill("This is a test course for playwright automation testing.");
        page.locator(instructorName).fill("Pratibha");
        page.locator(instructorName).dblclick();
        page.locator(coursePrice).fill("1500");
        page.locator(selectCategory).click();
        page.locator(selectCategoryOption1).click();
        page.locator(saveButton).click();
    }

    public Locator getCourseAlreayExisitError(){
        return page.locator("(//h2[normalize-space()='Course with this name already exists'])[1]");

    }

}
