package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;

import static io.qameta.allure.Allure.step;

public class CartAndProductTests extends BaseTest {

    @Test
    @DisplayName("Проверка поиска товара через поисковую строку")
    public void openProductCardFromSearchTest() {
        ProductPage currentProductPage = openFirstProductCard();
        step("Проверка результатов поиска", () -> {
            currentProductPage.verifyProductTitle(testData.SEARCH_FOR_TEXT);
        });
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void addProductToCartTest() {
        ProductPage productPage = openFirstProductCard();

        CartPage cartPage = addProductToCart(productPage);

        step("Проверить, что товар появился в корзине", () -> {
            cartPage.verifyProductByName(testData.SEARCH_FOR_TEXT);
        });
    }


    private ProductPage openFirstProductCard() {
        step("Поиск товара через поисковую строку", () -> {
            homePage.searchStore(testData.SEARCH_FOR_TEXT);
        });
         return step("Выбор первой карточки товара", () -> {
            return resultsPage.clickFirstProduct();
        });
    }

    private CartPage addProductToCart(ProductPage productPage) {
        step("Нажать на кнопку add to cart", () -> {
            productPage.clickAddToCart();
        });

        step("Проверка появления уведомления о добавлении", () -> {
            productPage.verifyNotificationAppeared(testData.NOTIFICATION_TEXT);
        });

        step("Перейти в корзину из уведомления", () -> {
            productPage.shoppingCartClicked();
        });
        return cartPage;
    }

}
