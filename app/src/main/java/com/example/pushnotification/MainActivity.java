package com.example.pushnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.pushnotification.databinding.ActivityMainBinding;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    // Khai báo biến để sử dụng View Binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sử dụng View Binding để gán layout cho Activity
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Gán sự kiện click cho button
        binding.btnNotification.setOnClickListener(v -> {
            senNotification(); // Gọi phương thức gửi thông báo khi nhấn button
        });
    }

    // Phương thức tạo và hiển thị thông báo
    private void senNotification() {
        // Tạo một Bitmap từ tài nguyên drawable để làm icon lớn trong thông báo
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);

        // Tạo một thông báo bằng Notification.Builder
        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle("Title Push Notification") // Tiêu đề thông báo
                .setContentText("Text Push Notification") // Nội dung thông báo
                .setSmallIcon(R.mipmap.ic_launcher_round) // Icon nhỏ hiển thị trên thanh thông báo
                .setLargeIcon(bitmap) // Icon lớn hiển thị khi mở rộng thông báo
                .build(); // Xây dựng đối tượng thông báo

        // Lấy dịch vụ NotificationManager để quản lý thông báo
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Kiểm tra nếu notificationManager không null thì gửi thông báo
        if (notificationManager != null) {
            notificationManager.notify(getNotificationId(), notification);
        }
    }
    private void senNotification2() {
        // Tạo một Bitmap từ tài nguyên drawable để làm icon lớn trong thông báo
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);

        // Tạo một thông báo bằng Notification.Builder
        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID_2)
                .setContentTitle("Title Push Notification_2") // Tiêu đề thông báo
                .setContentText("Text Push Notification_2") // Nội dung thông báo
                .setSmallIcon(R.mipmap.ic_launcher_round) // Icon nhỏ hiển thị trên thanh thông báo
                .setLargeIcon(bitmap) // Icon lớn hiển thị khi mở rộng thông báo
                .build(); // Xây dựng đối tượng thông báo

        // Lấy dịch vụ NotificationManager để quản lý thông báo
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Kiểm tra nếu notificationManager không null thì gửi thông báo
        if (notificationManager != null) {
            notificationManager.notify(getNotificationId(), notification);
        }
    }


    // Phương thức tạo ID ngẫu nhiên cho mỗi thông báo bằng cách lấy thời gian hiện tại
    private int getNotificationId() {
        return (int) System.currentTimeMillis();
    }
}
