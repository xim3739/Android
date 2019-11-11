package com.example.example_9_2_shapedraw;

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
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final int LINE = 1, CIRCLE = 2, RECTANGLE = 3, COLORRED = 4, COLORBLUE = 5, COLORGREEN = 6;
    private int selectValue = LINE;
    private int selectColorValue = COLORRED;
    private Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphic(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"선 그리기");
        menu.add(0,2,0,"원 그리기");
        menu.add(0,3,0,"사각형 그리기");

        SubMenu subMenu = menu.addSubMenu("색상 고르기");
        subMenu.add(1, 4, 0, "빨간색");
        subMenu.add(1, 5, 0, "파란색");
        subMenu.add(1, 6, 0, "초록색");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1 :
                selectValue = LINE;
                break;
            case 2 :
                selectValue = CIRCLE;
                break;
            case 3 :
                selectValue = RECTANGLE;
                break;
            case 4 :
                paint.setColor(Color.RED);
                break;
            case 5 :
                paint.setColor(Color.BLUE);
                break;
            case 6 :
                paint.setColor(Color.GREEN);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private class MyGraphic extends View {

        public int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphic(Context context) {
            super(context);
        }

        public MyGraphic(Context context, AttributeSet attr) {
            super(context, attr);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN :
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_UP :
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;

            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            switch (selectValue) {
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case RECTANGLE:
                    canvas.drawRect(startX, startY, stopX, stopY, paint);
                    break;
            }

        }
    }
}
