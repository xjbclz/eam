package com.ruwant.eam.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 00265372 on 2017/2/23.
 */

public class ActivityList {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
