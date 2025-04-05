package ru.praktikum.scooter.pages.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.scooter.pages.MainPage;
import ru.praktikum.scooter.utils.BaseTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FAQTest extends BaseTest {

    private MainPage mainPage;
    private final int questionIndex;
    private final String expectedAnswer;

    // Конструктор, принимающий параметры: индекс вопроса и ожидаемый ответ
    public FAQTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    // Параметризация тестов: передаем данные для каждого теста
    @Parameterized.Parameters(name = "FAQ #{0}")
    public static Collection<Object[]> faqData() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUp() {
        super.setUp(); // Инициализация WebDriver из BaseTest
        mainPage = new MainPage(driver);
        driver.get(MainPage.URL);
    }

    @Test
    public void testFAQAnswer() {
        // Кликаем по стрелке, чтобы открыть вопрос
        mainPage.clickOnDropdownArrow(questionIndex);

        // Проверяем, что ответ стал видимым
        assertTrue("FAQ ответ должен быть видимым", mainPage.isFaqVisible(questionIndex));

        // Получаем текст ответа
        String actualText = mainPage.getFaqText(questionIndex);

        // Проверяем, что текст ответа не пустой и не null
        assertNotNull("FAQ текст не должен быть null", actualText);
        assertFalse("FAQ текст не должен быть пустым", actualText.isEmpty());

        // Сравниваем фактический текст с ожидаемым
        assertEquals("FAQ текст не соответствует ожидаемому", expectedAnswer, actualText);
    }

    @After
    public void tearDown() {
        super.tearDown(); // Закрытие драйвера из BaseTest
    }
}





