package tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationTest extends BaseTest {

    private User user;
    private UserSteps userSteps;

    @Before
    public void init() {
        userSteps = new UserSteps();
        String randomPart = RandomStringUtils.randomAlphabetic(6).toLowerCase();//Нижний регистр чтобы не нарваться на баг с изменением регистра
        user = new User()
                .withEmail("user" + randomPart + "@example.com")
                .withPassword(RandomStringUtils.randomAlphabetic(8))
                .withFirstName("user" + randomPart);
    }

    @Test
    @DisplayName("UI: успешная регистрация нового пользователя -> переход на логин ")
    public void shouldRegisterSuccessfully() {
        mainPage.clickEnterProfileButton();
        loginPage.goToRegistration();
        loginPage.register(user.getFirstName(), user.getEmail(), user.getPassword());

        assertThat("Должны попасть на /login после регистрации", loginPage.isOpened(), is(true));

        // получаем токен через API, чтобы удалить пользователя
        Response loginResp = userSteps.login(user);
        assertThat("Логин через API после регистрации", loginResp.statusCode(), is(SC_OK));
        user.withAccessToken(loginResp.path("accessToken"));
    }

    @Test
    @DisplayName("UI: ошибка при вводе короткого пароля (<6 символов)")
    public void shouldShowErrorForShortPassword() {
        mainPage.clickEnterProfileButton();
        String shortPassword = "12345";

        loginPage.goToRegistration()
                .enterFirstName(user.getFirstName())
                .enterEmail(user.getEmail())
                .enterPassword(shortPassword)
                .submitRegistration();

        String errorText = loginPage.getPasswordErrorText();
        assertThat("Ошибка пароля должна быть показана",
                errorText.toLowerCase(), containsString("парол"));
    }

    @After
    public void cleanUpUserData() {
        if (user != null && user.getAccessToken() != null) {
            userSteps.deleteUser(user.getAccessToken());
        }
    }
}
