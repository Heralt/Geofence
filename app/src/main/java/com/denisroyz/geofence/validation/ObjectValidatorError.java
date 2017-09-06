package com.denisroyz.geofence.validation;

/**
 * Created by Heralt on 06.09.2017.
 */

public class ObjectValidatorError {
    private String message;
    private String fieldName;

    public ObjectValidatorError(String fieldName, String message) {
        this.message = message;
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }
}
