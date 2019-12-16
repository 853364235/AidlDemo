package com.example.testmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LanucherReceiver extends BroadcastReceiver {

    private static final String TAG = "调试";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, MainActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent2);
    }
}
