package com.bignerdranch.android.dumbbell;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bignerdranch.android.dumbbell.Activity.BaseActivity;
import com.bignerdranch.android.dumbbell.Activity.MainActivity;

public class FirstRunCheck extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mSettings = getSharedPreferences("prefs",0);
        boolean firstRun = mSettings.getBoolean("firstRun",false);
        if(!firstRun) {
            SharedPreferences.Editor mEditor = mSettings.edit();
            mEditor.putBoolean("firstRun",true);
            mEditor.apply();
            Intent i = new Intent(FirstRunCheck.this,OnBoardingActivity.class);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(FirstRunCheck.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }

}