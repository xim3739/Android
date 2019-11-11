package com.example.canvasgeometry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int cenX,cenY,picX,picY;
    private int selectValue;
    private final int ROTATE = 1, TRANS = 2, SCALE = 3, SREW = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,1,0,"회전");
        menu.add(0,2,0,"이동");
        menu.add(0,3,0,"확대");
        menu.add(0,4,0,"기울이기");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                selectValue = ROTATE;
                break;
            case 2:
                selectValue = TRANS;
                break;
            case 3:
                selectValue = SCALE;
                break;
            case 4:
                selectValue = SREW;
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }
        public MyGraphicView(Context context, AttributeSet attr) {
            super(context, attr);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.image1);

            cenX = this.getWidth() / 2;
            cenY = this.getHeight() / 2;
            picX = (this.getWidth() - picture.getWidth()) / 2;
            picY = (this.getHeight() - picture.getHeight()) / 2;


             switch (selectValue) {
                 case ROTATE :
                     canvas.rotate(45, cenX, cenY);
                     canvas.drawBitmap(picture, picX, picY, null);
                     break;
                 case TRANS :
                     canvas.translate(-150, 200);
                     canvas.drawBitmap(picture, picX, picY, null);
                     break;
                 case SCALE :
                     canvas.scale(2, 2, cenX, cenY);
                     canvas.drawBitmap(picture, picX, picY, null);
                     break;
                 case SREW :
                     canvas.skew(0.3f, 0.3f);
                     canvas.drawBitmap(picture, picX, picY, null);
                     break;
             }
            invalidate();
            picture.recycle();
        }
    }
}
