package env;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverUtil {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private static final String CHROME_DRIVER_PATH = "C:\\Users\\nithi\\Downloads\\chromedriver-win64\\chromedriver.exe";

    public static WebDriver getDefaultDriver() {
        if (driverThread.get() == null) {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriver driver = new ChromeDriver(chromeOptions);

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            driverThread.set(driver);
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
