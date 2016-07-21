package com.parse.starter;

/**
 * Created by Andrew on 17.07.2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final String LOG_TAG = "myLogs";
    ImageView makeMeLaugh;
    ImageView makeWorldLaugh;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "MainActivity onCreate");

        makeMeLaugh = (ImageView) findViewById(R.id.make_me_laugh);
        makeWorldLaugh = (ImageView) findViewById(R.id.make_world_laugh);

        makeMeLaugh.setOnClickListener(this);
        makeWorldLaugh.setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "MainActivity onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "MainActivity onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "MainActivity onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "MainActivity onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MainActivity onDestroy");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.make_me_laugh:
                Intent mml = new Intent(getApplicationContext(), FeedActivity.class);
                startActivity(mml);


                break;

            case R.id.make_world_laugh:
                Intent mwl = new Intent(getApplicationContext(), PhotoActivity.class);
                startActivity(mwl);

                break;
        }

    }
}