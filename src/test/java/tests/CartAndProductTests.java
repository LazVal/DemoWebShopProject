package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;

import static io.qameta.allure.Allure.step;
@DisplayName("Корзина и товары")
@Story("Поиск товара и добавление в корзину")
public class CartAndProductTests extends BaseTest {

    @Test
    @DisplayName("Проверка поиска товара через поисковую строку")
    public void openProductCardFromSearchTest() {
        ProductPage currentProductPage = shopSteps.openFirstProductCard();
        step("Проверка результатов поиска", () -> {
            currentProductPage.verifyProductTitle(testData.SEARCH_FOR_TEXT);
        });
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void addProductToCartTest() {
        ProductPage productPage = shopSteps.openFirstProductCard();

        CartPage cartPage = shopSteps.addProductToCart(productPage);

        step("Проверить, что товар появился в корзине", () -> {
            cartPage.verifyProductByName(testData.SEARCH_FOR_TEXT);
        });
    }

}
