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
import java.time.format.DateTimeFormatter;

public class ParkingArea {
    private int slotNumber;
    private String slotType; //Κανονική ή Ηλεκτρική
    private boolean isAvailable;
    private String parkedVehicleLicensePlate;
    private String parkedDriverPhone;
    private LocalDateTime startTime;
    
    //Constructor - Αρχικοποιήση της κλάσης ParkingArea
    public ParkingArea(int slotNumber, String slotType) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.isAvailable = true;
        this.parkedVehicleLicensePlate = null;
        this.parkedDriverPhone = null;
        this.startTime = null;
    }
    
    //Getters and Setters
    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getParkedVehicleLicensePlate() {
        return parkedVehicleLicensePlate;
    }

    public void setParkedVehicleLicensePlate(String parkedVehicleLicensePlate) {
        this.parkedVehicleLicensePlate = parkedVehicleLicensePlate;
    }

    public String getParkedDriverPhone() {
        return parkedDriverPhone;
    }

    public void setParkedDriverPhone(String parkedDriverPhone) {
        this.parkedDriverPhone = parkedDriverPhone;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    //Μέθοδος για έλεγχο αν η θέση είναι κατάλληλη για ένα όχημα
    public boolean isSuitableForVehicle(Vehicle vehicle) {
        if (this.slotType.equals("Ηλεκτρική") && !vehicle.getFuelType().equals("Ηλεκτρικό")) {
            return false; //Ηλεκτρική θέση δεν είναι για μη ηλεκτρικό
        }
        return true; //Αλλιώς είναι κατάλληλη
    }

    //Μέθοδος για κρατημένη θέση στάθμευσης
    public void occupySlot(Vehicle vehicle, LocalDateTime startTime) {
        this.isAvailable = false;
        this.parkedVehicleLicensePlate = vehicle.getLicensePlate();
        this.parkedDriverPhone = vehicle.getDriver().getPhone();
        this.startTime = startTime;
    }

    //Μέθοδος για απελευθέρωση θέσης
    public void vacateSlot() {
        this.isAvailable = true;
        this.parkedVehicleLicensePlate = null;
        this.parkedDriverPhone = null;
        this.startTime = null;
    }

    @Override
    public String toString() {
        if (isAvailable) {
            return "Θέση " + slotNumber + " (" + slotType + "): Ελεύθερη";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return "Θέση " + slotNumber + " (" + slotType + "): Κατειλημμένη από " + parkedVehicleLicensePlate + " (" + parkedDriverPhone + ") από " + startTime.format(formatter);
        }
    }
    
    public String toFileString() {
    if (isAvailable) {
        return slotNumber + "";
    } else {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return slotNumber + " " + parkedVehicleLicensePlate + " " + parkedDriverPhone + " " + startTime.format(formatter);
    }
}
    
}
