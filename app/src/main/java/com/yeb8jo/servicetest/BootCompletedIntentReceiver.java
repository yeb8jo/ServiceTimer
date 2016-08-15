package com.yeb8jo.servicetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BootCompletedIntentReceiver extends BroadcastReceiver {

    public BootCompletedIntentReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

            Intent timerIntent = new Intent(context,TimerService.class);
            context.stopService(timerIntent);

            Bundle bundle = new Bundle();
            bundle.putInt(TimerService.KEY_INTERVAL, 5000);
            timerIntent.putExtras(bundle);
            context.startService(timerIntent);
        }
    }
}
