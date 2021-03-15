package com.example.guessthecargame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.guessthecargame.model.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AdvanceLevelActivity extends AppCompatActivity {
    CarGameDatabase carGameDatabase;
    private Car car;
    private Button button_submit;
    Map<Integer,String> carIdsMakeArray = new HashMap<>();
    String[] arry = new String[3];
    private boolean answer1 = false;
    private boolean answer2 = false;
    private boolean answer3 = false;
    private boolean isAllCorrect = false;
    int tries = 0;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_level);
        randomImageSet();
        calculatePoints();
    }

    public void submitAnswers(View view) {
        button_submit = findViewById(R.id.button_submit_advance);
        EditText viewById1 = (EditText) findViewById(R.id.editText_input_1);
        System.out.println(arry[0] + "_" +viewById1.getText().toString());
        EditText viewById2 = (EditText) findViewById(R.id.editText_input_2);
        System.out.println(arry[1]  + "_" +viewById2.getText().toString());
        EditText viewById3 = (EditText) findViewById(R.id.editText_input_3);
        System.out.println(arry[2]  + "_" +viewById3.getText().toString());
        if (answer1==true && answer2 ==true && answer3==true){
            isAllCorrect = true;
        }
        if (button_submit.getText().equals("NEXT")){
            randomImageSet();
            button_submit.setText(R.string.btn_submit);
            viewById1.setEnabled(true);
            viewById1.setTextColor(Color.BLACK);
            viewById1.setBackgroundColor(Color.WHITE);
            viewById1.setText("");
            viewById2.setEnabled(true);
            viewById2.setTextColor(Color.BLACK);
            viewById2.setBackgroundColor(Color.WHITE);
            viewById2.setText("");
            viewById3.setEnabled(true);
            viewById3.setTextColor(Color.BLACK);
            viewById3.setBackgroundColor(Color.WHITE);
            viewById3.setText("");
            tries = 0;
            points = 0;
            calculatePoints();
        }else {
            tries++;
            if (isAllCorrect==false && tries < 3){
                if (arry[0].equals(viewById1.getText().toString())){
                    viewById1.setBackgroundColor(Color.GREEN);
                    viewById1.setTextColor(Color.WHITE);
                    viewById1.setEnabled(false);
                    answer1 = true;
                }else {
                    if (answer1==false){
                        viewById1.setBackgroundColor(Color.RED);
                        viewById1.setTextColor(Color.WHITE);
                    }
                }
                if (arry[1].equals(viewById2.getText().toString())){
                    viewById2.setBackgroundColor(Color.GREEN);
                    viewById2.setTextColor(Color.WHITE);
                    viewById2.setEnabled(false);
                    answer2=true;
                }else {
                    if (answer2==false){
                        viewById2.setBackgroundColor(Color.RED);
                        viewById2.setTextColor(Color.WHITE);
                    }
                }
                if (arry[2].equals(viewById3.getText().toString())){
                    viewById3.setBackgroundColor(Color.GREEN);
                    viewById3.setTextColor(Color.WHITE);
                    viewById3.setEnabled(false);
                    answer3 = true;
                }else {
                    if (answer3==false){
                        viewById3.setBackgroundColor(Color.RED);
                        viewById3.setTextColor(Color.WHITE);
                    }
                }
            }else {
                if (arry[0].equals(viewById1.getText().toString())){
                    viewById1.setBackgroundColor(Color.GREEN);
                    viewById1.setTextColor(Color.WHITE);
                    viewById1.setEnabled(false);
                    answer1 = true;
                }else {
                    if (answer1==false){
                        viewById1.setBackgroundColor(Color.RED);
                        viewById1.setTextColor(Color.WHITE);
                    }
                }
                if (arry[1].equals(viewById2.getText().toString())){
                    viewById2.setBackgroundColor(Color.GREEN);
                    viewById2.setTextColor(Color.WHITE);
                    viewById2.setEnabled(false);
                    answer2=true;
                }else {
                    if (answer2==false){
                        viewById2.setBackgroundColor(Color.RED);
                        viewById2.setTextColor(Color.WHITE);
                    }
                }
                if (arry[2] .equals(viewById3.getText().toString())){
                    viewById3.setBackgroundColor(Color.GREEN);
                    viewById3.setTextColor(Color.WHITE);
                    viewById3.setEnabled(false);
                    answer3 = true;
                }else {
                    if (answer3==false){
                        viewById3.setBackgroundColor(Color.RED);
                        viewById3.setTextColor(Color.WHITE);
                    }
                }

                System.out.println("answer1 : " + answer1);
                System.out.println("answer2 : " + answer2);
                System.out.println("answer3 : " + answer3);

                if (answer1==true && answer2==true && answer3==true){
                    calculatePoints();
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
                                    button_submit.setText(R.string.btn_next);
                                }
                            })
                            //set negative button
                            .setCancelable(true)
                            .show();
                }else {
                    calculatePoints();
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
                                    button_submit.setText(R.string.btn_next);
                                }
                            })
                            //set negative button
                            .setCancelable(true)
                            .show();
                }
            }
        }
        calculatePoints();
    }


    public void randomImageSet() {
        carGameDatabase = new CarGameDatabase(this);
        List<Car> carRandom = carGameDatabase.getCarRandom();
        int randomValue = 0;
        Map<String,String> carArray = new HashMap<>();
        int value = 0;
        for (int i = 0 ; i < carRandom.size() ; i++){
            randomValue = new Random().nextInt(carRandom.size());
            car = carRandom.get(randomValue);
            if (carArray.size() >= 3){
                break;
            }
            if (!carArray.containsKey(car.getMake())){
                carArray.put(car.getMake(),car.getImage());
                System.out.println(car.getMake()+" --- "+car.getImage());
//                carIdMakeArray.put(car.getId(),car.getMake());
                arry[value] = car.getMake();
                value ++;
            }
        }
        ArrayList<String> carimageList = new ArrayList<>();
        for (Map.Entry<String, String> entry :carArray.entrySet()) {
            carimageList.add(entry.getValue());
        }

        if (carArray.size() == 3){
            ImageView diceImage1 = (ImageView) findViewById(R.id.imageView_1);
            diceImage1.setImageResource(Integer.parseInt(carimageList.get(0)));
            ImageView diceImage2 = (ImageView) findViewById(R.id.imageView_2);
            diceImage2.setImageResource(Integer.parseInt(carimageList.get(1)));
            ImageView diceImage3 = (ImageView) findViewById(R.id.imageView_3);
            diceImage3.setImageResource(Integer.parseInt(carimageList.get(2)));
        }
        arry.toString();
    }

    public void calculatePoints(){

        if (answer1==true){
            points = 1;
        }
        if (answer2==true){
            points = 1;
        }
        if (answer3==true){
            points = 1;
        }
        if (answer1==true && answer2==true){
            points = 2;
        }
        if (answer1==true && answer3==true){
            points = 2;
        }
        if (answer2==true && answer3==true){
            points = 2;
        }
        if (answer1==true && answer2==true && answer3==true){
            points = 3;
        }

        TextView viewById = (TextView) findViewById(R.id.textView_points);
        String html = "Points : <font color='#018786'>" + points + "</font>";
        viewById.setText(Html.fromHtml(html));
    }
}