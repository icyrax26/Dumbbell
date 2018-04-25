package com.kylebruney.android.madcow.Activity;

import android.os.Bundle;

import com.kylebruney.android.madcow.R;

public class HelpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
