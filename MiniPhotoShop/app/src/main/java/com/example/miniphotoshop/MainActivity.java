package com.example.miniphotoshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButtonZoomIn, imageButtonZoomOut, imageButtonRotate, imageButtonBright, imageButtonDark, imageButtonGray;
    private LinearLayout bitmapLayout;
    private float scaleX=1.0f, scaleY = 1.0f;
    private float color = 1.0f;
    private float satur = 1.0f;
    private float angle = 0.0f;
    private MyGraphicView myGraphicView;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니 포토샵");

        imageButtonZoomIn = findViewById(R.id.imageButtonZoomIn);
        imageButtonZoomOut = findViewById(R.id.imageButtonZoomOut);
        imageButtonRotate = findViewById(R.id.imageButtonRotate);
        imageButtonBright = findViewById(R.id.imageButtonBright);
        imageButtonDark = findViewById(R.id.imageButtonDark);
        imageButtonGray = findViewById(R.id.imageButtonGray);
        bitmapLayout = findViewById(R.id.bitmapLayout);

        myGraphicView = new MyGraphicView(this);

        bitmapLayout.addView(myGraphicView);

        imageButtonZoomIn.setOnClickListener(this);
        imageButtonZoomOut.setOnClickListener(this);
        imageButtonRotate.setOnClickListener(this);
        imageButtonBright.setOnClickListener(this);
        imageButtonDark.setOnClickListener(this);
        imageButtonGray.setOnClickListener(this);

        imageButtonZoomIn.callOnClick();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonZoomIn :
                scaleX += 0.2f;
                scaleY += 0.2f;
                break;
            case R.id.imageButtonZoomOut :
                scaleX -= 0.2f;
                scaleY -= 0.2f;
                break;
            case R.id.imageButtonRotate :
                angle +=10;
                break;
            case R.id.imageButtonBright :
                color += 0.2f;
                break;
            case R.id.imageButtonDark :
                color -= 0.2f;
                break;
            case R.id.imageButtonGray :
                satur = (satur == 0) ? (1) : (0);
                break;

        }

        myGraphicView.invalidate();

    }

    private class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }
        public MyGraphicView(Context context, AttributeSet attr){
            super(context, attr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int centerX = this.getWidth()/2;
            int centerY = this.getHeight()/2;
            canvas.scale(scaleX, scaleY,centerX,centerY);
            canvas.rotate(angle, centerX, centerY);

            Paint paint = new Paint();
            float[] array = {color,0,0,0,0, 0,color,0,0,0, 0,0,color,0,0, 0,0,0,1,0};

            ColorMatrix colorMatrix = new ColorMatrix(array);

            if(satur == 0.0f) {
                colorMatrix.setSaturation(satur);
            }
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.renoir10);

            int locateX = (this.getWidth() - bitmap.getWidth())/2;
            int locateY = (this.getHeight() - bitmap.getHeight())/2;

            canvas.drawBitmap(bitmap, locateX, locateY, paint);
            bitmap.recycle();

        }
    }
}
