package pages;

import pages.components.AddressComponent;
import pages.components.HeaderComponent;
import pages.components.NotificationComponent;
import testdata.TestData;

public class BasePage {
    TestData testData = new TestData();
    NotificationComponent notification = new NotificationComponent();
    HeaderComponent header = new HeaderComponent();

    //нормально ли в BasePage писать методы??
    public BasePage verifyNotificationAppeared(String value) {
        notification.notificationIsAppear(value);
        return this;
    }

}
