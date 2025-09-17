/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it2021056;

/**
 *
 * @author rosa manoli
 */
public class Motorcycle extends Vehicle {
    //Χαρακτηριστικά Οχήματος-Μοτοσυκλέτα
    public Motorcycle(String licensePlate, String fuelType, Driver driver) {
        super(licensePlate, 1, fuelType, driver); //Η μοτοσυκλέτα έχει μέγεθος 1
    }

    @Override
    public String toString() {
        return "Μοτοσυκλέτα: " + super.toString();
    }
}
