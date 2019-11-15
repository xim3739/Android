package com.example.example_11_2_gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Gallery gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("포스터어어어어어갤러리이이");

        gallery = findViewById(R.id.gallery);

        MyGalleryAdapter myGalleryAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(myGalleryAdapter);
    }

    private class MyGalleryAdapter extends BaseAdapter {
        Context context;
        Integer[] movieID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10, R.drawable.mov11, R.drawable.mov12};
        String[] movieName = {"그림 1", "그림 2", "그림 3", "그림 4", "그림 5", "그림 6", "그림 7", "그림 8", "그림 9", "그림 10", "그림 11", "그림 12"};

        public MyGalleryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return movieID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);
            imageView.setImageResource(movieID[position]);
            final int pos = position;
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ImageView imageViewPoster = findViewById(R.id.imageViewPoster);
                    imageViewPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageViewPoster.setImageResource(movieID[pos]);

                    Toast toast = new Toast(getApplicationContext());
                    View toastView = View.inflate(getApplicationContext(),R.layout.toast, null);
                    TextView toastTextView = toastView.findViewById(R.id.toastTextView);
                    toastTextView.setText(movieName[pos]);
                    toast.setView(toastView);
                    toast.show();
                    return false;
                }
            });
            return imageView;
        }
    }
}
