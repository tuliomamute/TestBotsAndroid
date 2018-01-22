package com.bots.take.testbotsandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bots.take.testbotsandroid.adapter.ConfigurationAdapter;
import com.bots.take.testbotsandroid.models.ChatConfiguration;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView list_configuration = findViewById(R.id.list_configuration);
        List<ChatConfiguration> chatConfigurationList = ChatConfiguration.listAll(ChatConfiguration.class);

        ConfigurationAdapter adapter = new ConfigurationAdapter(chatConfigurationList, this, ListActivity.this);
        list_configuration.setAdapter(adapter);

        list_configuration.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(ListActivity.this, UpdateConfigurationActivity.class);
                intent.putExtra("configurationId", id);

                startActivity(intent);
            }
        });
    }

    public void openInsertActivity(View v) {
        Intent intent = new Intent(this, InsertConfigurationActivity.class);
        startActivity(intent);
    }

}
