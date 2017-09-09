package com.denisroyz.geofence.service;

import android.app.Activity;

/**
 * Created by Heralt on 09.09.2017.
 *
 * Mock permission manager impl.
 * Mock flavor does not need any permission.
 */
public class PermissionManagerImpl implements PermissionManager {
    @Override
    public boolean checkPermissions(Activity activity) {
        return true;
    }

    @Override
    public boolean validatePermissionResult(int requestCode, int[] grantResults) {
        return true;
    }

    @Override
    public void requestPermissions(Activity activity) {

    }
}
