package com.example.guessthecargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    CarGameDatabase carGameDatabase;
    private static boolean isInserted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carGameDatabase = new CarGameDatabase(this);
       /* if (isInserted==false){
            try {
                carGameDatabase.insertData();
                isInserted = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    public void identifyTheCarMakeActivity(View view) {
        Log.d(LOG_TAG, "Identify the car make button Clicked !");
        Intent intent = new Intent(this, IdentifyTheCarMakeActivity.class);
        startActivity(intent);
    }

    public void hintsActivity(View view) {
        Log.d(LOG_TAG, "Hints button Clicked !");
        Intent intent = new Intent(this, HintsActivity.class);
        startActivity(intent);
    }

    public void identifyTheCarImageActivity(View view) {
        Log.d(LOG_TAG, "Identify the car Image button Clicked !");
        Intent intent = new Intent(this, IdentifyTheCarImageActivity.class);
        startActivity(intent);
    }

    public void advanceLevelActivity(View view) {
        Log.d(LOG_TAG, "Advance Level Activity button Clicked !");
        Intent intent = new Intent(this, AdvanceLevelActivity.class);
        startActivity(intent);
    }
}