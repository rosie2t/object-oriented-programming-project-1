/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it2021056;

/**
 *
 * @author rosa manoli
 */

import java.time.LocalDateTime;
import java.util.List;

public class Parking {
    private Vehicle vehicle;
    private Driver driver;
    private List<ParkingArea> occupiedSlots;
    private LocalDateTime startTime;
    private int duration; 
    private double cost;
    
    //Constructor - Αρχικοποιήση της κλάσης Parking
    public Parking(Vehicle vehicle, Driver driver, List<ParkingArea> occupiedSlots, LocalDateTime startTime, int duration) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.occupiedSlots = occupiedSlots;
        this.startTime = startTime;
        this.duration = duration;
        this.cost = calculateCost(); 
    }
    
    //Getters and Setters
    public Vehicle getVehicle() {
        return vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public List<ParkingArea> getOccupiedSlots() {
        return occupiedSlots;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }

    //Μέθοδος για τον υπολογισμό του κόστους 
    public double calculateCost() {
        if (duration <= 3) {
            return 5.0;
        } else if (duration <= 8) {
            return 8.0;
        } else if (duration <= 23) {
            return 12.0;
        } else {
            return 15.0; // Για 24 ώρες
        }
    }
    
    @Override
    public String toString() {
        return "Στάθμευση για όχημα " + vehicle.getLicensePlate() +
           ", οδηγός: " + driver.getFirstName() + " " + driver.getLastName() +
           ", διάρκεια: " + duration + " ώρες, κόστος: " + cost + "€, θέσεις: " +
           occupiedSlots.stream().map(slot -> String.valueOf(slot.getSlotNumber())).toList();
}
    public String toFileString() {
        return vehicle.getLicensePlate() + "," +
           driver.getPhone() + "," +
           startTime.toString() + "," +
           duration + "," +
           cost;
}

}
