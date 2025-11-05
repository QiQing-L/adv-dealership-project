package com.pluralsight.dealership;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    /* Bonus Ideas : to  LIST CONTRACTS ?
    public void getContracts {
        Dealership dealership = null;
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("dealership.csv"))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (lineNumber == 0) { // dealership info
                    String name = fields[0];
                    String address = fields[1];
                    String phone = fields[2];
                    dealership = new Dealership(name, address, phone);
                } else { // vehicle info
                    int vin = Integer.parseInt(fields[0]);
                    int year = Integer.parseInt(fields[1]);
                    String make = fields[2];
                    String model = fields[3];
                    String vehicleType = fields[4];
                    String color = fields[5];
                    int odometer = Integer.parseInt(fields[6]);
                    double price = Double.parseDouble(fields[7]);
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    vehicles.add(vehicle);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dealership != null) {
            for (Vehicle vehicle : vehicles) {
                dealership.addVehicle(vehicle);
            }
        }

        return dealership;
    }
*/
    public void saveContract(Contract contract) {

        if (contract instanceof SalesContract){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("contracts.csv", true))) {
                // Write sales contract information:
                bw.write(//Type"sales"|contract date|name|email|
                        "SALE" + "|" + contract.getDateOfContract()+ "|"
                        + contract.getCustomerName() + "|" + contract.getCustomerEmail() + "|"
                        //vehicleSold information: car id|year|make|model|vehicle type|color|odometer|price|
                        + contract.getVehicleSold().getVin() + "|" + contract.getVehicleSold().getYear() + "|"
                        + contract.getVehicleSold().getMake() + "|" + contract.getVehicleSold().getModel() + "|"
                        + contract.getVehicleSold().getVehicleType() + "|" + contract.getVehicleSold().getColor() + "|"
                        + contract.getVehicleSold().getOdometer() + "|" + contract.getVehicleSold().getPrice() + "|"
                        // sales tax|recording fee|processing fee|total cost|finance|monthly payment
                        + ((SalesContract) contract).getSalesTaxAmount() + "|"
                        + ((SalesContract) contract).getRecordingFee() + "|"
                        + ((SalesContract) contract).getProcessingFee() + "|"
                        + contract.getTotalPrice() + "|" +((SalesContract) contract).isFinanceOption() + "|"
                        + contract.getMonthlyPayment() + "|");
                bw.newLine();
                System.out.println("Sales contract has been successfully saved to contracts.csv." );

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (contract instanceof LeaseContract){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("contracts.csv", true))) {
                // Write lease contract information:
                bw.write(//Type"LEASE"|contract date|name|email|
                        "LEASE" + "|" + contract.getDateOfContract()+ "|"
                        + contract.getCustomerName() + "|" + contract.getCustomerEmail() + "|"
                        //vehicleSold information: car id|year|make|model|vehicle type|color|odometer|price|
                        + contract.getVehicleSold().getVin() + "|" + contract.getVehicleSold().getYear() + "|"
                        + contract.getVehicleSold().getMake() + "|" + contract.getVehicleSold().getModel() + "|"
                        + contract.getVehicleSold().getVehicleType() + "|" + contract.getVehicleSold().getColor() + "|"
                        + contract.getVehicleSold().getOdometer() + "|" + contract.getVehicleSold().getPrice() + "|"
                        // ending value|lease fee|total cost|monthly payment
                        + ((LeaseContract) contract).getExpectedEndingValue() + "|"
                        + ((LeaseContract) contract).getLeaseFee() + "|"
                        + contract.getTotalPrice() + "|" + contract.getMonthlyPayment() + "|");
                bw.newLine();
                System.out.println("Lease contract has been successfully saved to contracts.csv.");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("Invalid contract type.");
        }

    }

}
