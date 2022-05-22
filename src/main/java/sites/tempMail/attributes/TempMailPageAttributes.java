package sites.tempMail.attributes;

import core.utils.ElementFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TempMailPageAttributes {

    protected WebDriver $driver;
    protected ElementFactory elementFactory;

    public TempMailPageAttributes(WebDriver driver) {
        $driver = driver;
        elementFactory = new ElementFactory(driver);
    }

    public WebElement emailField() {
        return elementFactory.getInteractableWebElement(By.id("email"));
    }

    public WebElement yourInboxIsEmpty() {
        return elementFactory.getInteractableWebElement(By.xpath("//*[contains(.,'Your inbox is empty')]"));
    }


}
