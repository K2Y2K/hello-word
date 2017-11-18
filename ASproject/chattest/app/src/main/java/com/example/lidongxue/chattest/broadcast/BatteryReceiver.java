package com.example.lidongxue.chattest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lidongxue on 17-10-10.
 */

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        int current=bundle.getInt("level");
        Log.d("----current---","  "+current);
        int total=bundle.getInt("scale");
        Log.d("----total---","  "+total);
        if(current*1.0 /total<0.15){
            Toast.makeText(context,"电量过低，请尽快充电！",Toast.LENGTH_LONG).show();
        }
    }
}
