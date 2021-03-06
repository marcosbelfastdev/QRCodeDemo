package tests;

import core.DriverManager;
import core.utils.Attempts;
import core.utils.Randoms;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import sites.qrgen.selenium.pages.HomePage;

import java.io.IOException;

public class QRCodesDemoSelenium {

    DriverManager driverManager;
    public WebDriver browser;

    @Before
    public void setup() {
        driverManager = new DriverManager();

    }

    @Test
    public void generateReadQrCodes() throws IOException, InterruptedException {
        browser = driverManager.launchBrowser(BrowserType.FIREFOX);
        browser.get("about:blank");
        browser.get("https://patrick-wied.at/static/qrgen/");
        HomePage homePage = new HomePage(browser);

        Attempts attempts = new Attempts(5);
        while (attempts.hasMoreToGo()) {
            String randomString = Randoms.getRandomString();
            String returnedQrCodeString = homePage
                    .enterText(randomString)
                    .generateQrCode()
                    .waitQrCodeReady(randomString)
                    .readQrCode();
            Thread.sleep(200);
            System.out.println("Informado: " + randomString + "\t Obtido: " + returnedQrCodeString);
            Assert.assertEquals(randomString, returnedQrCodeString);
        }
    }

    @After
    public void quit() {
        driverManager.quit();
    }
}
