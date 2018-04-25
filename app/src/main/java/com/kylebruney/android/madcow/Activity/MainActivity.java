package com.kylebruney.android.madcow.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.kylebruney.android.madcow.R;
import com.kylebruney.android.madcow.Settings;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void nextWorkout(View v) {
        Settings settings = new Settings(this);

        Intent workoutIntent = new Intent(MainActivity.this, WorkoutActivityDayOne.class);
        workoutIntent.putExtra("day", settings.getDay());
        workoutIntent.putExtra("week", settings.getWeek());
        MainActivity.this.startActivity(workoutIntent);
    }

    public void day2Workout(View v) {
        Settings settings = new Settings(this);

        Intent workoutIntent = new Intent(MainActivity.this, WorkoutActivityDayTwo.class);
        workoutIntent.putExtra("day", settings.getDay());
        workoutIntent.putExtra("week", settings.getWeek());
        MainActivity.this.startActivity(workoutIntent);
    }

    public void day3Workout(View v) {
        Settings settings = new Settings(this);

        Intent workoutIntent = new Intent(MainActivity.this, WorkoutActivityDayThree.class);
        workoutIntent.putExtra("day", settings.getDay());
        workoutIntent.putExtra("week", settings.getWeek());
        MainActivity.this.startActivity(workoutIntent);
    }

    public void completeWorkout(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage("Advance to next week?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                updateWeek();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

//    public void completeWorkout(View v) {
//        updateWeek();
//      }

    public void updateWeek() {
        Settings settings = new Settings(this);
        int currentWeek = settings.getWeek();
        settings.setWeek(currentWeek + 1);
    }

}
