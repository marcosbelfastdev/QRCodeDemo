package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Collections;

public class DriverManager {

    private WebDriver webDriver;

    public WebDriver launchBrowser(String browserType) {

        switch (browserType) {
            case BrowserType.CHROME:
                webDriver = launchChrome();
                break;
            case BrowserType.SAFARI:
                webDriver = launchSafari();
                break;
            case BrowserType.FIREFOX:
                webDriver = launchFirefox();
                break;
        }

        class Quit extends Thread {
            @Override
            public void run() {
                try {
                    webDriver.quit();
                } catch (Exception ignore) {

                }
            }
        }

        Runtime.getRuntime().addShutdownHook(new Quit());
        return webDriver;
    }

    public WebDriver launchChrome() {
        WebDriver webDriver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-notifications");
        options.addArguments("--incognito");
        options.addArguments("--ignore-certificate-errors");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        return webDriver;
    }

    public WebDriver launchSafari() {
        WebDriver webDriver;
        webDriver = new SafariDriver();
        return webDriver;
    }

    public WebDriver launchFirefox() {
        WebDriver webDriver;
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        return webDriver;
    }

    public WebDriver driver() {
        return webDriver;
    }

    public void quit() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

}
