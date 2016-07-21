package com.parse.starter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentToolbar extends Fragment {

    final String LOG_TAG = "myLogs";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(LOG_TAG, "FragmentToolbar onAttach");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "FragmentToolbar onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "FragmentToolbar onCreateView");
        return inflater.inflate(R.layout.fragment_toolbar, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "FragmentToolbar onActivityCreated");
    }

    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "FragmentToolbar onStart");
    }

    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "FragmentToolbar onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "FragmentToolbar onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "FragmentToolbar onStop");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.d(LOG_TAG, "FragmentToolbar onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "FragmentToolbar onDestroy");
    }

    public void onDetach() {
        super.onDetach();
        Log.d(LOG_TAG, "FragmentToolbar onDetach");
    }
}