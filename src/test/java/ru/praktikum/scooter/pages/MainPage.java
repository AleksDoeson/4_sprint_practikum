package ru.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы для вопросов и ответов в разделе "Вопросы о важном"
    private final By faqDropdownArrows = By.cssSelector(".accordion__button");
    private final By faqText = By.cssSelector(".accordion__panel");

    // URL как константа
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); // Инициализация WebDriverWait
    }

    // Метод клика по стрелочке для каждого вопроса с явным ожиданием
    public void clickOnDropdownArrow(int index) {
        List<WebElement> arrows = driver.findElements(faqDropdownArrows);
        if (index < 0 || index >= arrows.size()) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }

        // Ожидание, что стрелочка будет доступна для клика
        wait.until(ExpectedConditions.elementToBeClickable(arrows.get(index)));

        // Прокрутка до элемента, если он не виден
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", arrows.get(index));

        // Кликаем по элементу
        arrows.get(index).click();
    }

    // Метод для получения текста ответа на вопрос
    public String getFaqText(int index) {
        List<WebElement> texts = driver.findElements(faqText);
        if (index < 0 || index >= texts.size()) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }

        // Ожидаем, что текст будет видимым
        wait.until(ExpectedConditions.visibilityOf(texts.get(index)));

        return texts.get(index).getText().trim();
    }

    // Метод для проверки видимости ответа на вопрос
    public boolean isFaqVisible(int index) {
        List<WebElement> texts = driver.findElements(faqText);
        if (index < 0 || index >= texts.size()) {
            return false;
        }

        // Ожидаем, что текст будет видимым
        wait.until(ExpectedConditions.visibilityOf(texts.get(index)));

        return texts.get(index).isDisplayed();
    }

    // Метод для получения количества FAQ
    public int getFaqCount() {
        return driver.findElements(faqDropdownArrows).size();
    }
}




