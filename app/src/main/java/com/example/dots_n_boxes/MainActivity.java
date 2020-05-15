package com.example.dots_n_boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        View view=new View(MainActivity.this);
        setAnim(view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, major.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);

    }

    public void setAnim(View view){
        ImageView imageView=findViewById(R.id.iconmain);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.myanim);
        imageView.startAnimation(animation);
    }

    public void setrot(View view){
        ImageView imageView=findViewById(R.id.iconmain);
        Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.clockwise);
        imageView.startAnimation(animation);
    }
}
