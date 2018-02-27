package com.bignerdranch.android.dumbbell.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bignerdranch.android.dumbbell.R;
import com.bignerdranch.android.dumbbell.Settings;

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

    public void completeWorkout(View v) {
        updateWeek();
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

    public void updateWeek() {
        Settings settings = new Settings(this);
        int currentWeek = settings.getWeek();
        settings.setWeek(currentWeek + 1);
    }

}
