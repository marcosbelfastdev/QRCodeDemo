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

    public WebElement inboxDataList() {
        return elementFactory.getInteractableWebElement(By.xpath("//inbox-dataList"));
    }

    public WebElement latestEmailItem() {
        return inboxDataList().findElements(By.tagName("li")).get(0);
    }

    public WebElement openEmailLink() {
        return latestEmailItem().findElement(By.xpath("//href"));
    }

    public WebElement emailMessageContents() {
        return elementFactory.getWebElement(By.xpath("//div[@class='inbox-data-content-intro']"));
    }

}
