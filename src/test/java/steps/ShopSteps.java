package steps;

import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import testdata.TestData;
import tests.BaseTest;

import static io.qameta.allure.Allure.step;

public class ShopSteps {

    private final HomePage homePage;
    private final SearchResultsPage resultsPage;
    private final TestData testData;
    private final CartPage cartPage;

    public ShopSteps(HomePage homePage, SearchResultsPage resultsPage, TestData testData, CartPage cartPage) {
        this.homePage = homePage;
        this.resultsPage = resultsPage;
        this.testData = testData;
        this.cartPage = cartPage;
    }

    public ProductPage openFirstProductCard() {
        step("Поиск товара через поисковую строку", () -> {
            homePage.searchStore(testData.SEARCH_FOR_TEXT);
        });
        return step("Выбор первой карточки товара", () -> {
            return resultsPage.clickFirstProduct();
        });
    }

    public CartPage addProductToCart(ProductPage productPage) {
        step("Нажать на кнопку add to cart", () -> {
            productPage.clickAddToCart();
        });

        step("Проверка появления уведомления о добавлении", () -> {
            productPage.verifyNotificationAppeared(testData.NOTIFICATION_TEXT);
        });

        return step("Перейти в корзину из уведомления", () -> {
            return cartPage.shoppingCartClicked();
        });
    }
}