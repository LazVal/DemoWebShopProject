package steps;

import pages.*;
import testdata.TestData;

import static io.qameta.allure.Allure.step;

public class ShopSteps {
    HomePage homePage = new HomePage();
    TestData testData = new TestData();
    SearchResultsPage resultsPage = new SearchResultsPage();
    CartPage cartPage = new CartPage();

    public ProductPage openFirstProductCard() {
        step("Поиск товара через поисковую строку", () -> {
            homePage.searchStore(testData.SEARCH_FOR_TEXT);
        });
        return step("Выбор первой карточки товара", () -> resultsPage.clickFirstProduct());
    }

    public CartPage addProductToCart(ProductPage productPage) {
        step("Нажать на кнопку add to cart", () -> {
            productPage.clickAddToCart();
        });

        step("Проверка появления уведомления о добавлении", () -> {
            productPage.verifyNotificationAppeared(testData.NOTIFICATION_TEXT);
        });

        return step("Перейти в корзину из уведомления", () -> cartPage.shoppingCartClicked());
    }
}