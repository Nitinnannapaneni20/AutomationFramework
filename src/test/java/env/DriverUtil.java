package env;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverUtil {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private static final Logger LOGGER = Logger.getLogger(DriverUtil.class.getName());

    public static WebDriver getDefaultDriver() {
        if (driverThread.get() == null) {
            String browser = System.getProperty("browser", "chrome"); // Default to chrome if no property is set

            WebDriver driver = null;
            try {
                switch (browser.toLowerCase()) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver = new FirefoxDriver(firefoxOptions);
                        LOGGER.info("Firefox driver initialized successfully");
                        break;
                    case "chrome":
                    default:
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driver = new ChromeDriver(chromeOptions);
                        LOGGER.info("Chrome driver initialized successfully");
                        break;
                }

                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driverThread.set(driver);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to initialize the driver for " + browser, e);
                throw e;
            }
        }
        return driverThread.get();
    }

    public static void closeDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}
