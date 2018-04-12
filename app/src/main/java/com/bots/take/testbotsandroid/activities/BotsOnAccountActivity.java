package com.bots.take.testbotsandroid.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bots.take.testbotsandroid.R;
import com.bots.take.testbotsandroid.adapter.BotBasicInformationAdapter;
import com.bots.take.testbotsandroid.interfaces.IMessagingHubService;
import com.bots.take.testbotsandroid.models.BotAdvancedInformation;
import com.bots.take.testbotsandroid.models.BotBasicInformation;
import com.bots.take.testbotsandroid.utils.Messages;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BotsOnAccountActivity extends AppCompatActivity implements Callback<List<BotBasicInformation>> {
    private String EncodedString;
    private ListView listBasicBotInformation;
    private List<BotBasicInformation> botBasicInformationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bots_on_account);

        listBasicBotInformation = findViewById(R.id.list_basic_bot_information);

        Intent intent = getIntent();
        EncodedString = intent.getStringExtra("AuthenticationToken");

        String baseUrl = getResources().getString(R.string.MessagingHubBaseUrl);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        IMessagingHubService service = retrofit.create(IMessagingHubService.class);
        Call<List<BotBasicInformation>> repos = service.GetBotsList(EncodedString);

        repos.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<BotBasicInformation>> call, Response<List<BotBasicInformation>> response) {
        switch (response.code()) {
            case HttpURLConnection.HTTP_OK:
                botBasicInformationList = response.body();
                ConfigureListAdapter();
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                Messages.ShowToast("Ocorreu um erro ao buscar a lista de BOTS", BotsOnAccountActivity.this);
                break;
        }
    }

    @Override
    public void onFailure(Call<List<BotBasicInformation>> call, Throwable t) {
        Messages.ShowToast("Ocorreu um erro ao buscar a lista de BOTS", BotsOnAccountActivity.this);
    }

    public void ConfigureListAdapter() {
        BotBasicInformationAdapter adapter = new BotBasicInformationAdapter(botBasicInformationList, this, BotsOnAccountActivity.this);

        listBasicBotInformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String shortName = "";

                for (BotBasicInformation information : botBasicInformationList) {
                    if (information.Id == id) {
                        shortName = information.ShortName;
                        break;
                    }
                }

                if (shortName != "") {
                    StartInsertActivity(shortName);
                }
            }
        });

        listBasicBotInformation.setAdapter(adapter);
    }

    public void StartInsertActivity(String shortName) {
        Intent intent = new Intent(this, InsertConfigurationActivity.class);

        intent.putExtra("AuthenticationToken", EncodedString);
        intent.putExtra("BotIdentifier", shortName);

        startActivity(intent);

    }

}
