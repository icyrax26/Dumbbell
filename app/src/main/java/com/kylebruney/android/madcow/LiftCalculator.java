package com.kylebruney.android.madcow;

public class LiftCalculator {
    private Settings mSettings;

    public LiftCalculator(Settings settings) {
        mSettings = settings;
    }

    public int getMaxWeight(int week, int day, Lift lift) {
        if (day != 2 && (lift == Lift.DEADLIFT || lift == Lift.PRESS)) {
            throw new IllegalArgumentException("deadlift and press must be on day 2.");
        }

        float plate = mSettings.getSmallestPlate();
        if (day == 2 && lift == Lift.SQUAT) {
            int maxWeight = getMaxWeight(week, 1, lift);
            return getWarmupWeight(maxWeight, 2);
        }

        double startingLift = 0;
        switch (lift) {
            case BENCH:
                startingLift = mSettings.getStartingBench();
                break;
            case SQUAT:
                startingLift = mSettings.getStartingSquat();
                break;
            case ROW:
                startingLift = mSettings.getStartingRow();
                break;
            case PRESS:
                startingLift = mSettings.getStartingPress();
                break;
            case DEADLIFT:
                startingLift = mSettings.getStartingDeadlift();
                break;
        }

        if (week == 1 && (day == 1 || day == 2)) {
            return (int) startingLift;
        } else {
            if (day != 3) {
                week = week - 1;
            }
            double weekPower = Math.pow(1.025, week);
            int a = Math.round((float)(startingLift * weekPower / (2.0 * plate)));
            return (int)(a * 2.0 * plate);
        }
    }

    public int getWarmupWeight(int maxWeight, int warmupOffset) {
        float plate = mSettings.getSmallestPlate();

        int a = Math.round(maxWeight * (1- mSettings.getSetInterval() * warmupOffset) / (float)(2.0 * plate));
        return (int)(a * 2.0 * plate);
    }

    public int getOneRepMax(int weight, int reps) {
        return Math.round(weight / (float) (1.0278 - (0.0278 * reps)));
    }

    public int getFiveRepMax(int oneRepMax) {
        return Math.round(oneRepMax * (float)(1.0278 - (0.0278 * 5)));
    }

    public int getStartingWeight(int fiveRepMax) {
        int matchPrWeek = mSettings.getPrMatchWeek();
        float weekPower = (float) Math.pow((1 / 1.025), matchPrWeek);
        float smallestPlate = mSettings.getSmallestPlate();
        float tempStarting = Math.round(fiveRepMax * weekPower / (2 * smallestPlate));
        float startingWeight = tempStarting * 2 * smallestPlate;
        return (int)startingWeight;
    }
}
