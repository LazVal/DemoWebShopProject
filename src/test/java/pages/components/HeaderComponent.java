package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HeaderComponent {
    private final SelenideElement registerLink = $("a[href='/register']");
    private final SelenideElement loginLink = $("a[href='/login']");
    private final SelenideElement logoutLink = $("a[href='/logout']");
    private final SelenideElement shoppingCartLink = $("#topcartlink a[href='/cart']");
    private final SelenideElement wishlistLink = $$("a[href='/wishlist']").first();

    private final SelenideElement searchInput = $("#small-searchterms");
    private final SelenideElement searchButton = $("input[value='Search']");

    public void clickLogin() {
        loginLink.click();
    }
    public void clickLogout() {
        logoutLink.click();
    }
    public void searchFor(String query) {
        searchInput.setValue(query);
        searchButton.click();
    }

    public void openCart() {
        shoppingCartLink.click();
    }

    public void openWishlist() {
        wishlistLink.click();
    }

    @Step("Проверка наличия ссылок в хэдере")
    public HeaderComponent verifyLinksAreVisible() {
        registerLink.shouldBe(visible);
        loginLink.shouldBe(visible);
        shoppingCartLink.shouldBe(visible);
        wishlistLink.shouldBe(visible);
        return this;
    }

}

