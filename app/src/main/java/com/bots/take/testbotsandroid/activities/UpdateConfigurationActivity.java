package com.bots.take.testbotsandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.bots.take.testbotsandroid.R;
import com.bots.take.testbotsandroid.models.ChatConfiguration;
import com.bots.take.testbotsandroid.utils.InputValidations;

public class UpdateConfigurationActivity extends AppCompatActivity {
    private ChatConfiguration configuration;

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
        setContentView(R.layout.activity_update_configuration);

        this.UserIdentifier = findViewById(R.id.UserIdentifier);
        this.UserPassWord = findViewById(R.id.UserPassword);
        this.UserName = findViewById(R.id.UserName);
        this.UserEmail = findViewById(R.id.UserEmail);
        this.BotIdentifier = findViewById(R.id.BotIdentifier);
        this.AuthType = findViewById(R.id.AuthType);
        this.BotAlias = findViewById(R.id.BotAlias);

        Intent intent = getIntent();
        long configurationId = intent.getLongExtra("configurationId", 1);

        configuration = ChatConfiguration.findById(ChatConfiguration.class, configurationId);


        this.UserIdentifier.setText(configuration.UserIdentifier);
        this.UserPassWord.setText(configuration.UserPassWord);
        this.UserName.setText(configuration.UserName);
        this.UserEmail.setText(configuration.UserEmail);
        this.BotIdentifier.setText(configuration.BotIdentifier);

        switch (configuration.AuthType){
            case "DEV":
                this.AuthType.setSelection(0);
                break;
            case "GUEST":
                this.AuthType.setSelection(1);
                break;
            case "LOGIN":
                this.AuthType.setSelection(2);
                break;
        }

        this.BotAlias.setText(configuration.BotAlias);
    }

    public void updateConfigurations(View v) {
        InputValidations validations = new InputValidations(this);

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
