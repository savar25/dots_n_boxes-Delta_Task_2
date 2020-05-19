package com.example.dots_n_boxes;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.VIBRATOR_SERVICE;

public class Gview extends View {
    public Paint paint, cpaint, markpaint, bpaint,margpaint,letPaint,epaint,ebpaint;
    public ArrayList<ArrayList<Point>> grid;
    public int col, row;
    private Bitmap bitmap;
    public Canvas canvas;
    public Path path,ePath;
    public Point initPoint, finalPoint;
    public Point P_initPoint,P_finalPoint;
    ArrayList<ArrayList<Point>>lineStore=new ArrayList<>();
    private static final String TAG = "CV1";
    private static final String TAG1 = "Trial";
    private static final String TAG2 = "Box";
    public int p1s=0,p2s=0,p3s=0,p4s=0,p5s=0;
    public String p1n,p2n,p3n="A",p4n="A",p5n="A";
    public boolean gridcheck=true;
    public int lb,lp,lo;
    public int players=2;
    public boolean undoFlag=true;
    public boolean unUP=false,unDown=false,unRight=false,unLeft=false;



    public Gview(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
        grid = new ArrayList<>();

        path = new Path();
        ePath=new Path();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Gview, 0, 0);

        try {
            col = a.getInteger(R.styleable.Gview_columns, 0);
            row = a.getInteger(R.styleable.Gview_rows, 0);
            p1n=a.getString(R.styleable.Gview_p1name);
            p2n=a.getString(R.styleable.Gview_p2name);
        } finally {
            a.recycle();
        }

        lb=getResources().getColor(R.color.LightBlue);
        lp=getResources().getColor(R.color.LightPink);
        lo=getResources().getColor(R.color.LightOrange);

    }


   
    
    public void setupPaint() {



        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.BUTT);


        cpaint = new Paint(Paint.DITHER_FLAG);
        cpaint.setColor(getResources().getColor(R.color.Orange));
        cpaint.setAntiAlias(false);
        cpaint.setStrokeWidth(15);
        cpaint.setStyle(Paint.Style.FILL_AND_STROKE);
        cpaint.setStrokeJoin(Paint.Join.MITER);
        cpaint.setStrokeCap(Paint.Cap.BUTT);


        markpaint = new Paint(Paint.DITHER_FLAG);
        markpaint.setColor(Color.GREEN);
        markpaint.setAntiAlias(false);
        markpaint.setStrokeWidth(4);
        markpaint.setStyle(Paint.Style.FILL_AND_STROKE);
        markpaint.setStrokeJoin(Paint.Join.MITER);
        markpaint.setStrokeCap(Paint.Cap.BUTT);

        bpaint = new Paint(Paint.DITHER_FLAG);
        bpaint.setColor(Color.RED);
        bpaint.setAntiAlias(false);
        bpaint.setStrokeWidth(15);
        bpaint.setStyle(Paint.Style.STROKE);
        bpaint.setStrokeJoin(Paint.Join.MITER);
        bpaint.setStrokeCap(Paint.Cap.BUTT);

        margpaint = new Paint(Paint.DITHER_FLAG);
        margpaint.setColor(Color.BLACK);
        margpaint.setAntiAlias(false);
        margpaint.setStrokeWidth(5);
        margpaint.setStyle(Paint.Style.STROKE);
        margpaint.setStrokeJoin(Paint.Join.MITER);
        margpaint.setStrokeCap(Paint.Cap.BUTT);

        Typeface letter=Typeface.createFromAsset(getResources().getAssets(),"cena.ttf");

        letPaint = new Paint(Paint.DITHER_FLAG);
        letPaint.setColor(getResources().getColor(R.color.DBrown));
        letPaint.setAntiAlias(false);
        letPaint.setTextSize(40);
        letPaint.setTextAlign(Paint.Align.CENTER);
        letPaint.setTypeface(letter);

        epaint = new Paint(Paint.DITHER_FLAG);
        epaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        epaint.setAntiAlias(false);
        epaint.setStrokeWidth(4);
        epaint.setStyle(Paint.Style.FILL_AND_STROKE);
        epaint.setStrokeJoin(Paint.Join.MITER);
        epaint.setStrokeCap(Paint.Cap.BUTT);

        ebpaint = new Paint(Paint.DITHER_FLAG);
        ebpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        ebpaint.setAntiAlias(false);
        ebpaint.setStrokeWidth(2);
        ebpaint.setStyle(Paint.Style.FILL);
        ebpaint.setStrokeJoin(Paint.Join.MITER);
        ebpaint.setStrokeCap(Paint.Cap.BUTT);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

    }


   
    
    @Override
    public void onDraw(Canvas canvas) {

        int screenWidth = this.getMeasuredWidth();
        int screenHeight = this.getMeasuredHeight();
        //Bitmap Setup
        canvas.drawBitmap(bitmap, 0, 0, cpaint);


        for (int i = 0; i < row; i++) {
            ArrayList<Point> line = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                line.add(new Point(screenWidth / (col+1) + j * (screenWidth / (col+1)), screenHeight / (row+1) + i * (screenHeight / (row+1))));
            }
            grid.add(line);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.canvas.drawCircle(grid.get(i).get(j).x, grid.get(i).get(j).y, 5, paint);
            }
        }


        canvas.drawRect(0, 0, screenWidth, screenHeight, bpaint);
    }





    public Point returnPoint(Point p) {
        Point gp = new Point();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((p.x <= (grid.get(i).get(j).x + 50) && p.x >= (grid.get(i).get(j).x - 50)) && (p.y <= (grid.get(i).get(j).y + 50) && p.y >= (grid.get(i).get(j).y - 50))) {
                    gp = grid.get(i).get(j);

                }
            }


        }
        return gp;
    }



    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ArrayList<ArrayList<Point>> element=new ArrayList<>();
        ArrayList<Point> init1=new ArrayList<>(),init2=new ArrayList<>(),init3=new ArrayList<>(),init4=new ArrayList<>();
        Point p = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
        Point zero = new Point(0, 0);
        unUP=false;unDown=false;unRight=false;unLeft=false;
        int c=0;
        int sw = getMeasuredWidth();
        int sh = getMeasuredHeight();

        Boolean flag=false;
        undoFlag=true;

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: Down"+p);
                if (checkIngrid(p)) {
                    initPoint = returnPoint(p);
                    Log.d(TAG, "onTouchEvent: init"+ initPoint);
                    path.moveTo(initPoint.x, initPoint.y);
                    gridcheck=true;
                }else {gridcheck=false;}
                break;


            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: Up"+p);

                if (checkIngrid(p)&& gridcheck==true) {
                    finalPoint = returnPoint(p);
                    Log.d(TAG, "onTouchEvent: final"+ finalPoint);
                    path.lineTo(finalPoint.x, finalPoint.y);
                    if ((initPoint != finalPoint) && (finalPoint.x == initPoint.x + sw / (col + 1)) || (finalPoint.x == initPoint.x - sw / (col + 1) || (finalPoint.y == initPoint.y - sh / (row + 1)) || (finalPoint.y == initPoint.y + sh / (row + 1)))
                            && (initPoint != zero) && (finalPoint != zero)) {
                        if (initPoint.x == finalPoint.x || initPoint.y == finalPoint.y) {
                            canvas.drawPath(path, markpaint);
                            path.reset();


                            ArrayList<Point> initiate = new ArrayList<>();
                            initiate.add(initPoint);
                            initiate.add(finalPoint);
                            lineStore.add(initiate);
                            ArrayList<Point> init=new ArrayList<>();
                            init.add(finalPoint);
                            init.add(initPoint);
                            lineStore.add(init);



                            if(initPoint.x==finalPoint.x){
                                c=3;

                            }else if(initPoint.y==finalPoint.y){
                                c=1;
                            }



                            switch(c){


                                case 1:
                                    Log.d(TAG, "upperboxCheck: called");
                                    int dh = getMeasuredHeight() / (row + 1);
                                    Point UinitPlus = new Point(initPoint.x, initPoint.y - dh);
                                    Point UfinalPlus = new Point(finalPoint.x, finalPoint.y - dh);

                                    init1.add(initPoint);
                                    init1.add(finalPoint);
                                    element.add(init1);

                                    init2.add(UinitPlus);
                                    init2.add(initPoint);
                                    element.add(init2);

                                    init3.add(UfinalPlus);
                                    init3.add(finalPoint);
                                    element.add(init3);

                                    init4.add(UinitPlus);
                                    init4.add(UfinalPlus);
                                    element.add(init4);



                                    if  (checkPoint(element)){
                                        float top,left,right,bottom;
                                        if(UinitPlus.x>UfinalPlus.x){
                                            left=UinitPlus.x;
                                            right=UfinalPlus.x;
                                        }else{
                                            left=UfinalPlus.x;
                                            right=UinitPlus.x;
                                        }

                                        if(UinitPlus.y>finalPoint.y){
                                            top=finalPoint.y;
                                            bottom=UinitPlus.y;
                                        }else{
                                            bottom=finalPoint.y;
                                            top=UinitPlus.y;
                                        }
                                        canvas.drawRect(left-4, top+4, right+4, bottom-4, markpaint);
                                        canvas.drawRect(left-4, top+4, right+4, bottom-4, margpaint);

                                        unUP=true;
                                        Log.d(TAG2, "upperboxCheck: assigned");
                                        gamePage.Winmusic();
                                        if(markpaint.getColor()==Color.GREEN){
                                            this.p1s++;
                                            canvas.drawText(p1n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+UfinalPlus.y)/2+10,letPaint);
                                        }
                                        else if(markpaint.getColor()==Color.YELLOW){
                                            this.p2s++;
                                            canvas.drawText(p2n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+UfinalPlus.y)/2+10,letPaint);

                                        }else if(markpaint.getColor()==lb){
                                            this.p3s++;
                                            canvas.drawText(p3n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+UfinalPlus.y)/2+10,letPaint);
                                        }else if(markpaint.getColor()==lp) {
                                            this.p4s++;
                                            canvas.drawText(p4n, (finalPoint.x + initPoint.x) / 2, (finalPoint.y + UfinalPlus.y) / 2 + 10, letPaint);
                                        }else if(markpaint.getColor()==lo) {
                                            this.p5s++;
                                            canvas.drawText(p5n, (finalPoint.x + initPoint.x) / 2, (finalPoint.y + UfinalPlus.y) / 2 + 10, letPaint);
                                        }
                                        flag=true;

                                    }



                                case 2:
                                    Log.d(TAG, "lowerBoxCheck: called");
                                    int Ldh = getMeasuredHeight() / (row + 1);
                                    Point LinitPlus = new Point(initPoint.x, initPoint.y + Ldh);
                                    Point LfinalPlus = new Point(finalPoint.x, finalPoint.y + Ldh);


                                    init1.clear();
                                    init2.clear();
                                    init3.clear();
                                    init4.clear();
                                    element.clear();

                                    init1.add(initPoint);
                                    init1.add(finalPoint);
                                    element.add(init1);

                                    init2.add(LinitPlus);
                                    init2.add(initPoint);
                                    element.add(init2);

                                    init3.add(LfinalPlus);
                                    init3.add(finalPoint);
                                    element.add(init3);

                                    init4.add(LinitPlus);
                                    init4.add(LfinalPlus);
                                    element.add(init4);


                                    if(checkPoint(element)) {
                                        float top,left,right,bottom;
                                        if(LinitPlus.x<LfinalPlus.x){
                                            left=LinitPlus.x;
                                            right=LfinalPlus.x;
                                        }else{
                                            left=LfinalPlus.x;
                                            right=LinitPlus.x;
                                        }

                                        if(LinitPlus.y>finalPoint.y){
                                            top=finalPoint.y;
                                            bottom=LinitPlus.y;
                                        }else{
                                            bottom=finalPoint.y;
                                            top=LinitPlus.y;
                                        }

                                        canvas.drawRect(left+4, top+4, right-4, bottom-4, markpaint);
                                        canvas.drawRect(left+4, top+4, right-4, bottom-4, margpaint);
                                        unDown=true;
                                        Log.d(TAG2, "lowerBoxCheck: assgigned");
                                       gamePage.Winmusic();
                                        if(markpaint.getColor()==Color.GREEN){
                                            this.p1s++;
                                            canvas.drawText(p1n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+LfinalPlus.y)/2+10,letPaint);
                                        }
                                        else if(markpaint.getColor()==Color.YELLOW){
                                            this.p2s++;
                                            canvas.drawText(p2n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+LfinalPlus.y)/2+10,letPaint);

                                        }else if(markpaint.getColor()==lb){
                                            this.p3s++;
                                            canvas.drawText(p3n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+LfinalPlus.y)/2+10,letPaint);
                                        }else if(markpaint.getColor()==lp) {
                                            this.p4s++;
                                            canvas.drawText(p4n, (finalPoint.x + initPoint.x) / 2, (finalPoint.y + LfinalPlus.y) / 2 + 10, letPaint);
                                        }else if(markpaint.getColor()==lo) {
                                            this.p5s++;
                                            canvas.drawText(p5n, (finalPoint.x + initPoint.x) / 2, (finalPoint.y + LfinalPlus.y) / 2 + 10, letPaint);
                                        }
                                        flag=true;

                                    }
                                    break;


                                    case 3:
                                    Log.d(TAG, "rightCheckBox: called");
                                    int Rdw = getMeasuredWidth() / (col + 1);
                                    Point RinitPlus = new Point(initPoint.x+Rdw, initPoint.y);
                                    Point RfinalPlus = new Point(finalPoint.x+Rdw, finalPoint.y );
                                        init1.clear();
                                        init2.clear();
                                        init3.clear();
                                        init4.clear();
                                        element.clear();
                                    init1.add(initPoint);
                                    init1.add(finalPoint);
                                    element.add(init1);

                                    init2.add(RinitPlus);
                                    init2.add(initPoint);
                                    element.add(init2);

                                    init3.add(RfinalPlus);
                                    init3.add(finalPoint);
                                    element.add(init3);

                                    init4.add(RinitPlus);
                                    init4.add(RfinalPlus);
                                    element.add(init4);



                                    if (checkPoint(element)) {
                                        float top,left,right,bottom;
                                        if(initPoint.x<RinitPlus.x){
                                            left=initPoint.x;
                                            right=RinitPlus.x;
                                        }else{
                                            left=RinitPlus.x;
                                            right=initPoint.x;
                                        }

                                        if(initPoint.y>finalPoint.y){
                                            top=finalPoint.y;
                                            bottom=initPoint.y;
                                        }else{
                                            bottom=finalPoint.y;
                                            top=initPoint.y;
                                        }
                                        canvas.drawRect(left+4, top+4, right-4, bottom-4, markpaint);
                                        canvas.drawRect(left+4, top+4, right-4, bottom-4, margpaint);
                                        unRight=true;
                                        gamePage.Winmusic();
                                        Log.d(TAG2, "rightCheckBox: assigned");
                                        if (markpaint.getColor() == Color.GREEN) {
                                            this.p1s++;
                                            canvas.drawText(p1n, (initPoint.x + RinitPlus.x) / 2, (initPoint.y + finalPoint.y) / 2 + 10, letPaint);
                                        } else if (markpaint.getColor() == Color.YELLOW) {
                                            this.p2s++;
                                            canvas.drawText(p2n, (initPoint.x + RinitPlus.x) / 2, (initPoint.y + finalPoint.y) / 2 + 10, letPaint);
                                        } else if (markpaint.getColor() == lb) {
                                            this.p3s++;
                                            canvas.drawText(p3n, (initPoint.x + RinitPlus.x) / 2, (initPoint.y + finalPoint.y) / 2 + 10, letPaint);
                                        } else if (markpaint.getColor() == lp) {
                                            this.p4s++;
                                            canvas.drawText(p4n, (initPoint.x + RinitPlus.x) / 2, (initPoint.y + finalPoint.y) / 2 + 10, letPaint);
                                        } else if (markpaint.getColor() == lo) {
                                            this.p5s++;
                                            canvas.drawText(p5n, (initPoint.x + RinitPlus.x) / 2, (initPoint.y + finalPoint.y) / 2 + 10, letPaint);
                                        }

                                        flag=true;

                                    }


                                    case 4:

                                        Log.d(TAG, "leftCheckBox: called");
                                    int Ldw = getMeasuredWidth() / (col + 1);
                                    Point LEinitPlus = new Point(initPoint.x-Ldw, initPoint.y);
                                    Point LEfinalPlus = new Point(finalPoint.x-Ldw, finalPoint.y );

                                    init1.clear();
                                    init2.clear();
                                    init3.clear();
                                    init4.clear();
                                    element.clear();

                                    init1.add(initPoint);
                                    init1.add(finalPoint);
                                    element.add(init1);

                                    init2.add(LEinitPlus);
                                    init2.add(initPoint);
                                    element.add(init2);

                                    init3.add(LEfinalPlus);
                                    init3.add(finalPoint);
                                    element.add(init3);

                                    init4.add(LEinitPlus);
                                    init4.add(LEfinalPlus);
                                    element.add(init4);



                                    if (checkPoint(element)) {

                                        float top,left,right,bottom;
                                        if(initPoint.x<LEinitPlus.x){
                                            left=initPoint.x;
                                            right=LEinitPlus.x;
                                        }else{
                                            left=LEinitPlus.x;
                                            right=initPoint.x;
                                        }

                                        if(initPoint.y>finalPoint.y){
                                            top=finalPoint.y;
                                            bottom=initPoint.y;
                                        }else{
                                            bottom=finalPoint.y;
                                            top=initPoint.y;
                                        }

                                        canvas.drawRect(left+4, top+4, right-4, bottom-4, markpaint);
                                        canvas.drawRect(left+4, top+4, right-4, bottom-4, margpaint);
                                        gamePage.Winmusic();
                                        unLeft=true;
                                        Log.d(TAG2, "leftCheckBox: assigned");
                                        if(markpaint.getColor()==Color.GREEN){
                                            this.p1s++;
                                            canvas.drawText(p1n,(initPoint.x+LEinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
                                        }
                                        else if(markpaint.getColor()==Color.YELLOW){
                                            this.p2s++;
                                            canvas.drawText(p2n,(initPoint.x+LEinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
                                        } else if (markpaint.getColor() == lb) {
                                            this.p3s++;
                                            canvas.drawText(p2n,(initPoint.x+LEinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
                                        } else if (markpaint.getColor() == lp) {
                                            this.p4s++;
                                            canvas.drawText(p4n,(initPoint.x+LEinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
                                        } else if (markpaint.getColor() == lo) {
                                            this.p5s++;
                                            canvas.drawText(p5n,(initPoint.x+LEinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
                                        }
                                        flag=true;

                                    }
                                    break;
                            }

                            P_initPoint=initPoint;
                            P_finalPoint=finalPoint;

                            if(markpaint.getColor()==Color.GREEN){
                                if (flag == false) {
                                    markpaint.setColor(Color.YELLOW);
                                    gamePage.setColor(2);
                                    gamePage.setPlayerShow(2);
                                }

                            }else if(markpaint.getColor()==Color.YELLOW) {
                                if(players<3) {
                                    if (flag == false) {
                                        markpaint.setColor(Color.GREEN);
                                        gamePage.setColor(1);
                                        gamePage.setPlayerShow(1);
                                    }
                                }else {
                                    if (flag == false) {
                                        markpaint.setColor(lb);
                                        gamePage.setColor(3);
                                        gamePage.setPlayerShow(3);
                                    }
                                }

                            }else if(markpaint.getColor()==lb){
                                if(players<4) {
                                    if (flag == false) {
                                        markpaint.setColor(Color.GREEN);
                                        gamePage.setColor(1);
                                        gamePage.setPlayerShow(1);
                                    }
                                }else {
                                    if (flag == false) {
                                        markpaint.setColor(lp);
                                        gamePage.setColor(4);
                                        gamePage.setPlayerShow(4);
                                    }
                                }

                            }else if(markpaint.getColor()==lp){
                                if(players<5) {
                                    if (flag == false) {
                                        markpaint.setColor(Color.GREEN);
                                        gamePage.setColor(1);
                                        gamePage.setPlayerShow(1);
                                    }
                                }else {
                                    if (flag == false) {
                                        markpaint.setColor(lo);
                                        gamePage.setColor(5);
                                        gamePage.setPlayerShow(5);
                                    }
                                }
                            }else if(markpaint.getColor()==lo){
                                if(flag==false) {
                                    markpaint.setColor(Color.GREEN);
                                    gamePage.setColor(1);
                                    gamePage.setPlayerShow(1);
                                }
                            }
                            gamePage.setP1s(this.p1s, this.p2s, this.p3s,this.p4s,this.p5s);
                        } else {
                            path.reset();
                        }


                    }

                }else {path.reset();}



                break;
        }


        invalidate();
        return true;



    }


    
    public boolean checkPoint (ArrayList<ArrayList<Point>> element){
        getMeasuredHeight();
        getMeasuredWidth();

        Log.d(TAG, "checkPoint: called");
        ArrayList<Boolean> res=new ArrayList<>();
        ArrayList<ArrayList<Point>> lineStore=this.lineStore;

        for(ArrayList<Point> item:element) {
            boolean flag=false;
            for (ArrayList<Point> each:lineStore){

                if (checkval(item,each)) {
                    flag=true;
                    break;
                }
            }
            if(flag==true){
                res.add(true);
            }else{
                res.add(false);
            }
        }

        if(res.get(0)==true && res.get(1)==true && res.get(2)==true && res.get(3)==true){
            return true;
        }else{
            return false;
        }
    }


    
    
    public boolean checkval(ArrayList<Point> val,ArrayList<Point> checklist){

        if(val.get(0).x==checklist.get(0).x && val.get(0).y==checklist.get(0).y && val.get(1).x==checklist.get(1).x && val.get(1).y==checklist.get(1).y){

            return true;

        }else{
            return false;
        }
    }

    
    public void setCol(int col) {
        this.col = col;
    }

    
    public void setRow(int row) {
        this.row = row;
    }

    
    public void setP1n(String p1n) {
        this.p1n = p1n;
    }

    
    public void setP2n(String p2n) {
        this.p2n = p2n;
    }

    
    public boolean checkIngrid(Point p){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((p.x <= (grid.get(i).get(j).x + 50) && p.x >= (grid.get(i).get(j).x - 50)) && (p.y <= (grid.get(i).get(j).y + 50) && p.y >= (grid.get(i).get(j).y - 50))) {
                    Log.d(TAG, "checkIngrid: True");
                    return true;

                }
            }


        }
        Log.d(TAG, "checkIngrid: False");
        return false;
    }

    
    public void setP3n(String p3n) {
        this.p3n = p3n;
    }

    
    public void setP4n(String p4n) {
        this.p4n = p4n;
    }

    
    public void setP5n(String p5n) {
        this.p5n = p5n;
    }

    
    public void setPlayers(int players) {
        this.players = players;
    }

    
    public void undo(){
        int dh = getMeasuredHeight() / (row + 1);
        int dw=getMeasuredWidth()/(col+1);
    
   
        if(undoFlag==true) {
            ePath.moveTo(P_initPoint.x,P_initPoint.y);
            ePath.lineTo(P_finalPoint.x,P_finalPoint.y);
            canvas.drawPath(ePath,epaint);



            for(int i=0;i<lineStore.size();i++){
                if((lineStore.get(i).get(0)==P_initPoint && lineStore.get(i).get(1)==P_finalPoint)|| (lineStore.get(i).get(1)==P_initPoint && lineStore.get(i).get(0)==P_finalPoint)){
                    lineStore.remove(i);
                }
            }

            if(unUP){
                canvas.drawRect(initPoint.x, initPoint.y-dh, finalPoint.x, finalPoint.y, ebpaint);
            }
            if(unDown){
                canvas.drawRect(initPoint.x, initPoint.y+dh, finalPoint.x, finalPoint.y, ebpaint);
            }
            if(unRight){
                canvas.drawRect(initPoint.x, initPoint.y, initPoint.x+dw, finalPoint.y, ebpaint);
            }
            if(unLeft){
                canvas.drawRect(initPoint.x, initPoint.y, initPoint.x-dw, finalPoint.y, ebpaint);
            }



            if (markpaint.getColor() == Color.GREEN) {
                switch (players) {
                    case 2:
                        markpaint.setColor(Color.YELLOW);
                        gamePage.setColor(2);
                        gamePage.setPlayerShow(2);
                        undoFlag=false;
                        break;
                    case 3:
                        markpaint.setColor(lb);
                        gamePage.setColor(3);
                        gamePage.setPlayerShow(3);
                        undoFlag=false;
                        break;
                    case 4:
                        markpaint.setColor(lp);
                        gamePage.setColor(4);
                        gamePage.setPlayerShow(4);
                        undoFlag=false;
                        break;
                    case 5:
                        markpaint.setColor(lo);
                        gamePage.setColor(5);
                        gamePage.setPlayerShow(5);
                        undoFlag=false;
                        break;

                }
                if(this.p1s!=0) {
                    if (unUP) {
                        this.p1s--;
                        unUP = false;
                    }
                    if (unDown) {
                        this.p1s--;
                        unDown = false;
                    }
                    if (unLeft) {
                        this.p1s--;
                        unRight = false;
                    }
                    if (unRight) {
                        this.p1s--;
                        unLeft = false;
                    }
                }

            } else if (markpaint.getColor() == Color.YELLOW) {
                markpaint.setColor(Color.GREEN);
                gamePage.setColor(1);
                gamePage.setPlayerShow(1);
                undoFlag=false;

                if(this.p2s!=0) {
                    if (unUP) {
                        this.p2s--;
                        unUP = false;
                    }
                    if (unDown) {
                        this.p2s--;
                        unDown = false;
                    }
                    if (unLeft) {
                        this.p2s--;
                        unRight = false;
                    }
                    if (unRight) {
                        this.p2s--;
                        unLeft = false;
                    }
                }
            } else if (markpaint.getColor() == lb) {

                markpaint.setColor(Color.YELLOW);
                gamePage.setColor(2);
                gamePage.setPlayerShow(2);
                undoFlag=false;
                if (this.p3s != 0) {
                    if (unUP) {
                        this.p3s--;
                        unUP = false;
                    }
                    if (unDown) {
                        this.p3s--;
                        unDown = false;
                    }
                    if (unLeft) {
                        this.p3s--;
                        unRight = false;
                    }
                    if (unRight) {
                        this.p3s--;
                        unLeft = false;
                    }
                }

            } else if (markpaint.getColor() == lp) {

                markpaint.setColor(lb);
                gamePage.setColor(3);
                gamePage.setPlayerShow(3);
                undoFlag=false;
                if(this.p4s!=0) {
                    if (unUP) {
                        this.p4s--;
                        unUP = false;
                    }
                    if (unDown) {
                        this.p4s--;
                        unDown = false;
                    }
                    if (unLeft) {
                        this.p4s--;
                        unRight = false;
                    }
                    if (unRight) {
                        this.p4s--;
                        unLeft = false;
                    }
                }
            } else if (markpaint.getColor() == lo) {
                markpaint.setColor(lp);
                gamePage.setColor(4);
                gamePage.setPlayerShow(4);
                undoFlag=false;
                if(this.p5s!=0) {
                    if (unUP) {
                        this.p5s--;
                        unUP = false;
                    }
                    if (unDown) {
                        this.p5s--;
                        unDown = false;
                    }
                    if (unLeft) {
                        this.p5s--;
                        unRight = false;
                    }
                    if (unRight) {
                        this.p5s--;
                        unLeft = false;
                    }
                }
            }

            ePath.reset();
            gamePage.setP1s(this.p1s,this.p2s,this.p3s,this.p4s,this.p5s);
            invalidate();

        }

    }

}


