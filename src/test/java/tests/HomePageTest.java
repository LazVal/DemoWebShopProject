package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class HomePageTest extends BaseTest {

    @Test
    @DisplayName("Проверка открытия главной страницы магазина")
    public void successOpenHomePage() {
        step("Проверка открытия главной страницы магазина", () -> {
            homePage
                    .checkTitle(testData.WELCOME_TO_THE_SHOP_TEXT)
                    .checkHeaderLink();
        });
    }

}
