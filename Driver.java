/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.it2021056;

/**
 *
 * @author rosa manoli
 */
public class Driver {
    //Χαρακτηριστικά Οδηγού
    private String firstName;
    private String lastName;
    private String phone;
    
    //Constructor - Αρχικοποιήση της κλάσης Driver
    public Driver(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
    
    //Getters και Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "Οδηγός: " + firstName + " " + lastName + ", Τηλέφωνο: " + phone;
    }
}
