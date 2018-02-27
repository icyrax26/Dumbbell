package com.bignerdranch.android.dumbbell.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.dumbbell.Lift;
import com.bignerdranch.android.dumbbell.LiftCalculator;
import com.bignerdranch.android.dumbbell.R;
import com.bignerdranch.android.dumbbell.Settings;

import java.util.HashMap;
import java.util.Queue;

public class SetupWeightActivity extends AppCompatActivity {
    private Queue<Lift> mLifts;
    private Lift mCurrentLift;
    private HashMap<Lift, Integer> mStartingWeights;
    private EditText mMaxWeightEditText;
    private EditText mMaxRepsEditText;
    private TextView mMaxLiftTextView;
    private TextView mStartingWeightText;

    public SetupWeightActivity() {
        mStartingWeights = new HashMap<>();
        mLifts = Lift.getLiftsQueue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mMaxWeightEditText = findViewById(R.id.max_lift_weight);
        mMaxRepsEditText = findViewById(R.id.max_lift_reps);
        mMaxLiftTextView = findViewById(R.id.max_lift_label);
        mStartingWeightText = findViewById(R.id.estimated_starting_weight);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateResults(mMaxWeightEditText.getText().toString(), mMaxRepsEditText.getText().toString());
            }
        };
        mMaxWeightEditText.addTextChangedListener(watcher);
        mMaxRepsEditText.addTextChangedListener(watcher);

        setupNextLift();
    }

    private void setupNextLift() {
        if (mLifts.isEmpty()) {
            confirmStartingLifts();
            return;
        }

        mCurrentLift = mLifts.remove();
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Setup Starting " + mCurrentLift.toString());
        bar.setDisplayHomeAsUpEnabled(true);

        mMaxLiftTextView.setText("Max " + mCurrentLift.toString());
        mMaxWeightEditText.setText("");
        mMaxRepsEditText.setText("5");
        mMaxWeightEditText.requestFocus();

        // Allows the first edit text to show keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        // Allows other edit texts to show keyboards (at least on Nextbit Robin phones so far)
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mMaxWeightEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void confirmStartingLifts() {
        Intent intent = new Intent(getBaseContext(), ConfirmStartingWeightsActivity.class);
        intent.putExtra("STARTING_WEIGHTS", mStartingWeights);
        startActivity(intent);
    }

    private void calculateResults(String weightString, String repString) {
        TextView oneRepMaxText = findViewById(R.id.estimated_one_rep);
        TextView fiveRepMaxText = findViewById(R.id.estimated_five_rep);
        TextView startingWeightText = findViewById(R.id.estimated_starting_weight);
        Button nextStartingWeight = findViewById(R.id.next_starting_weight);

        if (isValidInteger(weightString) == false || isValidInteger(repString) == false) {
            final String notAvailable = "N/A";
            oneRepMaxText.setText(notAvailable);
            fiveRepMaxText.setText(notAvailable);
            startingWeightText.setText(notAvailable);
            nextStartingWeight.setEnabled(false);
            return;
        }

        int weight = Integer.parseInt(weightString);
        int reps = Integer.parseInt(repString);

        Settings settings = new Settings(this);
        LiftCalculator calc = new LiftCalculator(settings);

        int oneRepMax = calc.getOneRepMax(weight, reps);
        oneRepMaxText.setText(Integer.toString(oneRepMax));

        int fiveRepMax = calc.getFiveRepMax(oneRepMax);
        fiveRepMaxText.setText(Integer.toString(fiveRepMax));

        int startingWeight = calc.getStartingWeight(fiveRepMax);
        startingWeightText.setText(Integer.toString(startingWeight));
        nextStartingWeight.setEnabled(true);
    }

    private boolean isValidInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void nextStartingWeightClick(View v) {
        int startingWeight = Integer.parseInt(mStartingWeightText.getText().toString());
        mStartingWeights.put(mCurrentLift, startingWeight);
        setupNextLift();
    }
}
