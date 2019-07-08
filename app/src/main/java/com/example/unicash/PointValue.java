package com.example.unicash;

public class PointValue {

    int indexVal;
    int amount;
    String category;
    String date;
    String description;


    public PointValue() {

    }


    public PointValue(int amount, String category, int indexVal, String date, String description){
        this.amount = amount;
        this.category = category;
        this.indexVal = indexVal;
        this.date = date;
        this.description = description;
    }

    public String getDescription() { return description; }

    public int getAmount() { return amount; }

    public int getIndexVal() { return indexVal; }

    public String getCategory() { return category; }

    public String getDate() { return date; }
}