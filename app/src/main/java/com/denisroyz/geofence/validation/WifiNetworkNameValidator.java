package com.denisroyz.geofence.validation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.utils.EditTextUtil;

/**
 * Created by Heralt on 09.09.2017.
 */

public class WifiNetworkNameValidator extends EditTextValidator<String> {

    public WifiNetworkNameValidator(EditText editText) {
        super(editText);
    }

    @Nullable
    @Override
    protected String validateForError(Context context) {
        String value = getValue();
        if (isValidWifiName(value)) return null;
        return context.getString(R.string.wifi_network_name);
    }

    @Override
    public String getValue() {
        return getWrappedEditText().getText().toString();
    }

    private static boolean isValidWifiName(String wifiName) {
        return wifiName != null && !wifiName.isEmpty();
    }

}
