package com.example.ronanpiercehiggins.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.push.BackendlessBroadcastReceiver;

public class MainActivity extends AppCompatActivity {


    private static MainActivity ins;



    public static Boolean mIsInForegroundMode = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ins = this;







        String appVersion = "v1";
        Backendless.initApp(this, "A0819152-C875-C222-FF18-0516AB9ACC00", "94E2E030-C1B8-0F27-FFEE-CD829BAE3400", appVersion);
        Log.i("info", "backendless success");



        Backendless.Messaging.registerDevice("670988742449", "default", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {

                Log.i("info", "registered");
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

                Log.i("info", "failed to register, error is " + " " + backendlessFault);

            }
        });
    }

    public static MainActivity  getInstace(){
        return ins;
    }


    @Override
    protected void onPause() {
        super.onPause();

        Log.i("info", "pause");
        mIsInForegroundMode = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsInForegroundMode = true;
        Log.i("info", "resume");
    }

    public void updateTheimageView() {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                ImageView image = (ImageView) findViewById(R.id.imageView);
                Log.i("info", "inside method");

                image.setImageResource(R.drawable.open);
            }
        });
    }



}
