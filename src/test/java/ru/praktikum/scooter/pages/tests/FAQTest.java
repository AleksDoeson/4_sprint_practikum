package ru.praktikum.scooter.pages.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.scooter.pages.MainPage;
import ru.praktikum.scooter.utils.BrowserFactory;

import static org.junit.Assert.*;

public class FAQTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        // Используем BrowserFactory для создания драйвера
        driver = BrowserFactory.createDriver("chrome"); // Можно менять на "firefox"
        mainPage = new MainPage(driver);
        driver.get(MainPage.URL); // Используем константу URL
    }

    @Test
    public void testFaqDropdownOpens() {
        for (int i = 0; i < mainPage.getFaqCount(); i++) {
            // Кликаем по каждому вопросу
            mainPage.clickOnDropdownArrow(i);

            // Проверяем видимость текста ответа
            assertTrue("FAQ должен быть видимым", mainPage.isFaqVisible(i));

            // Проверяем текст ответа
            String faqText = mainPage.getFaqText(i);
            assertNotNull("Текст ответа не должен быть null", faqText);
            assertFalse("Текст ответа не должен быть пустым", faqText.isEmpty());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие драйвера
        }
    }
}


