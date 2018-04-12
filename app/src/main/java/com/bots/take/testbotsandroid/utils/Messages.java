package com.bots.take.testbotsandroid.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public final class Messages {
    public static AlertDialog.Builder CreateDialog(Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);

        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return alertBuilder;
    }

    public static void ShowToast(String text, Context context) {

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();

    }
}
