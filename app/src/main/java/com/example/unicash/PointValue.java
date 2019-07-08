package com.example.unicash;

public class PointValue {
    int amount;
    String category;
    int Index;
    String details;
    String date;

    public PointValue() {

    }

    public PointValue(int amount, String category){
        this.amount = amount;
        this.category=category;
    }

    public PointValue(int amount, String category,  String details){
        this.amount = amount;
        this.category=category;
        this.details = details;
    }

    public PointValue(int amount, String category,  String details, String date){
        this.amount = amount;
        this.category=category;
        this.details = details;
        this.date = date;
    }

    public PointValue(int amount, String category,  String details, String date, int Index){
        this.amount = amount;
        this.category=category;
        this.details = details;
        this.date = date;
        this.Index = Index;
    }

    public int getAmount(){ return amount; }

    public String getCategory() { return category; }

    public int getIndex(){
        return Index;
    }

    public String getDetails() { return details; }

    public String getDate(){ return date; }
}
