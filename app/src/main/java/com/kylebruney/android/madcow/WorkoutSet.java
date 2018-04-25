package com.kylebruney.android.madcow;

public class WorkoutSet {
    private int mWeight;
    private int mReps;
    private boolean mIsMaxLift;

    public WorkoutSet(int weight, int reps, boolean isMaxLift) {
        mWeight = weight;
        mReps = reps;
        mIsMaxLift = isMaxLift;
    }

    public int getWeight() {
        return mWeight;
    }

    public int getReps() {
        return mReps;
    }

    public boolean isMaxLift() {
        return mIsMaxLift;
    }
}
