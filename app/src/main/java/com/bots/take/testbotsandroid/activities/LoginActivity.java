package com.bots.take.testbotsandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bots.take.testbotsandroid.R;
import com.bots.take.testbotsandroid.interfaces.IMessagingHubService;
import com.bots.take.testbotsandroid.models.UserInformation;
import com.bots.take.testbotsandroid.utils.InputValidations;
import com.bots.take.testbotsandroid.utils.Messages;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginActivity extends AppCompatActivity implements Callback<UserInformation> {

    private EditText UserEmail;
    private EditText UserPassWord;
    private String EncodedString;
    private ProgressBar LoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.UserEmail = findViewById(R.id.UserEmail);
        this.UserPassWord = findViewById(R.id.UserPassword);
        this.LoginProgress = findViewById(R.id.LoginProgress);
    }

    public void authenticateUser(View v) {
        String userEmail;
        String userPassWord;
        String preparedString;
        String baseUrl;

        InputValidations validations = new InputValidations(this);

        userEmail = this.UserEmail.getText().toString();
        userPassWord = this.UserPassWord.getText().toString();

        preparedString = userEmail + ":" + userPassWord;

        if (!validations.ValidateAuthenticationInput(userEmail, userPassWord)) {
            return;
        }

        baseUrl = getResources().getString(R.string.MessagingHubBaseUrl);

        LoginProgress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        EncodedString = "Basic " + Base64.encodeToString(preparedString.getBytes(), 0).trim();

        IMessagingHubService service = retrofit.create(IMessagingHubService.class);
        Call<UserInformation> repos = service.AuthenticateUser(EncodedString);

        repos.enqueue(this);
    }

    @Override
    public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {

        LoginProgress.setVisibility(View.GONE);

        switch (response.code()) {
            case HttpURLConnection.HTTP_OK:
                Messages.ShowToast("Login feito com sucesso!", LoginActivity.this);

                Intent intent = new Intent(this, BotsOnAccountActivity.class);
                intent.putExtra("AuthenticationToken", EncodedString);

                startActivity(intent);

                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                Messages.ShowToast("Usuário e/ou Senha inválidos!", LoginActivity.this);
                break;
        }
    }

    @Override
    public void onFailure(Call<UserInformation> call, Throwable t) {
        Messages.ShowToast("Ocorreu um erro ao autenticar. Tente novamente!", LoginActivity.this);
        LoginProgress.setVisibility(View.GONE);

    }

}
