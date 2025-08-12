package org.example.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;


public class BaseClass {
    public static WebDriver createWebDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "yandex":
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

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
