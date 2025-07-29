package com.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class HomePage extends BasePage {

    private String courseList = "//div[@class='course-card row']//h2";
    private String addToCartBtn = "//button[contains(text(),'Add to Cart')]";
    private String cartBtn = "//button[@class='cartBtn']";
    private String enrollNowBtn = "//button[normalize-space()='Enroll Now']";
    private String price = "#cardChip";
    private String removeFromCartBtn = "//button[normalize-space()='Remove from Cart']";
   // private String courseName = ".name";
    private String enrollPopup = "//div[@class='modal-content']";
    private String address = "//textarea[@id='address']";
    private String phone = "#phone";
    private String cancelBtn = "//button[normalize-space()='Cancel']";
    private String EnrollBtn = "//button[@class='action-btn']";
    private String orderId = "//h4[@class='uniqueId']";
    private String errorMsg = "//div[@class='Toastify']";


    public HomePage(Page page){
        super();
        this.page = page;
    }

    public void verifyCourseList(){

        page.waitForSelector(courseList);
        int courseCount = page.locator(courseList).count();
        System.out.println("Total courses available:" + courseCount);
        for(int i=0; i < courseCount; i++){
            String courseName = page.locator(courseList).nth(i).innerText();
            System.out.println("Course" + (i+1) + ":" + courseName);
        }
    }

    public void verifyRemoveFromCartOnHomePage(){
        int courseCount = page.locator(courseList).count();
        for(int i=0; i < courseCount; i++){
            String name = page.locator(courseList).nth(i).innerText();
            System.out.println(name);
            if(name.contains("SELENIUM")) {
                page.locator(addToCartBtn).nth(i).click();
                page.locator(removeFromCartBtn).first().click();
            }
        }
    }

    public void verfiyAddToCartFunctionality(){
        int courseCount = page.locator(courseList).count();
       // System.out.println("Total courses available:" + courseCount);
        for(int i=0; i < courseCount; i++){
            String name = page.locator(courseList).nth(i).innerText();
            System.out.println(name);
            if(name.contains("SELENIUM")) {
                page.locator(addToCartBtn).nth(i).click();
            }
        }
    }

    public void  verifyEnrollNowFunctionality() {
        page.locator(cartBtn).click();
        page.waitForSelector(enrollNowBtn);
        page.locator(enrollNowBtn).click();
        page.waitForSelector(enrollPopup);
            page.locator(enrollPopup).click();
            page.locator(address).fill("mumbai");
            page.locator(phone).fill("9876543210");
            page.locator(EnrollBtn).click();
            String orderIdText = page.locator(orderId).innerText();
            System.out.println("Order ID: " + orderIdText);
    }
        public void  verifyErrorMsgForEnrollNowFunctionality(){

            page.waitForSelector(enrollNowBtn);
            page.waitForSelector(enrollPopup);
            page.locator(enrollPopup).click();
                page.locator(EnrollBtn).click();
                String errorText = page.locator(errorMsg).innerText();
                System.out.println("Error Message:" + errorText);
                PlaywrightAssertions.assertThat(page.locator(errorMsg)).containsText(errorText);
    }
}
