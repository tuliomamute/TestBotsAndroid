package com.bots.take.testbotsandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bots.take.testbotsandroid.R;
import com.bots.take.testbotsandroid.models.BotBasicInformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BotBasicInformationAdapter extends BaseAdapter {

    private final List<BotBasicInformation> botBasicInformationList;
    private final Activity act;
    private final Context context;

    public BotBasicInformationAdapter(List<BotBasicInformation> botBasicInformationList, Activity act, Context context) {
        this.botBasicInformationList = botBasicInformationList;
        this.act = act;
        this.context = context;
    }

    @Override
    public int getCount() {
        return botBasicInformationList.size();
    }

    @Override
    public Object getItem(int position) {
        return botBasicInformationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return botBasicInformationList.get(position).Id;
    }

    @Override
    public View getView(int position, View receiverView, ViewGroup viewGroup) {
        View view = act.getLayoutInflater().inflate(R.layout.list_basic_bot_information, viewGroup, false);
        BotBasicInformation botBasicInformation = botBasicInformationList.get(position);

        ImageView botImage = view.findViewById(R.id.BotImage);
        TextView botName = view.findViewById(R.id.BotName);
        TextView botIdentifier = view.findViewById(R.id.BotIdenfitier);

        Picasso.get()
                .load(botBasicInformation.ImageUri)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(botImage);

        botName.setText(botBasicInformation.Name);
        botIdentifier.setText(botBasicInformation.ShortName);
        return view;
    }
}
