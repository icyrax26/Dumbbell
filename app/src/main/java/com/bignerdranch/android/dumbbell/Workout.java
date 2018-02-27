package com.bignerdranch.android.dumbbell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Workout {
    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;

    private int mMaxLift;
    private Lift mLift;
    private List<WorkoutSet> mSets;
    private LiftCalculator mCalc;

    public Workout(Lift lift, int maxLift, LiftCalculator calc) {
        mLift = lift;
        mMaxLift = maxLift;
        mCalc = calc;
        mSets = new ArrayList<>();
    }

    public void addWarmup(int offset) {
        addWarmup(offset, mMaxLift);
    }

    public void addWarmup(int offset, int warmupToWeight) {
        addWarmup(offset, warmupToWeight, 5);
    }

    public void addWarmup(int offset, int warmupToWeight, int reps) {
        int warmup = mCalc.getWarmupWeight(warmupToWeight, offset);
        addSet(warmup, reps);
    }

    public void addMaxLift() {
        addMaxLift(5);
    }
    public void addMaxLift(int reps) {
        addSet(mMaxLift, reps);
    }

    public void addSet(int weight, int reps) {
        mSets.add(new WorkoutSet(weight, reps, weight == mMaxLift));
    }

    public List<WorkoutSet> getSets() {
        return Collections.unmodifiableList(mSets);
    }

    public String getLiftName() {
        return mLift.toString();
    }
}
