package com.bots.take.testbotsandroid.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.bots.take.testbotsandroid.models.ChatConfiguration;

public class InputValidations {
    private Context context;

    public InputValidations(Context context) {
        this.context = context;
    }

    public boolean ValidateUserInput(ChatConfiguration configuration) {

        AlertDialog.Builder alertBuilder = CreateDialog();

        if (TextUtils.isEmpty(configuration.BotIdentifier)) {
            alertBuilder.setMessage("Identificador do Bot não preenchido");
            alertBuilder.show();
            return false;
        }

        if (configuration.AuthType == "DEV") {
            if (TextUtils.isEmpty(configuration.UserIdentifier)) {
                alertBuilder.setMessage("Identificador do Usuário não preenchido");
                alertBuilder.show();
                return false;
            }
            if (TextUtils.isEmpty(configuration.UserPassWord)) {
                alertBuilder.setMessage("Senha do Usuário não preenchido");
                alertBuilder.show();
                return false;
            }
            if (TextUtils.isEmpty(configuration.UserName)) {
                alertBuilder.setMessage("Nome do Usuário não preenchido");
                alertBuilder.show();
                return false;
            }
        }
        return true;
    }

    private AlertDialog.Builder CreateDialog() {
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

}
