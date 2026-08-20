package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
@DisplayName("Регистрация")
@Story("Регистрация на сайте")
public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successRegistrationTest() {
        step("Открыть страницу регистарции", () -> {
            registrationPage.openPage();
        });

        step("Заполнить форму регистрации", () -> {
            registrationPage.chooseGender("Female")
                    .typeFirstName(testData.userName)
                    .typeLastName(testData.lastName)
                    .typeEmail(testData.userEmail)
                    .typePassword(testData.password)
                    .confirmPassword(testData.password)
                    .clickRegisterButton();
        });
        step("Проверка результатов заполнения формы", () -> {
            registrationPage.checkSuccessRegistration(testData.REGISTRATION_TEXT, testData.userEmail);
        });
    }

    @Test
    @DisplayName("Пароли не совпадают")
    public void wrongConfirmPasswordTest() {
        step("Открыть страницу регистарции", () -> {
            registrationPage.openPage();
        });

        step("Заполнить форму регистрации", () -> {
            registrationPage.chooseGender(testData.gender)
                    .typeFirstName(testData.userName)
                    .typeLastName(testData.lastName)
                    .typeEmail(testData.userEmail)
                    .typePassword(testData.password)
                    .confirmPassword(testData.wrongConfirmPassword)
                    .clickRegisterButton();
        });
        step("Проверка получения ошибки 'Пароли не совпадают'", () -> {
            registrationPage.passwordDoNotMatch(testData.PASSWORD_DO_NOT_MATCH_TEXT);
        });
    }
}
