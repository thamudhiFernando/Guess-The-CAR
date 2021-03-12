package com.example.guessthecargame.model;

import java.sql.Blob;

public class Car {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MAKE = "make";
    public static final String COLUMN_IMAGE = "image";

    private int id;
    private String make;
    private String image;

    public Car() {

    }

    public Car(int id, String make, String image) {
        this.id = id;
        this.make = make;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", image=" + image +
                '}';
    }
}
