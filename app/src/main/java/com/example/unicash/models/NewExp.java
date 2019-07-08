package com.example.unicash.models;

public class NewExp {
    private String description;
    private String date;
    private String category;
    private int amount;

    public NewExp(String description, String date, String category, int amount) {
        this.description = description;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }
}
