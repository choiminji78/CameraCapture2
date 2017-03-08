package com.camera.hslee.mycameraproject;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PictureInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_info);
        Drawable img = Drawable.createFromPath(getIntent().getStringExtra("picturePath"));
        String name = getIntent().getStringExtra("pictureName");
        ((ImageView)findViewById(R.id.pictureInfoImgView)).setImageDrawable(img);
        ((TextView)findViewById(R.id.pictureInfoTv)).setText(name);
    }
}
