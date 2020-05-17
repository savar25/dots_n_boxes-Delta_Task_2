package com.example.dots_n_boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class playerdet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerdet);


        final Intent intent=getIntent();
        final int players=intent.getIntExtra("nums",0);

        final EditText p1Name=findViewById(R.id.p1insert);
        final EditText p2Name=findViewById(R.id.p2insert);
        final EditText p3Name=findViewById(R.id.p3insert);
        final EditText p4Name=findViewById(R.id.p4insert);
        final EditText p5Name=findViewById(R.id.p5insert);
        final TextView p3title=findViewById(R.id.p3title);
        final TextView p4title=findViewById(R.id.p4title);
        final TextView p5title=findViewById(R.id.p5title);

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

        p3Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    p3Name.setHint("");
                }else {
                    p3Name.setHint("Player 3 Name");
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

        p4Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    p4Name.setHint("");
                }else {
                    p4Name.setHint("Player 4 Name");
                }
            }
        });

        p5Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    p5Name.setHint("");
                }else {
                    p5Name.setHint("Player 5 Name");
                }
            }
        });

        p1Name.setEnabled(true);
        p2Name.setEnabled(true);
        p3Name.setEnabled(true);
        p4Name.setEnabled(true);
        p5Name.setEnabled(true);


        if(players<5){p5Name.setEnabled(false);
        p5Name.setVisibility(View.INVISIBLE);
        p5title.setVisibility(View.INVISIBLE);}
        if(players<4){p4Name.setEnabled(false);
            p4Name.setVisibility(View.INVISIBLE);
            p4title.setVisibility(View.INVISIBLE);}
        if(players<3){p3Name.setEnabled(false);
            p3Name.setVisibility(View.INVISIBLE);
            p3title.setVisibility(View.INVISIBLE);}

        Button mainGrid=(Button)findViewById(R.id.grid);
        mainGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (players==5 && (p1Name.getText().toString().isEmpty() || p2Name.getText().toString().isEmpty()||p3Name.getText().toString().isEmpty()||p4Name.getText().toString().isEmpty()||p5Name.getText().toString().isEmpty())) {
                            String empty = "";


                            if (p1Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 1 \n");
                            }
                            if (p2Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 2 \n");
                            }

                                if (p3Name.getText().toString().isEmpty()) {
                                    empty = empty.concat("Player 3 \n");
                                }
                                if (p4Name.getText().toString().isEmpty()) {
                                    empty = empty.concat("Player 4 \n");
                                }
                                if (p5Name.getText().toString().isEmpty()) {
                                    empty = empty.concat("Player 5 \n");
                                }



                            Toast.makeText(playerdet.this, empty + " values not mentioned", Toast.LENGTH_SHORT).show();

                        } else  if (players==4 && (p1Name.getText().toString().isEmpty() || p2Name.getText().toString().isEmpty()||p3Name.getText().toString().isEmpty()||p4Name.getText().toString().isEmpty())) {
                            String empty = "";


                            if (p1Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 1 \n");
                            }
                            if (p2Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 2 \n");
                            }


                            if (p3Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 3 \n");
                            }


                            if (p4Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 4 \n");
                            }



                            Toast.makeText(playerdet.this, empty + " values not mentioned", Toast.LENGTH_SHORT).show();
                        } else  if (players==3 && (p1Name.getText().toString().isEmpty() || p2Name.getText().toString().isEmpty()||p3Name.getText().toString().isEmpty())) {
                            String empty = "";


                            if (p1Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 1 \n");
                            }
                            if (p2Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 2 \n");
                            }

                            if (p3Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 3 \n");
                            }


                            Toast.makeText(playerdet.this, empty + " values not mentioned", Toast.LENGTH_SHORT).show();
                        }else  if (players==2 && (p1Name.getText().toString().isEmpty() || p2Name.getText().toString().isEmpty())) {
                            String empty = "";


                            if (p1Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 1 \n");
                            }
                            if (p2Name.getText().toString().isEmpty()) {
                                empty = empty.concat("Player 2 \n");
                            }


                            Toast.makeText(playerdet.this, empty + " values not mentioned", Toast.LENGTH_SHORT).show();
                        }else {


                            String temp;
                            String p1n = p1Name.getText().toString();
                            String p2n = p2Name.getText().toString();
                            String p3n="",p4n="",p5n="";
                            if (players >= 3) {
                                p3n=p3Name.getText().toString();
                                temp = p3n.substring(0, 1);
                                temp = temp.toUpperCase();
                                p3n = temp + p3n.substring(1);
                            }
                           if(players>=4) {
                               p4n = p4Name.getText().toString();
                               temp = p4n.substring(0, 1);
                               temp = temp.toUpperCase();
                               p4n = temp + p4n.substring(1);
                           }
                           if(players>=5) {
                               p5n = p5Name.getText().toString();
                               temp = p5n.substring(0, 1);
                               temp = temp.toUpperCase();
                               p5n = temp + p5n.substring(1);
                           }


                            temp = p1n.substring(0, 1);
                            temp = temp.toUpperCase();
                            p1n = temp + p1n.substring(1);
                            temp = p2n.substring(0, 1);
                            temp = temp.toUpperCase();
                            p2n = temp + p2n.substring(1);


                            Intent intent1 = new Intent(playerdet.this, gamePage.class);

                            intent1.putExtra("rows",intent.getIntExtra("rows",0));
                            intent1.putExtra("cols",intent.getIntExtra("cols",0));
                            intent1.putExtra("nums",intent.getIntExtra("nums",0));
                            intent1.putExtra("p1n", p1n);
                            intent1.putExtra("p2n", p2n);
                            if(players>=3) {
                                intent1.putExtra("p3n", p3n);
                            }
                            if(players>=4) {
                                intent1.putExtra("p4n", p4n);
                            }
                            if(players>=5) {
                                intent1.putExtra("p5n", p5n);
                            }

                            startActivity(intent1);
                        }

                    }
                },50);
            }
        });
    }
}
