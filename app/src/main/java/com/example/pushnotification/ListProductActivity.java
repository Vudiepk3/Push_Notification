package com.example.pushnotification;

import com.example.pushnotification.databinding.ActivityListProductBinding;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ListProductActivity extends AppCompatActivity {
    private ActivityListProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnClick.setOnClickListener(v -> {
            startActivity(new Intent(this, DetailActivity.class));
        });
    }
}