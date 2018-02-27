package com.bignerdranch.android.dumbbell;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class WeightDialogFragment extends DialogFragment {
    private int mTextViewId;
    private String mLiftName;
    private String mStartingWeight;
    WeightDialogListener mListener;

    public interface WeightDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String liftName, String text, int textViewId);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    public void setArguments(String startingWeight, String liftName, int textViewId) {
        mLiftName = liftName;
        mStartingWeight = startingWeight;
        mTextViewId = textViewId;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof Activity) {
                mListener = (WeightDialogListener) context;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement WeightDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_weight_dialog, null);
        final TextView setStartingWeight = view.findViewById(R.id.setStartingWeight);
        final EditText weightText = view.findViewById(R.id.weight);
        setStartingWeight.setText(mLiftName + ": ");
        weightText.setText(mStartingWeight);
        weightText.setSelection(mStartingWeight.length());

        builder.setView(view)
            .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int id) {
                 String weightTextValue = weightText.getText().toString();
                if (mListener != null) {
                    mListener.onDialogPositiveClick(WeightDialogFragment.this, mLiftName, weightTextValue, mTextViewId);
                }
            }
        })
            .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                                     public void onClick(DialogInterface dialog, int id) {
                if (mListener != null) {
                    mListener.onDialogNegativeClick(WeightDialogFragment.this);
                }
            }
        });
        Dialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }
}
