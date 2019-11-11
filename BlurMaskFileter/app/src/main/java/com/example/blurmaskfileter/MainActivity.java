package com.example.blurmaskfileter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final int NORMAL = 1, INNER = 2, OUTER = 3, SOLID = 4;
    private int select = NORMAL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphic(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"NORMAL");
        menu.add(0,2,0,"INNER");
        menu.add(0,3,0,"OUTER");
        menu.add(0,4,0,"SOLID");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1 :    select = NORMAL;
                break;
            case 2 :    select = INNER;
                break;
            case 3 :    select = OUTER;
                break;
            case 4 :    select = SOLID;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyGraphic extends View {
        public MyGraphic(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            Paint paint = new Paint();
            BlurMaskFilter bMask;



            switch (select) {
                case NORMAL :
                    bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
                    paint.setMaskFilter(bMask);
                    canvas.drawBitmap(picture, picX, picY, paint);
                    break;
                case INNER :
                     bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.INNER);
                     paint.setMaskFilter(bMask);
                     canvas.drawBitmap(picture, picX, picY, paint);

                    break;
                case OUTER :
                     bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.OUTER);
                     paint.setMaskFilter(bMask);
                     canvas.drawBitmap(picture, picX, picY, paint);


                    break;
                case SOLID :
                     bMask = new BlurMaskFilter (30, BlurMaskFilter.Blur.SOLID);
                     paint.setMaskFilter(bMask);
                     canvas.drawBitmap(picture, picX, picY, paint);

                    break;
            }
            invalidate();
            picture.recycle();
        }
    }
}
