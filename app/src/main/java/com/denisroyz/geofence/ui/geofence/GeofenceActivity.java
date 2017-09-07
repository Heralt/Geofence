package com.denisroyz.geofence.ui.geofence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;
import com.denisroyz.geofence.validation.GPSRuleObjectValidator;
import com.denisroyz.geofence.validation.ObjectValidatorError;
import com.denisroyz.geofence.validation.ObjectValidatorResult;
import com.denisroyz.geofence.validation.WifiRuleObjectValidator;

public class GeofenceActivity extends AppCompatActivity implements GeofenceView{

    GPSRuleObjectValidator gpsRuleObjectValidator;
    WifiRuleObjectValidator wifiRuleObjectValidator;
    GeofencePresenter mGeofencePresenter;

    ToggleButton toggleButton;
    TextView statusTextView;

    Button saveConfigurationButton;

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
        mGeofencePresenter.fillView();
    }


    private void initDependencies(){
        mGeofencePresenter = new GeofencePresenterImpl(this);
        gpsRuleObjectValidator = new GPSRuleObjectValidator();
        wifiRuleObjectValidator = new WifiRuleObjectValidator();
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

    private void bindViews(){
        wifiNetworkNameRuleEditText = findViewById(R.id.geofence_configuration_wifi_name_et);
        gpsLatitudeRuleEditText = findViewById(R.id.geofence_configuration_gps_lat);
        gpsLongitudeRuleEditText = findViewById(R.id.geofence_configuration_gps_lon);
        gpsRadiusRuleEditText = findViewById(R.id.geofence_configuration_gps_radius);
        saveConfigurationButton = findViewById(R.id.geofence_configuration_save_button);
        statusTextView = findViewById(R.id.status_text_view);
        toggleButton = findViewById(R.id.geofence_sensors_toggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onToggleButtonClick();
            }
        });
        saveConfigurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveConfigurationButtonClick();
            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onConfigurationTextChange();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        gpsLatitudeRuleEditText.addTextChangedListener(textWatcher);
        gpsLongitudeRuleEditText.addTextChangedListener(textWatcher);
        gpsRadiusRuleEditText.addTextChangedListener(textWatcher);
        wifiNetworkNameRuleEditText.addTextChangedListener(textWatcher);

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
}
