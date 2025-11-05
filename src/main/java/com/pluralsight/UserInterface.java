package com.pluralsight;

import com.pluralsight.dealership.Contract;
import com.pluralsight.dealership.ContractFileManager;
import com.pluralsight.dealership.LeaseContract;
import com.pluralsight.dealership.SalesContract;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        init();
        boolean quit = false;
        while (!quit) {
            System.out.println("\n ---------- Menu ----------");
            System.out.println("1. Get vehicles by price");
            System.out.println("2. Get vehicles by make and model");
            System.out.println("3. Get vehicles by year");
            System.out.println("4. Get vehicles by color");
            System.out.println("5. Get vehicles by mileage");
            System.out.println("6. Get vehicles by type");
            System.out.println("7. Get all vehicles");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("10. Sell/Lease a vehicle");
            System.out.println("99. Quit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "10":
                    sellOrLeaseAVehicle();
                    break;
                case "99":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        System.out.print("Enter vehicle vin: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        System.out.print("Enter vehicle mileage: ");
        int mileage = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle type (Car, Truck, SUV, Motorcycle): ");
        String type = scanner.nextLine().trim();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);

        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        System.out.print("Enter the VIN of the vehicle you wish to remove: ");
        int vin = scanner.nextInt();

        boolean vehicleRemoved = false;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed successfully!");
                vehicleRemoved = true;
                break;
            }
        }

        if (!vehicleRemoved) {
            System.out.println("Vehicle not found. Please try again.");
            return;
        }

        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest(int vin) {

        boolean vehicleRemoved = false;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed from inventory successfully!");
                vehicleRemoved = true;
                break;
            }
        }

        if (!vehicleRemoved) {
            System.out.println("Vehicle not found. Please try again.");
            return;
        }

        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
    }

    public void sellOrLeaseAVehicle(){
        System.out.println("\n----Sell/Lease A Vehicle----");
        System.out.println("\n1. Sell "+"\n2. Lease " + "\n3. Back to Home Screen Menu" + "\nPlease enter your choice: ");
        String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    processSalesContractRequest();
                    break;
                case "2":
                    processLeaseContractRequest();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }



    }

    public void processSalesContractRequest(){
            System.out.println("Sales Contract");
            Vehicle matchedVehicle = null;
            boolean found = false;

            while (!found) {
                System.out.print("Enter vehicle VIN (Or '0' to quit): ");
                int vin = scanner.nextInt();
                scanner.nextLine();

                if (vin ==0){
                    System.out.println("Exiting Sales Contract process.");
                    return;
                }

                matchedVehicle = dealership.getVehicleByVin(vin);
                if (matchedVehicle != null) {
                    found = true;
                } else {
                    System.out.println("No matching vehicle found with VIN, lease try again. ");
                }
            }

            System.out.println("Please enter the follow information to create the contract: ");
            System.out.println("Date of contract: " +
                    "(* In format of 4 digit year followed by 2 digit month and 2 digit date: YYYYMMDD ) ");
            String dateOfContract = scanner.nextLine().trim();
            System.out.println("Customer name (First Name and last Name): ");
            String customerName = scanner.nextLine().trim();
            System.out.println("Customer email: ");
            String customerEmail = scanner.nextLine().trim();


            boolean financeOption = false;
            boolean done = false;
            while (!done) {
                System.out.println("\n Does customer want to finance (1: Yes / 2: No) " + "\nPlease enter your choice: ");
                String input = scanner.nextLine().trim();
                switch (input) {
                    case "1":
                        financeOption = true;
                        System.out.println("You have selected financing option.");
                        done = true;
                        break;
                    case "2":
                        done = true;
                        System.out.println("You have selected NO loan option.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            Contract salesContract = new SalesContract(dateOfContract, customerName,
                    customerEmail, matchedVehicle, financeOption);

            System.out.println(salesContract);
            ContractFileManager contractManager = new ContractFileManager();
            contractManager.saveContract(salesContract);
            processRemoveVehicleRequest(matchedVehicle.getVin());


    }


    public void processLeaseContractRequest(){

            System.out.println("Lease Contract");

            Vehicle matchedVehicle = null;
            boolean found = false;
            while (!found){
                System.out.print("Enter vehicle VIN (Or '0' to quit):");
                int vin = scanner.nextInt();
                scanner.nextLine();

                if (vin ==0){
                    System.out.println("Exiting Lease Contract process.");
                    return;
                }

                matchedVehicle = dealership.getVehicleByVin(vin);
                if (matchedVehicle != null){
                    int numberOfYear = LocalDate.now().getYear() - matchedVehicle.getYear();
                    if (numberOfYear <= 3){
                        found = true;
                    }else {
                        System.out.println("This vehicle is not available for lease, as it is over 3 years old. " +
                                "Please enter another vehicle VIN.");
                    }

                }else {
                    System.out.println("No matching vehicle found with VIN, lease try again.");
                }
            }

            System.out.println("Please enter the follow information to create the contract: ");
            System.out.println("Date of contract: " +
                    "(* In format of 4 digit year followed by 2 digit month and 2 digit date: YYYYMMDD ) ");
            String dateOfContract = scanner.nextLine().trim();
            System.out.println("Customer name (First Name and last Name): ");
            String customerName = scanner.nextLine().trim();
            System.out.println("Customer email: ");
            String customerEmail = scanner.nextLine().trim();

            Contract leaseContract = new LeaseContract(dateOfContract, customerName,
                    customerEmail, matchedVehicle);

            System.out.println(leaseContract);
            ContractFileManager contractManager = new ContractFileManager();
            contractManager.saveContract(leaseContract);
            processRemoveVehicleRequest(matchedVehicle.getVin());

    }


    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        dealership = manager.getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }


}
