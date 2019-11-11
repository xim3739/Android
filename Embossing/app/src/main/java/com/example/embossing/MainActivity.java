package com.example.embossing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphic(this));
    }

    private class MyGraphic extends View {
        public MyGraphic(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int cenX = this.getWidth()/2;
            int cenY = this.getHeight()/2;

            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            EmbossMaskFilter eMask;

            eMask = new EmbossMaskFilter(new float[]{3,3,3}, 0.5f, 5, 10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX,cenY,150,paint);
            invalidate();
        }
    }
}
