package com.kylebruney.android.madcow;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    final String SET_INTERVAL = "set_interval";
    final String SQUAT = "squat";
    final String BENCH = "bench";
    final String ROW = "row";
    final String PRESS = "press";
    final String DEADLIFT = "deadlift";
    final String WEEK = "week";
    final String DAY = "day";
    final String PLATE = "plate";
    final String PR_MATCH_WEEK = "pr_match_week";

    private SharedPreferences mPreferences;

    public Settings(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Settings(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public float getSetInterval() {
        return mPreferences.getFloat(SET_INTERVAL, 0.125f);
    }

    public int getStartingSquat() {
        return mPreferences.getInt(SQUAT, 60);
    }

    public void setStartingSquat(int weight) {
        saveInt(SQUAT, weight);
    }

    public int getStartingBench() {
        return mPreferences.getInt(BENCH, 40);
    }

    public void setStartingBench(int weight) {
        saveInt(BENCH, weight);
    }

    public int getStartingRow() {
        return mPreferences.getInt(ROW, 60);
    }

    public void setStartingRow(int weight) {
        saveInt(ROW, weight);
    }

    public int getStartingPress() {
        return mPreferences.getInt(PRESS, 20);
    }

    public void setStartingPress(int weight) {
        saveInt(PRESS, weight);
    }

    public int getStartingDeadlift() {
        return mPreferences.getInt(DEADLIFT, 80);
    }

    public void setStartingDeadlift(int weight) {
        saveInt(DEADLIFT, weight);
    }

    public float getSmallestPlate() {
        return mPreferences.getFloat(PLATE, 1.25f);
    }

    public void setSmallestPlate(float plate) {
        saveFloat(PLATE, plate);
    }

    public void setWeek(int week){
        saveInt(WEEK, week);
    }

    public int getWeek() {
        return mPreferences.getInt(WEEK, 1);
    }

    public void setDay(int day){
        saveInt(DAY, day);
    }

    public int getDay() {
        return mPreferences.getInt(DAY, 1);
    }

    public int getPrMatchWeek() {
        return mPreferences.getInt(PR_MATCH_WEEK, 4);
    }

    public void setPrMatchWeek(int prMatchWeek) {
        saveInt(PR_MATCH_WEEK, prMatchWeek);
    }

    private void saveInt(String name, int value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    private void saveFloat(String name, float value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putFloat(name, value);
        editor.commit();
    }
}
