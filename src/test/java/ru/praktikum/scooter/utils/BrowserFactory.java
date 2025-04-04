package ru.praktikum.scooter.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                // Инициализация драйвера для Chrome
                driver = new ChromeDriver();
                break;
            case "firefox":
                // Инициализация драйвера для Firefox
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Поддерживаются только браузеры: chrome или firefox. Неверный браузер: " + browser);
        }

        return driver;
    }
}


