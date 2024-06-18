package stepDefinitions;

import org.openqa.selenium.WebDriver;
import env.DriverUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.io.InputStream;
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

}
