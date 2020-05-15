package com.example.dots_n_boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class winner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        Intent intent=getIntent();

        int p1s=intent.getIntExtra("p1Score",0);
        int p2s=intent.getIntExtra("p2Score",0);
        String p1n=intent.getStringExtra("p1n");
        String p2n=intent.getStringExtra("p2n");

        TextView sc1=findViewById(R.id.disp1);
        sc1.setText(p1n+"\n"+(p1s));
        TextView sc2=findViewById(R.id.disp2);
        sc2.setText(p2n+"\n"+(p2s));
        final TextView winner=findViewById(R.id.winner);

        if(p1s>p2s){
            winner.setText("Player 1"+ " Won!");
        }else if(p2s>p1s){
            winner.setText("Player 2"+"  Won!");
        }else{
            winner.setText("Its a Draw!!");
        }

        Button button=findViewById(R.id.loop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveDown(new View(winner.this));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent1=new Intent(winner.this,major.class);
                        startActivity(intent1);
                    }
                },50);

            }
        });

    }

    public void moveDown(View view){
        Button end=findViewById(R.id.loop);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.go_down);
        end.startAnimation(animation);
    }
}

