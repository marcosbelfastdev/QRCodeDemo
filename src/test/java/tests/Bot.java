package tests;

import core.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import sites.tempMail.contants.Config;
import sites.tempMail.pages.TempMailPage;

import javax.mail.MessagingException;

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
        facebook.get("https://www.facebook.com");


        createDisposableEmail();

    }

    private void createDisposableEmail() {
        TempMailPage tempMailPage = new TempMailPage(email);
        tempMailPage.navegar(Config.HOME_URL);
        this.disposableEmail = tempMailPage.getDisposableEmail();
    }

    private void readFacebookOtp() {

    }

    @After
    public void quit() {
        facebookDriverManager.quit();
        emailDriverManager.quit();
    }
}
