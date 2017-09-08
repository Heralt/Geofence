package com.denisroyz.geofence.service;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Heralt on 08.09.2017.
 */

public class PermissionManagerImpl implements PermissionManager {
    private static final String[] PERMISSIONS = new String[] { Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1421;

    public boolean checkPermissions(Activity activity) {
        for(String permission: PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validatePermissionResult(int requestCode, int[] grantResults) {
        if (requestCode!=REQUEST_CODE_ASK_PERMISSIONS) return false;
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void requestPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity, PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
    }
}
