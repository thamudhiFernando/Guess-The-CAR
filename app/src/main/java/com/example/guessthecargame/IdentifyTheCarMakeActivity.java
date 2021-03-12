package com.example.guessthecargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.guessthecargame.model.Car;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class IdentifyTheCarMakeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button button_identify;
    CarGameDatabase carGameDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_make);

        setImage();

        carGameDatabase = new CarGameDatabase(this);
        Map<Integer, String> carMake = carGameDatabase.getCarMake();

        ArrayList<String> arraySpinner = new ArrayList<String>();
        for (String make:carMake.values()) {
            arraySpinner.add(make);
        }
        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner_dropdown);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , arraySpinner);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner_dropdown);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showToastMessage(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message_correct, Toast.LENGTH_LONG);
        button_identify = findViewById(R.id.button_identify);
        button_identify.setText(R.string.btn_next);
        toast.show();
    }

    public void setImage(){
        carGameDatabase = new CarGameDatabase(this);
        List<Car> carRandom = carGameDatabase.getCarRandom();
        int randomValue=new Random().nextInt(carRandom.size());
        Car car = carRandom.get(randomValue);
        ImageView diceImage= (ImageView) findViewById(R.id.imageView_random_1);
        diceImage.setImageResource(Integer.parseInt(car.getImage()));
    }
}