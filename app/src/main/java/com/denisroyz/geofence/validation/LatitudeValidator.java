package com.denisroyz.geofence.validation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.utils.EditTextUtil;

/**
 * Created by Heralt on 09.09.2017.
 */

public class LatitudeValidator extends EditTextValidator<Double> {

    public LatitudeValidator(EditText editText) {
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
        return EditTextUtil.getDoubleFromEditText(getWrappedEditText());
    }

    private boolean isValidLatitude(Double latitude) {
        return latitude != null && latitude >= -90 && latitude <= 90;
    }
}
