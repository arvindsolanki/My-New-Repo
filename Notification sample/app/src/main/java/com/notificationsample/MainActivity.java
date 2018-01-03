package com.notificationsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSimpleNotification;

    //notificaiton id
    private final int SIMPLE_NOTIFICATION_ID = 10;
    private CheckBox checkboxSticky;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btnSimpleNotification = (Button) findViewById(R.id.btnSimpleNotification);
        btnSimpleNotification.setOnClickListener(this);
        checkboxSticky = (CheckBox) findViewById(R.id.checkboxSticky);
    }

    private void showSimpleNotification(boolean b) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("My Simple notification");
        //auto cancel
        mBuilder.setAutoCancel(true);
        //sticky notification
        mBuilder.setOngoing(b);
        mBuilder.setContentText("Hello, this is simple notification");

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(SIMPLE_NOTIFICATION_ID, mBuilder.build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpleNotification:
                if (checkboxSticky.isChecked()) {
                    showSimpleNotification(true);
                } else {
                    showSimpleNotification(false);
                }

                break;
        }
    }
}
