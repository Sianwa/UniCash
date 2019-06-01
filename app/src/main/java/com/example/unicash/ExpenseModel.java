package com.example.unicash;

public class ExpenseModel {
    private String Category;
    private String Date;
    private String Amount;

    public ExpenseModel(String category, String date, String amount) {
        this.Category =category;
        this.Date = date;
        this.Amount = amount;
    }
    public String getCategory() {
        return Category;
    }

    public String getDate() {
        return Date;
    }

    public String getAmount() {
        return Amount;
    }
}
