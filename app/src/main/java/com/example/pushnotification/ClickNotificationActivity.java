package com.example.pushnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.pushnotification.databinding.ActivityClickNotificationBinding;

public class ClickNotificationActivity extends AppCompatActivity {
    private ActivityClickNotificationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClickNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnClick.setOnClickListener(v -> {
            startActivity(new Intent(this, ListProductActivity.class));
        });
        binding.btnSendNotification.setOnClickListener(v -> {
            sendNotifaction();
        });

    }

    private void sendNotifaction() {
        // Tạo một Bitmap từ tài nguyên drawable để làm icon lớn trong thông báo
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        // Lấy Uri của tệp âm thanh mặc định
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // Tạo Intent để mở Activity mong muốn.
        Intent resultIntent = new Intent(this, DetailActivity.class);

        // Tạo TaskStackBuilder và thêm Intent vào, giúp tạo lại ngăn xếp "Back".
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);

        // Lấy PendingIntent chứa toàn bộ ngăn xếp "Back".
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(getNotificationId(),
                        PendingIntent.FLAG_UPDATE_CURRENT);


        // Tạo một thông báo bằng Notification.Builder
        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle("Title Push Notification") // Tiêu đề thông báo
                .setContentText("Text Push Notification") // Nội dung thông báo
                .setSmallIcon(R.mipmap.ic_launcher_round) // Icon nhỏ hiển thị trên thanh thông báo
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Text Push Notification"))
                .setLargeIcon(bitmap) // Icon lớn hiển thị khi mở rộng thông báo
                .setSound(uri) // Cài đặt âm thanh cho thông báo
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)// Cài đặt độ ưu tiên cho thông báo
                .setContentIntent(resultPendingIntent)// Cài đặt Intent khi người dùng nhấp vào thông báo
                .setAutoCancel(true)// Tự động hủy thông báo khi người dùng nhấp vào
                .build(); // Xây dựng đối tượng thông báo

        // Lấy dịch vụ NotificationManager để quản lý thông báo
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Kiểm tra nếu notificationManager không null thì gửi thông báo
        if (notificationManager != null) {
            notificationManager.notify(getNotificationId(), notification);
        }
    }
    private int getNotificationId() {
        return (int) System.currentTimeMillis();
    }

}