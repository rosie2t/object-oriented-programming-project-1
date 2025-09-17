/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it2021056;

/**
 *
 * @author rosa manoli
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

//Constructor - Αρχικοποιήση της κλάσης ParkingLot
public class ParkingLot {
    private List<ParkingArea> parkingAreas;
    private List<Vehicle> vehicles;
    private List<Driver> drivers;
    public List<Parking> parkings;
    
    //Constructor - Αρχικοποιήση της κλάσης ParkingLot
    public ParkingLot() {
        this.parkingAreas = initializeParkingAreas();
        this.vehicles = new ArrayList<>();
        this.drivers = new ArrayList<>();
        this.parkings = new ArrayList<>();
    }
    
    //Μέθοδος για την αρχικοποίηση των θέσεων στάθμευσης
    private List<ParkingArea> initializeParkingAreas() {
        List<ParkingArea> slots = new ArrayList<>();
        //Δημιουργία 80 κανονικών θέσεων
        for (int i = 1; i <= 80; i++) {
            slots.add(new ParkingArea(i, "Κανονική"));
        }
        //Δημιουργία 20 θέσεων για τα ηλεκτρικά οχήματα
        for (int i = 81; i <= 100; i++) {
            slots.add(new ParkingArea(i, "Ηλεκτρική"));
        }
        return slots;
    }
    
    //Μέθοδος για την εύρεση ή δημιουργία οδηγού
    public Driver findOrCreateDriver(String firstName, String lastName, String phone) {
        for (Driver driver : drivers) {
            if (phone.equals(driver.getPhone())) {
        return driver;
    }
        }
        Driver newDriver = new Driver(firstName, lastName, phone);
        drivers.add(newDriver);
        return newDriver;
    }

    //Μέθοδος για την εύρεση ή δημιουργία οχήματος
    public Vehicle findOrCreateVehicle(String licensePlate, String fuelType, Driver driver, double length, String useType) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }

        Vehicle newVehicle;
        if (length > 0) {
            newVehicle = new Truck(licensePlate, fuelType, driver, length, useType);
        } else if (fuelType.equals("Ηλεκτρικό")) {
            newVehicle = new Car(licensePlate, fuelType, driver);
        } else {
            newVehicle = new Car(licensePlate, fuelType, driver); // or Motorcycle, depending on input
        }
        vehicles.add(newVehicle);
        return newVehicle;
    }
    
    //Μέθοδος για την εύρεση διαθέσιμων θέσεων για ένα όχημα
    public List<ParkingArea> findAvailableSlots(Vehicle vehicle) {
        List<ParkingArea> availableSlots = new ArrayList<>();
        for (ParkingArea slot : parkingAreas) {
            if (slot.isAvailable() && slot.isSuitableForVehicle(vehicle)) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }
    
    //Λειτουργία 1
    //Μέθοδος για τη στάθμευση ενός οχήματος
    public Parking parkVehicle(Scanner scanner) {
        System.out.println("Εισάγεται τα στοιχεία του οχήματος και του οδηγού:");
        System.out.print("Πινακίδα: ");
        String licensePlate = scanner.nextLine().toUpperCase(); //Μετατροπή σε κεφαλαία
        System.out.print("Τύπος καυσίμου (Βενζίνη/Πετρέλαιο/Ηλεκτρικό): ");
        String fuelType = scanner.nextLine();
        double length = 0;
        String useType = "";
        System.out.print("Είναι φορτηγό; (Ναι/Όχι): ");
            String isTruck = scanner.nextLine();
            if (isTruck.equalsIgnoreCase("Ναι")) {
                System.out.print("Μήκος φορτηγού (σε μέτρα): ");
                length = scanner.nextDouble();
                scanner.nextLine(); 
                do {
                System.out.print("Είδος χρήσης (ΤΡΟΦΙΜΑ/ΑΝΤΙΚΕΙΜΕΝΑ/ΠΑΡΑΓΓΕΛΙΕΣ): ");
                useType = scanner.nextLine().toUpperCase();
                if (!useType.equals("ΤΡΟΦΙΜΑ") && !useType.equals("ΑΝΤΙΚΕΙΜΕΝΑ") && !useType.equals("ΠΑΡΑΓΓΕΛΙΕΣ")) {
                    System.out.println("Μη έγκυρη τιμή. Παρακαλώ δοκιμάστε ξανά.");
                }           
              } while (!useType.equals("ΤΡΟΦΙΜΑ") && !useType.equals("ΑΝΤΙΚΕΙΜΕΝΑ") && !useType.equals("ΠΑΡΑΓΓΕΛΙΕΣ"));
            }
        System.out.print("Όνομα οδηγού: ");
        String firstName = scanner.nextLine();
        System.out.print("Επώνυμο οδηγού: ");
        String lastName = scanner.nextLine();
        System.out.print("Τηλέφωνο οδηγού: ");
        String phone = scanner.nextLine();

        //Εύρεση ή δημιουργία οδηγού και οχήματος
        Driver driver = findOrCreateDriver(firstName, lastName, phone);
        Vehicle vehicle = findOrCreateVehicle(licensePlate, fuelType, driver, length, useType);

        //Εύρεση διαθέσιμων θέσεων
        List<ParkingArea> availableSlots = findAvailableSlots(vehicle);
        int requiredSlots = vehicle.getSize();

        //Έλεγχος αν υπάρχουν αρκετές διαθέσιμες θέσεις
        if (availableSlots.size() >= requiredSlots) {
            List<ParkingArea> occupiedSlots = new ArrayList<>();
            LocalDateTime startTime = LocalDateTime.now();

            //Δέσμευση των θέσεων
            for (int i = 0; i < requiredSlots; i++) {
                ParkingArea slot = availableSlots.get(i);
                slot.occupySlot(vehicle, startTime);
                occupiedSlots.add(slot);
            }

            //Διάρκεια στάθμευσης
            System.out.print("Διάρκεια στάθμευσης (σε ώρες): ");
            int duration = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            //Έλεγχος για έγκυρη διάρκεια στάθμευσης
            if (duration > 24 || duration <= 0) {
                System.out.println("Η διάρκεια στάθμευσης πρέπει να είναι μεταξύ 1 και 24 ωρών.");
                for (ParkingArea slot : occupiedSlots) {
                slot.vacateSlot();
            }
            return null;
            }

             
        Parking parking = new Parking(vehicle, driver, occupiedSlots, startTime, duration);
            parkings.add(parking);

            System.out.println("Το όχημα σταθμεύτηκε επιτυχώς στις θέσεις: ");
            for (ParkingArea slot : occupiedSlots) {
                System.out.print(slot.getSlotNumber() + " ");
            }
            System.out.println("\nΏρα έναρξης: " + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println("Κόστος στάθμευσης: " + parking.calculateCost() + "€");
            return parking;

        } else {
            System.out.println("Δεν υπάρχουν διαθέσιμες θέσεις για το όχημα.");
            return null;
        }
    }
     
    //Λειτουργία 2
    //Μέθοδος για αποχώρηση οχήματος
    public void unparkVehicle(Scanner scanner){
        System.out.println("Εισάγετε την πινακίδα του οχήματος που αποχωρεί: ");
        String licensePlate = scanner.nextLine().toUpperCase();

        Parking parkingToRemove = null;
        //Εύρεση της στάθμευσης με βάση την πινακίδα
        for (Parking parking : parkings) {
            if (parking.getVehicle().getLicensePlate().equals(licensePlate)) {
                parkingToRemove = parking;
                break;
            }
        }

        //Αν βρεθεί η στάθμευση να απελευθερωθούν οι θέσεις και να αφαιρεθούν από τη λίστα
        if (parkingToRemove != null) {
            System.out.println("\nΑπόδειξη Στάθμευσης");
            System.out.println("Πινακίδα: " + licensePlate);
            System.out.println("Οδηγός: " + parkingToRemove.getDriver().getFirstName() + " " + parkingToRemove.getDriver().getLastName());
            System.out.println("Ώρα άφιξης: " + parkingToRemove.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println("Διάρκεια: " + parkingToRemove.getDuration() + " ώρες");
            System.out.println("Ποσό χρέωσης: " + parkingToRemove.getCost() + "€");   
            for (ParkingArea slot : parkingToRemove.getOccupiedSlots()) {
                slot.vacateSlot();
            }
            System.out.println("Το όχημα με πινακίδα " + licensePlate + " αποχώρησε.");
        } else {
            System.out.println("Δεν βρέθηκε όχημα με πινακίδα " + licensePlate);
        }
    }

    //Μέθοδος για την εμφάνιση των διαθέσιμων θέσεων
    public void displayAvailableSlots() {
        System.out.println("Διαθέσιμες Θέσεις:");
        for (ParkingArea slot : parkingAreas) {
            if (slot.isAvailable()) {
                System.out.println(slot);
            }
        }
    }

    //Μέθοδος για την εμφάνιση των κατειλημμένων θέσεων
    public void displayOccupiedSlots() {
        System.out.println("Κατειλημμένες Θέσεις:");
        for (ParkingArea slot : parkingAreas) {
            if (!slot.isAvailable()) {
                System.out.println(slot);
            }
        }
    }
    
    //Λειτουργία 3
    //Μέθοδος για την αναζήτηση πληροφοριών και αναφορές
    //Με βάση το τηλέφωνο του οδηγού
    public void searchByDriverPhone(Scanner scanner) {
    System.out.print("Εισάγετε το κινητό τηλέφωνο του οδηγού: ");
    String phone = scanner.nextLine();

    boolean found = false;
    for (Parking parking : parkings) {
        if (parking.getDriver().getPhone().equals(phone)) {
            System.out.println("Πινακίδα: " + parking.getVehicle().getLicensePlate() +
                               " | Χρέωση: " + parking.getCost() + "€");
            found = true;
        }
    }
    if (!found) {
        System.out.println("Δεν βρέθηκε ιστορικό για αυτόν τον οδηγό.");
    }
}
    
    //Με βάση τον τύπο της θέσης
    public void showSlotSummary() {
    int normalFree = 0, normalOccupied = 0;
    int electricFree = 0, electricOccupied = 0;

    for (ParkingArea slot : parkingAreas) {
        if (slot.getSlotType().equals("Κανονική")) {
            if (slot.isAvailable()) normalFree++;
            else normalOccupied++;
        } else {
            if (slot.isAvailable()) electricFree++;
            else electricOccupied++;
        }
    }

    System.out.println("Κανονικές θέσεις: " + normalFree + " ελεύθερες / " + normalOccupied + " κατειλημμένες");
    System.out.println("Ηλεκτρικές θέσεις: " + electricFree + " ελεύθερες / " + electricOccupied + " κατειλημμένες");
}
    
    //Με βάση την πινακίδα του οχήματος
    public void searchByLicensePlate(Scanner scanner) {
    System.out.print("Εισάγετε την πινακίδα του οχήματος: ");
    String plate = scanner.nextLine().toUpperCase();

    boolean found = false;
    for (Parking parking : parkings) {
        if (parking.getVehicle().getLicensePlate().equals(plate)) {
            System.out.println("Ημερομηνία Έναρξης: " + parking.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                               " | Χρέωση: " + parking.getCost() + "€");
            found = true;
        }
    }

    if (!found) {
        System.out.println("Δεν βρέθηκε ιστορικό για αυτό το όχημα.");
    }
}
    //λειτουργία 4
    //Μέθοδος για αποθήκευση σε αρχείο
    public void saveParkingStatusToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("parking_status.txt"))) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        for (ParkingArea slot : parkingAreas) {
            if (slot.isAvailable()) {
                writer.write(slot.getSlotNumber() + "\n");
            } else {
                writer.write(slot.getSlotNumber() + " " +
                             slot.getParkedVehicleLicensePlate() + " " +
                             slot.getParkedDriverPhone() + " " +
                             slot.getStartTime().format(formatter) + "\n");
            }
        }

        System.out.println("Η κατάσταση των θέσεων αποθηκεύτηκε στο αρχείο.");
    } catch (IOException e) {
        System.out.println("Σφάλμα κατά την αποθήκευση των δεδομένων: " + e.getMessage());
    }
}
}
