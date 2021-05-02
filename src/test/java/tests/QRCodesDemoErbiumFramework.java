package tests;

import core.utils.Attempts;
import core.utils.QRCodeReader;
import core.utils.Randoms;
import core.utils.Timer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.base.erbium.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;

import static core.utils.ImageHandling.getBufferedImage;
import static core.utils.Randoms.getRandomString;

public class QRCodesDemoErbiumFramework {

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
        baseState.setBaseStateUrl("https://www.patrick-wied.at/static/qrgen/");
        browser = baseState.execute(webDriver);
        //browser.manage().window().setPosition(new Point(600,1));
        //browser.manage().window().setSize(new Dimension(600,2000));
        browser.manage().deleteAllCookies();

        browser.setOption(Common.HIGHLIGHT_ELEMENTS);
        browser.setOption(Common.HIGHLIGHT_STYLE, "border: 4px solid red; border-radius: 5px;");
        browser.manage().scrollToElements(true);
    }

    @Test
    public void generateReadQrCodes() throws IOException {

        /*
        Primeiro, vamos gerar um qrcode no site e ler depois
         */
        Random random = new Random();

        Attempts attempts = new Attempts(5);
        while (attempts.hasMoreToGo()) {

            String randomString = getRandomString();

            browser.find(By.id("content")).setText(randomString); // definir texto to QR code
            browser.find(By.id("button"))
                    .setOption(Common.HIGHLIGHT_ELEMENTS, false).click(); // clicar em Generate

            EElement image = browser.find(By.id("qr-image"));
            Timer time = new Timer(10000);
            while (!image.highlight().getAttribute("alt").trim().contains(randomString)
                && !time.timedOut()) {
            }

            String returnedQrCodeString = null;
            time = new Timer(20000);
            while (!time.timedOut()) {
                returnedQrCodeString = new QRCodeReader().readQRCode(getBufferedImage(image));
                if (returnedQrCodeString!=null) break;
            }

            System.out.println("Informado: " + randomString + "\t Obtido: " + returnedQrCodeString);
            Assert.assertEquals(randomString, returnedQrCodeString);
        }

    }


}
