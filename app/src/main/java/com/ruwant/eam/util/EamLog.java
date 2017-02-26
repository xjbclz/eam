package com.ruwant.eam.util;

import android.util.Log;

import com.ruwant.eam.BuildConfig;

/**
 * Created by 00265372 on 2016/8/7.
 */
public class EamLog {
    private static final String	TAG	 = "rueam";

    public static void v(String tag, String msg){
        if(BuildConfig.DEBUG){
            if(tag == "") {
                Log.v(TAG, msg);
            }else {
                Log.v(tag, msg);
            }
        }
    }

    public static void d(String tag, String msg){
        if(BuildConfig.DEBUG){
            if(tag == "") {
                Log.d(TAG, msg);
            }else {
                Log.d(tag, msg);
            }
        }
    }

    public static void i(String tag, String msg){
        if(BuildConfig.DEBUG){
            if(tag == "") {
                Log.i(TAG, msg);
            }else {
                Log.i(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg){
       if(BuildConfig.DEBUG){
            if(tag == "") {
                Log.w(TAG, msg);
            }else {
                Log.w(tag, msg);
            }
       }
    }

    public static void e(String tag, String msg){
        if(BuildConfig.DEBUG){
            if(tag == "") {
                Log.e(TAG, msg);
            }else {
                Log.e(tag, msg);
            }
        }
    }

}
