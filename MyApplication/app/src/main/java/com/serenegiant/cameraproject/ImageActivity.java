package com.serenegiant.cameraproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
/*
        android.widget.ImageView image_gallery = (android.widget.ImageView)findViewById(R.id.galleryimages);

        String fileName = null;
        File[] listFiles = (new File(Environment.getExternalStorageDirectory()+"/CameraProject/").listFiles());

        if(listFiles[0].getName().endsWith(".jpg") || listFiles[0].getName().endsWith(".bmp"))
            fileName = listFiles[0].getName();

        File file = new File(Environment.getExternalStorageDirectory()+"/CameraProject/"+fileName);
        System.out.println("filename : " + file);
        int imageResource = getResources().getIdentifier(fileName,"drawable", "com.serenegiant.cameraproject");
        image_gallery.setImageResource(imageResource);*/
    }
}

