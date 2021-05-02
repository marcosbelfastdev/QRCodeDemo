package sites.qrgen.selenium.pages;

import core.utils.QRCodeReader;
import core.utils.Timer;
import org.openqa.selenium.WebDriver;
import sites.qrgen.selenium.attributes.HomePageAttributes;

import java.io.IOException;

import static core.utils.ImageHandling.getBufferedImage;

public class HomePage extends HomePageAttributes {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage enterText(String text) {
        caixaTexto().clear();
        caixaTexto().sendKeys(text);
        return this;
    }

    public HomePage generateQrCode() {
        botaoGenerate().click();
        return this;
    }

    public HomePage waitQrCodeReady(String text) {

        class Timer {
            long $endTime;
            Timer(long timeout) {
                $endTime = System.currentTimeMillis() + timeout;
            }
            boolean timedOut() {
                return $endTime - System.currentTimeMillis() < 0;
            }

        }

        Timer time = new Timer(10000);
        while (!qrCodeImage().getAttribute("alt").trim().contains(text)
            && !time.timedOut()) {
        }
        return this;
    }

    public String readQrCode() throws IOException {
        String qrCode = null;
        Timer time = new Timer(20000);
        while (!time.timedOut()) {
            qrCode = new QRCodeReader().readQRCode(getBufferedImage(qrCodeImage()));
            if (qrCode!=null) break;
        }
        return qrCode;
    }




}
