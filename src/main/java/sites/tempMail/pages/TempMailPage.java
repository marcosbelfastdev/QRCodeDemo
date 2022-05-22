package sites.tempMail.pages;

import core.utils.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sites.tempMail.attributes.TempMailPageAttributes;

import java.util.List;

import static java.util.Objects.isNull;

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

    public TempMailPage waitforAnyMessages() {

        Timer timer = new Timer(30*1000);
        while (isNull(yourInboxIsEmpty())) {
            timer.sleep(1000);
        }

        return this;
    }

    public TempMailPage openLatestMessage() {
        openEmailLink().click();
        return this;
    }

    public String readOtp(String leftDelimiter, String rightDelimiter) {
        String otp = null;
        List<WebElement> emailMessageContents = emailMessageContents().findElements(By.xpath("//*"));
        for (WebElement singleContent : emailMessageContents) {
            String singleContentText = singleContent.getText();
            if (singleContentText.contains(leftDelimiter) && singleContentText.contains(rightDelimiter)) {
                otp = singleContentText.replace(leftDelimiter,"").replace(rightDelimiter,"").trim();
                break;
            }
        }
        return otp;
    }

}
