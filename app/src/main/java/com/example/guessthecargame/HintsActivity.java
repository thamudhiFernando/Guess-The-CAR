package com.example.guessthecargame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.guessthecargame.model.Car;

import java.util.List;
import java.util.Random;

public class HintsActivity extends AppCompatActivity {

    CarGameDatabase carGameDatabase;
    private Car car;
    private int numberOfLines = 1;
    private String carMake;
    int tries = 0;
    boolean iterated = false;
    String temp = "";
    String holder = "";
    private Button button_submit;

    private static final String LOG_TAG = CarGameDatabase.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        setImage();
        loadTextFields();
    }

    public void clickedSubmit(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.text_group);
        View childAt = null;

        String guess = "";
        for (int x = 0; x < linearLayout.getChildCount(); x++) {
            childAt = linearLayout.getChildAt(x);
            guess = guess.concat(((TextView) childAt).getText().toString());
        }
        System.out.println("Guessed Letter : " + guess);


        for (int i = 0; i < car.getMake().length(); i++) {
            System.out.println("Selected Letter : " + Character.toString(car.getMake().charAt(i)));
            if ((guess.toLowerCase()).contains(Character.toString(car.getMake().charAt(i)).toLowerCase())) {
                temp = temp + Character.toString(car.getMake().charAt(i));
                childAt = linearLayout.getChildAt(i);
                ((TextView) childAt).setEnabled(false);
                ((TextView) childAt).setText(Character.toString(car.getMake().charAt(i)).toUpperCase());
            } else {
                temp = temp + "-";
                ((TextView) childAt).setText("");
            }
        }
        tries++;
        iterated = true;

        System.out.println("----temp---- " + temp);
        button_submit = findViewById(R.id.button);
        if (button_submit.getText().equals("NEXT")) {
            setImage();
            button_submit.setText(R.string.btn_submit);
            linearLayout.getChildAt(1).setEnabled(true);
        } else {
            if (temp.toLowerCase().equals(car.getMake().toLowerCase())) {
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
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GREEN))
            } else {
                String html = "Answer is : <font color='#ffea00'>" + car.getMake() + "</font>";
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
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GREEN));
            }
        }

    }

    public void setImage() {
        carGameDatabase = new CarGameDatabase(this);
        List<Car> carRandom = carGameDatabase.getCarRandom();
        int randomValue = new Random().nextInt(carRandom.size());
        car = carRandom.get(randomValue);
        Log.d(LOG_TAG, car.toString() + "--------Hints-------");
        ImageView diceImage = (ImageView) findViewById(R.id.imageView_random_2);
        diceImage.setImageResource(Integer.parseInt(car.getImage()));
    }

    public void loadTextFields() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.text_group);
        for (int i = 0; i < car.getMake().length(); i++) {
            // add edittext
            EditText et = new EditText(this);
            et.setTextSize(30);
            et.setId(numberOfLines + i);
            linearLayout.addView(et);
        }
    }
}