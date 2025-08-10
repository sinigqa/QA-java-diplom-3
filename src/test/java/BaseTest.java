import org.junit.Before;
import org.example.config.BaseClass;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@RunWith(Parameterized.class)
public class BaseTest {
    protected WebDriver driver;


    @Parameterized.Parameter
    public String browser;


    @Parameterized.Parameters(name = "Browser: {0}")
    public static String[] browsers() {
        return new String[]{"chrome", "yandex"};
    }

    @Before
    public void setUp() {
        try {
            driver = BaseClass.createWebDriver(browser);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (Exception e) {
            throw new RuntimeException("Не удалось запустить браузер: " + browser, e);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}