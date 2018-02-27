package com.bignerdranch.android.dumbbell.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.bignerdranch.android.dumbbell.Lift;
import com.bignerdranch.android.dumbbell.LiftCalculator;
import com.bignerdranch.android.dumbbell.R;
import com.bignerdranch.android.dumbbell.Settings;
import com.bignerdranch.android.dumbbell.Workout;
import com.bignerdranch.android.dumbbell.WorkoutRow;

public class WorkoutActivityDayOne extends BaseActivity {
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

        setContentView(R.layout.activity_workout_day1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSettings = new Settings(this);

        loadWorkouts();
    }

    public void loadWorkouts() {
        TextView weekText = findViewById(R.id.week);
        weekText.setText("Week: " + mWeek);

        LiftCalculator calc = new LiftCalculator(mSettings);

        setMondayWorkouts(mWeek, calc);
    }

    private void setMondayWorkouts(int week, LiftCalculator calc) {
        addMondayLift(Workout.FIRST, week, Lift.SQUAT, calc);
        addMondayLift(Workout.SECOND, week, Lift.BENCH, calc);
        addMondayLift(Workout.THIRD, week, Lift.ROW, calc);
    }

    private void addMondayLift(int workoutNumber, int week, Lift lift, LiftCalculator calc) {
        int maxLift = calc.getMaxWeight(week, 1, lift);
        Workout workout = new Workout(lift, maxLift, calc);
        workout.addWarmup(4);
        workout.addWarmup(3);
        workout.addWarmup(2);
        workout.addWarmup(1);
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
