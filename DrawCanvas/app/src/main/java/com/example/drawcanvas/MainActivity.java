package com.example.drawcanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final int LINE = 1, CIRCLE = 2, RECTANGLE = 3, CLEAR = 7;

    public int selectShape = LINE;
    private int selectColor = Color.BLACK;

    public static boolean isFinished = false;
    public static List<MyShape> myShapeList = new ArrayList<MyShape>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphics(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        menu.add(0,1,0,"선 그리기");
        menu.add(0,2,0,"원 그리기");
        menu.add(0,3,0,"사각형 그리기");
        SubMenu subMenu = menu.addSubMenu("색상 변경");
        subMenu.add(0,4,0,"빨강");
        subMenu.add(0,5,0,"초록");
        subMenu.add(0,6,0,"파랑");
        subMenu.add(0,7,0,"지우기");

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                selectShape = LINE;
                break;
            case 2:
                selectShape = CIRCLE;
                break;
            case 3:
                selectShape = RECTANGLE;
                break;
            case 4 :
                selectColor = Color.RED;
                break;
            case 5:
                selectColor = Color.GREEN;
                break;
            case 6:
                selectColor = Color.BLUE;
                break;
            case 7 :
                selectShape = CLEAR;
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private class MyGraphics extends View {
        private int startX = -1, startY = -1, stopX = -1, stopY = -1;
        public MyGraphics(Context context) {
            super(context);
        }
        public  MyGraphics(Context context, AttributeSet attr) {
            super(context, attr);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN :
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    isFinished = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    isFinished = false;
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    MyShape shape = new MyShape();
                    shape.shapeType = selectShape;
                    shape.startX = startX;
                    shape.startY = startY;
                    shape.stopX = stopX;
                    shape.stopY = stopY;
                    shape.color = selectColor;

                    myShapeList.add(shape);
                    isFinished = true;
                    this.invalidate();

                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            Bitmap bitmap;

            for(int i = 0; i < myShapeList.size(); i++) {
                MyShape shape = myShapeList.get(i);
                paint.setColor(shape.color);

                switch (shape.shapeType) {
                    case LINE :
                        canvas.drawLine(shape.startX, shape.startY, shape.stopX, shape.stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(shape.stopX - shape.startX, 2) + Math.pow(shape.stopY - shape.startY, 2));
                        canvas.drawCircle(shape.startX, shape.startY, radius, paint);
                        break;
                    case RECTANGLE :
                        Rect rect = new Rect(shape.startX, shape.startY, shape.stopX, shape.stopY);
                        canvas.drawRect(rect, paint);
                        break;
                    case CLEAR :
                        myShapeList.removeAll(myShapeList);
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        canvas.drawColor(Color.WHITE);
                        selectShape = LINE;
                        selectColor = Color.BLACK;
                        break;
                }
            }
            if(isFinished == false) {
                paint.setColor(selectColor);

                switch (selectShape) {
                    case LINE :
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE :
                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                        canvas.drawCircle(startX, startY, radius, paint);
                        break;
                    case RECTANGLE :
                        canvas.drawRect(startX, startY, stopX, stopY, paint);
                        break;

                }
            }
        }
    }

    private static class MyShape {
        int shapeType;
        int startX, startY, stopX,stopY;
        int color;
    }
}
