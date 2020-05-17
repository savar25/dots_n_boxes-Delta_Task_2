package com.example.dots_n_boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class major extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        final EditText row=findViewById(R.id.rowInsert);
        final EditText col=findViewById(R.id.columnInsert);
        final EditText num=findViewById(R.id.numInsert);

        TextView head=findViewById(R.id.Heading);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"gunplay.ttf");
        head.setTypeface(typeface);
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

        num.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    num.setHint("");
                }else {
                    num.setHint("Enter Number");
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


                        if (row.getText().toString().isEmpty() || col.getText().toString().isEmpty() || num.getText().toString().isEmpty()) {
                            String empty = "";
                            if (row.getText().toString().isEmpty()) {
                                empty = empty.concat("Row \n");
                            }
                            if (col.getText().toString().isEmpty()) {
                                empty = empty.concat("Column \n");
                            }
                            if (num.getText().toString().isEmpty()) {
                                empty = empty.concat("Number \n");
                            }


                            Toast.makeText(major.this, empty + " values not mentioned", Toast.LENGTH_SHORT).show();
                        } else if (Integer.parseInt(row.getText().toString()) > 11|| Integer.parseInt(row.getText().toString())==0 || Integer.parseInt(col.getText().toString()) > 11 || Integer.parseInt(col.getText().toString())==0||(Integer.parseInt(num.getText().toString()) > 5||Integer.parseInt(num.getText().toString()) ==0||Integer.parseInt(num.getText().toString()) ==1 )) {
                            boolean flag=false,flag1=false,flag2=false,flag3=false;
                            String val = "";
                            String val1="";
                            if (Integer.parseInt(row.getText().toString()) > 11) {
                                val = val.concat("Row Value ");
                                flag=true;
                            }
                            if (Integer.parseInt(row.getText().toString()) ==0) {
                                val1 = val1.concat("Row Value ");
                                flag2=true;
                            }
                            if (Integer.parseInt(col.getText().toString()) > 11) {
                                val = val.concat("Column Value ");
                                flag=true;
                            }

                            if (Integer.parseInt(col.getText().toString()) ==0) {
                                val1 = val1.concat("Column Value ");
                                flag2=true;
                            }
                            if(flag){Toast.makeText(major.this, val + "greater than 11", Toast.LENGTH_SHORT).show();}
                            if(flag2){Toast.makeText(major.this, val1 + "cannot be 0", Toast.LENGTH_SHORT).show();}
                            String vale = "";
                            val1="";
                            if (Integer.parseInt(num.getText().toString()) > 5) {
                                vale = vale.concat("Number Value ");
                                flag1=true;
                            }
                            if (Integer.parseInt(num.getText().toString()) ==0) {
                                val1 = val1.concat("Number Value ");
                                flag3=true;
                            }
                            if(Integer.parseInt(num.getText().toString()) ==1){
                                Toast.makeText(major.this,  "Number Value cannot be 1", Toast.LENGTH_SHORT).show();
                            }
                            if(flag1){Toast.makeText(major.this, vale + "greater than 5", Toast.LENGTH_SHORT).show();}
                            if(flag3){Toast.makeText(major.this, val1 + "cannot be 0", Toast.LENGTH_SHORT).show();}
                        } else {

                            Integer rows = Integer.parseInt(row.getText().toString());
                            Integer cols = Integer.parseInt(col.getText().toString());
                            Integer nums=Integer.parseInt(num.getText().toString());




                            Intent intent = new Intent(major.this, playerdet.class);
                            intent.putExtra("rows", rows);
                            intent.putExtra("cols", cols);
                            intent.putExtra("nums",nums);

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


