package com.example.guessthecargame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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
    private Car car;

    private static final String LOG_TAG = CarGameDatabase.class.getSimpleName();

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
        String spinnerValue = ((Spinner) findViewById(R.id.planets_spinner_dropdown)).getSelectedItem().toString();
        if (button_identify.getText().equals("NEXT")){
            setImage();
            button_identify.setText(R.string.btn_identify);
            ((Spinner) findViewById(R.id.planets_spinner_dropdown)).setEnabled(true);
        }else {
            if (spinnerValue.equals(car.getMake())){
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        //set icon
//                    .setIcon(android.R.drawable.ic_dialog_alert)
                        //set title
                        .setTitle(Html.fromHtml("<font color='#00b300'>CORRECT ANSWER !</font>"))
                        //set positive button
                        .setPositiveButton(Html.fromHtml("<font color='#00b300'>OK</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                button_identify.setText(R.string.btn_next);
                                ((Spinner) findViewById(R.id.planets_spinner_dropdown)).setEnabled(false);
                            }
                        })
                        //set negative button
                        .setCancelable(true)
                        .show();
            }else {
                String html = "Answer is : <font color='#ffea00'>"+car.getMake() +"</font>";
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        //set icon
//                    .setIcon(android.R.drawable.ic_dialog_alert)
                        //set title
                        .setTitle(Html.fromHtml("<font color='#E60000'>WRONG ANSWER !</font>"))
                        //set message
//                        .setMessage("Answer is : " + car.getMake())
                        .setMessage(Html.fromHtml(html))
                        //set positive button
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                button_identify.setText(R.string.btn_next);
                                ((Spinner) findViewById(R.id.planets_spinner_dropdown)).setEnabled(false);
                            }
                        })
                        //set negative button
                        .setCancelable(true)
                        .show();
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GREEN));
            }
        }
        toast.show();
    }

    public void setImage(){
        carGameDatabase = new CarGameDatabase(this);
        List<Car> carRandom = carGameDatabase.getCarRandom();
        int randomValue=new Random().nextInt(carRandom.size());
        car = carRandom.get(randomValue);
        Log.d(LOG_TAG,car.toString() + "---------------");
        ImageView diceImage= (ImageView) findViewById(R.id.imageView_random_1);
        diceImage.setImageResource(Integer.parseInt(car.getImage()));
    }
}