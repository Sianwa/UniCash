package com.android.example.unicash_analytics;

public class PointValue {
    int Amount;
    String Expense;
    int Index;


    public PointValue() {

    }

    public PointValue(int Amount, String Expense){
        this.Amount = Amount;
        this.Expense=Expense;
    }

    public PointValue(int Amount, String Expense, int Index){
        this.Amount = Amount;
        this.Expense=Expense;
        this.Index = Index;
    }

    public int getAmount(){

        return Amount;
    }

    public String getExpense() {
        return Expense;
    }

    public int getIndex(){
        return Index;
    }
}