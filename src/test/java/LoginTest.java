import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.api.UserApi;
import org.example.api.UserRequest;
import org.example.pages.AccountPage;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private UserApi apiClient = new UserApi();
    private String email;
    private String password;
    private String accessToken;

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private AccountPage accountPage;

    @Before
    @Override
    public void setUp() {
        super.setUp();

        email = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@test.com";
        password = RandomStringUtils.randomAlphanumeric(8);
        String name = RandomStringUtils.randomAlphabetic(5);

        UserRequest user = new UserRequest(email, password, name);
        ValidatableResponse response = apiClient.createUser(user);
        accessToken = response.extract().path("accessToken");

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Войти в аккаунт'")
    public void loginViaMainPageButtonShouldSeeLogoutButton() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(email, password);

        mainPage.clickPersonalAccount();

        assertTrue(accountPage.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Войти' на странице регистрации")
    public void clickSignInButtonOnRegistrationPage() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.login(email, password);

        mainPage.clickPersonalAccount();

        assertTrue(accountPage.isLogoutButtonDisplayed());

    }

    @Test
    @DisplayName("Авторизация через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountButtonShouldSeeLogoutButton() {
        mainPage.open();
        mainPage.clickPersonalAccount();
        loginPage.login(email, password);

        mainPage.clickPersonalAccount();

        assertTrue(accountPage.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Войти' на странице восстановления пароля")
    public void clickSignInButtonOnPasswordRecoveryPage() {
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.clickForgotPasswordLink();
        registerPage.clickLoginLink();
        loginPage.login(email, password);

        mainPage.clickPersonalAccount();


        assertTrue(accountPage.isLogoutButtonDisplayed());

    }

    @After
    @Override
    public void tearDown() {
        if (accessToken != null) {
            apiClient.deleteUser(accessToken);
        }
        super.tearDown();
    }
}