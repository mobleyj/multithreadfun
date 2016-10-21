package com.example.baske.multithreadfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button create;
    Button load;
    Button clear;

    ListView numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = (Button) findViewById(R.id._create);
        create.setOnClickListener(this);
        load = (Button) findViewById(R.id._load);
        load.setOnClickListener(this);
        clear = (Button) findViewById(R.id._clear);
        clear.setOnClickListener(this);

        numbers = (ListView) findViewById(R.id._list);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id._create: {

            }

        }

    }
}
