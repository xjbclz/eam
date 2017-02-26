package com.ruwant.eam.activity;

import android.view.MenuItem;

import com.ruwant.eam.util.EamLog;

/**
 * Created by 00265372 on 2017/2/24.
 */

abstract class EditBaseActivity extends BaseActivity{

    private String TAG = "EditBaseActivity";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (android.R.id.home == id) {

            EamLog.v(TAG, "onOptionsItemSelected");

            isSaveData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        EamLog.v(TAG, "onBackPressed");

        isSaveData();
    }

    abstract void isSaveData();

}
