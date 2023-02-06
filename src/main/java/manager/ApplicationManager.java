package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;
    //  EventFiringWebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        if (browser.equals(Browser.CHROME.browserName())) {
            wd = new ChromeDriver();
            logger.info("Start Chrome");
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            wd = new FirefoxDriver();
            logger.info("Start Chrome");
        } else if (browser.equals(Browser.IE.browserName())) {
            wd = new InternetExplorerDriver();
            logger.info("Start IE");
        }


        WebDriverListener listener = new ListenerWD();
        wd = new EventFiringDecorator<>(listener).decorate(wd);


        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro.web.app");
        logger.info("Current URL -->" + wd.getCurrentUrl());

        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);

    }

    public void stop() {

        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }
}

