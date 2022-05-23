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
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;

public class Bot {

    DriverManager facebookDriverManager = new DriverManager();
    DriverManager emailDriverManager = new DriverManager();
    public WebDriver facebook;
    public WebDriver email;

    String disposableEmail;

    @Before
    public void setup() {
        facebook = facebookDriverManager.launchChrome();
        email = emailDriverManager.launchChrome();
    }

    @Test
    public void doBotStuff() throws MessagingException, IOException, UnsupportedFlavorException {
        createFacebookAccount();
    }

    public void createFacebookAccount() throws IOException, UnsupportedFlavorException {

        LocalDate dateofBirth = sites.facebook.Config.DATE_OF_BIRTH;
        String email = createDisposableEmail();

        FacebookHomePage facebookHomePage = new FacebookHomePage(facebook);
        facebookHomePage.navegar(sites.facebook.Config.HOME_URL)
                        .signUp();

        String firstName = NamesGenerator.getRandomMaleFirstName();
        String lastName = NamesGenerator.getRandomLastName() + " " + NamesGenerator.getRandomLastName();
        String password = sites.facebook.Config.FIXED_PASSWORD;
        int gender = sites.facebook.Config.MALE;

        FacebookSignupPage facebookSignupPage = new FacebookSignupPage(facebook);
        facebookSignupPage
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .reenterEmail(email)
                .enterPassword(password)
                .selectMonthOfBirth(dateofBirth)
                .selectDayOfBirth(dateofBirth)
                .selectYearOfBirth(dateofBirth)
                .selectGender(gender);
        System.out.println(firstName + "," + lastName + ",[" + password + "]," + gender + "," + email + "," + dateofBirth);
        facebookSignupPage.signUp();

        // Confirm otp
        System.out.println("Confirm OTP");
    }

    private String createDisposableEmail() throws IOException, UnsupportedFlavorException {
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
