# RandomSearchAutomation - Selenium Bing Search Automation

This Java program automates random search queries on Bing using Selenium WebDriver with the Microsoft Edge browser. It is designed to simulate user actions by performing multiple searches with random text input, scrolling through the results, and navigating back to the homepage.

## Overview
The program:
1. Launches Microsoft Edge with specific configurations.
2. Performs 15 random searches on Bing.
3. Waits for search results to load, scrolls down the page, and repeats.
4. Closes the browser when finished.

## Key Components

### Imports
Imports required classes from Selenium, Java utilities, and libraries to:
- Control browser interactions.
- Wait for elements.
- Generate random sentences.

### WebDriver Setup
```
System.setProperty("webdriver.edge.driver", "path/to/msedgedriver.exe");
```

# RandomSearchAutomation - Detailed Workflow

## Edge Browser Configuration
Configures Edge browser settings:
- **start-maximized**: Maximizes the browser window.
- **--remote-debugging-port=9222**: Opens debugging on port 9222.
- **--remote-allow-origins=***: Allows remote connections.
- **pageLoadStrategy="normal"**: Waits until the page fully loads.

## Main Search Loop
The program performs a search 15 times using the following steps:

1. **Waits for Search Bar**: Waits for the Bing search bar (`By.name("q")`) to load before typing.
2. **Generates Random Text**: Calls `generateRandomSentence()` to create a random search query.
3. **Executes Search**: Sends the query and presses Enter to perform the search.

## Handling Search Results
The program waits for search results to load:
- **Primary Selector**: Attempts to locate search results with `li.b_algo h2 a`.
- **Alternative Selector**: If the primary selector fails, an alternative (`h2 a`) is tried.

## Scrolling Through Results
To simulate user interaction, the program:
- Scrolls down the page four times, each by 500 pixels.
- Pauses between each scroll for a realistic effect.

## Returning to the Homepage
After each search iteration, the program navigates back to Bing’s homepage and waits briefly to ensure the page fully loads before the next search.

## Browser Cleanup
Once all search iterations are complete, the program closes the Edge browser.

## Helper Method - `generateRandomSentence()`
Generates random search queries by:
- **Creating a random number of words**: Ranges from 1 to 20.
- **Generating random words**: Each word is a random sequence of characters (up to 22 characters).
- **Returning the sentence**: Provides the generated sentence for use in each search.


## To run this programme download some dependencies.
- **Download msEdgedriver and set-up path into the given code-line.**
```
System.setProperty("webdriver.edge.driver", "your_path_for_msEdgeDriver\\edgedriver_win64\\msedgedriver.exe");
 ````
- **Download maven and set-up environment path.**
- **Ensure that you have JDK and set-up path. otherwise it will java_home error.**
- **To run this code just type in your IDE terminal.**
```
mvn compile exec:java "-Dexec.mainClass=com.automation.RandomSearchAutomation"
```
