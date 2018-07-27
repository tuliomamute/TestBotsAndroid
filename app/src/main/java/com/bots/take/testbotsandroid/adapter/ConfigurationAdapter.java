package com.bots.take.testbotsandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Map<String, String> extras = null;

                ChatConfiguration configuration = chatConfigurationList.get(position);

                extras = ReturnExtrasDictionary(configuration.UserExtras);

                AuthConfig authConfig = new AuthConfig(configuration.AuthType, configuration.UserIdentifier, configuration.UserPassWord);
                Account account = new Account();
                account.setFullName(configuration.UserName);
                account.setEmail(configuration.UserEmail);

                if (extras != null)
                    account.setExtras(extras);

                BlipOptions blipOptions = new BlipOptions();
                blipOptions.setAuthConfig(authConfig);
                blipOptions.setAccount(account);

                BlipClient.openBlipThread(context, configuration.BotAppKey, blipOptions);
            }
        });

        return view;
    }

    private Map<String, String> ReturnExtrasDictionary(String savedExtras) {
        Map<String, String> extras = new HashMap<String, String>();

        if (TextUtils.isEmpty(savedExtras))
            return null;
        try {
            String[] extrasKeyValue = savedExtras.split(";");

            for (String keyValuePair : extrasKeyValue) {
                String extrasKey = keyValuePair.substring(0, keyValuePair.indexOf("="));
                String extrasValue = keyValuePair.substring(keyValuePair.indexOf("=") + 1, keyValuePair.length());

                extras.put(extrasKey, extrasValue);
            }

            return extras;
        } catch (Exception ex) {
            String message = ex.getMessage();
            return null;
        }
    }

}
