package com.example.mobiletest1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Appinfo extends AppCompatActivity {

        ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);
        if (UStats.getUsageStatsList(this).isEmpty()){
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }
        String AppName = getIntent().getExtras().getString("packageName");
       Button statsBtn = (Button) findViewById(R.id.stats_btn);
        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UStats.printCurrentUsageStatus(Appinfo.this);

            }
        });


        try {
            ApplicationInfo app = this.getPackageManager().getApplicationInfo(AppName,0);
            System.out.println("ApplicationInfo Are : -"+ app.splitSourceDirs);
            TextView appName = findViewById(R.id.ClickedApp);
            appName.setText(app.name);
            Drawable icon = getPackageManager().getApplicationIcon(AppName);
            image = findViewById(R.id.appIcon);
            image.setImageDrawable(icon);
            Log.println(Log.INFO,null,"Before UsageStateManager");
//            UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
//            System.out.println("usage state manager is: "+usageStatsManager);
//            if(usageStatsManager != null){
//
//            }else{
//
//            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "error in getting app info",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}