package com.example.mobiletest1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView,lastOpenApp;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);
        lastOpenApp = findViewById(R.id.lastOpenApp);
        SharedPreferences sharedPreferences =getSharedPreferences("MobileApps",MODE_PRIVATE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
//                System.out.println("clicked Item"+item);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("appName",item);
                lastOpenApp.setText(item);
                editor.apply();
                Intent intent = new Intent(MainActivity.this,Appinfo.class);
//                lastOpenApp.setText(item);
                intent.putExtra("packageName",item);
                startActivity(intent);
            }

        });

        String value = sharedPreferences.getString("appName","Last Open App name is displayed here");
        lastOpenApp.setText(value);
        lastOpenApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LatestOpenApp.class);
                    startActivity(intent);
            }
        });
    }

    public void buttonListApp(View view){
        List<ApplicationInfo> applicationInfoList = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);

        String[] stringsArray =new  String[applicationInfoList.size()];
        int i=0;
        for(ApplicationInfo applicationInfo:applicationInfoList){
            stringsArray[i] = applicationInfo.packageName;
            i++;
        }
        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, stringsArray));
        textView.setText(applicationInfoList.size() + "App are installed");
    }
}