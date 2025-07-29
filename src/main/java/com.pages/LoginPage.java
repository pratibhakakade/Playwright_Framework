package com.pages;
import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.FileNotFoundException;

public class LoginPage extends BasePage {

    private final Page page;

    // Locators
    private final String emailInput = "//input[@id='email1']";
    private final String passwordInput = "//input[@id='password1']";
    private final String loginButton = "button[type='submit']";
    private final String welcomeMessage = ".welcomeMessage";
    private final String signupLink = "//a[normalize-space()='New user? Signup']";
    private String interest = "(//label[normalize-space()='Playwright'])[1]";
    private String singUpMessage = "//div[contains(text(),'Signup successfully, Please login!')]";
    // Constructor
    public LoginPage(Page page) throws FileNotFoundException {
        super();
        this.page = page;
    }

    Faker faker = new Faker();
    String firstName = faker.name().firstName();

    public void verifyEmptyStringError(){
        page.click(loginButton);
    }

    public void verifyEmptyPasswordError(String email){
        page.locator(passwordInput).clear();
        page.fill(emailInput, email);
        page.click(loginButton);
    }

    public void verifyLoginWithInvalidCredentials(String email, String Password){
        page.fill(emailInput, email);
        page.fill(passwordInput, Password);
        page.click(loginButton);
    }

public void login(String email, String password) {
        page.fill(emailInput, email);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }

    public String getWelcomeText() {
        return page.locator(welcomeMessage).innerText();
    }

    public String getTitle() {
        return page.title();
    }

    public Locator getEmailPasswordError(){
        return page.locator("//h2[normalize-space()='Email and Password is required']");
    }

    public Locator getEmailPasswordNotMatchError(){
        return page.locator("(//h2[normalize-space()=\"Email and Password Doesn't match\"])[1]");
    }

    public Locator getEmptyPasswordError(){
        return page.locator("//h2[normalize-space()='Password is required']");
    }

    public Locator getLoginWelcomeMessage(){
        return page.locator(".welcomeMessage");
    }

    public Locator getSignUpMessage(){
        return page.locator(singUpMessage);
    }

    public void registerNewUser(){
        page.click(signupLink);
        page.fill("#name", firstName);
        page.fill("#email", firstName +"test@gmail.com");
        page.fill("#password", "test@1234");
        page.locator(interest).click();
        page.check("#gender2"); // Female
        page.selectOption("#state", "Maharashtra");
        page.selectOption("#hobbies", new String[]{"Playing", "Reading"});
        page.evalOnSelector(".submit-btn", "el => el.disabled = false");
        page.click(".submit-btn");
    }

    public void verifyContactLinks(){
        Locator contacntLinks = page.locator("//div[@class='social']//div[@class='social-btns']//a");
        for(int i=0; i < contacntLinks.count(); i++){
            contacntLinks.nth(i).click();
        }

        page.bringToFront();
        }
    }

