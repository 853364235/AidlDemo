package com.example.aidldemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.mylibrary.aidl.AidlDemoImpl;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "调试";

    private static final String BROADCAST_LANUCHER = "com.example.testmodule.lanuch";
    private static final String BROADCAST_EXIT = "com.example.testmodule.exit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: app");

        initAidl();

        //测试用
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1){
                    Log.d(TAG, AidlDemoImpl.getInstance().getTestData());
                    MainActivity.this.finish();
                }
            }
        };
        handler.sendEmptyMessageDelayed(1, 5000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: app");
        AidlDemoImpl.getInstance().unbindService(this);
        sendBroadcast(new Intent().setAction(BROADCAST_EXIT));
        System.exit(0);
    }

    private void initAidl(){
        //启动另一个应用
        Intent intent = new Intent(BROADCAST_LANUCHER);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);

        AidlDemoImpl.getInstance().bindService(this);
    }
}
