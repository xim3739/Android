package com.example.mychapturetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 672;

    private ImageView imageView;
    private Button btCapture;

    private String imageFilePath;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TedPermission.with(getApplicationContext()).setPermissionListener(new PermissionListener() {

            @Override
            public void onPermissionGranted() {

                Toast.makeText(getApplicationContext(), "Accept", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                Toast.makeText(getApplicationContext(), "Denied", Toast.LENGTH_LONG).show();
            }

        }).setRationaleMessage("카메라 권한 필요").setDeniedMessage("거부하셨습니다.").setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA).check();

        btCapture = findViewById(R.id.btCapture);
        imageView = findViewById(R.id.imageView);

        btCapture.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCapture :

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(intent.resolveActivity(getPackageManager()) != null) {

                    File photoFile = null;

                    try {

                        photoFile = createImaFile();

                    } catch (IOException e) {

                        Toast.makeText(getApplicationContext(), "Error is File", Toast.LENGTH_LONG).show();

                    }

                    if(photoFile != null) {

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                            photoUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName(), photoFile);

                        } else {

                            photoUri = Uri.fromFile(photoFile);

                        }

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

                    } else {

                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                    }


                }

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            int exifOrientation;
            int exifDegree;

            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            ExifInterface exifInterface = null;

            try {

                exifInterface = new ExifInterface(imageFilePath);

            } catch (IOException e) {

                e.printStackTrace();

            }

            if(exifInterface != null) {

                exifOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegress(exifOrientation);

            } else {

                exifDegree = 0;

            }

            imageView.setImageBitmap(rotate(bitmap, exifDegree));

        }

    }

    private Bitmap rotate(Bitmap bitmap, int exifDegree) {

        Matrix matrix = new Matrix();
        matrix.postRotate(exifDegree);

        return Bitmap.createBitmap(bitmap, 0 , 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

    }

    private int exifOrientationToDegress(int exifOrientation) {

        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {

            return 90;

        } else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {

            return 180;

        } else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {

            return 270;

        }

        return 0;
    }

    private File createImaFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
        String imageFileName = "test_"+timeStamp+"_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        imageFilePath = image.getAbsolutePath();

        return image;
    }
}
