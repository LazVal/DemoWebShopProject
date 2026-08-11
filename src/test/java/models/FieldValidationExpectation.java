package models;

public class FieldValidationExpectation {
    private final String fieldName;        // значение атрибута data-valmsg-for
    private final String expectedMessage;  // ожидаемый текст ошибки

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
