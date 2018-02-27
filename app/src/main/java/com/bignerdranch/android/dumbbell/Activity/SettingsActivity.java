package com.bignerdranch.android.dumbbell.Activity;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.android.dumbbell.R;
import com.bignerdranch.android.dumbbell.Settings;
import com.bignerdranch.android.dumbbell.WeightDialogFragment;

public class SettingsActivity extends BaseActivity implements WeightDialogFragment.WeightDialogListener {
    private Settings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSettings = new Settings(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView plateText = findViewById(R.id.plate_value);
        TextView matchPrText = findViewById(R.id.match_pr_week_value);
        TextView squatText = findViewById(R.id.squat_value);
        TextView benchText = findViewById(R.id.bench_value);
        TextView rowText = findViewById(R.id.row_value);
        TextView pressText = findViewById(R.id.press_value);
        TextView deadliftText = findViewById(R.id.deadlift_value);

        plateText.setText(Float.toString(mSettings.getSmallestPlate()));
        matchPrText.setText(Integer.toString(mSettings.getPrMatchWeek()));
        squatText.setText(Integer.toString(mSettings.getStartingSquat()));
        benchText.setText(Integer.toString(mSettings.getStartingBench()));
        rowText.setText(Integer.toString(mSettings.getStartingRow()));
        pressText.setText(Integer.toString(mSettings.getStartingPress()));
        deadliftText.setText(Integer.toString(mSettings.getStartingDeadlift()));
    }

    public void setStartingLiftClick(View v) {
        FragmentManager mFragmentManager = getFragmentManager();
        WeightDialogFragment mFragment = new WeightDialogFragment();
        FrameLayout mFrame = (FrameLayout)v;
        RelativeLayout mRelative = (RelativeLayout)mFrame.getChildAt(0);

        String liftName = ((TextView)mRelative.getChildAt(0)).getText().toString();
        String currentWeight = ((TextView)mRelative.getChildAt(1)).getText().toString();
        int textViewId = mRelative.getChildAt(1).getId();
        mFragment.setArguments(currentWeight, liftName, textViewId);

        mFragment.show(mFragmentManager, liftName.toLowerCase());
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String liftName, String result, int textViewId) {
        switch (liftName.toLowerCase()) {
            case "squat":
                mSettings.setStartingSquat(Integer.parseInt(result));
                break;
            case "bench":
                mSettings.setStartingBench(Integer.parseInt(result));
                break;
            case "row":
                mSettings.setStartingRow(Integer.parseInt(result));
                break;
            case "press":
                mSettings.setStartingPress(Integer.parseInt(result));
                break;
            case "deadlift":
                mSettings.setStartingDeadlift(Integer.parseInt(result));
                break;
            case "plate":
                mSettings.setSmallestPlate(Float.parseFloat(result));
                break;
            case "match_pr_week":
                mSettings.setSmallestPlate(Integer.parseInt(result));
                break;
        }

        TextView settingText = findViewById(textViewId);
        settingText.setText(result);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }
}
