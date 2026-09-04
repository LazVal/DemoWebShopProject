package pages.components;
import com.codeborne.selenide.SelenideElement;
import data.AddressData;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AddressComponent {
    private final String idPrefix;

    public AddressComponent(String idPrefix) {
        this.idPrefix = idPrefix;
    }

    private String buildId(String fieldName) {
        if (idPrefix == null || idPrefix.isEmpty()) {
            return fieldName;
        }
        return idPrefix + "_" + fieldName;
    }

    public AddressData selectRandomCountryAndState() {

        SelenideElement countrySelect = $("#" + buildId("CountryId"));
        countrySelect.shouldBe(visible);

        List<SelenideElement> validOptions = countrySelect.findAll("option").stream()
                .filter(opt -> !opt.getValue().equals("0") && !opt.getText().equals("Select country"))
                .collect(Collectors.toList());

        Random random = new Random();
        SelenideElement randomOption = validOptions.get(random.nextInt(validOptions.size()));
        String selectedCountryName = randomOption.getText();
        countrySelect.selectOption(selectedCountryName);

        String selectedStateName = null;

        if (selectedCountryName.equals("United States")) {
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
