package ru.praktikum.scooter.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.scooter.utils.BaseTest;

import java.util.List;

public class OrderPage extends BaseTest {

    final private WebDriverWait wait;

    // Локаторы для полей ввода
    private final By firstNameField = By.cssSelector("input[placeholder='* Имя']");
    private final By lastNameField = By.cssSelector("input[placeholder='* Фамилия']");
    private final By addressField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    private final By phoneField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    // Локаторы для кнопок
    private final By orderButtonTop = By.cssSelector(".Button_Button__ra12g");
    private final By orderButtonBottom = By.cssSelector(".Button_Middle__1CSJM");
    private final By nextButton = By.cssSelector(".Button_Middle__1CSJM");
    private final By cookieBannerCloseButton = By.cssSelector(".App_CookieButton__3cvqF");
    private final By yesButton = By.cssSelector(".Button_Middle__1CSJM");

    // Локаторы для других элементов формы
    private final By rentalPeriodArrow = By.xpath("//div[contains(@class, 'Dropdown-arrow-wrapper')]//span");
    private final By commentInput = By.cssSelector("input[placeholder='Комментарий для курьера']");

    // Универсальный локатор для чекбокса с параметризированным id
    private By colorCheckbox(String colorId) {
        return By.id(colorId);
    }

    private final By subwayInputField = By.cssSelector("input.select-search__input");
    private final By subwayOption = By.cssSelector(".select-search__option");
    private final By dateInputField = By.cssSelector("input[placeholder='* Когда привезти самокат']");

    // Конструктор с передачей driver в родительский класс
    public OrderPage(WebDriver driver) {
        super(driver);  // Передаем WebDriver в конструктор родительского класса
        wait = new WebDriverWait(driver, 10);  // Инициализация WebDriverWait с тайм-аутом 10 секунд
    }

    public void closeCookieBanner() {
        try {
            WebElement cookieButton = driver.findElement(cookieBannerCloseButton);
            cookieButton.click();
            System.out.println("Баннер cookie закрыт");
        } catch (NoSuchElementException e) {
            System.out.println("Баннер cookie не найден");
        }
    }

    public void clickOrderButton() {
        WebElement button = driver.findElement(orderButtonTop);
        button.click();
    }

    public WebElement getOrderButtonBottom() {
        return driver.findElement(orderButtonBottom);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToOrderButton() {
        WebElement orderButtonBottom = getOrderButtonBottom();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderButtonBottom);
    }

    public void clickOrderButtonBottom() {
        WebElement button = driver.findElement(orderButtonBottom);
        scrollToElement(button);
        button.click();
    }

    public void clickNextButton() {
        WebElement button = driver.findElement(nextButton);
        button.click();
    }

    public void fillDateField(String date) {
        WebElement dateField = driver.findElement(dateInputField);
        dateField.sendKeys(date);
    }

    public void fillOrderForm(String firstName, String lastName, String address, String subway, String phoneNumber) {
        fillFirstNameField(firstName);
        fillLastNameField(lastName);
        fillAddressField(address);
        fillPhoneField(phoneNumber);
        fillSubwayField(subway);
    }

    public void fillFirstNameField(String firstName) {
        WebElement firstNameInput = driver.findElement(firstNameField);
        firstNameInput.sendKeys(firstName);
    }

    public void fillLastNameField(String lastName) {
        WebElement lastNameInput = driver.findElement(lastNameField);
        lastNameInput.sendKeys(lastName);
    }

    public void fillAddressField(String address) {
        WebElement addressInput = driver.findElement(addressField);
        addressInput.sendKeys(address);
    }

    public void fillPhoneField(String phoneNumber) {
        WebElement phoneInput = driver.findElement(phoneField);
        phoneInput.sendKeys(phoneNumber);
    }

    public void fillSubwayField(String subwayName) {
        WebElement subwayInput = driver.findElement(subwayInputField);
        subwayInput.sendKeys(subwayName);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(subwayOption));

        List<WebElement> options = driver.findElements(subwayOption);
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(subwayName)) {
                option.click();
                break;
            }
        }
    }

    public void selectRentalPeriod(String period) {
        WebElement dropdownArrow = driver.findElement(rentalPeriodArrow);
        dropdownArrow.click();
        WebElement option = driver.findElement(By.xpath("//div[contains(@class, 'Dropdown-menu')]//div[text()='" + period + "']"));
        option.click();
    }

    public void fillComment(String comment) {
        WebElement commentInputElement = driver.findElement(commentInput);
        commentInputElement.sendKeys(comment);
    }

    public void selectColor(String colorId) {
        WebElement checkbox = driver.findElement(colorCheckbox(colorId));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void submitOrder() {
        WebElement submitBtn = driver.findElement(orderButtonTop);
        submitBtn.click();
    }

    public void confirmOrder() {
        WebElement confirmBtn = driver.findElement(yesButton);
        confirmBtn.click();
    }

    public boolean isOrderConfirmed() {
        try {
            WebElement confirmationMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Modal__YZ-d3 > div.Order_ModalHeader__3FDaJ"))
            );
            return confirmationMessage.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Сообщение о подтверждении заказа не появилось");
            return false;
        }
    }
}






