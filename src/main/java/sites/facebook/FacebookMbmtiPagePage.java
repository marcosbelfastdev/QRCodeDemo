package sites.facebook;

import org.openqa.selenium.WebDriver;

public class FacebookMbmtiPagePage extends FacebookMbtmiPagePageAttributes {

    public FacebookMbmtiPagePage(WebDriver driver) {
        super(driver);
    }

    public void navegar(String url) {
        $driver.get(url);
    }


}
