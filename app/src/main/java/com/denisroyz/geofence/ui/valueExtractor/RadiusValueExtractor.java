package com.denisroyz.geofence.ui.valueExtractor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.denisroyz.geofence.R;

/**
 * Created by Heralt on 09.09.2017.
 */

public class RadiusValueExtractor extends EditTextValueExtractor<Double> {

    public RadiusValueExtractor(EditText editText) {
        super(editText);
    }

    @Nullable
    @Override
    protected String validateForError(Context context) {
        Double value = getValue();
        if (isValidRadius(value)) return null;
        return context.getString(R.string.radius_error);
    }

    @Override
    public Double getValue() {
        return getDoubleFromEditText(getWrappedEditText());
    }

    private boolean isValidRadius(Double radius) {
        return radius != null && radius > 0;
    }


}
