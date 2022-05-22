package sites.facebook;

import core.utils.ElementFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FacebookSignupPageAttributes {

    protected WebDriver $driver;
    protected ElementFactory elementFactory;

    public FacebookSignupPageAttributes(WebDriver driver) {
        $driver = driver;
        elementFactory = new ElementFactory(driver);
    }

    protected WebElement firstName() {
        return elementFactory.getInteractableWebElement(By.name("firstname"));
    }

    protected WebElement lastName() {
        return elementFactory.getInteractableWebElement(By.name("lastname"));
    }

    protected WebElement mobileOrEmail() {
        return elementFactory.getInteractableWebElement(By.name("reg_email__"));
    }

    protected WebElement emailConfirmation() {
        return elementFactory.getInteractableWebElement(By.name("reg_email_confirmation__"));
    }

    protected WebElement newPassword() {
        return elementFactory.getInteractableWebElement(By.id("password_step_input"));
    }

    protected Select monthSelector() {
        WebElement selectElement = elementFactory.getWebElement(By.id("month"));
        return new Select(selectElement);
    }

    protected Select daySelector() {
        WebElement selectElement = elementFactory.getWebElement(By.id("month"));
        return new Select(selectElement);
    }

    protected Select yearSelector() {
        WebElement selectElement = elementFactory.getWebElement(By.id("month"));
        return new Select(selectElement);
    }

    protected WebElement gender(int gender) {
        WebElement genderRadio = elementFactory
                .getInteractableWebElement(By.xpath("//*[@name='sex' and @value=" + gender + "]"));
        return genderRadio;
    }

    protected WebElement signUpButton() {
        return elementFactory.getInteractableWebElement(By.name("websubmit"));
    }


}
