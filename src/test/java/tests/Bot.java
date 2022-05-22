package tests;

import core.DriverManager;
import core.utils.NamesGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import sites.facebook.FacebookHomePage;
import sites.facebook.FacebookSignupPage;
import sites.tempMail.contants.Config;
import sites.tempMail.pages.TempMailPage;

import javax.mail.MessagingException;
import java.time.LocalDate;

public class Bot {

    DriverManager facebookDriverManager = new DriverManager();
    DriverManager emailDriverManager = new DriverManager();
    public WebDriver facebook;
    public WebDriver email;

    String disposableEmail;

    @Before
    public void setup() {
        facebook = facebookDriverManager.launchFirefox();
        email = emailDriverManager.launchChrome();
    }

    @Test
    public void doBotStuff() throws MessagingException {
        createFacebookAccount();
    }

    public void createFacebookAccount() {

        LocalDate dateofBirth = sites.facebook.Config.DATE_OF_BIRTH;
        String email = createDisposableEmail();

        FacebookHomePage facebookHomePage = new FacebookHomePage(facebook);
        facebookHomePage.navegar(sites.facebook.Config.HOME_URL)
                        .signUp();

        FacebookSignupPage facebookSignupPage = new FacebookSignupPage(facebook);
        facebookSignupPage.enterFirstName(NamesGenerator.getRandomMaleFirstName())
                .enterLastName(NamesGenerator.getRandomLastName())
                .enterEmail(email)
                .reenterEmail(email)
                .enterPassword(sites.facebook.Config.FIXED_PASSWORD)
                .selectMonthOfBirth(dateofBirth)
                .selectDayOfBirth(dateofBirth)
                .selectYearOfBirth(dateofBirth)
                .selectGender(sites.facebook.Config.MALE)
                .signUp();

        // confirmar otp
    }

    private String createDisposableEmail() {
        TempMailPage tempMailPage = new TempMailPage(email);
        tempMailPage.navegar(Config.HOME_URL);
        this.disposableEmail = tempMailPage.getDisposableEmail();
        return disposableEmail;
    }

    private void readFacebookOtp() {

    }

    @After
    public void quit() {
        facebookDriverManager.quit();
        emailDriverManager.quit();
    }
}
