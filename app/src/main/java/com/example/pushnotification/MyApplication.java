package com.example.pushnotification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class MyApplication extends Application {
    public static final String CHANNEL_ID ="Channel_ID" ;
    public static final String CHANNEL_ID_2 ="Channel_ID_2" ;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel.
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            //Config channel 1
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setSound(uri, audioAttributes);

            //Config channel 2
            Uri sound_notification = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound_notification);
            CharSequence name_2 = getString(R.string.channel_name_2);
            String description_2 = getString(R.string.channel_description);
            int importance_2 = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setSound(sound_notification, audioAttributes);

            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel_2);
            }
        }
    }
}
