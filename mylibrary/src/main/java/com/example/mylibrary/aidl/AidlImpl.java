package com.example.mylibrary.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.mylibrary.Aidlable;

public class AidlImpl {
    private static final String TAG = "调试";
    private ServiceConnection mConnection;

    private static final String PKG = "com.example.testmodule";
    private static final String CLS = "com.example.testmodule.aidl.AidlService";

    private Aidlable mAidlable;

    private static AidlImpl sAidlDemo;
    public static AidlImpl getInstance(){
        if (sAidlDemo == null){
            sAidlDemo = new AidlImpl();
        }

        return sAidlDemo;
    }

    private AidlImpl(){
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mAidlable = Aidlable.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mAidlable = null;
            }
        };
    }

    public boolean bindService(Context context){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PKG, CLS));
        boolean result = context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        if (result) Log.d(TAG,"AIDL绑定成功");
        else Log.d(TAG,"AIDL绑定失败");

        return result;
    }

    public void unbindService(Context context){
        context.unbindService(mConnection);
        Log.d(TAG,"AIDL解绑成功");
    }

    public String getTestData() {
        try {
            return mAidlable.getTestData();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
