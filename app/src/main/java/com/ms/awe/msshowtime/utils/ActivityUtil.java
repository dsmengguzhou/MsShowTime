package com.ms.awe.msshowtime.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Musi
 * on 2019/1/30
 * Activity控制器
 */
public class ActivityUtil {

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
