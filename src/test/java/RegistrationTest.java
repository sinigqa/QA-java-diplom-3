import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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

public class RegistrationTest extends BaseTest {

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

        this.email = RandomStringUtils.randomAlphabetic(8) + "@test.com";
        this.password = RandomStringUtils.randomAlphanumeric(8);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        accountPage = new AccountPage(driver);
    }


    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации")
    public void testRegisterViaRegisterPageShouldSeeLogoutButton() {
        String name = RandomStringUtils.randomAlphabetic(7);

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.register(name, email, password);
        loginPage.waitForPageToLoad();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        mainPage.clickPersonalAccount();

        assertTrue(accountPage.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Ошибка при вводе некорректного пароля")
    @Description("Проверка наличия ошибки при вводе некорректного пароля")
    public void testRegisterWithShortPasswordShouldShowPasswordError() {

        String testEmail = RandomStringUtils.randomAlphabetic(8) + "@test.com";
        String testPassword = RandomStringUtils.randomAlphanumeric(5);

        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.enterName(RandomStringUtils.randomAlphabetic(7));
        registerPage.enterEmail(testEmail);
        registerPage.enterPassword(testPassword);
        registerPage.clickRegisterButton();

        assertTrue(registerPage.isPasswordErrorDisplayed());
    }

    @After
    @Override
    public void tearDown() {
        try {
            if (email != null && password != null) {
                accessToken = apiClient.loginUser(new UserRequest(email, password, ""))
                        .extract()
                        .path("accessToken");

                if (accessToken != null) {
                    apiClient.deleteUser(accessToken);
                }
            }
        } catch (Exception ignored) {

        }
        super.tearDown();
    }
}