package com.denisroyz.geofence.ui.geofence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ToggleButton;

import com.denisroyz.geofence.R;

public class GeofenceActivity extends AppCompatActivity {

    GeofencePresenterImpl mGeofencePresenter;

    ToggleButton toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofence);
        initDependencies();
        bindViews();
    }

    private void initDependencies(){
        mGeofencePresenter = new GeofencePresenterImpl();
    }

    private void bindViews(){
        toggleButton = (ToggleButton) findViewById(R.id.geofence_toggle_sensors);
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
}
