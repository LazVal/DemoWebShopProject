package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.FieldValidationExpectation;
import pages.components.AddressComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutPage extends BasePage {
    SelenideElement inputFirstName = $("#BillingNewAddress_FirstName");
    SelenideElement inputLastName = $("#BillingNewAddress_LastName");
    SelenideElement inputEmail = $("#BillingNewAddress_Email");
    SelenideElement inputCity = $("#BillingNewAddress_City");
    SelenideElement inputAddress = $("#BillingNewAddress_Address1");
    SelenideElement inputZipPostalCode = $("#BillingNewAddress_ZipPostalCode");
    SelenideElement inputPhoneNumber = $("#BillingNewAddress_PhoneNumber");
    SelenideElement checkoutAsGuestButton = $("[value='Checkout as Guest']");
    SelenideElement clickOnBillingSave = $("[onclick=\"Billing.save()\"]");
    SelenideElement clickOnShoppingSave = $("[onclick=\"Shipping.save()\"]");
    SelenideElement clickOnShippingMethodSave = $("[onclick=\"ShippingMethod.save()\"]");
    SelenideElement clickOnPaymentMethodSave = $("[onclick=\"PaymentMethod.save()\"]");
    SelenideElement clickOnPaymentInfoSave = $("[onclick=\"PaymentInfo.save()\"]");
    SelenideElement clickOnConfirmOrderSave = $("[onclick=\"ConfirmOrder.save()\"]");
    SelenideElement successfullyOrderText = $x("//strong[text()='Your order has been successfully processed!']");
    SelenideElement orderNumberElement = $("ul.details li:first-child");
    public AddressComponent billingAddress = new AddressComponent("BillingNewAddress");


    @Step("Нажать на кнопку 'Checkout as a guest")
    public CheckoutPage clickCheckoutAsGuestButton() {
        checkoutAsGuestButton.click();
        return this;
    }

    @Step("Ввести имя")
    public CheckoutPage inputName(String value) {
        inputFirstName.setValue(value);
        return this;
    }
    @Step("Ввести имя")
    public CheckoutPage inputLastName(String value) {
        inputLastName.setValue(value);
        return this;
    }
    @Step("Ввести имя")
    public CheckoutPage inputEmail(String value) {
        inputEmail.setValue(value);
        return this;
    }
    @Step("Ввести город")
    public CheckoutPage inputCity(String value) {
        inputCity.setValue(value);
        return this;
    }
    @Step("Ввести адрес")
    public CheckoutPage inputAddress(String value) {
        inputAddress.setValue(value);
        return this;
    }
    @Step("Ввести почтовый индекс")
    public CheckoutPage inputZipPostalCode(String value) {
        inputZipPostalCode.setValue(value);
        return this;
    }
    @Step("Ввести телефон")
    public CheckoutPage inputPhoneNumber(String value) {
        inputPhoneNumber.setValue(value);
        return this;
    }
    @Step("Продолжить")
    public CheckoutPage clickOnBillingSave() {
        clickOnBillingSave.click();
        return this;
    }
    @Step("Продолжить")
    public CheckoutPage clickOnShoppingSave() {
        clickOnShoppingSave.click();
        return this;
    }
    @Step("Продолжить")
    public CheckoutPage clickOnShippingMethodSave() {
        clickOnShippingMethodSave.click();
        return this;
    }
    @Step("Продолжить")
    public CheckoutPage clickOnPaymentMethodSave() {
        clickOnPaymentMethodSave.click();
        return this;
    }
    @Step("Продолжить")
    public CheckoutPage clickOnPaymentInfoSave() {
        clickOnPaymentInfoSave.click();
        return this;
    }
    @Step("Заказать товар")
    public CheckoutPage clickOnConfirmOrderSave() {
        clickOnConfirmOrderSave.click();
        return this;
    }
    @Step("Проверка успешного оформления товара")
    public CheckoutPage checkSuccessOrder() {
        successfullyOrderText.shouldBe(visible);
        orderNumberElement.shouldHave(text("Order number:"));
        String text = orderNumberElement.getText();
        assertThat(text).matches("Order number: \\d+");

        return this;
    }

    @Step("Завершить оформление заказа (все сохранения)")
    public CheckoutPage completeCheckoutSteps() {
        clickOnBillingSave.click();
        clickOnShoppingSave.click();
        clickOnShippingMethodSave.click();
        clickOnPaymentMethodSave.click();
        clickOnPaymentInfoSave.click();
        clickOnConfirmOrderSave.click();
        return this;
    }

    @Step("Проверить ошибки валидации для полей: {expectations}")
    public void assertValidationErrors(List<FieldValidationExpectation> expectations) {
        expectations.forEach(exp -> {
            step(String.format("Поле '%s' должно показывать ошибку: '%s'", exp.getFieldName(), exp.getExpectedMessage()), () -> {
                $(String.format("[data-valmsg-for='%s']", exp.getFieldName()))
                        .shouldBe(visible)
                        .shouldHave(cssClass("field-validation-error"))
                        .shouldHave(text(exp.getExpectedMessage()));
            });
        });
    }
}

