package com.denisroyz.geofence.ui.geofence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.model.GPSRule;
import com.denisroyz.geofence.model.WifiRule;

public class GeofenceActivity extends AppCompatActivity implements GeofenceView{

    GeofencePresenter mGeofencePresenter;

    ToggleButton toggleButton;
    TextView statusTextView;


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
    }

    private void bindViews(){
        statusTextView = (TextView) findViewById(R.id.status_text_view);
        toggleButton = (ToggleButton) findViewById(R.id.geofence_sensors_toggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onToggleButtonClick();
            }
        });
    }

    private void onToggleButtonClick(){
        mGeofencePresenter.enableSearch(toggleButton.isChecked());
    }

    @Override
    public void displayGeofenceStatus(boolean geoFenceStatus) {
        statusTextView.setText(geoFenceStatus?R.string.in_geofence_area:R.string.not_in_geofence_area);
    }

    @Override
    public void displayRulesPicker(GPSRule gpsRule, WifiRule wifiRule) {

    }
}
