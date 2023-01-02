package ui;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


abstract public class MainClass {
    public static void setUp() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");
        System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");

        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        System.setProperty("chromeoptions.prefs", "intl.accept_languages=en-GB");
    }

    @BeforeAll
    public static void init() {
        setUp();
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
