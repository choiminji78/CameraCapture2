package com.camera.hslee.mycameraproject;


import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        findViewById(R.id.captureBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    private void startCamera(){
            mCamera = getCameraInstance();
            mPreview = new CameraPreview(getApplicationContext(), mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
    }

    private void resetCamera(){
        if(mPreview!=null && mCamera!=null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
            ((FrameLayout) findViewById(R.id.camera_preview)).removeView(mPreview);
            mPreview = null;
        }
        startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        if(mCamera!=null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
            ((FrameLayout) findViewById(R.id.camera_preview)).removeView(mPreview);
            mPreview = null;
        }
    }


    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            new SaveImageTask().execute(data);
            resetCamera();
        }
    };
    private class SaveImageTask extends AsyncTask<byte[],Void,Void>{
        @Override
        protected Void doInBackground(byte[]... data) {
            FileOutputStream outStream = null;
            // Write to SD Card
            try {
                File dir = new File( Environment.getExternalStorageDirectory().getAbsolutePath() , "MyCameraApp");
                dir.mkdirs();
                String fileName = String.format("%d.jpg", System.currentTimeMillis());
                File outFile = new File(dir, fileName);
                outStream = new FileOutputStream(outFile);
                outStream.write(data[0]);
                outStream.flush();
                outStream.close();
                refreshGallery(outFile);
                Log.d("MyCameraApp", "onPictureTaken - wrote bytes: " + data.length + " to " + outFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
            return null;
        }
    }
    public void refreshGallery(File file){
        Intent mediaScanIntent = new Intent( Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(file));
        sendBroadcast(mediaScanIntent);
    }
    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}
