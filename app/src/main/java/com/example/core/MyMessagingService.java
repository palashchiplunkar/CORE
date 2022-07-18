package com.example.core;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        showNotification(message.getNotification().getTitle(),message.getNotification().getBody());
    }

    private void showNotification(String title,String message) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"My Notification");

        builder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(170701,builder.build());

    }

}
