package com.serenegiant.cameraproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class GalleryActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ImageView imgView = (ImageView)findViewById(R.id.imgView);

        Intent intent = getIntent();
        String filename = intent.getStringExtra("filename");

        if(filename != null) {  //찍은 이미지가 있을 때 파일 보여주기
            String imgpath = "sdcard/Android/data/" + getPackageName() + "/CameraProject/" + filename;
            Bitmap bm = BitmapFactory.decodeFile(imgpath);
            imgView.setImageBitmap(bm);
        }
        else {  //찍은 이미지가 없을 때 갤러리로 넘기기
            Uri uri = Uri.parse("content://media/external/images/media/");
            Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
            intent2.setAction(Intent.ACTION_GET_CONTENT);
            intent2.setType("image/*");
            startActivity(intent2);
        }

    }
}
