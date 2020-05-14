package com.example.dots_n_boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        TextView winner=findViewById(R.id.winner);

        if(p1s>p2s){
            winner.setText("Player 1"+ " Won!");
        }else if(p2s>p1s){
            winner.setText("Player 2"+"  Won!");
        }else{
            winner.setText("Its a Draw!!");
        }

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(winner.this,MainActivity.class);
                startActivity(intent1);
            }
        });

    }
}

