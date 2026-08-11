package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.AddressData;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AddressComponent {
    private final String idPrefix;

    public AddressComponent(String idPrefix) {
        this.idPrefix = idPrefix; // Может быть "BillingNewAddress", "ShippingNewAddress" или "" (пустая строка)
    }

    // Вспомогательный метод, который правильно склеивает ID
    private String buildId(String fieldName) {
        // Если префикс не задан (null или пустая строка), возвращаем просто имя поля
        if (idPrefix == null || idPrefix.isEmpty()) {
            return fieldName;
        }
        // Иначе склеиваем через нижнее подчеркивание
        return idPrefix + "_" + fieldName;
    }

    public AddressData selectRandomCountryAndState() {
        // Строим селектор через наш новый метод buildId
        SelenideElement countrySelect = $("#" + buildId("CountryId"));
        countrySelect.shouldBe(visible);

        // ... код по сбору стран ...
        List<SelenideElement> validOptions = countrySelect.findAll("option").stream()
                .filter(opt -> !opt.getValue().equals("0") && !opt.getText().equals("Select country"))
                .collect(Collectors.toList());

        Random random = new Random();
        SelenideElement randomOption = validOptions.get(random.nextInt(validOptions.size()));
        String selectedCountryName = randomOption.getText();
        countrySelect.selectOption(selectedCountryName);

        String selectedStateName = null;

        if (selectedCountryName.equals("United States")) {
            // И здесь тоже используем buildId
            SelenideElement stateSelect = $("#" + buildId("StateProvinceId"));
            stateSelect.shouldBe(visible);

            List<SelenideElement> validOptionsState = stateSelect.findAll("option").stream()
                    .filter(opt -> !opt.getValue().equals("0") && !opt.getText().equals("Select state"))
                    .collect(Collectors.toList());

            SelenideElement randomOptionState = validOptionsState.get(random.nextInt(validOptionsState.size()));
            selectedStateName = randomOptionState.getText();
            stateSelect.selectOption(selectedStateName);
        }

        return new AddressData(selectedCountryName, selectedStateName);
    }
}
