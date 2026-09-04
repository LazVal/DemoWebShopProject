package pages;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends BasePage {

    @Step("Проверить наличие товара корзине")
    public CartPage verifyProductByName(String name) {
        $x("//td[@class='product']//a[text()='" + name + "']").shouldBe(visible);
        return this;
    }
    @Step("Перейти в корзину из уведомления")
    public CartPage shoppingCartClicked() {
        notification.shoppingCartClick();
        return this;
    }

}
