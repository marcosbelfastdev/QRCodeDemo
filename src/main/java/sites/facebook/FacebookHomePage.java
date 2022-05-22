package sites.facebook;

import org.openqa.selenium.WebDriver;

public class FacebookHomePage extends FacebookHomePageAttributes {

    public FacebookHomePage(WebDriver driver) {
        super(driver);
    }

    public FacebookHomePage navegar(String url) {
        $driver.get(url);
        return this;
    }

    public FacebookHomePage signUp() {
        createNewAccountButton().click();
        return this;
    }

}
