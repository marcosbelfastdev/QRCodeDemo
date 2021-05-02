package tests;

import core.DriverManager;
import core.utils.Attempts;
import core.utils.QRCodeReader;
import core.utils.Timer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.base.erbium.BaseState;
import org.base.erbium.Common;
import org.base.erbium.EDriver;
import org.base.erbium.EElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sites.next.selenium.pages.HomePage;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;

import static core.utils.ImageHandling.getBufferedImage;
import static core.utils.Randoms.getRandomString;

public class NextQRCodeErbiumFramework {

    public WebDriver webDriver;
    EDriver browser;

    @Before
    public void setup() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-notifications");
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();

        BaseState baseState = new BaseState();
        baseState.setProjectName("QRCodeDemo");
        baseState.setEnvironment("Test");
        baseState.setTestName("Demo");
        baseState.setBaseStateUrl("https://path/to/next/webapp/");
        browser = baseState.execute(webDriver);
        //browser.manage().window().setPosition(new Point(600,1));
        //browser.manage().window().setSize(new Dimension(600,2000));
        browser.manage().deleteAllCookies();

        browser.setOption(Common.HIGHLIGHT_ELEMENTS);
        browser.setOption(Common.HIGHLIGHT_STYLE, "border: 4px solid red; border-radius: 5px;");
        browser.manage().scrollToElements(true);
    }

    @Test
    public void readQRCode() throws IOException {

        String returnedQrCodeString = new QRCodeReader()      // ler QR code
                .readQRCode(
                        getBufferedImage(browser.find(By.id("qrcode id")))
                );

        System.out.println("Obtido: " + returnedQrCodeString);
    }
}
