package com.cblue.a3dgame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by pavel on 2016/7/5.
 */
public class DownLoadService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //使用线程下载数据
        Log.i("aaa","数据下载Service");
        return super.onStartCommand(intent, flags, startId);
    }
}
