package tests;

import models.FieldValidationExpectation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;

import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.Allure.step;

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

        step("Оформить заказ без регистрации", () -> {
            checkoutPage.clickCheckoutAsGuestButton();
        });
        step("Нажать на кнопку 'Continue''", () -> {
            checkoutPage.clickOnBillingSave();
        });
        List<FieldValidationExpectation> expectedErrors = Arrays.asList(
                new FieldValidationExpectation("BillingNewAddress.FirstName", "First name is required."),
                new FieldValidationExpectation("BillingNewAddress.LastName", "Last name is required.")
        );

        // 4. Проверяем все ошибки
        checkoutPage.assertValidationErrors(expectedErrors);
    }
}
