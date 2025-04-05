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
        this.wait = new WebDriverWait(driver, 10);
    }

    // Метод для клика по стрелочке конкретного FAQ-вопроса
    public void clickOnDropdownArrow(int index) {
        List<WebElement> arrows = driver.findElements(faqDropdownArrows);
        if (index < 0 || index >= arrows.size()) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }
        WebElement arrow = arrows.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", arrow);
        wait.until(ExpectedConditions.elementToBeClickable(arrow));
        arrow.click();
    }

    // Метод для получения текста ответа по индексу
    public String getFaqText(int index) {
        List<WebElement> texts = driver.findElements(faqText);
        if (index < 0 || index >= texts.size()) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }
        WebElement text = texts.get(index);
        wait.until(ExpectedConditions.visibilityOf(text));
        return text.getText().trim();
    }

    // Метод для проверки, отображается ли текст ответа
    public boolean isFaqVisible(int index) {
        List<WebElement> texts = driver.findElements(faqText);
        if (index < 0 || index >= texts.size()) {
            return false;
        }
        WebElement text = texts.get(index);
        wait.until(ExpectedConditions.visibilityOf(text));
        return text.isDisplayed();
    }
}





