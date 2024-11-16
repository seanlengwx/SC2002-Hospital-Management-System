package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import userclasses.*;
import interfaces.*;
/**
 * A class made just to import the information of the CSV files for this project.
 * Medicine_List.csv, Patient_List.csv, Staff_List.csv
 * The files are indicated in Main.java
 */
public class CSVImport {
    
    /**
     * Default constructor for CSVImportManager.
     */
    public CSVImport() {
    }
    
    /**
     * Method to import staff information from the CSV file and initialize them
     * @param filePath the filepath to the CSV
     * @param staffManager the manager responsible for handling staff-related operations
     * @param medicineManager the manager responsible for handling medicine-related operations
     * @param pharmacistManager the manager responsible for handling pharmacist-related operations
     * @param doctorManager the manager responsible for handling doctor-related operations
     * @param prescriptionManager the manager responsible for handling prescription-related operations
     */
    public static void importStaffData(String filePath, IStaffHandler staffManager, IMedicineHandler medicineManager, IPharmacistHandler pharmacistManager, IDoctorHandler doctorManager, IPrescriptionHandler prescriptionManager) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 6) {
                    String userId = data[0];
                    String name = data[1];
                    String password = data[2];
                    String role = data[3];
                    String gender = data[4];
                    int age = Integer.parseInt(data[5]);

                    Staff newStaff;
                    if ("Doctor".equalsIgnoreCase(role)) {
                        newStaff = new Doctor(userId, password, name, gender, role, age, doctorManager);
                    } else if ("Pharmacist".equalsIgnoreCase(role)) {
                        newStaff = new Pharmacist(userId, password, name, gender, role, age, pharmacistManager, prescriptionManager);
                    } else  {
                        newStaff = new Administrator(userId, password, name, gender, role, age, staffManager, medicineManager);
                    }
                    staffManager.addStaff(newStaff);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + filePath + ".");
        }
    }

    /**
     * Method to import patient information from the CSV and initialize them
     * @param filePath the filepath to the CSV 
     * @param patientManager the manager responsible for handling patient-related operations
     * @param appointmentManager the manager responsible for handling appointment-related operations
     */
    public static void importPatientData(String filePath, IPatientHandler patientManager, IAppointmentHandler appointmentManager) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 6) {
                    String userId = data[0];
                    String password = data[1];
                    String name = data[2];
                    LocalDate dateOfBirth = LocalDate.parse(data[3]); 
                    String gender = data[4];
                    String bloodType = data[5];
                    String contactInfo = data[6];

                    Patient newPatient;
                    newPatient = new Patient(userId, password, name, gender, dateOfBirth, bloodType, contactInfo, 0, patientManager, appointmentManager);

                    patientManager.addPatient(newPatient);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found:" + filePath + ".");
        }
    }

    /**
     * Method to import medicine information from the CSV and initialize them
     * @param filePath the filepath to the CSV
     * @param medicineManager the manager responsible for handling medicine-related operations
     */
    public static void importMedicineData(String filePath, IMedicineHandler medicineManager) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 3) {
                    String name = data[0];
                    int stock = Integer.parseInt(data[1]);
                    int alertLevel = Integer.parseInt(data[2]);

                    medicineManager.addMedicine(name, stock, alertLevel);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found:" + filePath + "'.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid data format: " + filePath);
        }
    }


}