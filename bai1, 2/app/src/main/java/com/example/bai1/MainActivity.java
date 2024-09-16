package com.example.bai1;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int[] imgID = {R.drawable.cat,R.drawable.dog,R.drawable.penguin};
    int[] colorID = {R.color.black,R.color.teal,R.color.purple,R.color.white};
    ProgressDialog pd ;
    Handler handler = new Handler();
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        progressbar();
        change();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private  void change(){
        layout = findViewById(R.id.layout);
        ImageView img = findViewById(R.id.img);
        Random random = new Random();

        img.setImageResource(imgID[random.nextInt(imgID.length)]);
        layout.setBackgroundResource(colorID[ random.nextInt(colorID.length) ]);
    }

    private void progressbar(){
       pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("loading");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(100);
        pd.setProgress(0);
        pd.show();
        new Thread(new Runnable(){
            int count;
            public void run(){
                while(pd.getProgress()<= pd.getMax()){
                    try{
                        Thread.sleep(100);
                        handler.post(new Runnable(){
                            @Override
                            public void run() {
                                pd.setProgress(count++);
                            }
                        });
                        if (pd.getProgress()== pd.getMax()) pd.dismiss();

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


}