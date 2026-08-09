package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends BasePage {

    @Step("Проверить наличие товара корзине")
    public CartPage verifyProductByName(String name) {
        $x("//td[@class='product']//a[text()='" + name + "']").shouldBe(visible);
        return this;
    }

}
