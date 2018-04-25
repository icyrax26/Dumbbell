package com.kylebruney.android.madcow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IndividualLift extends LinearLayout {

    public IndividualLift(Context context, String set, boolean isMaxLift) {
        super(context);
        init(context, set, isMaxLift);
    }

    private void init(Context context, String set, boolean isMaxLift) {
        this.setGravity(Gravity.CENTER);
        setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 0.20f));

        LinearLayoutCompat.LayoutParams mParams = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView mTV = new TextView(context);
        mTV.setLayoutParams(mParams);
        mTV.setText(set);
        if (isMaxLift) {
            mTV.setBackground(getResources().getDrawable(R.drawable.max_weight_circle));
            mTV.setGravity(Gravity.CENTER);
        }
        addView(mTV);
    }
}
