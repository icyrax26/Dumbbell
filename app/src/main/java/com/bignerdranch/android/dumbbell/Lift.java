package com.bignerdranch.android.dumbbell;

import java.util.LinkedList;
import java.util.Queue;

public enum Lift {
    SQUAT("Squats"),
    BENCH("Bench Press"),
    PRESS("Overhead Press"),
    ROW("Barbell Row"),
    DEADLIFT("Deadlift");

    private final String mLiftDescription;

    Lift(String value) {
        mLiftDescription = value;
    }

    @Override
    public String toString() {
        return mLiftDescription;
    }

    public static Queue<Lift> getLiftsQueue() {
        Queue<Lift> lifts = new LinkedList<>();
        lifts.add(Lift.SQUAT);
        lifts.add(Lift.BENCH);
        lifts.add(Lift.ROW);
        lifts.add(Lift.PRESS);
        lifts.add(Lift.DEADLIFT);

        return lifts;
    }
}
