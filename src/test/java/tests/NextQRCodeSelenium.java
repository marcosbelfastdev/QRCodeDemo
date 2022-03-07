package tests;

import core.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import sites.next.selenium.pages.HomePage;

import java.io.IOException;

public class NextQRCodeSelenium {

    DriverManager driverManager;
    public WebDriver browser;

    @Before
    public void setup() {
        driverManager = new DriverManager();
        browser = driverManager.launchBrowser(BrowserType.SAFARI);
    }

    @Test
    public void readNextQrCode() throws IOException {
        browser.get("about:blank");
        JavascriptExecutor js = (JavascriptExecutor)browser;
        js.executeScript("CertificateWarningController.visitInsecureWebsiteWithTemporaryBypass()");
        browser.get("https://path/to/next/wbeapp/");
        HomePage homePage = new HomePage(browser);

        String returnedQrCodeString = homePage.readNextQrCode();
        System.out.println("Obtido: " + returnedQrCodeString);
    }

    @After
    public void quit() throws InterruptedException {
        Thread.sleep(1000 * 5 * 60);
        driverManager.quit();
    }
}
