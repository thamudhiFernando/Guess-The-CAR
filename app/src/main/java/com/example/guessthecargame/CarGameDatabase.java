package com.example.guessthecargame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.guessthecargame.model.Car;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarGameDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME = "CarGames";
    static final private String DB_TABLE = "Car";
    static final private int DB_VERSION = 1;

    private static final String LOG_TAG = CarGameDatabase.class.getSimpleName();

    Context ctx;
    SQLiteDatabase sqLiteDatabase;

    public CarGameDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_TABLE + " (id integer primary key autoincrement,make text,image text);");
        Log.d(LOG_TAG, "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        Log.d(LOG_TAG, "onUpgrade");
        onCreate(db);
    }

    public void insertData() throws IOException {
        sqLiteDatabase = getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(Car.COLUMN_MAKE, "Panda");
        values.put(Car.COLUMN_IMAGE, (R.drawable.car6)+"");
//        System.out.println(values.toString());

        // insert row
        sqLiteDatabase.insert(DB_TABLE, null,values);
        // close db connection
        sqLiteDatabase.close();
//        Toast.makeText(ctx,"Data Saved Successfully",Toast.LENGTH_SHORT).show();
    }

    public Map<Integer,String> getCarMake() {
        sqLiteDatabase = getReadableDatabase();
        Cursor cr = sqLiteDatabase.rawQuery("select * from "+DB_TABLE ,null);

        Map<Integer,String> spinnerarray = new HashMap<>();

        while (cr.moveToNext()){
            int id = Integer.parseInt(cr.getString(0));
            String make = cr.getString(1);
            String image = cr.getString(2);
//            Log.d(LOG_TAG,id + " - " + make + " - " + image);

            if (!spinnerarray.containsValue(make)){
                spinnerarray.put(id,make);
            }
        }
        return spinnerarray;
    }


    public List<Car> getCarRandom() {
        sqLiteDatabase = getReadableDatabase();
        Cursor cr = sqLiteDatabase.rawQuery("select * from "+DB_TABLE ,null);

        List<Car> carList = new ArrayList<>();

        while (cr.moveToNext()){
            int id = Integer.parseInt(cr.getString(0));
            String make = cr.getString(1);
            String image = cr.getString(2);
            carList.add(new Car(id,make,image));
        }
        return carList;
    }
}
