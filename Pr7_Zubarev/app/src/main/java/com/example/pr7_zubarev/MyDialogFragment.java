package com.example.pr7_zubarev;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Сообщение");
        builder.setMessage("Кастомный диалог");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setNeutralButton("OK",null);
        return builder.create();
    }
}