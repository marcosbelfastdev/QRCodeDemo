package sites.facebook;

import core.utils.ElementFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacebookHomePageAttributes {

    protected WebDriver $driver;
    protected ElementFactory elementFactory;

    public FacebookHomePageAttributes(WebDriver driver) {
        $driver = driver;
        elementFactory = new ElementFactory(driver);
    }

    protected WebElement createNewAccountButton() {
        return elementFactory.getInteractableWebElement(By.xpath("//*[contains(.,'Create new account')]"));
    }


}
