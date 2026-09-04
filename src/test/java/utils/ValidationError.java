package utils;

import models.FieldValidationExpectation;

public enum ValidationError {
    FIRST_NAME_REQUIRED("BillingNewAddress.FirstName", "First name is required."),
    LAST_NAME_REQUIRED("BillingNewAddress.LastName", "Last name is required."),
    EMAIL_REQUIRED("BillingNewAddress.Email", "Email is required."),
    COUNTRY_REQUIRED("BillingNewAddress.CountryId", "Country is required."),
    CITY_REQUIRED("BillingNewAddress.City", "City is required"),
    ADDRESS_REQUIRED("BillingNewAddress.Address1", "Street address is required"),
    ZIP_REQUIRED("BillingNewAddress.ZipPostalCode", "Zip / postal code is required"),
    PHONE_REQUIRED("BillingNewAddress.PhoneNumber", "Phone is required");

    private final String fieldName;
    private final String message;

    ValidationError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }


    public FieldValidationExpectation toExpectation() {
        return new FieldValidationExpectation(this.fieldName, this.message);
    }
}
