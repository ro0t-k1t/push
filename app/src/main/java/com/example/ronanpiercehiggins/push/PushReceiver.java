package com.example.ronanpiercehiggins.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.backendless.push.BackendlessBroadcastReceiver;

/**
 * Created by ronanpiercehiggins on 20/09/2016.
 */

public class PushReceiver extends BackendlessBroadcastReceiver {




    private static final int NOTIFICATION_ID = 1;





    @Override
    public boolean onMessage(Context context, Intent intent)

    {

        if (MainActivity.mIsInForegroundMode) {

            Log.i("info", "It worked!");

            //ImageView txt = (ImageView)this.activity.findViewById(R.id.imageView);

            //title.setBackgroundResource(R.drawable.open);


            try {



                MainActivity  .getInstace().updateTheimageView();

                Log.i("info", "try");

            } catch (Exception e) {


                Log.i("info", "catch" + e);


            }





        } else {



            String message = intent.getStringExtra("message");

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

//Here you put the Activity that you want to open when you click the Notification

//(and you can pass some Bundle/Extra if you want to add information about the Notification)

            Intent notificationIntent = new Intent(context, MainActivity.class);

            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

//Custom notification

            NotificationCompat.Builder notification = new NotificationCompat.Builder(context)

                    .setContentTitle("Hello").setContentText(message).setSmallIcon(R.drawable.logo);

            notification.setContentIntent(contentIntent);

//Dismiss notification when click on it

            notification.setAutoCancel(true);

            mNotificationManager.notify(NOTIFICATION_ID, notification.build());

// super.onMessage(context, intent);










        }

        return false;



    }

}
