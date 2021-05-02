package sites.next.selenium.pages;

import core.utils.QRCodeReader;
import org.openqa.selenium.WebDriver;
import sites.next.selenium.attributes.HomePageAttributes;

import java.io.IOException;

import static core.utils.ImageHandling.getBufferedImage;

public class HomePage extends HomePageAttributes {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String readNextQrCode() throws IOException {
        return new QRCodeReader().readQRCode(getBufferedImage(qrCodeImage()));
    }




}
