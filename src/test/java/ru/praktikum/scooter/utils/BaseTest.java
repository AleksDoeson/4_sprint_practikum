package ru.praktikum.scooter.utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseTest {
    protected WebDriver driver;
    public BaseTest(WebDriver driver) {
        this.driver = driver;
    }
    // Пустой конструктор для совместимости с JUnit
    public BaseTest() {
    }

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome"); // По умолчанию chrome
        System.out.println("[INFO] Запуск браузера: " + browser);

        driver = createDriver(browser);
    }

    public WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("❌ Поддерживаются только браузеры: chrome или firefox. Указано: " + browser);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println("[INFO] Закрытие браузера");
            driver.quit();
        }
    }
}





