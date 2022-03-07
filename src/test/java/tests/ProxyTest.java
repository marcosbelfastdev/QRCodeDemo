package tests;

import core.DriverManager;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ProxyTest {

    DriverManager driverManager;
    public WebDriver browser;

    @Before
    public void setup() {
        driverManager = new DriverManager();

    }

    @Test
    public void testProxyInterruption() throws Exception {

        final int DEFAULT_LATENCY = 0;
        final int INFINITE_LATENCY = 999999;
        final int PROXY_PORT = 8182;

        BrowserMobProxyServer server = new BrowserMobProxyServer();
        server.setRequestTimeout(15, TimeUnit.SECONDS);
        server.start(PROXY_PORT);

        Proxy client = new Proxy();
        client.setProxyType(Proxy.ProxyType.MANUAL);
        client.setHttpProxy("127.0.0.1:" + PROXY_PORT);

        browser = driverManager.launchBrowser(BrowserType.CHROME);

        // Navigate normally
        browser.navigate().to("http://www.bbc.co.uk/");

        // Set high latency to break connection
        server.setLatency(INFINITE_LATENCY, TimeUnit.MILLISECONDS);

        // Fail to navigate
        browser.navigate().to("http://www.cnn.com/");
        Thread.sleep(20000);

        // Remove proxy-added latency and navigate again
        server.setLatency(DEFAULT_LATENCY, TimeUnit.MILLISECONDS);
        server.setRequestTimeout(60, TimeUnit.SECONDS);
        browser.navigate().to("http://www.theguardian.co.uk/");

    }

    @After
    public void quit() {
        driverManager.quit();
    }
}
