package com.blackphoenix.watchfacegallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.parentLayout);
        final ImageView myImage = (ImageView) findViewById(R.id.watchFace_View);

        String path = Environment.getExternalStorageDirectory().getPath();

        File wFaceFolder = new File(path+"/WatchFace/");
        ArrayList<String> imageList = new ArrayList<>();

        if(wFaceFolder.isDirectory() && wFaceFolder.exists()) {
            File[] watchFaceList = wFaceFolder.listFiles();
            for (File file : watchFaceList){
                if (file.getAbsolutePath().contains(".png"))
                    imageList.add(file.getAbsolutePath());
            }
        }

        if(imageList.size() >0) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imageList.get(0));
            myImage.setImageBitmap(myBitmap);
        } else {
            Toast.makeText(MainActivity.this,"No More Watch Faces to Load",Toast.LENGTH_LONG).show();
        }

        final ListIterator<String> imageListIterator = imageList.listIterator();


        layout.setOnTouchListener(new OnCustomTouchListener(MainActivity.this){
            @Override
            public void onSwipeLeft(View view) {
                //super.onSwipeLeft(view);

                if(imageListIterator.hasNext()){
                    String imagePath = imageListIterator.next();
                    Bitmap myBitmap = BitmapFactory.decodeFile(imagePath);
                    myImage.setImageBitmap(myBitmap);
                } else {
                    Toast.makeText(MainActivity.this,"No More Watch Faces to Load",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onSwipeRight(View view) {
                //super.onSwipeRight(view);
                if(imageListIterator.hasPrevious()){
                    String imagePath = imageListIterator.previous();
                    Bitmap myBitmap = BitmapFactory.decodeFile(imagePath);
                    myImage.setImageBitmap(myBitmap);
                } else {
                    Toast.makeText(MainActivity.this,"No More Watch Faces to Load",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
