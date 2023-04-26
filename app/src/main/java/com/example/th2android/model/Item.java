package com.example.th2android.model;

import java.io.Serializable;

public class Item implements Serializable {

    private int id;
    private String name,author,date, phamvi,doituong,rating;

    public Item(int id, String name, String author, String date, String phamvi, String doituong, String rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.phamvi = phamvi;
        this.doituong = doituong;
        this.rating = rating;

    }

    public Item() {
    }

    public Item(String name, String author, String date, String phamvi, String doituong, String rating) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.phamvi = phamvi;
        this.doituong = doituong;
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPhamvi() {
        return phamvi;
    }

    public void setPhamvi(String phamvi) {
        this.phamvi = phamvi;
    }

    public String getDoituong() {
        return doituong;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
