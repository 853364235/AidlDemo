package com.example.testmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.testmodule.utils.ActivityManager;
import com.example.testmodule.utils.MyApplication;

public class ExitReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ActivityManager.getInstance().finishAllActivity();
        System.exit(0);
    }
}
