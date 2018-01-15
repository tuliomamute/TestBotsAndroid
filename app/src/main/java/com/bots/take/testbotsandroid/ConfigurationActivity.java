package com.bots.take.testbotsandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.VolumeShaper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import net.take.blipchat.AuthType;
import net.take.blipchat.AuthType.*;
import net.take.blipchat.BlipClient;
import net.take.blipchat.BlipOptions;

public class ConfigurationActivity extends AppCompatActivity {
    private EditText UserIdentifier;
    private EditText UserPassWord;
    private EditText UserName;
    private EditText UserEmail;
    private EditText BotIdentifier;
    private Spinner AuthType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        this.UserIdentifier = findViewById(R.id.UserIdentifier);
        this.UserPassWord = findViewById(R.id.UserPassword);
        this.UserName = findViewById(R.id.UserName);
        this.UserEmail = findViewById(R.id.UserEmail);
        this.BotIdentifier = findViewById(R.id.BotIdentifier);
        this.AuthType = findViewById(R.id.AuthType);

    }

    public void saveConfigurations(View v) {

        ChatConfiguration configuration = new ChatConfiguration();
        configuration.UserIdentifier = this.UserIdentifier.getText().toString();
        configuration.UserPassWord = this.UserPassWord.getText().toString();
        configuration.UserName = this.UserName.getText().toString();
        configuration.UserEmail = this.UserEmail.getText().toString();
        configuration.BotIdentifier = this.BotIdentifier.getText().toString();
        configuration.AuthType = net.take.blipchat.AuthType.valueOf(this.AuthType.getSelectedItem().toString());

        if (ValidateUserInput(configuration)) {

            BlipOptions blipOptions = new BlipOptions();
            blipOptions.setAuthType(configuration.AuthType);
            blipOptions.setUserIdentifier(configuration.UserIdentifier);
            blipOptions.setUserPassword(configuration.UserPassWord);
            blipOptions.setUserName(configuration.UserName);
            blipOptions.setUserEmail(configuration.UserEmail);
            BlipClient.openBlipThread(ConfigurationActivity.this, configuration.BotIdentifier, blipOptions);
        }
    }

    private boolean ValidateUserInput(ChatConfiguration configuration) {

        AlertDialog.Builder alertBuilder = CreateDialog();

        if (TextUtils.isEmpty(configuration.BotIdentifier)) {
            alertBuilder.setMessage("Identificador do Bot não preenchido");
            alertBuilder.show();
            return false;
        }

        if (configuration.AuthType == net.take.blipchat.AuthType.DEV) {
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
    private AlertDialog.Builder CreateDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ConfigurationActivity.this);
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

