package com.ruwant.eam;

import android.app.Application;
import android.content.Context;

import com.ruwant.eam.model.network.NetworkManager;
import com.ruwant.eam.util.EamLog;

/**
 * Created by Allen Lin on 2016/02/17.
 */
public class EamApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

//        EamLog.v("login", "onCreate");
//
//        if(sContext != null){
//            NetworkManager.getInstance(sContext);
//
//            EamLog.v("login", "getInstance");
//        }
    }

    public static Context getContext() {

        return sContext;
    }

}

