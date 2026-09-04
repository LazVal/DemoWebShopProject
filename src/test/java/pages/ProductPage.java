package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends BasePage{
    private final SelenideElement productTitle = $("h1");
    private final SelenideElement productPrice = $(".product-price span");
    private final SelenideElement addToCartButton = $("#add-to-cart-button-22");

    @Step("Проверка названия товара на странице")
    public ProductPage verifyProductTitle(String value) {
        productTitle.shouldHave(text(value));
        return this;
    }

    @Step("Добавить товар в корзину")
    public ProductPage clickAddToCart() {
        addToCartButton.click();
        return this;
    }

//    @Step("Получение стоимости товара")
//    public String getProductPrice() {
//        return productPrice.getText();
//    }
}
