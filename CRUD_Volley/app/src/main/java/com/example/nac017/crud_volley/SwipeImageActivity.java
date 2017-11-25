package com.example.nac017.crud_volley;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nac017.crud_volley.Adapter.SwipaImageAdapter;

public class SwipeImageActivity extends AppCompatActivity {
    ViewPager viewPager;
    SwipaImageAdapter swipaImageAdapter;
    String images[] = {
            "https://images3.alphacoders.com/169/169085.jpg",
            "https://images7.alphacoders.com/421/421423.jpg",
            "https://images5.alphacoders.com/350/350374.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_image);

        //initialize
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        swipaImageAdapter = new SwipaImageAdapter(SwipeImageActivity.this, images);
        viewPager.setAdapter(swipaImageAdapter);
    }
}
