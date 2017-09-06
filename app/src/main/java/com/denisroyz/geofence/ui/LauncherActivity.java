package com.denisroyz.geofence.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.denisroyz.geofence.R;
import com.denisroyz.geofence.ui.geofence.GeofenceActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Intent intent = new Intent(this, GeofenceActivity.class);
        startActivity(intent);
    }
}
