package sites.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacebookHomePage extends FacebookHomePageAttributes {

    public FacebookHomePage(WebDriver driver) {
        super(driver);
    }

    public void navegar(String url) {
        $driver.get(url);
    }

    protected WebElement createNewAccountButton() {
        return elementFactory.getInteractableWebElement(By.xpath("//*[contains(.,'Create new account')]"));
    }

}
