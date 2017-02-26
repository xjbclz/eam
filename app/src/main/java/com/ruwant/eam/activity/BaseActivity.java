package com.ruwant.eam.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ruwant.eam.R;
import com.ruwant.eam.util.EamLog;

/**
 * Created by 00265372 on 2017/2/23.
 */

abstract class BaseActivity extends AppCompatActivity {

    private String TAG = "BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        ActivityList.addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        EamLog.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        restoreData();

        EamLog.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveData();

        EamLog.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        EamLog.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {

        releaseMemory();

        ActivityList.removeActivity(this);

        super.onDestroy();

        EamLog.v(TAG, "onDestroy");
    }

    abstract void initVariables();

    abstract void initViews();

    abstract void loadData();

    abstract void saveData();

    abstract void restoreData();

    abstract void releaseMemory();
}
