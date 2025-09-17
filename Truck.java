/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it2021056;

/**
 *
 * @author rosa manoli
 */
public class Truck extends Vehicle {
    //Χαρακτηριστικά Οχήματος-Φορτηγό
    private double length;
    private String useType; //Είδος χρήσης του φορτηγού 
    
    public Truck(String licensePlate, String fuelType, Driver driver, double length, String useType) {
        super(licensePlate, 2, fuelType, driver); //Το φορτηγό έχει μέγεθος 2
        this.length = length;

        if (useType.equals("ΤΡΟΦΙΜΑ") || useType.equals("ΑΝΤΙΚΕΙΜΕΝΑ") || useType.equals("ΠΑΡΑΓΓΕΛΙΕΣ")) {
            this.useType = useType;
        } else {
            System.out.println("Λάθος δεδομένα εισχώρησης!");
            //Μη έγκυρος τύπος χρήσης φορτηγού
        }
    }

    // Getters and Setters 
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getUseType() {
        return useType;
    }

    //Μέθοδος για επιλογή χρήσης φορτηγού
    public void setUseType(String useType) {
        if (useType.equals("ΤΡΟΦΙΜΑ") || useType.equals("ΑΝΤΙΚΕΙΜΕΝΑ") || useType.equals("ΠΑΡΑΓΓΕΛΙΕΣ")) {
            this.useType = useType;
        } else {
            throw new IllegalArgumentException("Μη έγκυρο είδος χρήσης φορτηγού.");
        }
    }

    @Override
    public String toString() {
        return "Φορτηγό: " + super.toString() + ", Μήκος: " + length + ", Χρήση: " + useType;
    }
}
