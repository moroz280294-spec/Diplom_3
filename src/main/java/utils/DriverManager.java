package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static WebDriver createDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");

                return new ChromeDriver(chromeOptions);

            case "yandex":
                WebDriverManager.chromedriver()
                        .browserVersion("142")
                        .setup();

                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary(
                        "C:\\Users\\user\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"
                );
                yandexOptions.addArguments("--incognito");

                return new ChromeDriver(yandexOptions);

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
        }
    }
}
