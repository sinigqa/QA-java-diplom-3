import io.qameta.allure.Description;
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
    @Description("Переключаемся на вкладку 'Соусы' и обратно на вкладку 'Булки', проверяем что вкладка 'Булки' активна")
    public void testBunsSection() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Соусы'")
    @Description("Переключаемся на вкладку 'Соусы', проверяем что вкладка 'Соусы' активна")
    public void testSaucesSection() {
        mainPage.clickSaucesTab();
        assertTrue(mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Начинки'")
    @Description("Переключаемся на вкладку 'Начинки', проверяем что вкладка 'Начинки' активна")
    public void testFillingsSection() {
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isFillingsTabActive());
    }
}