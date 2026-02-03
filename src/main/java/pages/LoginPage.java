package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static utils.Constants.DEFAULT_TIMEOUT;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    // Страница Личный кабинет до авторизации
    //Гиперссылка Зарегистрироваться
    private final By registerLinkLocator = By.xpath("//a[text()='Зарегистрироваться']");
    //Поле Имя
    private final By firstNameInputLocator = By.xpath("//label[text()='Имя']/following-sibling::input");
    //Поле email
    private final By emailInputLocator = By.xpath("//label[text()='Email']/following-sibling::input");
    //Поле Пароль
    private final By passwordInputLocator = By.xpath("//label[text()='Пароль']/following-sibling::input");
    //Кнопка Зарегистрироваться
    private final By registerButtonLocator = By.xpath("//button[text()='Зарегистрироваться']");
    //Ошибка при вводе пароля менее 6 символов
    private final By passwordErrorLocator = By.xpath("//div[@class='input__container' and .//input[@name='Пароль']]//p[contains(@class, 'input__error')]");
    //Кнопка Войти
    private final By loginButtonLocator = By.xpath("//button[text()='Войти']");
    //Гиперссылка Войти
    private final By loginLinkLocator = By.xpath("//a[text()='Войти']");
    //Гиперссылка Восстановить пароль
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");
    //Заголовок Вход
    private final By loginHeaderLocator = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    @Step("Ожидаем загрузку страницы логина")
    public boolean isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeaderLocator));
        return driver.getCurrentUrl().contains("/login");
    }

    @Step("Авторизация: email={email}")
    public boolean login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLocator)).clear();
        driver.findElement(emailInputLocator).sendKeys(email);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        // Ждём ухода со страницы /login
        return wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
    }

    @Step("Переход на страницу регистрации со страницы логина")
    public LoginPage openRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLinkLocator)).click();
        return new LoginPage(driver);
    }

    @Step("Переход на страницу восстановления пароля")
    public LoginPage openForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
        return new LoginPage(driver);
    }
    @Step("Перейти на страницу регистрации")
    public LoginPage goToRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLinkLocator)).click();
        return this;
    }

    @Step("Ввести имя: {name}")
    public LoginPage enterFirstName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInputLocator)).sendKeys(name);
        return this;
    }

   @Step("Ввести email: {email}")
    public LoginPage enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInputLocator)).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInputLocator)).sendKeys(password);
        return this;
    }

    @Step("Нажать 'Зарегистрироваться'")
    public LoginPage submitRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButtonLocator)).click();
        return new LoginPage(driver);
    }

    @Step("Получить текст ошибки пароля на форме регистрации")
    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorLocator)).getText();
    }

   @Step("Переход к форме входа со страницы регистрации")
    public LoginPage goToLoginFromRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLinkLocator)).click();
        return new LoginPage(driver);
    }

    @Step("Полное заполнение формы регистрации")
    public LoginPage register(String name, String email, String password) {
        return enterFirstName(name)
                .enterEmail(email)
                .enterPassword(password)
                .submitRegistration();
    }
    @Step("Переход к форме входа со страницы восстановления пароля")
    public LoginPage goToLoginFromForgot() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLinkLocator)).click();
        return new LoginPage(driver);
    }
}

