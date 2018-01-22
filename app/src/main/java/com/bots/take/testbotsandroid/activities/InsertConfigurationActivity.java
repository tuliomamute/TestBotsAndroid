package com.bots.take.testbotsandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.bots.take.testbotsandroid.R;
import com.bots.take.testbotsandroid.models.ChatConfiguration;
import com.bots.take.testbotsandroid.utils.InputValidations;

public class InsertConfigurationActivity extends AppCompatActivity {
    private EditText UserIdentifier;
    private EditText UserPassWord;
    private EditText UserName;
    private EditText UserEmail;
    private EditText BotIdentifier;
    private Spinner AuthType;
    private EditText BotAlias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_configuration);

        this.UserIdentifier = findViewById(R.id.UserIdentifier);
        this.UserPassWord = findViewById(R.id.UserPassword);
        this.UserName = findViewById(R.id.UserName);
        this.UserEmail = findViewById(R.id.UserEmail);
        this.BotIdentifier = findViewById(R.id.BotIdentifier);
        this.AuthType = findViewById(R.id.AuthType);
        this.BotAlias = findViewById(R.id.BotAlias);
    }

    public void saveConfigurations(View v) {

        InputValidations validations = new InputValidations(this);

        ChatConfiguration configuration = new ChatConfiguration();
        configuration.UserIdentifier = this.UserIdentifier.getText().toString();
        configuration.UserPassWord = this.UserPassWord.getText().toString();
        configuration.UserName = this.UserName.getText().toString();
        configuration.UserEmail = this.UserEmail.getText().toString();
        configuration.BotIdentifier = this.BotIdentifier.getText().toString();
        configuration.AuthType = this.AuthType.getSelectedItem().toString();
        configuration.BotAlias = this.BotAlias.getText().toString();

        if (validations.ValidateUserInput(configuration)) {
            configuration.save();

            Intent intent = new Intent(this, ListActivity.class);

            startActivity(intent);
        }
    }
}

