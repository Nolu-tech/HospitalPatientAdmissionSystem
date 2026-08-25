package com.hospital;

import java.util.Scanner;

public class HospitalPatientAdmissionSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Hospital hospital = new Hospital();

        boolean running = true;

        while (running) {

            System.out.println();

            System.out.println("====================================");

            System.out.println("      MEDICARE HOSPITAL SYSTEM");

            System.out.println("====================================");

            System.out.println("1. Register Patient");

            System.out.println("2. Search Patient");

            System.out.println("3. Update Patient");

            System.out.println("4. Delete Patient");

            System.out.println("5. Display All Patients");

            System.out.println("6. Display Ward Layout");

            System.out.println("7. Display Available Beds");

            System.out.println("8. Display Occupied Beds");

            System.out.println("9. Allocate Bed");

            System.out.println("10. Release Bed");

            System.out.println("11. Reports");

            System.out.println("12. Sort Patients by Surname");

            System.out.println("0. Exit");

            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Patient ID: ");

                    String patientId = scanner.nextLine();

                    System.out.print("Enter First Name: ");

                    String firstName = scanner.nextLine();

                    System.out.print("Enter Last Name: ");

                    String lastName = scanner.nextLine();

                    System.out.print("Enter Age: ");

                    int age = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter Gender: ");

                    String gender = scanner.nextLine();

                    System.out.print("Enter Medical Condition: ");

                    String medicalCondition = scanner.nextLine();

                    System.out.println("Select Patient Category:");

                    System.out.println("1. Inpatient");

                    System.out.println("2. Outpatient");

                    System.out.println("3. Emergency");

                    System.out.print("Enter category: ");

                    int categoryChoice = scanner.nextInt();

                    scanner.nextLine();

                    PatientCategory patientCategory;

                    if (categoryChoice == 1) {

                        patientCategory = PatientCategory.INPATIENT;

                    } else if (categoryChoice == 2) {

                        patientCategory = PatientCategory.OUTPATIENT;

                    } else {

                        patientCategory = PatientCategory.EMERGENCY;

                    }

                    Patient patient;

                    if (patientCategory == PatientCategory.INPATIENT) {

                        System.out.print("Enter Ward Number: ");

                        String wardNumber = scanner.nextLine();

                        System.out.print("Enter Bed Number: ");

                        String bedNumber = scanner.nextLine();

                        patient = new Inpatient(

                                patientId,

                                firstName,

                                lastName,

                                age,

                                gender,

                                medicalCondition,

                                patientCategory,

                                wardNumber,

                                bedNumber

                        );

                    } else {

                        patient = new Patient(

                                patientId,

                                firstName,

                                lastName,

                                age,

                                gender,

                                medicalCondition,

                                patientCategory

                        );

                    }

                    if (hospital.registerPatient(patient)) {

                        System.out.println("Patient registered successfully.");

                    } else {

                        System.out.println("Patient ID already exists.");

                    }

                    break;

                case 2:

                    System.out.print("Enter Patient ID to search: ");

                    String searchId = scanner.nextLine();

                    Patient foundPatient = hospital.searchPatient(searchId);

                    if (foundPatient != null) {

                        foundPatient.displayDetails();

                    } else {

                        System.out.println("Patient not found.");

                    }

                    break;

                case 3:

                    System.out.print("Enter Patient ID to update: ");

                    String updateId = scanner.nextLine();

                    System.out.print("Enter new First Name: ");

                    String newFirstName = scanner.nextLine();

                    System.out.print("Enter new Last Name: ");

                    String newLastName = scanner.nextLine();

                    System.out.print("Enter new Age: ");

                    int newAge = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter new Gender: ");

                    String newGender = scanner.nextLine();

                    System.out.print("Enter new Medical Condition: ");

                    String newMedicalCondition = scanner.nextLine();

                    System.out.println("Select new Patient Category:");

                    System.out.println("1. Inpatient");

                    System.out.println("2. Outpatient");

                    System.out.println("3. Emergency");

                    System.out.print("Enter category: ");

                    int newCategoryChoice = scanner.nextInt();

                    scanner.nextLine();

                    PatientCategory newCategory;

                    if (newCategoryChoice == 1) {

                        newCategory = PatientCategory.INPATIENT;

                    } else if (newCategoryChoice == 2) {

                        newCategory = PatientCategory.OUTPATIENT;

                    } else {

                        newCategory = PatientCategory.EMERGENCY;

                    }

                    if (hospital.updatePatient(

                            updateId,

                            newFirstName,

                            newLastName,

                            newAge,

                            newGender,

                            newMedicalCondition,

                            newCategory)) {

                        System.out.println("Patient updated successfully.");

                    } else {

                        System.out.println("Patient not found.");

                    }

                    break;

                case 4:

                    System.out.print("Enter Patient ID to delete: ");

                    String deleteId = scanner.nextLine();

                    if (hospital.deletePatient(deleteId)) {

                        System.out.println("Patient deleted successfully.");

                    } else {

                        System.out.println("Patient not found.");

                    }

                    break;

                case 5:

                    hospital.displayAllPatients();

                    break;

                case 6:

                    hospital.displayWardLayout();

                    break;

                case 7:

                    hospital.displayAvailableBeds();

                    break;

                case 8:

                    hospital.displayOccupiedBeds();

                    break;

                case 9:

                    System.out.print("Enter Patient ID: ");

                    String allocatePatientId = scanner.nextLine();

                    System.out.print("Enter Bed Number: ");

                    String allocateBedNumber = scanner.nextLine();

                    if (hospital.allocateBed(

                            allocatePatientId,

                            allocateBedNumber)) {

                        System.out.println("Bed allocated successfully.");

                    } else {

                        System.out.println("Bed allocation failed.");

                    }

                    break;

                case 10:

                    System.out.print("Enter Bed Number to release: ");

                    String releaseBedNumber = scanner.nextLine();

                    if (hospital.releaseBed(releaseBedNumber)) {

                        System.out.println("Bed released successfully.");

                    } else {

                        System.out.println("Bed could not be released.");

                    }

                    break;

                case 11:

                    System.out.println();

                    System.out.println("========== WARD REPORT ==========");

                    System.out.println(

                            "Total Registered Patients: "

                            + hospital.getTotalPatients()

                    );

                    System.out.println(

                            "Total Occupied Beds: "

                            + hospital.getTotalOccupiedBeds()

                    );

                    System.out.println(

                            "Ward Occupancy: "

                            + hospital.getOccupancyPercentage()

                            + "%"

                    );

                    System.out.println("=================================");

                    break;

                case 12:

                    hospital.sortPatientsBySurname();

                    System.out.println("Patients sorted by surname.");

                    break;

                case 0:

                    running = false;

                    System.out.println(

                            "Thank you for using MediCare Hospital System."

                    );

                    break;

                default:

                    System.out.println(

                            "Invalid choice. Please try again."

                    );

            }

        }

        scanner.close();

    }

}