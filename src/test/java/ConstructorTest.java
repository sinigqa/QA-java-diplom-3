import io.qameta.allure.junit4.DisplayName;
import org.example.pages.MainPage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class ConstructorTest extends BaseTest {
    private MainPage mainPage;

    @Before
    public void setUpPage() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Булки'")
    public void testBunsSection() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsSectionDisplayed());
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Соусы'")
    public void testSaucesSection() {
        mainPage.clickSaucesTab();
        assertTrue(mainPage.isSaucesSectionDisplayed());
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Начинки'")
    public void testFillingsSection() {
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isFillingsSectionDisplayed());
    }
}