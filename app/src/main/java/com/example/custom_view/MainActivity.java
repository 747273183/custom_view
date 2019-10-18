package com.example.custom_view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       final View view= findViewById(R.id.id_pb);

       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ObjectAnimator.ofInt(view,"progress",0,100).setDuration(3000).start();
           }
       });

    }
}
