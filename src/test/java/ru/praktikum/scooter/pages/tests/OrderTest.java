package ru.praktikum.scooter.pages.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.pages.OrderPage;
import ru.praktikum.scooter.pages.MainPage;
import ru.praktikum.scooter.utils.BaseTest;

import static org.junit.Assert.assertTrue;

public class OrderTest extends BaseTest {

    private OrderPage orderPage;

    // Тестовые данные вынесены в константы
    private static final String FIRST_NAME_1 = "Иван";
    private static final String LAST_NAME_1 = "Иванов";
    private static final String ADDRESS_1 = "Москва, ул. Тестовая, 1";
    private static final String PHONE_NUMBER_1 = "+79161234567";
    private static final String SUBWAY_1 = "Киевская";
    private static final String RENTAL_DATE_1 = "01.05.2025";
    private static final String RENTAL_PERIOD_1 = "сутки";
    private static final String COLOR_1 = "black";
    private static final String COMMENT_1 = "Нужна доставка на утро.";

    private static final String FIRST_NAME_2 = "Петр";
    private static final String LAST_NAME_2 = "Петров";
    private static final String ADDRESS_2 = "Москва, ул. Тестовая, 2";
    private static final String PHONE_NUMBER_2 = "+79169876543";
    private static final String SUBWAY_2 = "Сокольники";
    private static final String RENTAL_DATE_2 = "05.05.2025";
    private static final String RENTAL_PERIOD_2 = "двое суток";
    private static final String COLOR_2 = "grey";
    private static final String COMMENT_2 = "Нужна доставка на вечер.";

    @Before
    public void setUp() {
        super.setUp(); // Инициализация WebDriver из BaseTest
        orderPage = new OrderPage(driver);
        driver.get(MainPage.URL); // Переход на главную страницу
    }

    @Test
    public void testOrderFormFirst() {
        // Закрыть баннер cookie, если он появляется
        orderPage.closeCookieBanner();
        orderPage.clickOrderButton();

        // Заполнение первой формы (имя, фамилия, адрес, метро, телефон)
        orderPage.fillOrderForm(FIRST_NAME_1, LAST_NAME_1, ADDRESS_1, SUBWAY_1, PHONE_NUMBER_1);

        // Нажатие на кнопку "Далее" после первого набора данных
        orderPage.clickNextButton();

        // Заполнение второго набора данных (когда привезти самокат, срок аренды, цвет и комментарий)
        orderPage.fillDateField(RENTAL_DATE_1);
        orderPage.selectRentalPeriod(RENTAL_PERIOD_1);
        orderPage.selectColor(COLOR_1);
        orderPage.fillComment(COMMENT_1);

        // Завершаем заказ
        orderPage.submitOrder();
        orderPage.confirmOrder();
        System.out.println("Получилось создать заказ");

        // Проверка, что заказ оформлен (не нужно проверять номер)
        assertTrue("Сообщение о подтверждении заказа должно быть отображено", orderPage.isOrderConfirmed());
    }

    @Test
    public void testOrderFormSecond() {
        // Закрыть баннер cookie, если он появляется
        orderPage.closeCookieBanner();
        orderPage.scrollToOrderButton();
        orderPage.clickOrderButtonBottom();

        // Заполнение первой формы (имя, фамилия, адрес, метро, телефон)
        orderPage.fillOrderForm(FIRST_NAME_2, LAST_NAME_2, ADDRESS_2, SUBWAY_2, PHONE_NUMBER_2);

        // Нажатие на кнопку "Далее" после первого набора данных
        orderPage.clickNextButton();

        // Заполнение второго набора данных (когда привезти самокат, срок аренды, цвет и комментарий)
        orderPage.fillDateField(RENTAL_DATE_2);
        orderPage.selectRentalPeriod(RENTAL_PERIOD_2);
        orderPage.selectColor(COLOR_2);
        orderPage.fillComment(COMMENT_2);

        // Завершаем заказ
        orderPage.submitOrder();
        orderPage.confirmOrder();
        System.out.println("Получилось создать заказ");

        // Проверка, что заказ оформлен (не нужно проверять номер)
        assertTrue("Сообщение о подтверждении заказа должно быть отображено", orderPage.isOrderConfirmed());
    }

    @After
    public void tearDown() {
        super.tearDown(); // Закрытие браузера из BaseTest
    }
}










