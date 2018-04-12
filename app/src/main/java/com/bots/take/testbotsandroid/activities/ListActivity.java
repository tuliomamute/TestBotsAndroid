package com.bots.take.testbotsandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bots.take.testbotsandroid.R;
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

        list_configuration.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(ListActivity.this, UpdateConfigurationActivity.class);
                intent.putExtra("configurationId", id);

                startActivity(intent);
            }
        });

        list_configuration.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_bot, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedOption = item.getItemId();
        if (selectedOption == R.id.new_bot) {
            //Intent intent = new Intent(this, InsertConfigurationActivity.class);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
