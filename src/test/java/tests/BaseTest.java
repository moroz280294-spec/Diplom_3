package tests;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import utils.DriverManager;

import static utils.Constants.BASE_URL;

public abstract class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;

    @Before
    public void setUp() {
        // Настройка RestAssured (если нужны API-вызовы)
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();

        // Получаем браузер из системного свойства, по умолчанию chrome
        String browser = System.getProperty("browser", "chrome");

        // Запуск браузера
        driver = DriverManager.createDriver(browser);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(BASE_URL);

        // Инициализация страниц
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        Allure.description("Тест запущен в браузере: " + browser);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        RestAssured.reset();
    }
}
