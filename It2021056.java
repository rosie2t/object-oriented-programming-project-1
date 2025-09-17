/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.it2021056;

/**
 *
 * @author rosa manoli
 */
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDateTime;
import java.util.List;

public class It2021056 {

    public static void main(String[] args) {
       ParkingLot parkingLot = new ParkingLot();
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        //Εισαγωγή δεδομένων ενός σεναρίου/demo για έλεγχο
        Driver demoDriver = parkingLot.findOrCreateDriver("Γιάννης", "Παπαδόπουλος", "6900000001");
        Vehicle demoCar = parkingLot.findOrCreateVehicle("ΙΚΑ1234", "Βενζίνη", demoDriver, 0, "");
        LocalDateTime demoTime = LocalDateTime.now().minusHours(2); //Στάθμευση πριν 2 ώρες
        List<ParkingArea> demoSlots = parkingLot.findAvailableSlots(demoCar).subList(0, 1);
        demoSlots.get(0).occupySlot(demoCar, demoTime);
        parkingLot.parkings.add(new Parking(demoCar, demoDriver, demoSlots, demoTime, 2));

        //Μενού Εφαρμογής
        do {
            System.out.println("\nΣύστημα Διαχείρισης Χώρου Στάθμευσης");
            System.out.println("1. Στάθμευση Οχήματος");
            System.out.println("2. Αποχώρηση Οχήματος");
            System.out.println("3. Εμφάνιση Διαθέσιμων Θέσεων");
            System.out.println("4. Εμφάνιση Κατειλημμένων Θέσεων");
            System.out.println("5. Αναζήτηση Ιστορικού Στάθμευσης Οδηγού (με κινητό)");
            System.out.println("6. Αναζήτηση Ιστορικού Οχήματος (με πινακίδα)");
            System.out.println("7. Συνολική Αναφορά Κατάστασης Θέσεων");
            System.out.println("0. Έξοδος");
            System.out.print("Επιλέξτε μια λειτουργία: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Μη έγκυρη είσοδος. Παρακαλώ εισάγετε έναν αριθμό.");
                scanner.nextLine(); 
                choice = -1; 
                continue;
            }

            switch (choice) {
                case 1:
                    parkingLot.parkVehicle(scanner);
                    break;
                case 2:
                    parkingLot.unparkVehicle(scanner);
                    break;
                case 3:
                    parkingLot.displayAvailableSlots();
                    break;
                case 4:
                    parkingLot.displayOccupiedSlots();
                    break;
                case 5:
                    parkingLot.searchByDriverPhone(scanner);
                    break;
                case 6:
                    parkingLot.searchByLicensePlate(scanner);
                    break;
                case 7:
                    parkingLot.showSlotSummary();
                    break;
                case 0:
                    parkingLot.saveParkingStatusToFile();
                    System.out.println("Αποθήκευση και έξοδος από το πρόγραμμα.");

                    break;
                default:
                    System.out.println("Μη έγκυρη επιλογή. Παρακαλώ προσπαθήστε ξανά.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
