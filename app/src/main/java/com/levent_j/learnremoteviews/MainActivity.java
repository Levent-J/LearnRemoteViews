package com.levent_j.learnremoteviews;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn_notify);
        button.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_notify:
                Intent intent = new Intent(this,Main2Activity.class);
                PendingIntent pendingIntent=PendingIntent.getActivities(this,0, new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
                Notification notification = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    notification = new Notification.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setTicker("有消息了!")
                            .setContentTitle("标题")
                            .setContentText("内容")
                            .setFullScreenIntent(pendingIntent,true)//直接进入activity2
//                            .setContentIntent(pendingIntent)
                            .setNumber(1)
                            .build();
                }
                notification.flags=Notification.FLAG_AUTO_CANCEL;

                RemoteViews contentView = new RemoteViews(getPackageName(),R.layout.notify);
                contentView.setTextViewText(R.id.text_context,"show");
                notification.contentView=contentView;
//
//                RemoteViews expandleView =new RemoteViews(getPackageName(),R.layout.notify_expandle);
//                notification.bigContentView=expandleView;

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1,notification);

                break;
        }
    }
}
