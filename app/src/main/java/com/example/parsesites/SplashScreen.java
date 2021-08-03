package com.example.parsesites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    public static int TIME_OUT= 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler =new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                Intent intent =new Intent(SplashScreen.this,Dropdown.class);
                startActivity(intent);
                finish();

            }
        },TIME_OUT);
    }
}
