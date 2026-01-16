package tests;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import utils.DriverManager;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collection;

import static utils.Constants.*;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected final String browser;

    public BaseTest(String browser) {
        this.browser = browser;
    }


    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Collection<String[]> browsers() {
        return Arrays.asList(new String[][]{
                {CHROME_BROWSER},
                {YANDEX_BROWSER}
        });
    }

    @Before
    public void setUp() throws RemoteException {
        // Настройка RestAssured (если нужны API-вызовы)
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();

        // Запуск браузера
        driver = DriverManager.createDriver(browser);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);

        // Инициализация страниц
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        RestAssured.reset();
    }
}