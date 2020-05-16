package com.example.dots_n_boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class gamePage extends AppCompatActivity {
    public static TextView PlayerShow;
    public static TextView p1s;
    public static TextView p2s;
    public static ImageView color;
    public static int p1sc,p2sc,p3sc,p4sc,p5sc;
public static Context context,context1;
    public  static int rows,cols,nums;
    public static String p1n,p2n;
    public static String p1nS,p2nS,p3nS,p4nS,p5nS;
    public static View view;
    public static Vibrator vibrator;
    public static int lb,lp,lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        Intent intent=getIntent();
            rows=intent.getIntExtra("rows",0);
            cols=intent.getIntExtra("cols",0);
            nums=intent.getIntExtra("nums",0);
            p1nS=intent.getStringExtra("p1n");
            p2nS=intent.getStringExtra("p2n");
            if (nums >= 3) {
            p3nS=intent.getStringExtra("p3n");
            }
            if(nums>=4){
                p4nS=intent.getStringExtra("p4n");
            }
            if(nums>=5){
                p5nS=intent.getStringExtra("p5n");
            }


        Gview gview=(Gview)findViewById(R.id.trial);
            gview.setCol(cols);
            gview.setRow(rows);
            gview.setPlayers(nums);
            gview.setP1n(p1nS.substring(0,1));
            gview.setP2n(p2nS.substring(0,1));
            if(nums>=3) {
                gview.setP3n(p3nS.substring(0, 1));
            }
            if(nums>=4){
                gview.setP4n(p4nS.substring(0, 1));
            }
            if(nums>=5){
                gview.setP5n(p5nS.substring(0, 1));
            }


            PlayerShow=(TextView)findViewById(R.id.presentPlayer);
            p1s=(TextView)findViewById(R.id.p1);
            color=(ImageView)findViewById(R.id.colorV);
            PlayerShow.setText(p1nS);
            PlayerShow.setTextColor(Color.GREEN);
            color.setBackgroundColor(Color.GREEN);
            p1s.setText(p1nS+" : 0");

            p1s.setTextColor(Color.RED);



            Button end=findViewById(R.id.button2);
            end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveDown(new View(gamePage.this));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent  intent=new Intent(gamePage.this,winner.class);

                        intent.putExtra("nums",nums);
                        intent.putExtra("p1Score",p1sc);
                        intent.putExtra("p2Score",p2sc);
                        if (nums >= 3) {
                            intent.putExtra("p3Score",p3sc);
                        }
                        if(nums>=4){
                            intent.putExtra("p4Score",p4sc);
                        }
                        if(nums>=5){
                            intent.putExtra("p5Score",p5sc);
                        }


                        intent.putExtra("p1n",p1nS);
                        intent.putExtra("p2n",p2nS);

                        if (nums >= 3) {
                            intent.putExtra("p3n",p3nS);
                        }
                        if(nums>=4){
                            intent.putExtra("p4n",p4nS);
                        }
                        if(nums>=5){
                            intent.putExtra("p5n",p5nS);
                        }

                        startActivity(intent);
                    }
                },200);

            }
        });
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            context=gamePage.this;
            context1=getApplicationContext();
            view=new View(context);
            lb=getResources().getColor(R.color.LightBlue);
            lp=getResources().getColor(R.color.LightPink);
            lo=getResources().getColor(R.color.LightOrange);
        }



        static public void setPlayerShow(int choice) {

            switch (choice){
                case  1:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PlayerShow.setTextColor(Color.GREEN);
                        PlayerShow.setText(p1nS);

                    }
                },600);
                    break;
                case 2:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PlayerShow.setText(p2nS);
                        PlayerShow.setTextColor(Color.YELLOW);
                    }
                },600);
    break;
                case 3:

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PlayerShow.setText(p3nS);
                            PlayerShow.setTextColor(lb);
                        }
                    },600);
break;
                case 4:

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PlayerShow.setText(p4nS);
                            PlayerShow.setTextColor(lp);
                        }
                    },600);
                    break;
                case 5:

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PlayerShow.setText(p5nS);
                            PlayerShow.setTextColor(lo);
                        }
                    },600);
                    break;

            }
        }

        static public void setColor(int color) {
            switch (color){
                case 1:
                move(view);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gamePage.color.setBackgroundColor(Color.GREEN);}
                    },400);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        come(view);
                        if (Build.VERSION.SDK_INT > 26) {
                            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(100);
                        }

                       }
                },50);
                break;

                case 2:

                move(view);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gamePage.color.setBackgroundColor(Color.YELLOW);
                    }
                }, 400);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        come(view);
                        if (Build.VERSION.SDK_INT > 26) {
                            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(100);
                        }}
                },50);
                break;
                case 3:
                    move(view);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gamePage.color.setBackgroundColor(lb);
                        }
                    }, 400);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            come(view);
                            if (Build.VERSION.SDK_INT > 26) {
                                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(100);
                            }}
                    },50);
                    break;
                case 4:
                    move(view);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gamePage.color.setBackgroundColor(lp);
                        }
                    }, 400);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            come(view);
                            if (Build.VERSION.SDK_INT > 26) {
                                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(100);
                            }}
                    },50);
                    break;
                case 5:
                    move(view);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gamePage.color.setBackgroundColor(lo);
                        }
                    }, 400);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            come(view);
                            if (Build.VERSION.SDK_INT > 26) {
                                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(100);
                            }}
                    },50);
                    break;

            }
        }


        public static void setP1s(final int p1s,final int p2s,final int p3s,final int p4s,final int p5s) {

        p1sc=p1s;
        p2sc=p2s;
        p3sc=p3s;
        p4sc=p4s;
        p5sc=p5s;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gamePage.p1s.setText(p1nS+" : "+p1s);
                gamePage.p1s.setTextColor(Color.RED);
            }
        },1000);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gamePage.p1s.setText(p2nS+" : "+p2s);
                    gamePage.p1s.setTextColor(Color.BLACK);
                }
            },2000);

            if (nums >= 3) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gamePage.p1s.setText(p3nS + " : " + p3s);
                        gamePage.p1s.setTextColor(Color.MAGENTA);
                    }
                }, 3000);
            }

            if(nums>=4){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gamePage.p1s.setText(p4nS + " : " + p4s);
                        gamePage.p1s.setTextColor(Color.BLUE);
                    }
                }, 4000);
            }
           if(nums>=5){
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       gamePage.p1s.setText(p5nS + " : " + p5s);
                       gamePage.p1s.setTextColor(Color.DKGRAY);
                   }
               }, 5000);
           }
            checkfull();
        }


        public static void checkfull() {

            if (p1sc + p2sc +p3sc+ p4sc+ p5sc== (rows - 1) * (cols - 1)) {
                Intent intent=new Intent(context,winner.class);
                intent.putExtra("p1Score",p1sc);
                intent.putExtra("p2Score",p2sc);
                intent.putExtra("nums",nums);
                if (nums >= 3) {
                    intent.putExtra("p3Score",p3sc);
                }
                if(nums>=4){
                    intent.putExtra("p4Score",p4sc);
                }
                if(nums>=5){
                    intent.putExtra("p5Score",p5sc);
                }


                intent.putExtra("p1n",p1nS);
                intent.putExtra("p2n",p2nS);

                if (nums >= 3) {
                    intent.putExtra("p3n",p3nS);
                }
                if(nums>=4){
                    intent.putExtra("p4n",p4nS);
                }
                if(nums>=5){
                    intent.putExtra("p5n",p5nS);
                }

                context.startActivity(intent);
            }
        }


    @Override
    protected void onRestart() {
        super.onRestart();
        Intent pa = new Intent(gamePage.this, major.class);
        startActivity(pa);
    }

    public void moveDown(View view){
        Button end=findViewById(R.id.button2);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.go_down);
        end.startAnimation(animation);
    }

    public static void move(View view){
        Animation animation= AnimationUtils.loadAnimation(context1,R.anim.move);
        gamePage.color.startAnimation(animation);

    }

    public static void come(View view){
        Animation animation= AnimationUtils.loadAnimation(context1,R.anim.come);
        gamePage.color.startAnimation(animation);
    }

    public static void Winmusic(){
        MediaPlayer winMusic=MediaPlayer.create(context,R.raw.wins);
        winMusic.start();
    }

}

