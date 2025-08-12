package org.example.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class BaseClass {
    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser", "chrome"); // "chrome" по умолчанию
        return createDriver(browser);
    }

    private static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "yandex":
                return setupYandexDriver();
            case "chrome":
            default:
                return setupChromeDriver();
        }
    }

    private static WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver setupYandexDriver() {
        File driverExecutable = new File("C:/Users/user/chromedriver-win64/chromedriver.exe");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(driverExecutable)
                .build();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/user/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        options.addArguments(
                "--remote-allow-origins=*",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-blink-features=AutomationControlled",
                "--start-maximized",
                "--disable-extensions"
        );
        options.setExperimentalOption("detach", true);

        return new ChromeDriver(service, options);
    }
}
