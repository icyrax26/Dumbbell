package com.kylebruney.android.madcow.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.kylebruney.android.madcow.Lift;
import com.kylebruney.android.madcow.LiftCalculator;
import com.kylebruney.android.madcow.R;
import com.kylebruney.android.madcow.Settings;
import com.kylebruney.android.madcow.Workout;
import com.kylebruney.android.madcow.WorkoutRow;

public class WorkoutActivityDayTwo  extends BaseActivity {
    private Settings mSettings;
    private int mWeek;

    @Override
    protected void onResume() {
        super.onResume();
        loadWorkouts();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        mWeek = b.getInt("week");

        setContentView(R.layout.activity_workout_day2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSettings = new Settings(this);

        loadWorkouts();
    }

    public void loadWorkouts() {
        TextView weekText = findViewById(R.id.week);
        weekText.setText("Week: " + mWeek);

        LiftCalculator calc = new LiftCalculator(mSettings);

        setWednesdayWorkouts(mWeek,calc);
    }

    private void setWednesdayWorkouts(int week, LiftCalculator calc) {
        addWednesdayLift(Workout.FIRST, week, Lift.SQUAT, calc);
        addWednesdayLift(Workout.SECOND, week, Lift.PRESS, calc);
        addWednesdayLift(Workout.THIRD, week, Lift.DEADLIFT, calc);
    }

    private void addWednesdayLift(int workoutNumber, int week, Lift lift, LiftCalculator calc) {
        int maxLift = calc.getMaxWeight(week, 2, lift);
        Workout workout = new Workout(lift, maxLift, calc);

        if (workoutNumber == Workout.FIRST) {
            int warmupToWeight = calc.getMaxWeight(week, 1, lift);
            workout.addWarmup(4, warmupToWeight);
            workout.addWarmup(3, warmupToWeight);
            workout.addMaxLift();
        } else {
            workout.addWarmup(3);
            workout.addWarmup(2);
            workout.addWarmup(1);
        }
        workout.addMaxLift();

        setLifts(workoutNumber, workout);
    }

    private void setLifts(int workoutNumber, Workout workout) {
        WorkoutRow row = getWorkoutRow(workoutNumber);
        row.SetLifts(this, workout);
    }

    private WorkoutRow getWorkoutRow(int workoutNumber) {
        if (workoutNumber == Workout.FIRST) {
            return (WorkoutRow) findViewById(R.id.firstWorkout);
        }
        if (workoutNumber == Workout.SECOND) {
            return (WorkoutRow) findViewById(R.id.secondWorkout);
        }
        return (WorkoutRow) findViewById(R.id.thirdWorkout);
    }
}


