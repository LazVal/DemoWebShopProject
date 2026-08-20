package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
@DisplayName("Главная страница")
@Story("Открытие главной страницы магазина")
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
