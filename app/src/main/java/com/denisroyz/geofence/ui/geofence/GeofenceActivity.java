package com.denisroyz.geofence.ui.geofence;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;
import com.denisroyz.geofence.service.PermissionManager;
import com.denisroyz.geofence.service.PermissionManagerImpl;
import com.denisroyz.geofence.utils.CollapseExpand;
import com.denisroyz.geofence.utils.TextChangeWatcher;
import com.denisroyz.geofence.validation.GPSRuleObjectValidator;
import com.denisroyz.geofence.validation.ObjectValidatorError;
import com.denisroyz.geofence.validation.ObjectValidatorResult;
import com.denisroyz.geofence.validation.WifiRuleObjectValidator;

public class GeofenceActivity extends AppCompatActivity implements GeofenceView, GeofenceActivityAPI{

    GPSRuleObjectValidator gpsRuleObjectValidator;
    WifiRuleObjectValidator wifiRuleObjectValidator;
    GeofencePresenter mGeofencePresenter;
    PermissionManager mPermissionManager;

    ToggleButton toggleButton;
    TextView statusTextView;

    Button saveConfigurationButton;
    View requestPermissionButton;
    View layoutGeofenceStatus;

    EditText wifiNetworkNameRuleEditText;
    EditText gpsLatitudeRuleEditText;
    EditText gpsLongitudeRuleEditText;
    EditText gpsRadiusRuleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofence);
        initDependencies();
        bindViews();

    }

    @Override
    protected void onResume(){
        super.onResume();
        mGeofencePresenter.fillView();
    }

    @Override
    public boolean checkPermissions(){
        return mPermissionManager.checkPermissions(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        boolean gotPermissions = mPermissionManager.validatePermissionResult(requestCode, grantResults);
        mGeofencePresenter.savePermissionState(gotPermissions);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGeofencePresenter.subscribe();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mGeofencePresenter.unSubscribe();
    }

    @Override
    public void displayRulesPicker(GPSRule gpsRule, WifiRule wifiRule) {
        wifiNetworkNameRuleEditText.setText(wifiRule.getWifiNetworkName());
        gpsLatitudeRuleEditText.setText(String.valueOf(gpsRule.getLat()));
        gpsLongitudeRuleEditText.setText(String.valueOf(gpsRule.getLon()));
        gpsRadiusRuleEditText.setText(String.valueOf(gpsRule.getRadius()));
    }

    @Override
    public void displayGeofenceStatus(boolean geoFenceStatus) {
        statusTextView.setText(geoFenceStatus?R.string.in_geofence_area:R.string.not_in_geofence_area);
    }
    @Override
    public void displayGeoFenceEnabled(boolean isSearchEnabled) {
        toggleButton.setChecked(isSearchEnabled);
    }

    @Override
    public void displayPermissionRequestView(boolean visible) {
        if (visible){
            CollapseExpand.expand(requestPermissionButton);
        } else {
            CollapseExpand.collapse(requestPermissionButton);
        }
    }

    private void onRequestPermissionButtonClick(){
        mPermissionManager.requestPermissions(this);
    }

    private void onConfigurationTextChange(){
        saveConfigurationButton.setEnabled(true);
    }

    private void onSaveConfigurationButtonClick(){
        ObjectValidatorResult<GPSRule> gpsRule = readGPSRule();
        ObjectValidatorResult<WifiRule> wifiRule = readWifiRule();
        if (gpsRule.isValid()&&wifiRule.isValid()){
            saveConfigurationButton.setEnabled(false);
            mGeofencePresenter.save(gpsRule.getObject());
            mGeofencePresenter.save(wifiRule.getObject());
            hideKeyboard();
        }

    }

    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private ObjectValidatorResult<WifiRule> readWifiRule(){
        WifiRule wifiRule = new WifiRule();
        if (!wifiNetworkNameRuleEditText.getText().toString().isEmpty())
            wifiRule.setWifiNetworkName(wifiNetworkNameRuleEditText.getText().toString());
        ObjectValidatorResult<WifiRule> validatedWifiRule = wifiRuleObjectValidator.validateObject(this, wifiRule);
        processValidationError(wifiNetworkNameRuleEditText, validatedWifiRule.getError(WifiRuleObjectValidator.FIELD_NETWORK_NAME));
        return validatedWifiRule;
    }

    private ObjectValidatorResult<GPSRule> readGPSRule(){
        GPSRule gpsRule = new GPSRule();
        if (!gpsLatitudeRuleEditText.getText().toString().isEmpty())
            gpsRule.setLat( Double.parseDouble(gpsLatitudeRuleEditText.getText().toString()));
        if (!gpsLongitudeRuleEditText.getText().toString().isEmpty())
            gpsRule.setLon( Double.parseDouble(gpsLongitudeRuleEditText.getText().toString()));
        if (!gpsRadiusRuleEditText.getText().toString().isEmpty())
            gpsRule.setRadius( Double.parseDouble(gpsRadiusRuleEditText.getText().toString()));
        ObjectValidatorResult<GPSRule> validatedGPSRule = gpsRuleObjectValidator.validateObject(this, gpsRule);
        processValidationError(gpsLatitudeRuleEditText, validatedGPSRule.getError(GPSRuleObjectValidator.FIELD_LAT));
        processValidationError(gpsLongitudeRuleEditText, validatedGPSRule.getError(GPSRuleObjectValidator.FIELD_LON));
        processValidationError(gpsRadiusRuleEditText, validatedGPSRule.getError(GPSRuleObjectValidator.FIELD_RADIUS));
        return validatedGPSRule;
    }

    private void processValidationError(EditText editText,@Nullable  ObjectValidatorError objectValidatorError){
        if (objectValidatorError==null){
            editText.setError(null);
        } else {
            editText.setError(objectValidatorError.getMessage());
        }
    }

    private void onToggleButtonClick(){
        mGeofencePresenter.enableSearch(toggleButton.isChecked());
    }


    private void initDependencies(){
        mGeofencePresenter = new GeofencePresenterImpl(this, this);
        mPermissionManager = new PermissionManagerImpl();
        gpsRuleObjectValidator = new GPSRuleObjectValidator();
        wifiRuleObjectValidator = new WifiRuleObjectValidator();
    }


    /**
     * In production i'd prefer to use library like ButterKnife for view-binding, or move all
     * bindings to ViewHolder class.
     */
    private void bindViews(){
        wifiNetworkNameRuleEditText = findViewById(R.id.geofence_configuration_wifi_name_et);
        gpsLatitudeRuleEditText = findViewById(R.id.geofence_configuration_gps_lat);
        gpsLongitudeRuleEditText = findViewById(R.id.geofence_configuration_gps_lon);
        gpsRadiusRuleEditText = findViewById(R.id.geofence_configuration_gps_radius);
        saveConfigurationButton = findViewById(R.id.geofence_configuration_save_button);
        requestPermissionButton = findViewById(R.id.geofence_permission_lay);
        layoutGeofenceStatus = findViewById(R.id.geofence_status_lay);
        statusTextView = findViewById(R.id.status_text_view);
        toggleButton = findViewById(R.id.geofence_sensors_toggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onToggleButtonClick();
            }
        });
        requestPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRequestPermissionButtonClick();
            }
        });
        saveConfigurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveConfigurationButtonClick();
            }
        });
        TextWatcher textWatcher = new TextChangeWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onConfigurationTextChange();
            }
        };
        gpsLatitudeRuleEditText.addTextChangedListener(textWatcher);
        gpsLongitudeRuleEditText.addTextChangedListener(textWatcher);
        gpsRadiusRuleEditText.addTextChangedListener(textWatcher);
        wifiNetworkNameRuleEditText.addTextChangedListener(textWatcher);
        gpsRadiusRuleEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onSaveConfigurationButtonClick();
                    return true;
                }
                return false;
            }
        });
    }


}
