package com.example.desiredeal.activity;

import com.razorpay.PaymentData;

public class CustomPaymentData extends PaymentData {

    private double totalAmount;
    private double Currency;

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


    public double getCurrency() {
        return Currency;
    }

    public void setCurrency(double currency) {
        Currency = currency;
    }
}
