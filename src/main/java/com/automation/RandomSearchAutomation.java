package com.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;

public class RandomSearchAutomation {

    public static void main(String[] args) throws InterruptedException {
        // Set up WebDriver for Microsoft Edge (change path to where your msedgedriver is located)
        System.setProperty("webdriver.edge.driver", "C:\\Users\\agraw\\Downloads\\edgedriver_win64\\msedgedriver.exe");

        // Set up Edge options for debugging and other settings
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized");  // Maximizes the window
        edgeOptions.addArguments("--remote-debugging-port=9222");  // Set a custom remote debugging port
        edgeOptions.addArguments("--remote-allow-origins=*");  // Allow connections from any origin (fix WebSocket issue)
        edgeOptions.setCapability("pageLoadStrategy", "normal");  // Adjust page load strategy

        // Create WebDriver instance with the specified options for Edge
        WebDriver driver = new EdgeDriver(edgeOptions);

        try {
            driver.manage().window().maximize();
            driver.get("https://www.bing.com"); // Use Bing for searching

            // WebDriverWait to ensure elements are present before interacting
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased wait time to 15 seconds

            // Repeat the search process 10 times
            for (int i = 0; i < 15; i++) {
                System.out.println("Starting search iteration " + (i + 1));

                // Wait for the search bar to be visible before interacting
                WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));

                // Generate a random sentence to search for
                searchBox.clear(); // Clear the search box before typing
                searchBox.sendKeys(generateRandomSentence());

                // Instead of clicking the button, press the Enter key
                searchBox.sendKeys(Keys.RETURN);

                // Wait for the search results to be visible and try alternative selectors if needed
                try {
                    // Check if primary selector works
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.b_algo h2 a")));
                    System.out.println("Search results loaded successfully.");
                } catch (Exception e) {
                    System.out.println("Primary selector not found, trying alternative...");
                    try {
                        // Try alternative selector in case Bing page structure has changed
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2 a")));
                        System.out.println("Search results loaded successfully with alternative selector.");
                    } catch (Exception e2) {
                        System.out.println("Search results not found: " + e2.getMessage());
                    }
                }

                // Wait a bit more to ensure that the page is fully loaded
                Thread.sleep(1000);

                // Scroll the page for 2 seconds after search results load
                for (int j = 0; j < 4; j++) {
                    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
                    Thread.sleep(1500); // Pause briefly between scrolls
                }

                // Go back to the Bing homepage for the next search
                driver.navigate().to("https://www.bing.com");
                Thread.sleep(2500);  // Slight delay to ensure the page loads properly
            }

        } finally {
            driver.quit(); // Close the browser when done
            System.out.println("Test completed and browser closed.");
        }
    }

    // Helper method to generate random sentences
    private static String generateRandomSentence() {
        Random rand = new Random();
        int wordCount = rand.nextInt(20) + 1; // Sentence length from 1 to 20 words
        StringBuilder sentence = new StringBuilder();

        for (int i = 0; i < wordCount; i++) {
            int wordLength = rand.nextInt(22) + 1; // Word length from 1 to 22 letters
            StringBuilder word = new StringBuilder();

            for (int j = 0; j < wordLength; j++) {
                char randomChar = (char) (rand.nextInt(26) + 'a');
                word.append(randomChar);
            }

            sentence.append(word).append(" ");
        }

        return sentence.toString().trim();
    }
}
