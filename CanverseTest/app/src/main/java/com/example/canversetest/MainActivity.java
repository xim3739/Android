package com.example.canversetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public final int SELECTELINE = 1, SELECTECIRCLE = 2;
    private int saveValue = SELECTELINE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,1,0,"선그리기");
        menu.add(0,2,0,"원그리기");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1 :
                saveValue = SELECTELINE;
                break;
            case 2 :
                saveValue = SELECTECIRCLE;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyGraphicView extends View {
        public int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        public MyGraphicView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN :
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    break;
                case MotionEvent.ACTION_UP :
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                    //무우효오화아
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE :

                    break;
                case MotionEvent.ACTION_CANCEL :

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
            paint.setColor(Color.RED);

            switch (saveValue) {
                case SELECTELINE :
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;

                case SELECTECIRCLE :
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY,2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
            }
        }
    }
}
