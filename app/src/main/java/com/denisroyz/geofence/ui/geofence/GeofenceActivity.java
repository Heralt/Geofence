package com.denisroyz.geofence.ui.geofence;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.denisroyz.geofence.dao.GPSRule;
import com.denisroyz.geofence.dao.WifiRule;
import com.denisroyz.geofence.service.PermissionManager;
import com.denisroyz.geofence.service.PermissionManagerImpl;
import com.denisroyz.geofence.utils.CollapseExpand;
import com.denisroyz.geofence.utils.TextChangeWatcher;
import com.denisroyz.geofence.ui.valueExtractor.LatitudeValueExtractor;
import com.denisroyz.geofence.ui.valueExtractor.LongitudeValueExtractor;
import com.denisroyz.geofence.ui.valueExtractor.RadiusValueExtractor;
import com.denisroyz.geofence.ui.valueExtractor.WifiNetworkNameValidator;

public class GeofenceActivity extends AppCompatActivity implements GeofenceView, GeofenceActivityAPI{

    private LatitudeValueExtractor latitudeValidator;
    private LongitudeValueExtractor longitudeValidator;
    private RadiusValueExtractor radiusValidator;
    private WifiNetworkNameValidator wifiNetworkNameValidator;

    private GeofencePresenter mGeofencePresenter;
    private PermissionManager mPermissionManager;

    private ToggleButton toggleButton;
    private TextView statusTextView;

    private Button saveConfigurationButton;
    private View requestPermissionButton;

    private EditText wifiNetworkNameRuleEditText;
    private EditText gpsLatitudeRuleEditText;
    private EditText gpsLongitudeRuleEditText;
    private EditText gpsRadiusRuleEditText;

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
        GPSRule gpsRule = readGPSRule();
        WifiRule wifiRule = readWifiRule();
        if (gpsRule!=null&&wifiRule!=null){
            saveConfigurationButton.setEnabled(false);
            mGeofencePresenter.save(gpsRule);
            mGeofencePresenter.save(wifiRule);
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


    private WifiRule readWifiRule(){
        if (wifiNetworkNameValidator.isValueValid(this)){
            WifiRule wifiRule = new WifiRule();
            wifiRule.setWifiNetworkName(wifiNetworkNameValidator.getValue());
            return wifiRule;
        }
        return null;
    }

    private GPSRule readGPSRule(){
        boolean haveErrors = false;
        if (!latitudeValidator.isValueValid(this)) haveErrors = true;
        if (!longitudeValidator.isValueValid(this)) haveErrors = true;
        if (!radiusValidator.isValueValid(this)) haveErrors = true;
        if (!haveErrors){
            GPSRule gpsRule = new GPSRule();
            gpsRule.setLatLng(
                    latitudeValidator.getValue(),
                    longitudeValidator.getValue()
            );
            gpsRule.setRadius(radiusValidator.getValue());
            return gpsRule;
        }
        return null;
    }

    private void onToggleButtonClick(){
        mGeofencePresenter.enableSearch(toggleButton.isChecked());
    }


    private void initDependencies(){
        mGeofencePresenter = new GeofencePresenterImpl(this, this);
        mPermissionManager = new PermissionManagerImpl();
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
        wifiNetworkNameValidator = new WifiNetworkNameValidator(wifiNetworkNameRuleEditText);
        latitudeValidator = new LatitudeValueExtractor(gpsLatitudeRuleEditText);
        longitudeValidator = new LongitudeValueExtractor(gpsLongitudeRuleEditText);
        radiusValidator = new RadiusValueExtractor(gpsRadiusRuleEditText);
    }


}
