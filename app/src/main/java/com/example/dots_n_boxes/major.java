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
import android.widget.EditText;
import android.widget.Toast;

public class major extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        final EditText row=findViewById(R.id.rowInsert);
        final EditText col=findViewById(R.id.columnInsert);
        final EditText p1Name=findViewById(R.id.p1insert);
        final EditText p2Name=findViewById(R.id.p2insert);

        row.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    row.setHint("");
                }else {
                    row.setHint("Enter Rows");
                }
            }
        });

        col.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    col.setHint("");
                }else {
                    col.setHint("Enter Columns");
                }
            }
        });

        p1Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    p1Name.setHint("");
                }else {
                    p1Name.setHint("Player 1 Name");
                }
            }
        });

        p2Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    p2Name.setHint("");
                }else {
                    p2Name.setHint("Player 2 Name");
                }
            }
        });


        Button startg=findViewById(R.id.button);
        startg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moveDown(new View(major.this));



                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        if (row.getText().toString().isEmpty() || col.getText().toString().isEmpty() || p1Name.getText().toString().isEmpty() || p2Name.getText().toString().isEmpty()) {
                            String empty = "";
                            if (row.getText().toString().isEmpty()) {
                                empty = empty.concat("Row ");
                            }
                            if (col.getText().toString().isEmpty()) {
                                empty = empty.concat("Column ");
                            }
                            if (p1Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 1 ");
                            }
                            if (p2Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 2 ");
                            }
                            Toast.makeText(major.this, empty + " values not mentioned", Toast.LENGTH_SHORT).show();
                        } else if (Integer.parseInt(row.getText().toString()) > 11 || Integer.parseInt(col.getText().toString()) > 11) {
                            String val = "";
                            if (Integer.parseInt(row.getText().toString()) > 11) {
                                val = val.concat("Row Value ");
                            }
                            if (Integer.parseInt(col.getText().toString()) > 11) {
                                val = val.concat("Column Value ");
                            }
                            Toast.makeText(major.this, val + "greater than 11", Toast.LENGTH_SHORT).show();
                        } else {

                            Integer rows = Integer.parseInt(row.getText().toString());
                            Integer cols = Integer.parseInt(col.getText().toString());
                            String p1n = p1Name.getText().toString();
                            String p2n = p2Name.getText().toString();

                            String temp;
                            temp = p1n.substring(0, 1);
                            temp = temp.toUpperCase();
                            p1n = temp + p1n.substring(1);
                            temp = p2n.substring(0, 1);
                            temp = temp.toUpperCase();
                            p2n = temp + p2n.substring(1);

                            Intent intent = new Intent(major.this, gamePage.class);
                            intent.putExtra("rows", rows);
                            intent.putExtra("cols", cols);
                            intent.putExtra("p1n", p1n);
                            intent.putExtra("p2n", p2n);
                            startActivity(intent);
                        }

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


