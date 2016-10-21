package com.example.baske.multithreadfun;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button create;
    private Button load;
    private Button clear;
    private ProgressBar progress;
    String filename = "numbers.txt";
    ArrayAdapter<String> adapter;

    private Handler mHandler = new Handler();


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

        progress = (ProgressBar) findViewById(R.id.progressBar);

        list = (ListView) findViewById(R.id._list);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id._create: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(getFilesDir(), filename);
                        FileOutputStream outputStream;
                        StringBuilder s = new StringBuilder();
                        progress.setProgress(0);


                        for(int i = 1; i <= 10; ++i) {
                            s.append(i + "\n");
                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progress.setProgress(progress.getProgress() + 10);


                        }

                        try {
                            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

                            outputStream.write(s.toString().getBytes());

                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                        progress.setProgress(0);

                    }


                }).start();

                break;

            }

            case R.id._load: {
                ArrayList<String> nums = new ArrayList<String>();
                String line = null;
                progress.setProgress(0);

                try {
                    InputStream instream = openFileInput(filename);

                    InputStreamReader inputReader = new InputStreamReader(instream);
                    BufferedReader buffRead = new BufferedReader(inputReader);

                    while ((line = buffRead.readLine()) != null){
                        nums.add(new String(line));
                        progress.setProgress(progress.getProgress() + 10);
                        Thread.sleep(250);

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nums);
                list.setAdapter(adapter);
                progress.setProgress(0);
                break;
            }

            case R.id._clear: {
                adapter.clear();
                list.setAdapter(adapter);
            }

        }

    }
}
