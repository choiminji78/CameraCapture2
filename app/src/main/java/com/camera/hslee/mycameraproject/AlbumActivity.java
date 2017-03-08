package com.camera.hslee.mycameraproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {
    Activity act = this;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        gridView = (GridView) findViewById(R.id.albGridView);
        gridView.setAdapter(new gridAdapter());
    }

    private class gridAdapter extends BaseAdapter {
        LayoutInflater inflater;
        File dir;
        File[] childFiles;

        public gridAdapter() {
//            dir = new File(getApplicationContext().getFilesDir(), "MyCameraApp");
//            dir = new File(getApplicationContext().getExternalCacheDir(),"MyCamerApp");
            File dir = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),"MyCamerApp");
            childFiles = dir.listFiles();
            inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            if(childFiles!=null) {
                return childFiles.length;
            }else{
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            if(childFiles!=null) {
                return childFiles[position];
            }else{
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item, parent, false);
            }
            final File file = (File) getItem(position);
            if(file!=null) {
                final Drawable picture = Drawable.createFromPath(file.getAbsolutePath());
                ImageView img = (ImageView) convertView.findViewById(R.id.itemImg);
                img.setImageDrawable(picture);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(AlbumActivity.this, file.getName(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),PictureInfoActivity.class);
                        i.putExtra("picturePath",file.getAbsolutePath());
                        i.putExtra("pictureName",file.getName());
                        startActivity(i);
                    }
                });
            }
            return convertView;
        }
    }
}

