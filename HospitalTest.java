package com.hospital;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HospitalTest {

    @Test

    public void testRegisterPatient() {

        Hospital hospital = new Hospital();

        Patient patient = new Patient(

                "P001",

                "Thando",

                "Maseko",

                35,

                "Female",

                "Fever",

                PatientCategory.OUTPATIENT

        );

        assertTrue(hospital.registerPatient(patient));

    }

    @Test

    public void testSearchPatient() {

        Hospital hospital = new Hospital();

        Patient patient = new Patient(

                "P002",

                "John",

                "Smith",

                30,

                "Male",

                "Flu",

                PatientCategory.OUTPATIENT

        );

        hospital.registerPatient(patient);

        assertNotNull(hospital.searchPatient("P002"));

    }

    @Test

    public void testUpdatePatient() {

        Hospital hospital = new Hospital();

        Patient patient = new Patient(

                "P003",

                "Jane",

                "Smith",

                25,

                "Female",

                "Cold",

                PatientCategory.OUTPATIENT

        );

        hospital.registerPatient(patient);

        boolean result = hospital.updatePatient(

                "P003",

                "Jane",

                "Brown",

                26,

                "Female",

                "Fever",

                PatientCategory.OUTPATIENT

        );

        assertTrue(result);

    }

    @Test

    public void testDeletePatient() {

        Hospital hospital = new Hospital();

        Patient patient = new Patient(

                "P004",

                "Mike",

                "Jones",

                40,

                "Male",

                "Flu",

                PatientCategory.OUTPATIENT

        );

        hospital.registerPatient(patient);

        assertTrue(hospital.deletePatient("P004"));

    }

    @Test

    public void testAllocateBed() {

        Hospital hospital = new Hospital();

        Inpatient patient = new Inpatient(

                "P005",

                "Sarah",

                "Jones",

                28,

                "Female",

                "Fever",

                PatientCategory.INPATIENT,

                "Ward 1",

                ""

        );

        hospital.registerPatient(patient);

        assertTrue(hospital.allocateBed("P005", "B01"));

    }

    @Test

    public void testReleaseBed() {

        Hospital hospital = new Hospital();

        Inpatient patient = new Inpatient(

                "P006",

                "David",

                "Brown",

                35,

                "Male",

                "Flu",

                PatientCategory.INPATIENT,

                "Ward 1",

                ""

        );

        hospital.registerPatient(patient);

        hospital.allocateBed("P006", "B01");

        assertTrue(hospital.releaseBed("B01"));

    }

    @Test

    public void testDuplicatePatientId() {

        Hospital hospital = new Hospital();

        Patient patient1 = new Patient(

                "P007",

                "John",

                "Smith",

                30,

                "Male",

                "Flu",

                PatientCategory.OUTPATIENT

        );

        Patient patient2 = new Patient(

                "P007",

                "Peter",

                "Jones",

                40,

                "Male",

                "Cold",

                PatientCategory.OUTPATIENT

        );

        assertTrue(hospital.registerPatient(patient1));

        assertFalse(hospital.registerPatient(patient2));

    }

    @Test

    public void testOccupiedBed() {

        Hospital hospital = new Hospital();

        Inpatient patient1 = new Inpatient(

                "P008",

                "Anna",

                "Smith",

                30,

                "Female",

                "Flu",

                PatientCategory.INPATIENT,

                "Ward 1",

                ""

        );

        Inpatient patient2 = new Inpatient(

                "P009",

                "Mary",

                "Jones",

                35,

                "Female",

                "Cold",

                PatientCategory.INPATIENT,

                "Ward 1",

                ""

        );

        hospital.registerPatient(patient1);

        hospital.registerPatient(patient2);

        hospital.allocateBed("P008", "B01");

        assertFalse(hospital.allocateBed("P009", "B01"));

    }

    @Test

    public void testAllBedsOccupied() {

        Hospital hospital = new Hospital();

        for (int i = 1; i <= 20; i++) {

            String patientId = String.format("P%03d", i);

            String bedNumber = String.format("B%02d", i);

            Inpatient patient = new Inpatient(

                    patientId,

                    "Test",

                    "Patient",

                    30,

                    "Male",

                    "Flu",

                    PatientCategory.INPATIENT,

                    "Ward 1",

                    ""

            );

            hospital.registerPatient(patient);

            assertTrue(hospital.allocateBed(patientId, bedNumber));

        }

        Inpatient extraPatient = new Inpatient(

                "P021",

                "Extra",

                "Patient",

                30,

                "Male",

                "Flu",

                PatientCategory.INPATIENT,

                "Ward 1",

                ""

        );

        hospital.registerPatient(extraPatient);

        assertFalse(hospital.allocateBed("P021", "B01"));

    }

    @Test

    public void testSortPatientsBySurname() {

        Hospital hospital = new Hospital();

        Patient patient1 = new Patient(

                "P010",

                "John",

                "Zulu",

                30,

                "Male",

                "Flu",

                PatientCategory.OUTPATIENT

        );

        Patient patient2 = new Patient(

                "P011",

                "Peter",

                "Adams",

                30,

                "Male",

                "Cold",

                PatientCategory.OUTPATIENT

        );

        hospital.registerPatient(patient1);

        hospital.registerPatient(patient2);

        hospital.sortPatientsBySurname();

        assertNotNull(hospital.searchPatient("P010"));

        assertNotNull(hospital.searchPatient("P011"));

    }

}

