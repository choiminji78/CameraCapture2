package com.camera.hslee.mycameraproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class DelReceiver extends BroadcastReceiver {
    public DelReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getData().getSchemeSpecificPart();
        Log.d("MyCameraApp", "Package REMOVED : " + packageName);

        if (packageName.equals("com.camera.hslee")){
            Log.d("MyCameraApp","LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOL");
            Toast.makeText(context, "LOOOOOOOOOOOOOOOL", Toast.LENGTH_SHORT).show();
        }

    }
}
