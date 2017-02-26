package com.ruwant.eam.service;

import android.app.IntentService;
import android.content.Intent;

import com.ruwant.eam.util.EamLog;

/**
 * Created by 00265372 on 2017/2/25.
 */

public class DataIntentService extends IntentService {
    public DataIntentService() {
        super("DataIntentService ");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        readData();
    }

    private void readData(){
        EamLog.v("", "readData");
    };
}