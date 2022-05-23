package sites.facebook;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class FacebookSignupPage extends FacebookSignupPageAttributes {

    public FacebookSignupPage(WebDriver driver) {
        super(driver);
    }

    public void navegar(String url) {
        $driver.get(url);
    }

    public FacebookSignupPage enterFirstName(String firstName) {
        firstName().sendKeys(firstName);
        return this;
    }

    public FacebookSignupPage enterLastName(String lastName) {
        lastName().sendKeys(lastName);
        return this;
    }

    public FacebookSignupPage enterEmail(String email) {
        mobileOrEmail().sendKeys(email);
        return this;
    }

    public FacebookSignupPage reenterEmail(String email) {
        emailConfirmation().sendKeys(email);
        return this;
    }

    public FacebookSignupPage enterPassword(String password) {
        newPassword().sendKeys(password);
        return this;
    }

    public FacebookSignupPage selectMonthOfBirth(LocalDate dateOfBirth) {
        monthSelector().selectByVisibleText(dateOfBirth.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        return this;
    }

    public FacebookSignupPage selectDayOfBirth(LocalDate dateOfBirth) {
        daySelector().selectByVisibleText(String.valueOf(dateOfBirth.getDayOfMonth()).trim());
        return this;
    }

    public FacebookSignupPage selectYearOfBirth(LocalDate dateOfBirth) {
        yearSelector().selectByVisibleText(String.valueOf(dateOfBirth.getYear()).trim());
        return this;
    }

    public FacebookSignupPage selectGender(int gender) {
        gender(gender).click();
        return this;
    }

    public FacebookSignupPage signUp() {
        signUpButton().submit();
        return this;
    }




}
