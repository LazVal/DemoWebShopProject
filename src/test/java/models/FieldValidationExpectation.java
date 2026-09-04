package models;

public class FieldValidationExpectation {
    private final String fieldName;
    private final String expectedMessage;

    public FieldValidationExpectation(String fieldName, String expectedMessage) {
        this.fieldName = fieldName;
        this.expectedMessage = expectedMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
}
