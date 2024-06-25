
# Java Selenium Cucumber Project
## Overview

This project is a test automation framework built using Java, Selenium WebDriver, and Cucumber. It allows you to write and run automated tests for web applications using behavior-driven development (BDD) methodology.

## Key Features

- **Cross-browser Testing:** Supports both Chrome and Firefox browsers.
- **Behavior-Driven Development (BDD):** Uses Cucumber for writing test scenarios in Gherkin language.
- **Automated Browser Setup:** Uses WebDriverManager to handle browser driver binaries.
- **Customizable:** Easily switch browsers using system properties.

## Prerequisites

- **Java Development Kit (JDK) 8 or above**
- **Apache Maven**
- **Web Browsers:** Chrome or Firefox

## Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/Nitinnannapaneni20/AutomationFramework.git
    cd AutomationFramework
    ```

2. **Install dependencies:**
    ```sh
    mvn clean install
    ```

## Usage

### Running Tests

1. **From Command Line:**
    ```sh
    mvn test
    ```

   To specify a browser, use the `-Dbrowser` property:
    ```sh
    mvn test -Dbrowser=firefox
    ```

2. **From IDE:**
    - Right-click on `TestRunner` class in `runner` package and select `Run 'TestRunner'`.
    - Specify the browser name in the configurations for firefox its -Dbrowser=firefox and for chrome its -Dbrowser=chrome
### Project Structure

- `src/test/resources/features`: Contains feature files written in Gherkin language.
- `src/test/java/stepDefinitions`: Contains step definition files.
- `src/test/java/runner`: Contains the test runner class.

## Reporting

After running the tests, the test results will be available in the `target/cucumber-reports.html` file.

## Contributing

1. Fork the repository.
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request.

## Contact

For any inquiries or support, please contact Nitinnannpaneni@gmail.com.
