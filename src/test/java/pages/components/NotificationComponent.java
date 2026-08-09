package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NotificationComponent {
    private final SelenideElement notificationBarAppear = $("p.content");
    private final SelenideElement shoppingCart = $("p.content a[href='/cart']");

    public void notificationIsAppear(String value) {
        notificationBarAppear.shouldBe(visible)
                .shouldHave(text(value));
    }

    public void shoppingCartClick() {
        shoppingCart.click();
    }
}
