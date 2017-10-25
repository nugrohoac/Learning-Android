package com.example.nac017.widget;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Spinner spinner;
    String buah[] = {"Nanas", "Mangga", "Kelapa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //glide to open image internet
        imageView = (ImageView)findViewById(R.id.img1);
        //Nanti tambahkan compile glide
        //Glide.with(this).load("urlnya").into(imageView);

        //set spinner content
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> namaBuah = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,buah);
        spinner.setAdapter(namaBuah);
    }
}
