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
    public void testBunsSection() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsSectionDisplayed());
    }

    @Test
    public void testSaucesSection() {
        mainPage.clickSaucesTab();
        assertTrue(mainPage.isSaucesSectionDisplayed());
    }

    @Test
    public void testFillingsSection() {
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isFillingsSectionDisplayed());
    }
}