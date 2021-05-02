package tests;

import core.utils.QRCodeReader;
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
        options.setExperimentalOption("useAutomationExtension", false);
        webDriver = new ChromeDriver(options);

        BaseState baseState = new BaseState();
        baseState.setProjectName("QRCodeDemo");
        baseState.setEnvironment("Test");
        baseState.setTestName("Demo");
        baseState.setBaseStateUrl("https://www.patrick-wied.at/static/qrgen/");
        browser = baseState.execute(webDriver);
        browser.manage().window().maximize();
        browser.manage().deleteAllCookies();

        browser.setOption(Common.SCROLL_TO_ELEMENTS);
        browser.setOption(Common.HIGHLIGHT_ELEMENTS);
        browser.setOption(Common.HIGHLIGHT_STYLE, "border: 3px solid purple; border-radius: 5px;");
    }

    @Test
    public void readQRCode() throws IOException {

        /*
        Primeiro, vamos gerar um qrcode no site e ler depois
         */
        Random random = new Random();

        int attempts = 0;
        while (attempts++ < 5) {

            String randomNumber = String.valueOf(random.nextLong());
            browser.find(By.id("content")).setText(randomNumber);
            browser.find(By.id("button")).click();

            // espera qrcode ser atualizado e destaca com highlight
            EElement image = browser.find(By.id("qr-image"));
            image.highlight();
            while (!image.getAttribute("alt").trim().contains(randomNumber)) {}

            // lê imagem como BufferedImage
            QRCodeReader qrCodeReader = new QRCodeReader();
            String returnedQrCodeString = qrCodeReader.readQRCode(getBufferedImage(image));

            System.out.println("Informado: " + randomNumber + "\t Obtido: " + returnedQrCodeString);
            Assert.assertEquals(randomNumber, returnedQrCodeString);
        }

    }


}
