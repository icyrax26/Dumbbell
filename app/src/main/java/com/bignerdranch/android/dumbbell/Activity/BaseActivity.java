package com.bignerdranch.android.dumbbell.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.bignerdranch.android.dumbbell.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.settings:
                Intent settingsIntent = new Intent(BaseActivity.this, SettingsActivity.class);
                BaseActivity.this.startActivity(settingsIntent);
                return true;
            case R.id.setup:
                Intent setupIntent = new Intent(BaseActivity.this, SetupWeightActivity.class);
                BaseActivity.this.startActivity(setupIntent);
                return true;
            case R.id.help:
                Intent helpIntent = new Intent(BaseActivity.this, HelpActivity.class);
                BaseActivity.this.startActivity(helpIntent);
                return true;
            case R.id.soon:
                Intent soonIntent = new Intent(BaseActivity.this, ComingSoonActivity.class);
                BaseActivity.this.startActivity(soonIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
