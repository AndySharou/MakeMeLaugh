/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class Registration extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {




  EditText usernameField;
  EditText passwordField;
  TextView changeSignUpModeTextView;
  Button signUpButton;
  ImageView logo;
  RelativeLayout relativeLayout;

  Boolean signUpModeActive;



  @Override
  public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {


      if (keyCode == keyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

          signUpOrLogIn(view);
      }
      return false;
  }

  @Override
  public void onClick(View view) {

        if (view.getId() == R.id.changeSignUpMode) {

           if (signUpModeActive == true) {

               signUpModeActive = false;
               changeSignUpModeTextView.setText("Sign Up");
               signUpButton.setText("Log In");
           } else {

               signUpModeActive = true;
               changeSignUpModeTextView.setText("Log In");
               signUpButton.setText("Sign Up");
           }
        } else if (view.getId() == R.id.logo || view.getId() == R.id.relativeLayout){

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

  }
  public void signUpOrLogIn(View view) {

      if (signUpModeActive == true) {

          ParseUser user = new ParseUser();
          user.setUsername(String.valueOf(usernameField.getText()));
          user.setPassword(String.valueOf(passwordField.getText()));
          user.put("Carma", 0);

          user.signUpInBackground(new SignUpCallback() {
              @Override
              public void done(ParseException e) {
                  if (e == null) {

                      Log.i("AppInfo", "Signup Succesful");

                      showMainActivity();

                  } else {

                      Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                  }
              }
          });
      } else {

          ParseUser.logInInBackground(String.valueOf(usernameField.getText()), String.valueOf(passwordField.getText()), new LogInCallback() {
              @Override
              public void done(ParseUser user, ParseException e) {

                  if (user != null) {

                      Log.i("AppInfo", "Login Succesful");

                      showMainActivity();
                  } else {

                      Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                  }

              }
          });
      }
    }

  public void showMainActivity() {
      Intent i = new Intent(getApplicationContext(), MainActivity.class);
      startActivity(i);

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);
    Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);



      if (ParseUser.getCurrentUser() != null) {
          showMainActivity();

      }

      signUpModeActive = true;

      usernameField = (EditText) findViewById(R.id.username);
      passwordField = (EditText) findViewById(R.id.password);
      changeSignUpModeTextView = (TextView) findViewById(R.id.changeSignUpMode);
      signUpButton = (Button)findViewById(R.id.signUpButton);
      logo = (ImageView) findViewById(R.id.logo);
      relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
      changeSignUpModeTextView.setOnClickListener(this);
      logo.setOnClickListener(this);
      relativeLayout.setOnClickListener(this);

      usernameField.setOnKeyListener(this);
      passwordField.setOnKeyListener(this);






    ParseAnalytics.trackAppOpenedInBackground(getIntent());



  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }



}
