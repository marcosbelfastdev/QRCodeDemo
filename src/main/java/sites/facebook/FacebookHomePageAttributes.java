package sites.facebook;

import core.utils.ElementFactory;
import org.openqa.selenium.WebDriver;

public class FacebookHomePageAttributes {

    protected WebDriver $driver;
    protected ElementFactory elementFactory;

    public FacebookHomePageAttributes(WebDriver driver) {
        $driver = driver;
        elementFactory = new ElementFactory(driver);
    }




}
