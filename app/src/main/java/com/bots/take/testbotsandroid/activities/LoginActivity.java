package com.bots.take.testbotsandroid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bots.take.testbotsandroid.R;
import com.bots.take.testbotsandroid.interfaces.IMessagingHubService;
import com.bots.take.testbotsandroid.models.UserInformation;
import com.bots.take.testbotsandroid.utils.InputValidations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginActivity extends AppCompatActivity implements Callback<UserInformation> {

    private EditText UserEmail;
    private EditText UserPassWord;
    private String EncodedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.UserEmail = findViewById(R.id.UserEmail);
        this.UserPassWord = findViewById(R.id.UserPassword);
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
        switch (response.code()) {
            case 200:
                ShowToast("Login feito com sucesso!");
                break;
            case 401:
                ShowToast("Usuário e/ou Senha inválidos!");
                break;
        }
    }

    @Override
    public void onFailure(Call<UserInformation> call, Throwable t) {
        ShowToast("Ocorreu um erro ao autenticar. Tente novamente!");
    }

    public void ShowToast(String text) {

        Toast.makeText(LoginActivity.this, text, Toast.LENGTH_LONG).show();

    }
}
