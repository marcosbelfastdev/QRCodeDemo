package tests;

import core.utils.QRCodeReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.base.erbium.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Random;

import static core.utils.EElementImage.getBufferedImage;

public class QRCodeDemo {

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
    }

    @Test
    public void readQRCode() throws IOException {

        /*
        Primeiro, vamos gerar um qrcode no site e ler depois
         */
        Random random = new Random();

        int attempts = 0;
        while (attempts++ < 5) {

            int length = 30;
            boolean useLetters = true;
            boolean useNumbers = true;
            String randomString = RandomStringUtils.random(length, useLetters, useNumbers);


            browser.find(By.id("content")).setText(randomString); // definir texto to QR code
            browser.find(By.id("button"))
                    .setOption(Common.HIGHLIGHT_ELEMENTS, false).click(); // clicar em Generate

            EElement image = browser.find(By.id("qr-image"));
            image.highlight();                                    // destacar QR Code
            while (!image.getAttribute("alt").trim().contains(randomString)) {

            }

            String returnedQrCodeString = new QRCodeReader()      // ler QR code
                    .readQRCode(
                            getBufferedImage(image)
                    );

            System.out.println("Informado: " + randomString + "\t Obtido: " + returnedQrCodeString);
            Assert.assertEquals(randomString, returnedQrCodeString);
        }

    }


}
