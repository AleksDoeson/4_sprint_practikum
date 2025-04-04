package ru.praktikum.scooter.pages.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.scooter.pages.OrderPage;
import ru.praktikum.scooter.pages.MainPage;
import ru.praktikum.scooter.utils.BrowserFactory;

import static org.junit.Assert.assertNotNull;

public class OrderTest {

    private WebDriver driver;
    private OrderPage orderPage;

    @Before
    public void setUp() {
        // Инициализация WebDriver через BrowserFactory
        driver = BrowserFactory.createDriver("chrome");
        orderPage = new OrderPage(driver);
        driver.get(MainPage.URL); // Используем константу из MainPage
    }

    @Test
    public void testOrderFormFirst() {
        // Закрыть баннер cookie, если он появляется
        orderPage.closeCookieBanner();
        orderPage.clickOrderButton();
        // Заполнение первой формы (имя, фамилия, адрес, метро, телефон)
        String firstName = "Иван";
        String lastName = "Иванов";
        String address = "Москва, ул. Тестовая, 1";
        String phoneNumber = "+79161234567";
        String subway = "Киевская";

        // Заполнение формы заказа
        orderPage.fillOrderForm(firstName, lastName, address, subway, phoneNumber);

        // Нажатие на кнопку "Далее" после первого набора данных
        orderPage.clickNextButton();

        // Заполнение второго набора данных (когда привезти самокат, срок аренды, цвет и комментарий)
        orderPage.fillDateField("01.05.2025");
        orderPage.selectRentalPeriod("сутки");
        orderPage.selectColor("black");
        String comment = "Нужна доставка на утро.";
        orderPage.fillComment(comment);
        // Завершаем заказ
        orderPage.submitOrder();
        orderPage.confirmOrder();
        System.out.println("Получилось создать заказ");

        // Получаем номер заказа и выводим его в консоль
        String orderNumber = orderPage.getOrderNumber();
        System.out.println("Номер заказа: " + orderNumber);

        // Проверка, что номер заказа отображается
        assertNotNull("Номер заказа не был получен", orderNumber);
    }

    @Test
    public void testOrderFormSecond() {
        // Закрыть баннер cookie, если он появляется
        orderPage.closeCookieBanner();
        orderPage.scrollToOrderButton();
        orderPage.clickOrderButtonBottom();
        // Заполнение первой формы (имя, фамилия, адрес, метро, телефон)
        String firstName = "Петр";
        String lastName = "Петров";
        String address = "Москва, ул. Тестовая, 2";
        String phoneNumber = "+79169876543";
        String subway = "Сокольники";

        // Заполнение формы заказа
        orderPage.fillOrderForm(firstName, lastName, address, subway, phoneNumber);

        // Нажатие на кнопку "Далее" после первого набора данных
        orderPage.clickNextButton();

        // Заполнение второго набора данных (когда привезти самокат, срок аренды, цвет и комментарий)
        orderPage.fillDateField("05.05.2025");
        orderPage.selectRentalPeriod("двое суток");
        orderPage.selectColor("grey");
        String comment = "Нужна доставка на вечер.";
        orderPage.fillComment(comment);

        // Завершаем заказ
        orderPage.submitOrder();
        orderPage.confirmOrder();
        System.out.println("Получилось создать заказ");

        // Получаем номер заказа и выводим его в консоль
        String orderNumber = orderPage.getOrderNumber();
        System.out.println("Номер заказа: " + orderNumber);

        // Проверка, что номер заказа отображается
        assertNotNull("Номер заказа не был получен", orderNumber);
    }

    @After
    public void tearDown() {
        // Закрытие браузера после теста
        if (driver != null) {
            driver.quit();
        }
    }


}









