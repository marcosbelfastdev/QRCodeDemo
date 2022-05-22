package sites.tempMail.pages;

import core.utils.Timer;
import org.openqa.selenium.WebDriver;
import sites.tempMail.attributes.TempMailPageAttributes;

public class TempMailPage extends TempMailPageAttributes {

    public TempMailPage(WebDriver driver) {
        super(driver);
    }

    public void navegar(String url) {
        $driver.get(url);
    }

    public String getDisposableEmail() {
        Timer timer = new Timer(30*1000);
        while (!emailField().getText().contains("@")) {
            timer.sleep(1000);
        }

        return emailField().getText();
    }

}
