package com.example.guessthecargame;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CarGameDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME = "CarGame";
    static final private String DB_TABLE = "Car";
    static final private int DB_VERSION = 1;

    private static final String LOG_TAG = CarGameDatabase.class.getSimpleName();
    private String imageName = "car";

    Context ctx;
    SQLiteDatabase sqLiteDatabase;

    public CarGameDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ DB_TABLE +" (_id integer primary key,_make text,_image blob);");
        Log.i("Database","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+DB_TABLE);
        Log.i("Database","onUpgrade");
        onCreate(db);
    }

    public void insertData() throws IOException {
        sqLiteDatabase = getWritableDatabase();
//        String stringFilePath = Environment.getRootDirectory().getPath()+"/F/IIT/6th Semester/Mobile Application Development/AssessmentF/IIT/6th Semester/Mobile Application Development/Assessment/GuessTheCarGame/res/drawable/"+"car1"+".jpg";
        int image[] = {R.drawable.car1};
        String stringFilePath = Environment.getExternalStorageDirectory() + "/myFolderName/"+ imageName + "1.jpg" ;
        Bitmap bitmap = BitmapFactory.decodeFile(stringFilePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        sqLiteDatabase.execSQL("insert into "+DB_TABLE+ " values(1,'Audi',"+bytesImage+");");

        Toast.makeText(ctx,"Data Saved Successfully",Toast.LENGTH_SHORT).show();
    }

    public void getCarMake(){
        sqLiteDatabase = getReadableDatabase();
        Cursor cr = sqLiteDatabase.rawQuery("select * from "+DB_TABLE+" group by _make",null);
        Resources res = Resources.getSystem();
        String[] planets = res.getStringArray(R.array.planets_array);
        StringBuilder str = new StringBuilder();
        while (cr.moveToNext()){
            String make = cr.getString(0);
            str.append(make+"\n");
            planets[planets.length] = make;
        }
        Toast.makeText(ctx,str.toString(),Toast.LENGTH_LONG).show();
        Log.d(LOG_TAG,str.toString());


    }
}
