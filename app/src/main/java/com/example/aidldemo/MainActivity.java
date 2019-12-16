package com.example.aidldemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.aidldemo.aidl.DemoAidlImpl;
import com.example.aidldemo.utils.MyApplication;
import com.example.mylibrary.DemoAidlInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler;
    private static final String TAG = "调试";

    private static final String BROADCAST_LANUCHER = "com.example.testmodule.lanuch";
    private static final String BROADCAST_EXIT = "com.example.testmodule.exit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: app");

        //启动另一个应用
        Intent intent = new Intent(BROADCAST_LANUCHER);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);

        DemoAidlImpl.getInstance().bindService(this);

        mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1){
                    Log.d(TAG, DemoAidlImpl.getInstance().getTestData());
                    finish();
                }
            }
        };
        mHandler.sendEmptyMessageDelayed(1, 1000);

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: app");
        sendBroadcast(new Intent().setAction(BROADCAST_EXIT));
        DemoAidlImpl.getInstance().unbindService(this);
        super.onDestroy();
    }


}
