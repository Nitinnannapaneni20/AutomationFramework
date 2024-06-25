package pages;

import env.DriverUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LandingPageValidation {
    public static void validate(){
        WebDriver driver = DriverUtil.getDefaultDriver();
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals("Validation failed: The page title is not 'rs'.", "Raising Solution || Software Solution", title);


    }
}
