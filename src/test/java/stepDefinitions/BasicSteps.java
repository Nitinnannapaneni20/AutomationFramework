package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import env.DriverUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LandingPageValidation;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BasicSteps {

    WebDriver driver = DriverUtil.getDefaultDriver();
    Properties properties = new Properties();
    private String terminalOutput = "";

    public BasicSteps() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("env.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find env.properties");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Given("User opens the browser and navigates to the URL")
    public void user_opens_the_browser_and_navigates_to_the_url() {
        String url = properties.getProperty("url");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL not specified in the env.properties file.");
        }
        driver.get(url);

        // Validate the title
        LandingPageValidation.validate();
    }

    @Then("User prints success")
    public void user_prints_success() {
        System.out.println("Success");
        DriverUtil.closeDriver();
    }

    @Given("User opens the terminal and prints a message")
    public void userOpensTheTerminalAndPrintsAMessage() {
        try {
            // Execute a command in the terminal (cmd.exe for Windows)
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", "echo Hello, Terminal!");

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            terminalOutput = output.toString().trim(); // Store the terminal output
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("User prints success message")
    public void userPrintsSuccessMessage() {
        if ("Hello, Terminal!".equals(terminalOutput)) {
            System.out.println("Success: Message printed correctly.");
        } else {
            System.out.println("Failure: Message not printed correctly.");
        }
    }

    @Given("User navigates to the Contact Us page")
    public void user_navigates_to_the_contact_us_page() {
        driver.findElement(By.linkText("Contact Us")).click();
    }

    @Given("User fills out the contact form with first name {string}, last name {string}, email {string}, phone number {string}, and message {string}")
    public void user_fills_out_the_contact_form(String firstName, String lastName, String email, String phoneNumber, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname")));
        WebElement lastNameInput = driver.findElement(By.id("lname"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement phoneNumberInput = driver.findElement(By.id("pnumber"));
        WebElement messageInput = driver.findElement(By.id("message"));

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        phoneNumberInput.sendKeys(phoneNumber);
        messageInput.sendKeys(message);
    }

    @Then("User submits the form and captures the confirmation message")
    public void user_submits_the_form_and_captures_the_confirmation_message() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Assuming the confirmation message is displayed in an alert
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        System.out.println("Confirmation Message: " + alertMessage);
    }
}
