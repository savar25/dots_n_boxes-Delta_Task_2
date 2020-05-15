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
    public Paint paint, cpaint, markpaint, bpaint,margpaint,letPaint;
    public ArrayList<ArrayList<Point>> grid;
    public int col, row;
    private Bitmap bitmap;
    public Canvas canvas;
    public Path path;
    public Point initPoint, finalPoint;
    ArrayList<ArrayList<Point>>lineStore=new ArrayList<>();
    private static final String TAG = "CV1";
    private static final String TAG1 = "Trial";
    private static final String TAG2 = "Box";
    public int s=0;
    public int p1s=0,p2s=0;
    public String p1n,p2n;
    public boolean gridcheck=true;

    public Gview(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
        grid = new ArrayList<>();

        path = new Path();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Gview, 0, 0);

        try {
            col = a.getInteger(R.styleable.Gview_columns, 0);
            row = a.getInteger(R.styleable.Gview_rows, 0);
            p1n=a.getString(R.styleable.Gview_p1name);
            p2n=a.getString(R.styleable.Gview_p2name);
        } finally {
            a.recycle();
        }



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

        letPaint = new Paint(Paint.DITHER_FLAG);
        letPaint.setColor(getResources().getColor(R.color.DBrown));
        letPaint.setAntiAlias(false);
        letPaint.setTextSize(40);
        letPaint.setTextAlign(Paint.Align.CENTER);
        letPaint.setTypeface(Typeface.DEFAULT_BOLD);


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


    public Paint getMarkpaint() {
        return markpaint;
    }

    public void setMarkpaint(Paint markpaint) {
        this.markpaint = markpaint;
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
        Point p1=new Point();
        int c=0;
        int sw = getMeasuredWidth();
        int sh = getMeasuredHeight();

        Boolean flag=false;

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
                                        canvas.drawRect(UinitPlus.x, UinitPlus.y, UfinalPlus.x, finalPoint.y, markpaint);
                                        canvas.drawRect(UinitPlus.x, UinitPlus.y, UfinalPlus.x, finalPoint.y, margpaint);
                                        Log.d(TAG2, "upperboxCheck: assigned");
                                        gamePage.Winmusic();
                                        if(markpaint.getColor()==Color.GREEN){
                                            this.p1s++;
                                            canvas.drawText(p1n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+UfinalPlus.y)/2+10,letPaint);
                                        }
                                        else{
                                            this.p2s++;
                                            canvas.drawText(p2n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+UfinalPlus.y)/2+10,letPaint);
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
                                        canvas.drawRect(LinitPlus.x, LinitPlus.y, LfinalPlus.x, finalPoint.y, markpaint);
                                        canvas.drawRect(LinitPlus.x, LinitPlus.y, LfinalPlus.x, finalPoint.y, margpaint);
                                        Log.d(TAG2, "lowerBoxCheck: assgigned");
                                       gamePage.Winmusic();
                                        if(markpaint.getColor()==Color.GREEN){
                                            this.p1s++;
                                            canvas.drawText(p1n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+LfinalPlus.y)/2+10,letPaint);
                                        }
                                        else{
                                            this.p2s++;
                                            canvas.drawText(p2n,(finalPoint.x+initPoint.x)/2,(finalPoint.y+LfinalPlus.y)/2+10,letPaint);

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
                                        canvas.drawRect(initPoint.x, initPoint.y, RinitPlus.x, finalPoint.y, markpaint);
                                        canvas.drawRect(initPoint.x, initPoint.y, RinitPlus.x, finalPoint.y, margpaint);
                                        gamePage.Winmusic();
                                        Log.d(TAG2, "rightCheckBox: assigned");
                                        if(markpaint.getColor()==Color.GREEN){
                                            this.p1s++;
                                            canvas.drawText(p1n,(initPoint.x+RinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
                                        }
                                        else{
                                            this.p2s++;
                                            canvas.drawText(p2n,(initPoint.x+RinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
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
                                        canvas.drawRect(initPoint.x, initPoint.y, LEinitPlus.x, finalPoint.y, markpaint);
                                        canvas.drawRect(initPoint.x, initPoint.y, LEinitPlus.x, finalPoint.y, margpaint);
                                        gamePage.Winmusic();
                                        Log.d(TAG2, "leftCheckBox: assigned");
                                        if(markpaint.getColor()==Color.GREEN){
                                            this.p1s++;
                                            canvas.drawText(p1n,(initPoint.x+LEinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
                                        }
                                        else{
                                            this.p2s++;
                                            canvas.drawText(p2n,(initPoint.x+LEinitPlus.x)/2,(initPoint.y+finalPoint.y)/2+10,letPaint);
                                        }
                                        flag=true;

                                    }
                                    break;
                            }


                            switch (markpaint.getColor()) {
                                case Color.GREEN:
                                    if(flag==false) {
                                        markpaint.setColor(Color.YELLOW);
                                        gamePage.setColor(2);
                                        gamePage.setPlayerShow(2);
                                    }
                                    gamePage.setP1s(this.p1s);
                                    gamePage.setP2s(this.p2s);


                                    break;
                                case Color.YELLOW:

                                    if(flag==false) {
                                        markpaint.setColor(Color.GREEN);
                                        gamePage.setColor(1);
                                        gamePage.setPlayerShow(1);
                                    }
                                    gamePage.setP1s(this.p1s);
                                    gamePage.setP2s(this.p2s);



                                    break;
                            }

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


}


