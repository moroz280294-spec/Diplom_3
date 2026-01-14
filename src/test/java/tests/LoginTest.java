package tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import steps.UserSteps;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends BaseTest {

    private User user;
    private UserSteps userSteps;

    public  LoginTest(String browser) {
        super(browser);
    }

    @Before
    public void initUser() {
        userSteps = new UserSteps();
        String randomPart = RandomStringUtils.randomAlphabetic(6).toLowerCase();//Нижний регистр чтобы не нарваться на баг с изменением регистра
        user = new User()
                .withEmail("user" + randomPart + "@example.com")
                .withPassword(RandomStringUtils.randomAlphabetic(8))
                .withFirstName("user" + randomPart);


        Response createResp = userSteps.createUser(user);

        assertThat("Создание пользователя для логина через API",
                createResp.statusCode(), is(SC_OK));

        user.withAccessToken(createResp.path("accessToken"));
    }

    @Test
    @DisplayName("UI:Вход по кнопке «Войти в аккаунт» на главной ")
    public void loginFromMainEnterButton() {
        mainPage.clickEnterProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        assertThat("Страница логина должна открыться", loginPage.isOpened(), is(true));
        assertThat("Логин должен завершиться успешно",
                loginPage.login(user.getEmail(), user.getPassword()), is(true));
        assertThat("После логина должна открыться главная страница",
                mainPage.isOpened(), is(true));
    }

    @Test
    @DisplayName("UI:Вход через кнопку «Личный кабинет» ")
    public void loginFromPersonalProfileButton() {
        mainPage.clickPersonalProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        assertThat(loginPage.isOpened(), is(true));
        assertThat(loginPage.login(user.getEmail(), user.getPassword()), is(true));
        assertThat("После логина должна открыться главная страница",
                mainPage.isOpened(), is(true));
    }

    @Test
    @DisplayName("UI:Вход через кнопку в форме регистрации ")
    public void loginFromRegistrationFormLink() {
        mainPage.clickEnterProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration()
                .goToLoginFromRegistration();
        assertThat("Возврат к логину из регистрации", loginPage.isOpened(), is(true));
        assertThat(loginPage.login(user.getEmail(), user.getPassword()), is(true));
        assertThat("После логина должна открыться главная страница",
                mainPage.isOpened(), is(true));
    }

    @Test
    @DisplayName("UI:Вход через кнопку в форме восстановления пароля ")
    public void loginFromForgotPasswordForm() {
        mainPage.clickEnterProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openForgotPassword();
        LoginPage fromForgot = loginPage.goToLoginFromForgot();
        assertThat(fromForgot.isOpened(), is(true));
        assertThat(fromForgot.login(user.getEmail(), user.getPassword()), is(true));
        assertThat("После логина должна открыться главная страница",
                mainPage.isOpened(), is(true));
    }

    @After
    public void cleanUpUserData() {
        if (user != null && user.getAccessToken() != null) {
            userSteps.deleteUser(user.getAccessToken());
        }
    }
}