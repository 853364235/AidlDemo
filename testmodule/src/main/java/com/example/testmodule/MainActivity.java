package com.example.testmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.testmodule.utils.ActivityManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "调试";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityManager.getInstance().pushOneActivity(this);

        Log.d(TAG, "onCreate: testmodule");
        moveTaskToBack(true);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: testmodule");
        super.onDestroy();
    }
}
