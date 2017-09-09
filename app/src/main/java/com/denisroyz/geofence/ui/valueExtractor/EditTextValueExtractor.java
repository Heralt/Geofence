package com.denisroyz.geofence.ui.valueExtractor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

/**
 * Created by Heralt on 06.09.2017.
 *
 * Validates {@link #editText} value.
 * Provides {@link #editText} value in formatted way.
 */
public abstract class EditTextValueExtractor<T> {

    private final EditText editText;

    protected EditText getWrappedEditText(){
        return editText;
    }
    public EditTextValueExtractor(EditText editText) {
        this.editText = editText;
    }

    /**
     * @return null if no error
     */
    protected abstract @Nullable String validateForError(Context context);

    /**
     * Checks if value is valid
     * @param context is used to get string resource
     * @return true if value is valid. Otherwise return false.
     */
    public final boolean isValueValid(Context context){
        String error = validateForError(context);
        editText.setError(error);
        return error==null;
    }

    /**
     *
     * @return {@link #editText} value in expected format
     */
    public abstract T getValue();


    Double getDoubleFromEditText(EditText editText){
        if (editText.getText().toString().isEmpty()) return null;
        try{
            return Double.parseDouble(editText.getText().toString());
        } catch (final NumberFormatException e) {
            return null;
        }
    }


}
