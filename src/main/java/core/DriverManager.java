package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.base.erbium.BaseState;
import org.base.erbium.EDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

public class DriverManager {

    public WebDriver webDriver;
    public EDriver browser;

    public WebDriver launchBrowser() {

        if (webDriver != null)
            return webDriver;

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-notifications");
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();

        class Quit extends Thread {
            @Override
            public void run() {
                if (webDriver != null)
                    webDriver.quit();
            }
        }

        Runtime.getRuntime().addShutdownHook(new Quit());
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
