package com.example.myvideoplayertest;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.hardware.Camera;
import android.icu.text.UnicodeSetSpanner;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {

    private Button btRecord, btRecordStop;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private Camera camera;
    private MediaRecorder mediaRecorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRecord = findViewById(R.id.btRecord);
        btRecordStop = findViewById(R.id.btRecordStop);
        surfaceView = findViewById(R.id.surfaceView);

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        TedPermission.with(this).setPermissionListener(permissionListener).setRationaleMessage("녹화 권한 허용").setDeniedMessage("녹화 권한 거부").setPermissions(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO).check();

        btRecord.setOnClickListener(this);
        btRecordStop.setOnClickListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("MainActivity", "surfaceCreated");

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(surfaceHolder.getSurface() == null) {
            return;
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "권한 취소", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btRecord :

                btRecord.setEnabled(false);
                btRecordStop.setEnabled(true);

                camera = Camera.open();
                camera.setDisplayOrientation(90);

                if(camera == null) {
                    try {
                        camera.setPreviewDisplay(surfaceHolder);
                        camera.startPreview();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            Toast.makeText(MainActivity.this, "녹화 시작", Toast.LENGTH_LONG).show();

                            mediaRecorder = new MediaRecorder();

                            camera.unlock();

                            mediaRecorder.setCamera(camera);
                            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                            mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_480P));
                            mediaRecorder.setOrientationHint(90);
                            mediaRecorder.setOutputFile("/sdcard/test.mp4");
                            mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                            mediaRecorder.prepare();
                            mediaRecorder.start();

                        } catch (IOException e) {
                            Toast.makeText(MainActivity.this, "녹화 오류", Toast.LENGTH_LONG).show();
                            mediaRecorder.release();
                            camera.release();
                        }
                    }
                });
                break;
            case R.id.btRecordStop  :

                btRecord.setEnabled(true);
                btRecordStop.setEnabled(false);

                mediaRecorder.stop();
                mediaRecorder.release();

                camera.lock();

                if(camera != null) {

                    camera.stopPreview();
                    camera.release();
                    camera = null;

                }

                break;
        }
    }
}
