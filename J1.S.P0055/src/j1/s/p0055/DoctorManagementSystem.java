/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.s.p0055;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class DoctorManagementSystem {

    private List<Doctor> doctors;

    public DoctorManagementSystem() {
        this.doctors = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    addDoctor(scanner);
                    break;
                case 2:
                    updateDoctor(scanner);
                    break;
                case 3:
                    deleteDoctor(scanner);
                    break;
                case 4:
                    searchDoctor(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        } while (choice != 5);
    }

    private void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add Doctor");
        System.out.println("2. Update Doctor");
        System.out.println("3. Delete Doctor");
        System.out.println("4. Search Doctor");
        System.out.println("5. Exit");
    }

    private void addDoctor(Scanner scanner) {
        System.out.println("Add Doctor");
        String code = getStringInput(scanner, "Enter code: ");

        // Check if the code already exists
        for (Doctor doctor : doctors) {
            if (doctor.getCode().equals(code)) {
                System.out.println("Doctor code already exists. Aborting addition.");
                return;
            }
        }

        String name = getStringInput(scanner, "Enter name: ");
        String specialization = getStringInput(scanner, "Enter specialization: ");
        int availability = getIntInput(scanner, "Enter availability: ");

        Doctor newDoctor = new Doctor(code, name, specialization, availability);
        doctors.add(newDoctor);

        System.out.println("Doctor added successfully.");
    }

    private void updateDoctor(Scanner scanner) {
        System.out.println("Update Doctor");
        String code = getStringInput(scanner, "Enter code: ");

        Doctor doctorToUpdate = null;

        // Find the doctor with the given code
        for (Doctor doctor : doctors) {
            if (doctor.getCode().equals(code)) {
                doctorToUpdate = doctor;
                break;
            }
        }

        if (doctorToUpdate == null) {
            System.out.println("Doctor code does not exist.");
            return;
        }

        System.out.println("Enter new information (leave blank for no change):");

        String name = getStringInput(scanner, "Name: ");
        if (!name.isEmpty()) {
            doctorToUpdate.setName(name);
        }

        String specialization = getStringInput(scanner, "Specialization: ");
        if (!specialization.isEmpty()) {
            doctorToUpdate.setSpecialization(specialization);
        }

        String availabilityStr = getStringInput(scanner, "Availability: ");
        if (!availabilityStr.isEmpty()) {
            int availability = parsePositiveInt(availabilityStr);
            if (availability != -1) {
                doctorToUpdate.setAvailability(availability);
            } else {
                System.out.println("Invalid availability. No changes were made.");
            }
        }

        System.out.println("Doctor updated successfully.");
    }

    private void deleteDoctor(Scanner scanner) {
        System.out.println("Delete Doctor");
        String code = getStringInput(scanner, "Enter code: ");

        Doctor doctorToDelete = null;

        // Find the doctor with the given code
        for (Doctor doctor : doctors) {
            if (doctor.getCode().equals(code)) {
                doctorToDelete = doctor;
                break;
            }
        }

        if (doctorToDelete == null) {
            System.out.println("Doctor code does not exist.");
            return;
        }

        doctors.remove(doctorToDelete);
        System.out.println("Doctor deleted successfully.");
    }

    private void searchDoctor(Scanner scanner) {
        System.out.println("Search Doctor");
        String searchString = getStringInput(scanner, "Enter search string: ");

        List<Doctor> searchResults = new ArrayList<>();

        for (Doctor doctor : doctors) {
            if (doctor.getCode().contains(searchString) || doctor.getName().contains(searchString)
                    || doctor.getSpecialization().contains(searchString)) {
                searchResults.add(doctor);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No matching doctors found.");
        } else {
            System.out.println("Search results:");
            for (Doctor doctor : searchResults) {
                System.out.println(doctor);
            }
        }
    }

    private String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(Scanner scanner, String prompt) {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                choice = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return choice;
    }

    private int parsePositiveInt(String input) {
        try {
            int value = Integer.parseInt(input);
            if (value >= 0) {
                return value;
            }
        } catch (NumberFormatException e) {
            // Ignore and return -1 below
        }
        return -1;
    }
}
