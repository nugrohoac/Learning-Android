package com.example.nac017.crud_volley;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.disklrucache.DiskLruCache;

public class SplashScreen extends AppCompatActivity {
    ImageView img;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        img = (ImageView) findViewById(R.id.imgLogo);
        Glide.with(this).load("http://www.xforms-kickstarter.com/Icons/screenshot-launch.png").into(img);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("isLogin", 1); // Storing integer
                editor.putString("username", "iniUsername"); // Storing string
                editor.putString("password", "iniPassword"); // Storing string
                editor.commit(); // commit changes

                Integer isLogin = pref.getInt("isLogin", 0); // getting Integer status login
                String username = pref.getString("username", null); // getting String username
                String password = pref.getString("password", null); // getting String password

                Log.d("login", "status loginya : " + isLogin);
                Log.d("username", "usernamenya : " + username);
                Log.d("password", "passwordnya : " + password);
                if (isLogin == 1) {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashScreen.this, Register.class);
                    startActivity(i);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
