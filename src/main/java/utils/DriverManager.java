package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.rmi.RemoteException;


public class DriverManager {

    public static WebDriver createDriver(String browser) throws RemoteException {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");//Запуск в режиме инкогнито
            return new ChromeDriver(options);
        }
        else if (browser.equalsIgnoreCase("yandex")) {

            WebDriverManager.chromedriver().browserVersion("142").setup();//с версией 143 у меня не работало

            ChromeOptions options = new ChromeOptions();
            //Нужно указать путь до исполняемого файла яндекс браузера
            options.setBinary("C:\\Users\\user\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            options.addArguments("--incognito");//Запуск в режиме инкогнито

            return new ChromeDriver(options);
        }
        else {
            throw new RemoteException("Browser undefined: " + browser);
        }
    }
}
