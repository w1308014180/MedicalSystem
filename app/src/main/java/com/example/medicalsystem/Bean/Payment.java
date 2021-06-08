package com.example.medicalsystem.Bean;

public class Payment {
    private String paymentName;
    private double paymentPrice;
    private int paymentQuantity;
    private int patientId;

    public Payment(String paymentName, double paymentPrice, int paymentQuantity, int patientId){
        this.paymentName = paymentName;
        this.paymentPrice = paymentPrice;
        this.paymentQuantity = paymentQuantity;
        this.patientId = patientId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public double getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(double paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public int getPaymentQuantity() {
        return paymentQuantity;
    }

    public void setPaymentQuantity(int paymentQuantity) {
        this.paymentQuantity = paymentQuantity;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
