package com.denisroyz.geofence.validation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.utils.EditTextUtil;

/**
 * Created by Heralt on 09.09.2017.
 */

public class LongitudeValidator extends EditTextValidator<Double> {

    public LongitudeValidator(EditText editText) {
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
        return EditTextUtil.getDoubleFromEditText(getWrappedEditText());
    }

    private boolean isValidLongitude(Double longitude) {
        return longitude != null && longitude >= - 180 && longitude <= 180;
    }

}
