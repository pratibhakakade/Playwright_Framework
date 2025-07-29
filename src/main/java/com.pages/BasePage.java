package com.pages;

import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class BasePage {
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    protected Page page;

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public Page initBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("browser name is : " + browserName);

        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(List.of("--window-size=1500,600")).setSlowMo(500)));
                break;
            case "edge":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
                break;
            default:
                System.out.println("please pass the right browser name......");
                break;
        }

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions().setViewportSize(1500, 755);
        tlBrowserContext.set(getBrowser().newContext(contextOptions));
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
    }

    public Properties init_prop() {
        Properties prop = new Properties();
        try (FileInputStream ip = new FileInputStream(".//src//resources//config.properties")) {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }

    public void closeBrowser() {
        if (getPlaywright() != null) {
            getPlaywright().close();
        }
    }
}