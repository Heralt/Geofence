package com.denisroyz.geofence;

/**
 * Created by Heralt on 21.11.2016.
 */

public class Const {

    public interface ACTION {
        public static String MAIN_ACTION = "com.denisroyz.geofence.action.main";
        public static String START_FOREGROUND_ACTION = "com.denisroyz.geofence.action.startforeground";
        public static String STOP_FOREGROUND_ACTION = "com.denisroyz.geofence.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}
