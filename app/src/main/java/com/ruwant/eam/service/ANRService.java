package com.ruwant.eam.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

/**
 * Created by 00265372 on 2017/1/18.
 */

public class ANRService extends Service {

    private String TAG = "ANRService ";

    private int workThreadTick = 0;
    private int mainThreadTick = 0;

    private boolean flag = true;

    //主线程响应超时时间
    private int mainThreadTimeOut = 5000;

    private Handler mHandler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG, "onCreate");

        exception();
    }

    private void exception(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag){
                    workThreadTick = mainThreadTick;

                    //向主线程发送消息 计数器值加1
                    mHandler.post(tickerRunnable);

                    try {
                        Thread.sleep(mainThreadTimeOut);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //子线程在等待5秒后，判断子线程和主线程的变量值是否相等；如果相等意味着主线程在5秒内没有响应子线程发的消息，发生了ANR异常
                    if(workThreadTick == mainThreadTick){
                        flag = false;

                        //获取主线程的堆栈信息
                        Thread mainThread = Looper.getMainLooper().getThread();
                        StackTraceElement[] stackElements = mainThread.getStackTrace();

                        String stackString = "ANR Exception\n";

                        if (stackElements != null) {
                            for (int i = 0; i < stackElements.length; i++) {
                                stackString = stackString + stackElements[i].getClassName() + "."
                                        + stackElements[i].getMethodName() + " ("
                                        + stackElements[i].getFileName() + ":"
                                        + stackElements[i].getLineNumber() + ")" + "\n";
                            }

                            Log.e(TAG, stackString);
                        }
                    }
                }
            }
        }).start();
    }

    private final Runnable tickerRunnable = new Runnable() {
        @Override public void run() {
            mainThreadTick = (mainThreadTick + 1) % 10;

            Log.e(TAG, "mainThreadTick=" + String.valueOf(mainThreadTick));
        }
    };
}
