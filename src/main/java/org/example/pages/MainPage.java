package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By bunsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Булки']/..");
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Соусы']/..");
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Начинки']/..");

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountLink = By.xpath("//p[text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickPersonalAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountLink)).click();
    }

    public boolean isTabActive(By tabLocator) {
        WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(tabLocator));
        return tab.getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }
}
