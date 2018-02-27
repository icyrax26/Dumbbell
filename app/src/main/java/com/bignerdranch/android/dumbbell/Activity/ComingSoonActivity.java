package com.bignerdranch.android.dumbbell.Activity;

import android.os.Bundle;

import com.bignerdranch.android.dumbbell.R;

public class ComingSoonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
