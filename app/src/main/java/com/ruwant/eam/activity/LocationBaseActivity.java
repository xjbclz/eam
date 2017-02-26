package com.ruwant.eam.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.ruwant.eam.util.EamLog;

/**
 * Created by 00265372 on 2017/2/24.
 */

abstract class LocationBaseActivity extends BaseActivity{

    @Override
    protected void onResume() {
        super.onResume();

        resumeLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();

        pauseLocation();
    }

    @Override
    protected void onDestroy() {

        destroyLocation();

        super.onDestroy();

    }

    abstract void initLocation();
    abstract void resumeLocation();
    abstract void pauseLocation();
    abstract void destroyLocation();
}
