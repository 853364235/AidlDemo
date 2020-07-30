package com.example.testmodule.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.mylibrary.Aidlable;

public class AidlService extends Service {
    private static final String TAG = "调试";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: AidlService");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: AidlService");
        super.onDestroy();
    }

    public AidlService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private class MyBinder extends Aidlable.Stub{

        @Override
        public String getTestData() throws RemoteException {
            return "Aidl test data";
        }
    }
}
