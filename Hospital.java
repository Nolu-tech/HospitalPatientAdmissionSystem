package com.hospital;

import java.util.ArrayList;

import java.util.List;

public class Hospital {

    private List<Patient> patients;

    private String[][] beds;

    private boolean[][] occupied;

    public Hospital() {

        patients = new ArrayList<>();

        beds = new String[4][5];

        occupied = new boolean[4][5];

        int bedNumber = 1;

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                beds[row][column] = String.format("B%02d", bedNumber);

                bedNumber++;

            }

        }

    }

    public boolean registerPatient(Patient patient) {

        for (Patient p : patients) {

            if (p.getPatientId().equalsIgnoreCase(patient.getPatientId())) {

                return false;

            }

        }

        patients.add(patient);

        return true;

    }

    public Patient searchPatient(String patientId) {

        for (Patient patient : patients) {

            if (patient.getPatientId().equalsIgnoreCase(patientId)) {

                return patient;

            }

        }

        return null;

    }

    public boolean updatePatient(String patientId, String firstName,

                                 String lastName, int age, String gender,

                                 String medicalCondition,

                                 PatientCategory patientCategory) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {

            return false;

        }

        patient.setFirstName(firstName);

        patient.setLastName(lastName);

        patient.setAge(age);

        patient.setGender(gender);

        patient.setMedicalCondition(medicalCondition);

        patient.setPatientCategory(patientCategory);

        return true;

    }

    public boolean deletePatient(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {

            return false;

        }

        patients.remove(patient);

        return true;

    }

    public void displayAllPatients() {

        if (patients.isEmpty()) {

            System.out.println("No patients are registered.");

            return;

        }

        for (Patient patient : patients) {

            patient.displayDetails();

            System.out.println("-------------------------");

        }

    }

    public boolean allocateBed(String patientId, String bedNumber) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {

            return false;

        }

        if (!(patient instanceof Inpatient)) {

            return false;

        }

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                if (beds[row][column].equalsIgnoreCase(bedNumber)) {

                    if (occupied[row][column]) {

                        return false;

                    }

                    occupied[row][column] = true;

                    Inpatient inpatient = (Inpatient) patient;

                    inpatient.setWardNumber("Ward 1");

                    inpatient.setBedNumber(beds[row][column]);

                    return true;

                }

            }

        }

        return false;

    }

    public boolean releaseBed(String bedNumber) {

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                if (beds[row][column].equalsIgnoreCase(bedNumber)) {

                    if (!occupied[row][column]) {

                        return false;

                    }

                    occupied[row][column] = false;

                    return true;

                }

            }

        }

        return false;

    }

    public void displayWardLayout() {

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                System.out.print(beds[row][column]);

                if (occupied[row][column]) {

                    System.out.print("(Occupied) ");

                } else {

                    System.out.print("(Available) ");

                }

            }

            System.out.println();

        }

    }

    public void displayAvailableBeds() {

        System.out.println("Available Beds:");

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                if (!occupied[row][column]) {

                    System.out.print(beds[row][column] + " ");

                }

            }

        }

        System.out.println();

    }

    public void displayOccupiedBeds() {

        System.out.println("Occupied Beds:");

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                if (occupied[row][column]) {

                    System.out.print(beds[row][column] + " ");

                }

            }

        }

        System.out.println();

    }

    public int getTotalPatients() {

        return patients.size();

    }

    public int getTotalOccupiedBeds() {

        int count = 0;

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                if (occupied[row][column]) {

                    count++;

                }

            }

        }

        return count;

    }

    public double getOccupancyPercentage() {

        return (getTotalOccupiedBeds() / 20.0) * 100;

    }

    public void sortPatientsBySurname() {

        patients.sort((p1, p2) ->

                p1.getLastName().compareToIgnoreCase(p2.getLastName()));

    }

}