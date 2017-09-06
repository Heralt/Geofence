package com.denisroyz.geofence.validation;

import android.content.Context;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.model.GPSRule;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GPSRuleObjectValidator implements ObjectValidator<GPSRule> {

    public static final String FIELD_LAT = "lat";
    public static final String FIELD_LON = "lon";
    public static final String FIELD_RADIUS = "radius";

    @Override
    public ObjectValidatorResult<GPSRule> validateObject(Context context, GPSRule object) {
        ObjectValidatorResult<GPSRule> objectValidatorResult = new ObjectValidatorResult<>(object);
        if (!isValidLatitude(object.getLat()))  objectValidatorResult.withError(new ObjectValidatorError(FIELD_LAT,context.getString(R.string.latitude_error)));
        if (!isValidLongitude(object.getLon())) objectValidatorResult.withError(new ObjectValidatorError(FIELD_LON, context.getString(R.string.longitude_error)));
        if (!isValidRadius(object.getRadius())) objectValidatorResult.withError(new ObjectValidatorError(FIELD_RADIUS, context.getString(R.string.radius_error)));
        return objectValidatorResult;
    }

    private boolean isValidLatitude(Double latitude) {
        return latitude != null && latitude >= -90 && latitude <= 90;
    }

    private boolean isValidLongitude(Double longitude) {
        return longitude != null && longitude >= - 180 && longitude <= 180;
    }

    private boolean isValidRadius(Double radius) {
        return radius != null && radius > 0;
    }

}
