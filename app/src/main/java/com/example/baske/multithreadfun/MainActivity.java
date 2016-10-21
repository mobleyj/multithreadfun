package com.example.baske.multithreadfun;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

import java.lang.String;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button create;
    private Button load;
    private Button clear;
    private ProgressBar progress;


    ListView list;

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

        //list = (ListView) findViewById(R.id._list);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id._create: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String filename = "numbers";
                        File file = new File(getFilesDir(), filename);
                        FileOutputStream outputStream;
                        StringBuilder s = new StringBuilder();

                        for(int i = 1; i <= 10; ++i) {
                            s.append(i + "\n");
                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

                            outputStream.write(s.toString().getBytes());

                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }

                    }

                })
                        .start();

            }

        }

    }
}
