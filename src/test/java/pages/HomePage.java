package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage{
    private final SelenideElement titleMainPage = $("h2.topic-html-content-header");

    @Step("Проверка заголовка 'Welcome to our store'")
    public HomePage checkTitle(String value) {
        titleMainPage.shouldHave(text(value));
        return this;
    }

    @Step("Проверка наличия ссылок в хэдере")
    public HomePage checkHeaderLink() {
        header.verifyLinksAreVisible();
        return this;
    }

    @Step("Поиск товара через поисковую строку")
    public HomePage searchStore(String value) {
        header.searchFor(value);
        return this;
    }

}
