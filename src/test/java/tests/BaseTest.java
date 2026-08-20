package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.*;
import steps.ShopSteps;
import testdata.TestData;

import java.util.Map;

public class BaseTest {
    RegistrationPage registrationPage = new RegistrationPage();
    HomePage homePage = new HomePage();
    TestData testData = new TestData();
    SearchResultsPage resultsPage = new SearchResultsPage();
    CartPage cartPage = new CartPage();
    ProductPage productPage = new ProductPage();
    ShoppingCartPage  shoppingCartPage = new ShoppingCartPage();

    CheckoutPage checkoutPage = new CheckoutPage();
    ShopSteps shopSteps = new ShopSteps(homePage, resultsPage, testData, cartPage);

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    public static void setupSelenideEnv() {
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080"); //расширение браузера
        Configuration.baseUrl =System.getProperty("URL","https://demowebshop.tricentis.com/"); //
        Configuration.browser = System.getProperty("BROWSER", "chrome");//
        Configuration.browserVersion = System.getProperty("BROWSER_VERSION");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("HEADLESS","false"));


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        //Configuration.remote = System.getProperty("REMOTE");
        Configuration.remote = System.getProperty("https://user1:1234@selenoid.qa.guru/wd/hub");

    }
    @BeforeEach
    public void setUp() {
        Selenide.open(Configuration.baseUrl);
    }

    @AfterEach
    void closeWebDriver() {
        Attach.screenshootAs("Last screenshoot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
