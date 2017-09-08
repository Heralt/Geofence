package com.denisroyz.geofence.service;

import android.app.Activity;

/**
 * Created by Heralt on 08.09.2017.
 */

public interface PermissionManager {

    boolean checkPermissions(Activity activity) ;
    boolean validatePermissionResult(int requestCode, int[] grantResults);
    void requestPermissions(Activity activity);
}
