import org.example.config.BaseClass;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        // Теперь метод createWebDriver() без параметров
        driver = BaseClass.createWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}