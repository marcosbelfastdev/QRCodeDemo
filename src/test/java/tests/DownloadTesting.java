package tests;

import core.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import sites.sqlite.pages.DownloadPageSqlite;

public class DownloadTesting {

    DriverManager driverManager;
    public WebDriver browser;

    @Before
    public void setup() {
        driverManager = new DriverManager();

    }

    @Test
    public void downloadAFile() {
        browser = driverManager.launchBrowser(BrowserType.CHROME);
        DownloadPageSqlite downloadPageSqlite = new DownloadPageSqlite(browser);
        downloadPageSqlite.navegar("https://www.jetbrains.com/pt-br/idea/download/other.html");
        downloadPageSqlite.downloadFile();
    }

    @After
    public void quit() {
        driverManager.quit();
    }
}
