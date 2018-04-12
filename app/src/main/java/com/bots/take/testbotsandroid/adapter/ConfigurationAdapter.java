package com.bots.take.testbotsandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bots.take.testbotsandroid.R;
import com.bots.take.testbotsandroid.models.ChatConfiguration;

import net.take.blipchat.BlipClient;
import net.take.blipchat.models.Account;
import net.take.blipchat.models.AuthConfig;
import net.take.blipchat.models.BlipOptions;

import java.util.List;

public class ConfigurationAdapter extends BaseAdapter {
    private final List<ChatConfiguration> chatConfigurationList;
    private final Activity act;
    private final Context context;

    public ConfigurationAdapter(List<ChatConfiguration> chatConfigurationList, Activity activity, Context context) {
        this.chatConfigurationList = chatConfigurationList;
        this.act = activity;
        this.context = context;
    }

    @Override
    public int getCount() {
        return chatConfigurationList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatConfigurationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return chatConfigurationList.get(position).getId();
    }

    @Override
    public View getView(final int position, View receiverView, ViewGroup viewGroup) {
        View view = act.getLayoutInflater().inflate(R.layout.list_configurations, viewGroup, false);

        ChatConfiguration chatConfiguration = chatConfigurationList.get(position);
        TextView alias = view.findViewById(R.id.Alias);

        alias.setText(chatConfiguration.BotAlias);

        Button configurationButton = view.findViewById(R.id.ExecuteConfiguration);

        configurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatConfiguration configuration = chatConfigurationList.get(position);

                AuthConfig authConfig = new AuthConfig(configuration.AuthType, configuration.UserIdentifier, configuration.UserPassWord);
                Account account = new Account();
                account.setFullName(configuration.UserName);
                account.setEmail(configuration.UserEmail);

                BlipOptions blipOptions = new BlipOptions();
                blipOptions.setAuthConfig(authConfig);
                blipOptions.setAccount(account);

                BlipClient.openBlipThread(context, configuration.BotAppKey, blipOptions);
            }
        });

        return view;
    }
}
