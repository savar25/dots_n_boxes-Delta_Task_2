package com.example.dots_n_boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class gamePage extends AppCompatActivity {
    public static TextView PlayerShow;
    public static TextView p1s;
    public static TextView p2s;
    public static ImageView color;
    public static int p1sc,p2sc;
public static Context context;
    public  static int rows,cols;
    public static String p1n,p2n;
    public static String p1nS,p2nS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        Intent intent=getIntent();
            rows=intent.getIntExtra("rows",0);
            cols=intent.getIntExtra("cols",0);
            p1nS=intent.getStringExtra("p1n");
            p2nS=intent.getStringExtra("p2n");

        Gview gview=(Gview)findViewById(R.id.trial);
            gview.setCol(cols);
            gview.setRow(rows);
            gview.setP1n(p1nS.substring(0,1));
            gview.setP2n(p2nS.substring(0,1));



            PlayerShow=(TextView)findViewById(R.id.presentPlayer);
            p1s=(TextView)findViewById(R.id.p1);
            p2s=(TextView)findViewById(R.id.p2);
            color=(ImageView)findViewById(R.id.colorV);
            PlayerShow.setText(p1nS);
            PlayerShow.setTextColor(Color.GREEN);
            color.setBackgroundColor(Color.GREEN);
            p1s.setText(p1nS+" : 0");
            p2s.setText(p2nS+" : 0");
            p1s.setTextColor(Color.RED);

            p2s.setTextColor(Color.RED);

            Button end=findViewById(R.id.button2);
            end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent  intent=new Intent(gamePage.this,winner.class);
                intent.putExtra("p1Score",p1sc);
                intent.putExtra("p2Score",p2sc);
                intent.putExtra("p1n",p1nS);
                intent.putExtra("p2n",p2nS);
                startActivity(intent);
            }
        });
            context=gamePage.this;
        }



        static public void setPlayerShow(int choice) {

            if(choice==1){
                PlayerShow.setText(p1nS);
                PlayerShow.setTextColor(Color.GREEN);
            }else{
                PlayerShow.setText(p2nS);
                PlayerShow.setTextColor(Color.YELLOW);
            }
        }

        static public void setColor(int color) {
            if(color==1){
                gamePage.color.setBackgroundColor(Color.GREEN);}
            else {
                gamePage.color.setBackgroundColor(Color.YELLOW);}
        }


        public static void setP1s(int p1s) {

        p1sc=p1s;
        gamePage.p1s.setText(p1nS+" : "+p1s);
            gamePage.p1s.setTextColor(Color.RED);
            checkfull();
        }

        public static void setP2s(int p2s) {
        p2sc=p2s;
            gamePage.p2s.setText(p2nS+" : " + p2s);
            gamePage.p2s.setTextColor(Color.RED);
            checkfull();
        }

        public static void checkfull() {

            if (p1sc + p2sc == (rows - 1) * (cols - 1)) {
                Intent intent=new Intent(context,winner.class);
                intent.putExtra("p1Score",p1sc);
                intent.putExtra("p2Score",p2sc);
                intent.putExtra("p1n",p1nS);
                intent.putExtra("p2n",p2nS);
                context.startActivity(intent);
            }
        }


    @Override
    protected void onRestart() {
        super.onRestart();
        Intent pa = new Intent(gamePage.this, major.class);
        startActivity(pa);
    }
}

