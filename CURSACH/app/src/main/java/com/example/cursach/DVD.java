package com.example.cursach;

import android.widget.ImageView;

import java.io.PrintStream;
import java.io.Serializable;

public class DVD implements Serializable {
    private String name;
    private int img;
    private String info;
    private double price;

    public DVD(String name, int img, String info, double price) {
        this.name = name;
        this.img = img;
        this.info = info;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
