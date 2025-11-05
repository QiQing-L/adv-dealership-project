package com.pluralsight.dealership;

import com.pluralsight.Vehicle;

public class SalesContract extends Contract{
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean financeOption;

    //sales tax|recording fee|processing fee|total cost|finance|monthly payment

    public SalesContract(String dateOfContract, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean financeOption) {

        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = 0.05 * vehicleSold.getPrice(); //sale tax 5%
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() > 10000)?295:495;
        //Processing fee ($295 for vehicles under $10,000 and $495 for all others
        this.financeOption = financeOption;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 0;
        double interestRate = 0;
        if (financeOption) {
            if (getVehicleSold().getPrice() >= 10000) {
                numberOfPayments = 48;
                interestRate = 4.25 / 1200;
            } else {
                numberOfPayments = 24;
                interestRate = 5.25 / 1200;
            }

            double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
            monthlyPayment = Math.round(monthlyPayment * 100);
            monthlyPayment /= 100;
            return monthlyPayment;
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "\nSales Contract: " +
                "\nDate of Contract: " + getDateOfContract() +
                "\nCustomer Name: " + getCustomerName() +
                "\nCustomer Email: " + getCustomerEmail() +
                "\nVehicle Sold: " + getVehicleSold() +
                "\nSales Tax Amount: " + salesTaxAmount +
                "\nRecording Fee: " + recordingFee +
                "\nProcessing Fee: " + processingFee +
                "\nFinanceOption: " + financeOption +
                "\nTotal Price: $" + getTotalPrice() +
                "\nMonthly payment: $" +getMonthlyPayment();
    }
}
