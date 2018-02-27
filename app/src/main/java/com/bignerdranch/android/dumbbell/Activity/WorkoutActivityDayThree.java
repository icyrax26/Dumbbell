package com.bignerdranch.android.dumbbell.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.bignerdranch.android.dumbbell.Lift;
import com.bignerdranch.android.dumbbell.LiftCalculator;
import com.bignerdranch.android.dumbbell.R;
import com.bignerdranch.android.dumbbell.Settings;
import com.bignerdranch.android.dumbbell.Workout;
import com.bignerdranch.android.dumbbell.WorkoutRow;

public class WorkoutActivityDayThree extends BaseActivity {
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

            setContentView(R.layout.activity_workout_day3);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mSettings = new Settings(this);

            loadWorkouts();
        }

    public void loadWorkouts() {
        TextView weekText = findViewById(R.id.week);
        weekText.setText("Week: " + mWeek);

        LiftCalculator calc = new LiftCalculator(mSettings);

        setFridayWorkouts(mWeek, calc);
    }

    private void setFridayWorkouts(int week, LiftCalculator calc) {
        addFridayLift(Workout.FIRST, week, Lift.SQUAT, calc);
        addFridayLift(Workout.SECOND, week, Lift.BENCH, calc);
        addFridayLift(Workout.THIRD, week, Lift.ROW, calc);
    }

    private void addFridayLift(int workoutNumber, int week, Lift lift, LiftCalculator calc) {
        int maxLift = calc.getMaxWeight(week, 3, lift);

        Workout workout = new Workout(lift, maxLift, calc);
        workout.addWarmup(4);
        workout.addWarmup(3);
        workout.addWarmup(2);
        workout.addWarmup(1);
        workout.addMaxLift(3);

        int warmupToWeight = calc.getMaxWeight(week, 1, lift);
        workout.addWarmup(2, warmupToWeight, 8);

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
