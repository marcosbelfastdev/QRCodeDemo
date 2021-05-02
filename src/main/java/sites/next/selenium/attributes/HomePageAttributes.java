package sites.next.selenium.attributes;

import core.utils.ElementFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageAttributes {

    WebDriver $driver;
    ElementFactory elementFactory;

    public HomePageAttributes(WebDriver driver) {
        $driver = driver;
        elementFactory = new ElementFactory(driver);
    }

    protected WebElement qrCodeImage() {
        return elementFactory.getInteractableWebElement(By.id("qr-image"));
    }


}
