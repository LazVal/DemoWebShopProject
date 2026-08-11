package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.AddressComponent;

import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage extends BasePage{
    SelenideElement countrySelect = $("#CountryId");
    SelenideElement stateSelect = $("#StateProvinceId");
    SelenideElement selectAgree = $("#termsofservice");
    SelenideElement checkoutButton = $("#checkout");
    ElementsCollection allOptionsCountry = countrySelect.findAll("option");
    ElementsCollection allOptionsState = stateSelect.findAll("option");

    public AddressComponent simpleAddress = new AddressComponent("");

    @Step("Принять соглашение")
    public ShoppingCartPage selectAgree() {
        selectAgree.click();
        return this;
    }

    @Step("Нажать на кнопку 'Checkout'")
    public ShoppingCartPage clickCheckoutButton() {
        checkoutButton.click();
        return this;
    }
}
