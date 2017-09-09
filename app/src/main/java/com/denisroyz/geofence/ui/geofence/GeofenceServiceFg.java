package com.denisroyz.geofence.ui.geofence;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.denisroyz.geofence.Const;
import com.denisroyz.geofence.GeofenceApplication;
import com.denisroyz.geofence.R;
import com.denisroyz.geofence.di.BeanTags;
import com.denisroyz.geofence.dao.UserLocation;
import com.denisroyz.geofence.repository.UserLocationRepository;
import com.denisroyz.geofence.service.GeofenceGPSManager;
import com.denisroyz.geofence.service.GeofenceReceiver;
import com.denisroyz.geofence.service.GeofenceWiFiManager;

import static android.content.ContentValues.TAG;

/**
 * Created by Heralt on 05.09.2017.
 *
 * Foreground service.
 * Creates notification in notification bar, listens for location updates.
 */
public class GeofenceServiceFg extends Service implements GeofenceReceiver.GeofenceReceiverListener {
    private static final String LOG_TAG = "GeofenceServiceFg";

    private GeofenceWiFiManager mGeofenceWiFiManager;
    private GeofenceGPSManager mGeofenceGPSManager;
    private UserLocationRepository mCurrentLocationRepository;
    private GeofenceReceiver mGeofenceReceiver;
    private Handler mHandler;

    private boolean mShouldStop = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGeofenceReceiver.removeGeofenceReceiverListener(this);
        mShouldStop = true;
    }

    public void onCreate(){
        super.onCreate();
        inject();
    }

    public void inject(){
        mHandler = new Handler();
        mCurrentLocationRepository = (UserLocationRepository) GeofenceApplication.getApplicationDIContext().getBean(BeanTags.USER_LOCATION_REPOSITORY);
        mGeofenceWiFiManager = (GeofenceWiFiManager)  GeofenceApplication.getApplicationDIContext().getBean(BeanTags.GEOFENCE_WIFI_MANAGER);
        mGeofenceGPSManager = (GeofenceGPSManager)  GeofenceApplication.getApplicationDIContext().getBean(BeanTags.GEOFENCE_GPS_MANAGER);
        mGeofenceReceiver = (GeofenceReceiver)  GeofenceApplication.getApplicationDIContext().getBean(BeanTags.GEOFENCE_RECEIVER);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent==null||intent.getAction()==null){
            String source = null == intent ? "intent" : "action";
            Log.e (TAG, source + " was null, flags=" + flags + " bits=" + Integer.toBinaryString (flags));
            return START_STICKY;
        }
        if (intent.getAction().equals(Const.ACTION.START_FOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "<< Start Foreground Intent ");
            mGeofenceGPSManager.subscribe();
            mGeofenceReceiver.addGeofenceReceiverListener(this);
            runTask();
            showNotification("Starting");
        } else if (intent.getAction().equals(
                Const.ACTION.STOP_FOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "<< Stop Foreground Intent");
            mGeofenceGPSManager.unSubscribe();
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }
    private void showNotification(String message){

        startForeground(Const.NOTIFICATION_ID.FOREGROUND_SERVICE,
                buildNotification(message));

    }

    /**
     * Does not work on api level 26
     */
    public Notification buildNotification(String content){
        Intent notificationIntent = new Intent(this, GeofenceActivity.class);
        notificationIntent.setAction(Const.ACTION.MAIN_ACTION);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        return new NotificationCompat.Builder(this, Const.NOTIFICATION_CHANNEL_ID.FOREGROUND_SERVICE)
                .setContentTitle(content)
                .setTicker("Geofence is scanning")
                .setSmallIcon( R.drawable.ic_my_location_black_24dp)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();

    }

    @Override
    public void onGeofenceStatusUpdated(boolean inside) {
        showNotification(getString(inside?R.string.in_geofence_area:R.string.not_in_geofence_area));
    }
    private Runnable task = new Runnable() {
        @Override
        public void run()  {
            if(!mShouldStop) {
                taskTick();
                runTask();
            }
        }
    };


    public void taskTick(){
        String wifiNetworkName = mGeofenceWiFiManager.getWifiNetworkName();
        Location location = mGeofenceGPSManager.getLastLocation();
        UserLocation userLocation = new UserLocation();

        userLocation.setWifiNetworkName(wifiNetworkName);
        if (location!=null){
            userLocation.setLatitude(location.getLatitude());
            userLocation.setLongitude(location.getLongitude());
        }
        Log.i(LOG_TAG, "updated current location "+userLocation.toString());
        mCurrentLocationRepository.updateUserLocation(userLocation);

    }

    private void runTask() {
        mHandler.postDelayed(task, Const.UPDATE_FREQ);
    }


}
