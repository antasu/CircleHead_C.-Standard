package com.example.antasu.circlehead_c_standard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private antasucircle head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        head = findViewById(R.id.antasu_image);
        head.setImageResource(R.drawable.ic_head);
    }
}
