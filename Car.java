/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it2021056;

/**
 *
 * @author rosa manoli
 */
public class Car extends Vehicle {
    //Χαρακτηριστικά Οχήματος-Αυτοκίνητο 
    public Car(String licensePlate, String fuelType, Driver driver) {
        super(licensePlate, 1, fuelType, driver); //Το αυτοκίνητο έχει μέγεθος 1
    }

    @Override
    public String toString() {
        return "Αυτοκίνητο: " + super.toString();
    }
}
