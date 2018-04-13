package com.bots.take.testbotsandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.bots.take.testbotsandroid.R;
import com.bots.take.testbotsandroid.interfaces.IMessagingHubService;
import com.bots.take.testbotsandroid.models.BotAdvancedInformation;
import com.bots.take.testbotsandroid.models.BotBasicInformation;
import com.bots.take.testbotsandroid.models.ChatConfiguration;
import com.bots.take.testbotsandroid.models.UserInformation;
import com.bots.take.testbotsandroid.utils.InputValidations;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class InsertConfigurationActivity extends AppCompatActivity implements Callback<BotAdvancedInformation> {
    private EditText UserIdentifier;
    private EditText UserPassWord;
    private EditText UserName;
    private EditText UserEmail;
    private EditText BotAppKey;
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
        this.BotAppKey = findViewById(R.id.BotAppKey);
        this.AuthType = findViewById(R.id.AuthType);
        this.BotAlias = findViewById(R.id.BotAlias);

        Intent intent = getIntent();
        String authenticationToken = intent.getStringExtra("AuthenticationToken");
        String botShortName = intent.getStringExtra("BotIdentifier");
        String baseUrl;

        baseUrl = getResources().getString(R.string.MessagingHubBaseUrl);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        IMessagingHubService service = retrofit.create(IMessagingHubService.class);
        Call<BotAdvancedInformation> repos = service.GetDetailedBot(authenticationToken, botShortName);

        repos.enqueue(this);
    }

    public void saveConfigurations(View v) {

        InputValidations validations = new InputValidations(this);

        ChatConfiguration configuration = new ChatConfiguration();
        configuration.UserIdentifier = this.UserIdentifier.getText().toString();
        configuration.UserPassWord = this.UserPassWord.getText().toString();
        configuration.UserName = this.UserName.getText().toString();
        configuration.UserEmail = this.UserEmail.getText().toString();
        configuration.BotAppKey = this.BotAppKey.getText().toString();
        configuration.AuthType = this.AuthType.getSelectedItem().toString();
        configuration.BotAlias = this.BotAlias.getText().toString();

        if (validations.ValidateUserInput(configuration)) {
            configuration.save();

            Intent intent = new Intent(this, ListActivity.class);

            startActivity(intent);
        }
    }

    @Override
    public void onResponse(Call<BotAdvancedInformation> call, Response<BotAdvancedInformation> response) {
        switch (response.code()) {
            case HttpURLConnection.HTTP_OK:

                BotAdvancedInformation botAdvancedInformation = response.body();
                this.BotAlias.setText(botAdvancedInformation.Name);
                this.BotAppKey.setText(GenerateBlipChatAppKey(botAdvancedInformation));
                break;

        }
    }

    @Override
    public void onFailure(Call<BotAdvancedInformation> call, Throwable t) {

    }

    public String GenerateBlipChatAppKey(BotAdvancedInformation botAdvancedInformation) {
        String temporaryAppKey = botAdvancedInformation.ShortName + ":" + botAdvancedInformation.AccessKey;
        return Base64.encodeToString(temporaryAppKey.getBytes(), 0).trim();
    }
}

