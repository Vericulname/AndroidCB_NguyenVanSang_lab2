package com.example.bai1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int[] imgID = {R.drawable.cat,R.drawable.dog,R.drawable.penguin};
    int[] colorID = {R.color.black,R.color.teal,R.color.purple,R.color.white};
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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

}