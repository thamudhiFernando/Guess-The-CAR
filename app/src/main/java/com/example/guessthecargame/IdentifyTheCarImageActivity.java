package com.example.guessthecargame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.guessthecargame.model.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class IdentifyTheCarImageActivity extends AppCompatActivity {
    CarGameDatabase carGameDatabase;
    private Car car;
    Map<Integer,String> carIdMakeArray = new HashMap<>();
    int indexOfSelectedImage = 0;
    private String randomMake ;
    private static final String LOG_TAG = CarGameDatabase.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);
        randomImageSet();
        randomMake();
    }

    public void submitImage(View view) {
        ArrayList<String> arrayMake = new ArrayList<String>();
        for (String make:carIdMakeArray.values()) {
            arrayMake.add(make);
        }
        if (randomMake.toLowerCase().equals(arrayMake.get(indexOfSelectedImage).toLowerCase())){
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
                            randomImageSet();
                            randomMake();
                        }
                    })
                    //set negative button
                    .setCancelable(true)
                    .show();
        }else {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
//                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle(Html.fromHtml("<font color='#E60000'>WRONG ANSWER !</font>"))
                    //set message
//                        .setMessage("Answer is : " + car.getMake())
                    .setMessage("Answer is : ")
            //set positive button
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what would happen when positive button is clicked
                        }
                    })
                    //set negative button
                    .setCancelable(true)
                    .show();
        }
    }

    public void randomImageSet() {
        carGameDatabase = new CarGameDatabase(this);
        List<Car> carRandom = carGameDatabase.getCarRandom();
        int randomValue = 0;
        Map<String,String> carArray = new HashMap<>();

        for (int i = 0 ; i < carRandom.size() ; i++){
            randomValue = new Random().nextInt(carRandom.size());
            car = carRandom.get(randomValue);
            if (carArray.size() >= 3){
                break;
            }
            if (!carArray.containsKey(car.getMake())){
                carArray.put(car.getMake(),car.getImage());
                System.out.println(car.getMake()+" --- "+car.getImage());
                carIdMakeArray.put(car.getId(),car.getMake());
            }
        }
        ArrayList<String> carimageList = new ArrayList<>();
        for (Map.Entry<String, String> entry :carArray.entrySet()) {
            carimageList.add(entry.getValue());
        }

        if (carArray.size() == 3){
            ImageView diceImage1 = (ImageView) findViewById(R.id.imageView_select_1);
            diceImage1.setImageResource(Integer.parseInt(carimageList.get(0)));
            ImageView diceImage2 = (ImageView) findViewById(R.id.imageView_select_2);
            diceImage2.setImageResource(Integer.parseInt(carimageList.get(1)));
            ImageView diceImage3 = (ImageView) findViewById(R.id.imageView_select_3);
            diceImage3.setImageResource(Integer.parseInt(carimageList.get(2)));
        }
    }

    public void randomMake(){
        int randomValue = new Random().nextInt(carIdMakeArray.size());

        ArrayList<String> arrayMake = new ArrayList<String>();
        for (String make:carIdMakeArray.values()) {
            arrayMake.add(make);
        }
        randomMake = arrayMake.get(randomValue);
        TextView viewById = (TextView) findViewById(R.id.textview_select_image);
        String html = "Select the CORRECT image of :  <font color='#018786'>" + randomMake + "</font>";
        viewById.setText(Html.fromHtml(html));
    }

    public void selectedImage1(View view) {
        indexOfSelectedImage = 0;
    }

    public void selectedImage2(View view) {
        indexOfSelectedImage = 1;
    }

    public void selectedImage3(View view) {
        indexOfSelectedImage = 2;
    }
}