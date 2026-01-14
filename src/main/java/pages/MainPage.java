package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

public class MainPage {

    private final WebDriverWait wait;

    // Локаторы
    //Заголовок Соберите Бургер
    private final By mainHeaderLocator = By.xpath("//h1[contains(text(),'Соберите бургер')]");
    //Кнопка Личный кабинет
    private final By personalProfileButtonLocator = By.xpath("//p[normalize-space()='Личный Кабинет']");
    //Кнопка Войти в аккаунт
    private final By enterProfileButtonLocator = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    //Вкладка Булки
    private final By bunsTabLocator = By.xpath("//span[contains(text(), 'Булки')]");
    //Вкладка Соусы
    private final By saucesTabLocator = By.xpath("//span[contains(text(), 'Соусы')]");
    //Вкладка начинки
    private final By fillingsTabLocator = By.xpath("//span[contains(text(), 'Начинки')]");

    public MainPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Constants.DEFAULT_TIMEOUT);
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickPersonalProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalProfileButtonLocator)).click();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickEnterProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterProfileButtonLocator)).click();
    }

    @Step("Переход во вкладку 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTabLocator)).click();
    }

    @Step("Переход во вкладку 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTabLocator)).click();
    }

    @Step("Переход во вкладку 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTabLocator)).click();
    }

    @Step("Проверка, что открыта главная страница")
    public boolean isOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mainHeaderLocator)).isDisplayed();
    }



    @Step("Проверка, что вкладка '{tabName}' активна")
    public boolean isTabActive(String tabName) {
        By activeTabLocator = By.xpath(
                "//div[contains(@class, 'tab_tab_type_current')]//span[text()='" + tabName + "']"
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTabLocator)).isDisplayed();
    }

    // Метод для проверки заголовка секции
    @Step("Проверка наличия заголовка секции '{sectionName}'")
    public boolean isSectionHeaderVisible(String sectionName) {
        By headerLocator = By.xpath("//h2[text()='" + sectionName + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator)).isDisplayed();
    }
}