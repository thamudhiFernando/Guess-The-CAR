package com.example.guessthecargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.guessthecargame.model.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class IdentifyTheCarImageActivity extends AppCompatActivity {
    CarGameDatabase carGameDatabase;
    private Car car;
    private static final String LOG_TAG = CarGameDatabase.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);
        randomImageSet();
    }

    public void submitImage(View view) {

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
                Log.d(LOG_TAG, car.toString() + "---------------");
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
}