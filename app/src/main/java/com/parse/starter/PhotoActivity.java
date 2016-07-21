package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView takePhoto;
    ImageView getBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        takePhoto = (ImageView) findViewById(R.id.takePhoto);
        getBack = (ImageView)findViewById(R.id.getBack);

        takePhoto.setOnClickListener(this);
        getBack.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {



    }
}
