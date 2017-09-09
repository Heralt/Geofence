package com.denisroyz.geofence.ui.valueExtractor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.denisroyz.geofence.R;

public class LongitudeValueExtractor extends EditTextValueExtractor<Double> {

    public LongitudeValueExtractor(EditText editText) {
        super(editText);
    }

    @Nullable
    @Override
    protected String validateForError(Context context) {
        Double value = getValue();
        if (isValidLongitude(value)) return null;
        return context.getString(R.string.longitude_error);
    }

    @Override
    public Double getValue() {
        return getDoubleFromEditText(getWrappedEditText());
    }

    private boolean isValidLongitude(Double longitude) {
        return longitude != null && longitude >= - 180 && longitude <= 180;
    }

}
