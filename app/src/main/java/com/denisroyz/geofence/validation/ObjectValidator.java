package com.denisroyz.geofence.validation;

import android.content.Context;

/**
 * Created by Heralt on 06.09.2017.
 */

public interface ObjectValidator <T> {

    ObjectValidatorResult<T> validateObject(Context context, T object);
}
