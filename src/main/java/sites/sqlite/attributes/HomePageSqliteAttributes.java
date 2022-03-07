package sites.sqlite.attributes;

import core.utils.ElementFactory;
import org.openqa.selenium.WebDriver;

public class HomePageSqliteAttributes {

    protected WebDriver $driver;
    protected ElementFactory elementFactory;

    public HomePageSqliteAttributes(WebDriver driver) {
        $driver = driver;
        elementFactory = new ElementFactory(driver);
    }



}
