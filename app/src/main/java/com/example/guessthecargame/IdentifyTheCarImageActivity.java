package com.example.guessthecargame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
    private static final String LOG_TAG = CarGameDatabase.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);
        randomImageSet();
        randomMake();
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
        Map<Integer, String> carMake = carGameDatabase.getCarMake();
        int randomValue = new Random().nextInt(carMake.size());

        ArrayList<String> arrayMake = new ArrayList<String>();
        for (String make:carMake.values()) {
            arrayMake.add(make);
        }
        TextView viewById = (TextView) findViewById(R.id.textview_select_image);
//        viewById.setText(viewById.getText() + " " + arrayMake.get(randomValue));

        String html = viewById.getText() + " <font color='#018786'>" + arrayMake.get(randomValue) + "</font>";
        viewById.setText(Html.fromHtml(html));
    }

    public void selectedImage1(View view) {
        ImageView viewById = (ImageView) findViewById(R.id.imageView_select_1);
//        viewById.setImageDrawable(getResources().getDrawable(R.drawable.your_drawable));
        Drawable.ConstantState constantState = viewById.getDrawable().getConstantState();
        System.out.println("constantState  : " + constantState);
    }

    public void selectedImage2(View view) {
        ImageView viewById = (ImageView) findViewById(R.id.imageView_select_2);
    }

    public void selectedImage3(View view) {
        ImageView viewById = (ImageView) findViewById(R.id.imageView_select_3);
    }
}