package com.denisroyz.geofence.ui.valueExtractor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.denisroyz.geofence.R;

public class LatitudeValueExtractor extends EditTextValueExtractor<Double> {

    public LatitudeValueExtractor(EditText editText) {
        super(editText);
    }

    @Nullable
    @Override
    protected String validateForError(Context context) {
        Double value = getValue();
        if (isValidLatitude(value)) return null;
        return context.getString(R.string.latitude_error);
    }

    @Override
    public Double getValue() {
        return getDoubleFromEditText(getWrappedEditText());
    }

    private boolean isValidLatitude(Double latitude) {
        return latitude != null && latitude >= -90 && latitude <= 90;
    }
}
