package sites.facebook;

import core.utils.ElementFactory;
import org.openqa.selenium.WebDriver;

public class FacebookMbtmiPagePageAttributes {

    protected WebDriver $driver;
    protected ElementFactory elementFactory;

    public FacebookMbtmiPagePageAttributes(WebDriver driver) {
        $driver = driver;
        elementFactory = new ElementFactory(driver);
    }




}
