package com.denisroyz.geofence.ui.valueExtractor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.denisroyz.geofence.R;

public class WifiNetworkNameValidator extends EditTextValueExtractor<String> {

    public WifiNetworkNameValidator(EditText editText) {
        super(editText);
    }

    @Nullable
    @Override
    protected String validateForError(Context context) {
        String value = getValue();
        if (isValidWifiName(value)) return null;
        return context.getString(R.string.wifi_network_name_error);
    }

    @Override
    public String getValue() {
        return getWrappedEditText().getText().toString();
    }

    private static boolean isValidWifiName(String wifiName) {
        return wifiName != null && !wifiName.isEmpty();
    }

}
