package com.example.desiredeal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.AppPreference;
import com.example.desiredeal.R;

public class splash extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences prefIntro = getSharedPreferences("intro", MODE_PRIVATE);
        SharedPreferences prefLogin = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences prefSignup = getSharedPreferences("signup", MODE_PRIVATE);
        Boolean isFirstTime = prefIntro.getBoolean("firstTime", true);
        Boolean isLogin = prefLogin.getBoolean("login", false);
        Boolean issignup = prefSignup.getBoolean("signup", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (isFirstTime) {
                    intent = new Intent(splash.this, IntroActivity.class);
                } else if (isLogin) {
                    intent = new Intent(splash.this, MainActivity.class);
                } else if(issignup) {
//                    SharedPreferences.Editor editor = getSharedPreferences("signup", MODE_PRIVATE).edit();
//                    editor.remove("signup");
//                    editor.apply();
                    intent = new Intent(splash.this, MainActivity.class);
                }else {
                    intent = new Intent(splash.this, Welcome.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);

    }


//    @Override
//    protected void onRestart() {
//        super.onRestart();
//
//        // Check if the user is already logged in
//        Boolean check = pref.getBoolean("flag", false);
//
//        Intent intent;
//        if (check){
//            intent = new Intent(splash.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
}