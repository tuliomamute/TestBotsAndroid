package com.bots.take.testbotsandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.take.blipchat.BlipClient;
import net.take.blipchat.BlipOptions;

public class ChatActivity extends AppCompatActivity {

    private ChatConfiguration chatConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            chatConfiguration = (ChatConfiguration) bundle.getSerializable("chatProperties");
        }

        BlipOptions blipOptions = new BlipOptions();
        blipOptions.setAuthType(chatConfiguration.AuthType);
        blipOptions.setUserIdentifier(chatConfiguration.UserIdentifier);
        blipOptions.setUserPassword(chatConfiguration.UserPassWord);
        blipOptions.setUserName(chatConfiguration.UserName);
        blipOptions.setUserEmail(chatConfiguration.UserEmail);
        BlipClient.openBlipThread(ChatActivity.this, chatConfiguration.BotIdentifier, blipOptions);
    }

}
