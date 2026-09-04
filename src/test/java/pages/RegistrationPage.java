package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement genderFemale = $("#gender-female");
    private final SelenideElement genderMale = $("#gender-male");
    private final SelenideElement firstName = $("#FirstName");
    private final SelenideElement lastName = $("#LastName");
    private final SelenideElement email = $("#Email");
    private final SelenideElement password = $("#Password");
    private final SelenideElement confirmPassword = $("#ConfirmPassword");
    private final SelenideElement registerButton = $("#register-button");
    private final SelenideElement registerCompleted = $("[class=result]");
    private final SelenideElement checkEmail = $$("a[href='/customer/info']").first();
    private final SelenideElement passwordMismatchError = $("span[for='ConfirmPassword']");


    @Step("Открываем страницу регистрации на сайте https://demowebshop.tricentis.com/")
    public RegistrationPage openPage() {
        open("/register");
        return this;
    }

    @Step("Выбрать пол")
    public RegistrationPage chooseGender(String value) {
        if (value.equals("Male")) {
            genderMale.click();
        } else {
            genderFemale.click();
        }
        return this;
    }

    @Step("Заполнить имя")
    public RegistrationPage typeFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    @Step("Заполнить фамилию")
    public RegistrationPage typeLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    @Step("Заполнить email")
    public RegistrationPage typeEmail(String value) {
        email.setValue(value);
        return this;
    }

    @Step("Заполнить пароль")
    public RegistrationPage typePassword(String value) {
        password.setValue(value);
        return this;
    }

    @Step("Подтвердить пароль")
    public RegistrationPage confirmPassword(String value) {
        confirmPassword.setValue(value);
        return this;
    }
    @Step("Нажать на кнопку регистрации")
    public RegistrationPage clickRegisterButton() {
        registerButton.click();
        return this;
    }
    @Step("Проверка успешной регистрации")
    public RegistrationPage checkSuccessRegistration(String value, String email) {
        registerCompleted.shouldHave(text(value));
        checkEmail.shouldHave(text(email));
        return this;
    }
    @Step("Пароли не совпадают")
    public RegistrationPage passwordDoNotMatch (String value) {
        passwordMismatchError.shouldHave(text(value));
        return this;
    }

}
