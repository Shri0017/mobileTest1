package com.example.mobiletest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class LatestOpenApp extends AppCompatActivity {
    private TextView appname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_open_app);
        SharedPreferences sharedPreferences = getSharedPreferences("MobileApps",MODE_PRIVATE);
        String appName = sharedPreferences.getString("appName","");
        appname = findViewById(R.id.activity3AppName);
        if(!appName.equals(""))
        {
            appname.setText(appName);
        }else
        {
            appname.setText("Failed to get app Name from sharedPreference");
        }

    }
}