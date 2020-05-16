package com.example.dots_n_boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
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
        int nums=intent.getIntExtra("nums",0);
        int p1s=intent.getIntExtra("p1Score",0);
        int p2s=intent.getIntExtra("p2Score",0);
        int p3s=0,p4s=0,p5s=0;
        if(nums>=3) {
            p3s = intent.getIntExtra("p3Score", 0);

        }
        if(nums>=4) {
            p4s = intent.getIntExtra("p4Score", 0);

        }
        if(nums>=3) {
            p5s = intent.getIntExtra("p5Score", 0);

        }

        String p1n=intent.getStringExtra("p1n");
        String p2n=intent.getStringExtra("p2n");
      String p3n="",p4n="",p5n="";

        if(nums>=3) {
            p3n=intent.getStringExtra("p3n");
        }
        if(nums>=4) {
            p4n=intent.getStringExtra("p4n");
        }
        if(nums>=3) {
            p5n=intent.getStringExtra("p5n");
        }

        final MediaPlayer popMusic=MediaPlayer.create(winner.this,R.raw.applause);
        popMusic.start();

        TextView sc1=findViewById(R.id.disp1);

        if (nums == 5) {
            sc1.setText(p1n+"-\t"+(p1s)+"\n"
                    +p2n+"-\t"+(p2s)+"\n"+
                    p3n+"-\t"+(p3s)+"\n"+
                    p4n+"-\t"+p4s+"\n"+
                    p5n+"-\t"+p5s);
        }else if(nums==4){
            sc1.setText(p1n+"-\t"+(p1s)+"\n"
                    +p2n+"-\t"+(p2s)+"\n"+
                    p3n+"-\t"+(p3s)+"\n"+
                    p4n+"-\t"+p4s);
        }else if(nums==3){
            sc1.setText(p1n+"-\t"+(p1s)+"\n"
                    +p2n+"-\t"+(p2s)+"\n"+
                    p3n+"-\t"+(p3s));
        }else if(nums==2){
            sc1.setText(p1n+"-\t"+(p1s)+"\n"
                    +p2n+"-\t"+(p2s));
        }


        final TextView winner=findViewById(R.id.winner);

        int max=p1s;
        String maxn=p1n;
        if(p2s>max){max=p2s;maxn=p2n;}
        if(p3s>max){max=p3s;maxn=p3n;}
        if(p4s>max){max=p4s;maxn=p4n;}
        if(p5s>max){max=p5s;maxn=p5n;}
        winner.setText(maxn+" Won!!");

        if(nums==2){
            if(p2s==p1s){winner.setText("Its a Draw!!");}
        }else if(nums==3){if(p1s==max && p2s==max && p3s==max){winner.setText("Its a Draw!!");}
        }else if(nums==4){if(p1s==max && p2s==max && p3s==max && p4s==max){winner.setText("Its a Draw!!");}
        }else if(nums==5){if(p1s==max && p2s==max && p3s==max && p4s==max && p5s==max){winner.setText("Its a Draw!!");}}


        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveDown(new View(winner.this));
                popMusic.stop();
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
        Button end=findViewById(R.id.button);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.go_down);
        end.startAnimation(animation);
    }
}

