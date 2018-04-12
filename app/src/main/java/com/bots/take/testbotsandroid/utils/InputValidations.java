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

        AlertDialog.Builder alertBuilder = Messages.CreateDialog(context);

        if (TextUtils.isEmpty(configuration.BotAppKey)) {
            alertBuilder.setMessage("Chave de Acesso do BLiP Chat 2.0 não preenchido");
            alertBuilder.show();
            return false;
        }

        if (configuration.AuthType.toUpperCase() == "DEV") {
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

    public boolean ValidateAuthenticationInput(String userEmail, String userPassword) {
        AlertDialog.Builder alertBuilder = Messages.CreateDialog(context);

        if (TextUtils.isEmpty(userEmail)) {
            alertBuilder.setMessage("Email do Usuário não preenchido");
            alertBuilder.show();
            return false;
        }

        if (TextUtils.isEmpty(userPassword)) {
            alertBuilder.setMessage("Senha do Usuário não preenchido");
            alertBuilder.show();
            return false;
        }
        return true;
    }

}
