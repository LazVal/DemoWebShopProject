package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class SearchResultsPage extends BasePage{
    private final SelenideElement firstProductCart = $("div.product-item .product-title a");
    @Step("Выбрать первый товар из списка")
    public ProductPage clickFirstProduct() {
        firstProductCart.shouldNotBe(empty).click();
        return new ProductPage();
    }
}

