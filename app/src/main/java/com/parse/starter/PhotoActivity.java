package com.parse.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener{

    private Uri fileUri;
    private ImageView imageView;
    private ImageView photoButton;
    private ImageView back;
    private ImageView chek;
    private Switch photoVideo;

    public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1777;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        photoVideo= (Switch) findViewById(R.id.switch1);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        this.imageView = (ImageView)this.findViewById(R.id.imageView);
        photoButton = (ImageView)findViewById(R.id.takePhoto);
        back = (ImageView)findViewById(R.id.getBack);
        chek = (ImageView)findViewById(R.id.chek);


        photoButton.setOnClickListener(this);
        back.setOnClickListener(this);
        chek.setOnClickListener(this);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Check that request code matches ours:
        if (requestCode == CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE)
        {
            //Get our saved file into a bitmap object:
            File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
            Bitmap bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 2000, 1400);
            imageView.setImageBitmap(bitmap);

            try {


                Log.i("AppInfo", "Image Recieved");

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);

                byte[] byteArray = stream.toByteArray();

                ParseFile fileImage = new ParseFile("image.jpg", byteArray);

                final ParseObject object = new ParseObject("Images");

                object.put("username", ParseUser.getCurrentUser().getUsername());

                object.put("image", fileImage);

                ParseACL parseACL = new ParseACL();
                parseACL.setPublicReadAccess(true);
                object.setACL(parseACL);

                object.saveInBackground(new SaveCallback() {


                    @Override
                    public void done(ParseException e) {

                        if (e == null) {

                            Toast.makeText(getApplication().getBaseContext(), "Your image has been posted!", Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(getApplication().getBaseContext(), "There was an error1", Toast.LENGTH_LONG).show();
                        }
                    }

                });
                Log.i("Info", "Save successfully");

                ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
                query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
                query.setLimit(1);
                Log.i("Info", "user found and get quered");


                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e2) {
                        if (e2 == null) {

                                try {

                                    objects.get(0).put("carma", 10);
                                    Log.i("Info", "Get Carma");
                                    objects.get(0).saveInBackground();
                                    Log.i("Info", "Save Carma");
                                } catch (Exception e3) {
                                    e3.printStackTrace();

                                    Toast.makeText(getApplication().getBaseContext(), "There was an error with Carma", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                e2.printStackTrace();
                            }

                    }
                });






            } catch (Exception e) {
                e.printStackTrace();

                Toast.makeText(getApplication().getBaseContext(), "There was an error2", Toast.LENGTH_LONG).show();
            }







        }


    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight)
    { // BEST QUALITY MATCH

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight)
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth)
        {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.takePhoto:
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);
                photoButton.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
                //chek.setVisibility(View.VISIBLE);
                break;

            case R.id.chek:
                Intent mwl = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mwl);
                chek.setVisibility(View.INVISIBLE);
                break;

            case R.id.getBack:
                Intent mw2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mw2);
                chek.setVisibility(View.INVISIBLE);
                break;


        }
    }




}