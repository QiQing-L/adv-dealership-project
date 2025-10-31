package com.pluralsight.dealership;

import com.pluralsight.Vehicle;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    // ending value|lease fee|total cost|monthly payment

    public LeaseContract(String dateOfContract, String customerName, String customerEmail,
                         Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = 0.5 * vehicleSold.getPrice(); //50% of the original price
        this.leaseFee = 0.07 * vehicleSold.getPrice(); // 7% of the original price
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }


    @Override
    public double getTotalPrice() {
        return (getVehicleSold().getPrice() - expectedEndingValue) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 36;
        double interestRate = 4.0 / 1200;
        double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
        monthlyPayment = Math.round(monthlyPayment * 100);
        monthlyPayment /= 100;
        return monthlyPayment;
    }
}
