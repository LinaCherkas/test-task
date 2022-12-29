import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

abstract public class MainClass {
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        System.setProperty("chromeoptions.prefs", "intl.accept_languages=en-GB");
    }

    @Before
    public void init() {
        setUp();
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
