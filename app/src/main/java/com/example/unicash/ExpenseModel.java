package com.example.unicash;

public class ExpenseModel {
    private String Category;
    private String Description;
    private String Amount;


    public ExpenseModel(String category, String description, String amount) {
        this.Category =category;
        this.Description= description;
        this.Amount = amount;
    }

    public String getCategory() {
        return Category;
    }


    public String getDescription() {
        return Description;
    }

    public String getAmount() {
        return Amount;
    }
}
