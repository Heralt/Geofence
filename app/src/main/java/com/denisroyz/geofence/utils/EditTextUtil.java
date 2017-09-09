package com.denisroyz.geofence.utils;

import android.widget.EditText;

/**
 * Created by Heralt on 09.09.2017.
 */
public class EditTextUtil {

    public static Double getDoubleFromEditText(EditText editText){
        if (editText.getText().toString().isEmpty()) return null;
        try{
            return Double.parseDouble(editText.getText().toString());
        } catch (final NumberFormatException e) {
            return null;
        }
    }

}
