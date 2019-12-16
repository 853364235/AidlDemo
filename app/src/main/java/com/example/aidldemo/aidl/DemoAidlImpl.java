package com.example.aidldemo.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.mylibrary.DemoAidlInterface;

public class DemoAidlImpl {
    private static final String TAG = "调试";
    private ServiceConnection mConnection;
    private DemoAidlInterface mDemoAidlInterface;

    private static final String PKG = "com.example.testmodule";
    private static final String CLS = "com.example.testmodule.aidl.AidlService";

    private static DemoAidlImpl sDemoAidl;
    public static DemoAidlImpl getInstance(){
        if (sDemoAidl == null){
            sDemoAidl = new DemoAidlImpl();
        }

        return sDemoAidl;
    }

    public DemoAidlImpl(){
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mDemoAidlInterface = DemoAidlInterface.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mDemoAidlInterface = null;
            }
        };
    }

    public boolean bindService(Context context){
        Intent intent = new Intent();
//        intent.setAction("com.example.testmodule.aidl.AidlService");
//        intent.setPackage("com.example.testmodule");
        intent.setComponent(new ComponentName(PKG, CLS));
        boolean result = context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        if (result) Log.d(TAG,"AIDL绑定成功");
        else Log.d(TAG,"AIDL绑定失败");

        return result;
    }

    public void unbindService(Context context){
        //context.unbindService(mConnection);
        Log.d(TAG,"解绑成功");
    }

    public String getTestData() {
        try {
            return mDemoAidlInterface.getTestData();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
