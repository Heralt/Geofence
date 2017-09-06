package com.denisroyz.geofence.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heralt on 06.09.2017.
 */

public class ObjectValidatorResult<T> {
    private boolean isValid = true;
    private List<ObjectValidatorError> errors;
    private T object;

    public ObjectValidatorResult(T object){
        this.object = object;
        this.errors = new ArrayList<>();
    }
    public void withError(ObjectValidatorError error){
        errors.add(error);
        isValid = false;
    }
    public T getObject(){
        return object;
    }

    public boolean isValid(){
        return isValid;
    }

    public ObjectValidatorError getError(String key){
        for (ObjectValidatorError objectValidatorError: errors){
            if (objectValidatorError.getFieldName().equalsIgnoreCase(key))
                return objectValidatorError;
        }
        return null;
    }
    public boolean haveError(String key){
        return getError(key)!=null;
    }
}
