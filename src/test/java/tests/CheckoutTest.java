package tests;

import io.qameta.allure.Story;
import models.FieldValidationExpectation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;
import utils.ValidationError;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.Allure.step;
@DisplayName("Оформление заказа")
@Story("Успешное оформление заказа")
public class CheckoutTest extends BaseTest {

    @Test
    @DisplayName("Успешное оформление заказа неавторизированным пользователем")
    public void orderTheProductUnregisteredTest() {
        ProductPage productPage = shopSteps.openFirstProductCard();
        CartPage cartPage = shopSteps.addProductToCart(productPage);

        step("Оформление заказа (заполнение адреса и согласие)", () -> {
        shoppingCartPage.simpleAddress.selectRandomCountryAndState();

            shoppingCartPage.selectAgree()
            .clickCheckoutButton();
        });

        step("Оформить заказ без регистрации", () -> {
            checkoutPage.clickCheckoutAsGuestButton();
        });
        step("Ввод личных данных", () -> {
            checkoutPage.inputName(testData.userName)
                    .inputLastName(testData.lastName)
                    .inputEmail(testData.userEmail)
                    .inputCity(testData.userCity)
                    .inputAddress(testData.userAddress)
                    .inputZipPostalCode(testData.userPrefix)
                    .inputPhoneNumber(testData.userPhoneNumber);

        });
        step("Заполнить страну и штат", () -> {
            checkoutPage.billingAddress.selectRandomCountryAndState();
        });
        step("Завершить оформление заказа'", () -> {
            checkoutPage.completeCheckoutSteps();
        });
        step("Проверка успешного заказа'", () -> {
            checkoutPage.checkSuccessOrder();
        });
    }

    @Test
    @DisplayName("Ошибка валидации при оформление заказа неавторизированным пользователем")
    public void validationErrorOrderTheProductUnregisteredTest() {
        ProductPage productPage = shopSteps.openFirstProductCard();
        CartPage cartPage = shopSteps.addProductToCart(productPage);

        step("Оформление заказа (заполнение адреса и согласие)", () -> {
            shoppingCartPage.simpleAddress.selectRandomCountryAndState();

            shoppingCartPage.selectAgree()
                    .clickCheckoutButton();
        });

        step("Оформление заказа без регистрации", () -> {
            checkoutPage.clickCheckoutAsGuestButton();
        });
        step("Нажать на кнопку 'Continue'", () -> {
            checkoutPage.clickOnBillingSave();
        });
        List<FieldValidationExpectation> expectedErrors = Arrays.asList(
                ValidationError.FIRST_NAME_REQUIRED.toExpectation(),
                ValidationError.LAST_NAME_REQUIRED.toExpectation(),
                ValidationError.EMAIL_REQUIRED.toExpectation(),
                ValidationError.COUNTRY_REQUIRED.toExpectation(),
                ValidationError.CITY_REQUIRED.toExpectation(),
                ValidationError.ADDRESS_REQUIRED.toExpectation(),
                ValidationError.ZIP_REQUIRED.toExpectation(),
                ValidationError.PHONE_REQUIRED.toExpectation()
        );
        step("Проверяем все ошибки", () -> {
            checkoutPage.assertValidationErrors(expectedErrors);
        });
    }
}
