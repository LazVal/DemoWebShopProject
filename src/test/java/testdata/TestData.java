package testdata;

import net.datafaker.Faker;
import utils.RandomUtils;
import java.util.Locale;

public class TestData {
    public final String WELCOME_TO_THE_SHOP_TEXT = "Welcome to our store";
    public final String REGISTRATION_TEXT = "Your registration completed";
    public final String PASSWORD_DO_NOT_MATCH_TEXT = "The password and confirmation password do not match.";
    public final String SEARCH_FOR_TEXT = "Health Book";
    public final String NOTIFICATION_TEXT = "The product has been added to your shopping cart";
    Faker faker = new Faker();
    Faker fakerRu = new Faker(new Locale("ru"));
    public String userName = fakerRu.name().firstName();
    public String lastName = fakerRu.name().lastName();
    public String userEmail = faker.internet().emailAddress();
    public String userEmailNegative = RandomUtils.getRandomNegativeEmail();
    public String gender = RandomUtils.getRandomGender();
    public String password = RandomUtils.getRandomString(6);
    public String wrongConfirmPassword = RandomUtils.getRandomString(6);

    public String userCity = faker.address().city();
    public String userAddress = faker.address().fullAddress();
    public String userPrefix = faker.phoneNumber().subscriberNumber(7);
    public String userPhoneNumber = faker.phoneNumber().subscriberNumber(10);;
}
