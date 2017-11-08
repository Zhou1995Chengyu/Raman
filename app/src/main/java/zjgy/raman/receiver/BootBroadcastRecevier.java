package zjgy.raman.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.raman.AdminLogin;

/**
 * Created by panda on 2017/11/3.
 */

public class BootBroadcastRecevier extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent startIntent=new Intent();
            startIntent.setClass(context,AdminLogin.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startIntent);
        }
    }
}

