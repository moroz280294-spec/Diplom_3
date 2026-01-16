package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SwitchTest extends BaseTest {

    @Test
    @DisplayName("UI:Проверка переключения на вкладку 'Соусы'")
    public void shouldSwitchToSaucesTab() {
        mainPage.clickSaucesTab();
        assertTrue(mainPage.isTabActive("Соусы"));
        assertTrue(mainPage.isSectionHeaderVisible("Соусы"));
    }

    @Test
    @DisplayName("UI:Проверка переключения на вкладку 'Начинки'")
    public void shouldSwitchToFillingsTab() {
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isTabActive("Начинки"));
        assertTrue(mainPage.isSectionHeaderVisible("Начинки"));
    }

    @Test
    @DisplayName("UI:Проверка переключения на вкладку 'Булки'")
    public void shouldSwitchToBunsSection() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.isTabActive("Булки"));
        assertTrue(mainPage.isSectionHeaderVisible("Булки"));
    }
}