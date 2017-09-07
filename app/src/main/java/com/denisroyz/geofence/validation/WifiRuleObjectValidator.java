package com.denisroyz.geofence.validation;

import android.content.Context;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;

/**
 * Created by Heralt on 06.09.2017.
 */

public class WifiRuleObjectValidator implements ObjectValidator<WifiRule> {

    public static final String FIELD_NETWORK_NAME = "wifi_network_name";

    @Override
    public ObjectValidatorResult<WifiRule> validateObject(Context context, WifiRule object) {
        ObjectValidatorResult<WifiRule> objectValidatorResult = new ObjectValidatorResult<>(object);
        if (!isValidWifiName(object.getWifiNetworkName()))  objectValidatorResult.withError(new ObjectValidatorError(FIELD_NETWORK_NAME,context.getString(R.string.latitude_error)));
        return objectValidatorResult;
    }


    private static boolean isValidWifiName(String wifiName) {
        return wifiName != null && !wifiName.isEmpty();
    }


}
