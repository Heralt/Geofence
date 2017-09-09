package com.denisroyz.geofence.validation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.EditText;

/**
 * Created by Heralt on 06.09.2017.
 */

public abstract class EditTextValidator<T> {


    private EditText editText;


    protected EditText getWrappedEditText(){
        return editText;
    }
    public EditTextValidator(EditText editText) {
        this.editText = editText;
    }

    /**
     * @return null if no error
     */
    protected abstract @Nullable String validateForError(Context context);


    public final boolean validate(Context context){
        String error = validateForError(context);
        editText.setError(error);
        return error==null;
    }

    public abstract T getValue();



}
