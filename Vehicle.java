/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it2021056;

/**
 *
 * @author rosa manoli
 */
public class Vehicle {
    //Χαρακτηριστικά Οχήματος
    private String licensePlate;
    private int size;
    private String fuelType; //Βενζίνη, Πετρέλαιο, Ηλεκτρικό
    private Driver driver;
    
    //Constructor - Αρχικοποιήση της κλάσης Vehicle
    public Vehicle(String licensePlate, int size, String fuelType, Driver driver) {
        this.licensePlate = licensePlate;
        this.size = size;
        this.fuelType = fuelType;
        this.driver = driver;
    }
    
    //Getters και Setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    @Override
    public String toString() {
        return "Όχημα: " + licensePlate + ", Μέγεθος: " + size + ", Καύσιμο: " + fuelType + ", Οδηγός: " + driver;
    }
}
