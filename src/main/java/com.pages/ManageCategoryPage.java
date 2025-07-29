package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import com.microsoft.playwright.options.WaitForSelectorState;


public class ManageCategoryPage extends BasePage {

    public ManageCategoryPage(Page page) {
        super();
        this.page = page;
    }

    private String manageCategoryMenu = "//a[normalize-space()='Manage Categories']";
    private String manageLink = "//span[normalize-space()='Manage']";
    private String addNewCategoryButton = "div[class='manage-btns'] button";



    public void verifyAddNewCategory(String CategoryName ) {
        Page newTab = getManageCategoryPage(page, manageCategoryMenu); // or use a specific selector
        System.out.println("New tab URL: " + newTab.url());
        newTab.onDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            Assert.assertEquals(dialog.message(), "Enter a Category Name");
            dialog.accept(CategoryName);
        });
        newTab.locator(addNewCategoryButton).click();
    }

    public void verifyEditCategory(String CategoryName, String updatedCategoryName) {
        Page newTab = getManageCategoryPage(page, manageCategoryMenu); // or use a specific selector
        System.out.println("New tab URL: " + newTab.url());
        newTab.onDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            if (dialog.message().equals("Update the category")) {
                dialog.accept(updatedCategoryName);
            }
        });
        String row = "xpath=//td[contains(text(),'"+CategoryName+"')]//ancestor::tr//td[last()]//button//img";
        Locator updateButton = newTab.locator(row);
        System.out.println("Update button locator: " + updateButton.count());
        updateButton.scrollIntoViewIfNeeded();
        updateButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        updateButton.click();
    }

    public void verifyDeleteCategory(String updatedCategoryName ) {
        Page newTab = getManageCategoryPage(page, manageCategoryMenu); // or use a specific selector
        System.out.println("New tab URL: " + newTab.url());
        newTab.onDialog(dialog -> {
           System.out.println("Dialog message: " + dialog.message());
           if (dialog.message().equals("Are you sure you want to delete this category?")) {
             dialog.accept();
         }
       });
        String delete = "xpath=(//td[contains(text(), '"+updatedCategoryName+"')]//ancestor::tr//td//button//img)[1]";
        Locator deleteButtonLocator = newTab.locator(delete);
        System.out.println("Update button locator: " + deleteButtonLocator.count());
        deleteButtonLocator.scrollIntoViewIfNeeded();
        deleteButtonLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        deleteButtonLocator.click();
        newTab.locator("//button[@class='action-btn'][normalize-space()='Delete']").click();
    }

    public Page getManageCategoryPage(Page currentPage, String pageLink) {
        page.locator(manageLink).click();
        Page newTab = currentPage.waitForPopup(() -> {
            currentPage.locator(pageLink).click();
        });
        newTab.waitForLoadState();
        return newTab;
    }
}





