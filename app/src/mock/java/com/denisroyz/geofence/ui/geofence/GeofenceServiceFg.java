package com.denisroyz.geofence.ui.geofence;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.denisroyz.geofence.Const;
import com.denisroyz.geofence.R;

/**
 * Created by Heralt on 05.09.2017.
 */

public class GeofenceServiceFg extends Service {
    private static final String LOG_TAG = "GeofenceServiceFg";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Const.ACTION.START_FOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "<< Start Foreground Intent ");
            startForeground(Const.NOTIFICATION_ID.FOREGROUND_SERVICE,
                    buildNotification("Starting"));
        } else if (intent.getAction().equals(
                Const.ACTION.STOP_FOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "<< Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
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

}
