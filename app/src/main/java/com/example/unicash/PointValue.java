package com.example.unicash;

public class PointValue {

    int Index;
    int amount;
    String category;
    String date;
    String description;


    public PointValue() {

    }

    public PointValue(int amount, String category){
        this.amount = amount;
        this.category = category;
    }


    public PointValue(int amount, String category, int Index){
        this.amount = amount;
        this.category = category;
        this.Index = Index;
    }

    public PointValue(int amount, String category, int Index, String date){
        this.amount = amount;
        this.category = category;
        this.Index = Index;
        this.date = date;
    }

    public PointValue(int amount, String category, int Index, String date, String description){
        this.amount = amount;
        this.category = category;
        this.Index = Index;
        this.date = date;
        this.description = description;
    }

    public String getDescription() { return description; }

    public int getAmount() { return amount; }

    public int getIndex() { return Index; }

    public String getCategory() { return category; }

    public String getDate() { return date; }
}