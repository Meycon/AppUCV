package com.catolica.salesianoapp.ucv.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Meycon Augusto on 12/02/2018.
 */

public final class TimeUtils {
    public static TimeUtils INSTANCE = null;
    private static Map<Integer, String> times = new HashMap<Integer, String>();

    static {
        new TimeUtils();
    }

    private TimeUtils() {
        INSTANCE = (TimeUtils) this;

        times.put(0, "12 AM");
        times.put(1, "1 AM");
        times.put(2, "2 AM");
        times.put(3, "3 AM");
        times.put(4, "4 AM");
        times.put(5, "5 AM");
        times.put(6, "6 AM");
        times.put(7, "7 AM");
        times.put(8, "8 AM");
        times.put(9, "9 AM");
        times.put(10, "10 AM");
        times.put(11, "11 AM");
        times.put(12, "12 PM");
        times.put(13, "1 PM");
        times.put(14, "2 PM");
        times.put(15, "3 PM");
        times.put(16, "4 PM");
        times.put(17, "5 PM");
        times.put(18, "6 PM");
        times.put(19, "7 PM");
        times.put(20, "8 PM");
        times.put(21, "9 PM");
        times.put(22, "10 PM");
        times.put(23, "11 PM");

    }

    public final void setTimes(Map var1) {
        times = var1;
    }

    public final String getDisplayableTime(int time) {
        String displaytime;
        if (0 <= time) {
            if (23 >= time) {
                Object var2 = times.get(time);
                if (var2 == null) {
                }

                displaytime = (String) var2;
                return displaytime;
            }
        }
        displaytime = "?";
        return displaytime;
    }


}
