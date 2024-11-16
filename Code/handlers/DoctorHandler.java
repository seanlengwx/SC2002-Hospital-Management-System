package handlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import interfaces.*;
import resources.*;
import userclasses.*;
/**
 * DoctorHandler class, the logic implementation for Doctor class
 */
public class DoctorHandler implements IDoctorHandler {

    private IStaffHandler staffHandler;
    private IPrescriptionHandler prescriptionHandler; 

    /**
     * DoctorHandler Constructor
     * @param staffHandler  manager responsible for staff-related logic
     * @param prescriptionHandler manager responsible for prescription-related logic
     */
    public DoctorHandler(IStaffHandler staffHandler, IPrescriptionHandler prescriptionHandler) {
        this.staffHandler = staffHandler;
        this.prescriptionHandler = prescriptionHandler;
    }

    /**
     * setting StaffHandler (to prevent cyclic in Main)
     * @param sm set staffHandler
     */
    public void setStaffHandler(IStaffHandler sm){
        this.staffHandler = sm;
    }

    /**
     * setting PrescriptionHandler (to prevent cyclic in Main)
     * @param pm set prescritpionHandler
     */
    public void setPrescriptionHandler(IPrescriptionHandler pm) {
        this.prescriptionHandler = pm;
    }

    
    /**
     * get all the doctors from the list
     * @return a seperate doctorList so operations wont tamper the original information accidentally
     */
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        for (Staff staff : staffHandler.getAllStaff()) {
            if (staff instanceof Doctor) {
                doctorList.add((Doctor) staff);
            }
        }
        return doctorList;  // copy to prevent direct modification
    }
    
    /**
     * Retrieves patient record through patient Identifier
     * @param doctor to ensure that the doctor has the authorization to view the record
     * @param patientIdentifier Identifier to retrieve the patient medical records
     */
    public void viewPatientRecord(Doctor doctor, String patientIdentifier) {
        if (!doctor.getAssignedPatientIdentifiers().contains(patientIdentifier)) {
            System.out.println("Access denied: Patient is not under your care.");
            return;
        }
        MedicalRecord record = MedicalRecord.getRecordByPatientIdentifier(patientIdentifier);
        if (record != null) {
            System.out.println("Medical Record for Patient Identifier: " + patientIdentifier);
            record.viewMedicalRecord();
        } else {
            System.out.println("Error: No medical record found.");
        }
    }

    /**
     * Method to retrieve doctor by doctorIdentifier
     * @param doctorId Identifier to retrieve doctor by
     */
    public Doctor findDoctorById(String doctorId) {
        for (Doctor doctor : getAllDoctors()) {  
            if (doctor.getUserId().equalsIgnoreCase(doctorId)) {
                return doctor;
            }
        }
        System.out.println("Error: Doctor:" + doctorId + " not found.");
        return null;
    }
    
    /**
     * Method to check whether if a timeslot is available for a specific doctor
     * @param doctor the doctor that has the available timeslot
     * @param timeSlot the chosen timeslot to check for availability 
     * @return whether if the timeslot is available or not
     */
    public boolean isAvailable(Doctor doctor, TimeSlot timeSlot) {
        return doctor.isAvailable(timeSlot);
    }

    /**
     * Method to get a list of available timeslot based on a specified doctor
     * @param doctor the specified doctor for get availability from
     * @return a list of avaiable timeslot
     */
    public List<TimeSlot> getAvailability(Doctor doctor) {
        return doctor.getAvailability(); 
    }
    

    /**
     * Assign a patient to doctor
     * @param doctor the doctor that patient is going to be assigned to
     * @param patientIdentifier the patient to be assigned
     */
    public void assignPatient(Doctor doctor, String patientIdentifier) {
        if (!doctor.getAssignedPatientIdentifiers().contains(patientIdentifier)) {
            doctor.getAssignedPatientIdentifiers().add(patientIdentifier);
            System.out.println("Notice: Patient Identifier " + patientIdentifier + " assigned to Dr. " + doctor.getName());
        } else {
            System.out.println("Error: Patient Identifier " + patientIdentifier + " is already assigned to Dr. " + doctor.getName());
        }
    }
    
    /**
     * Adds an appointment to the doctor's schedule
     * @param doctor the doctor to which the appointment is being added
     * @param appointment the appointment that is going to be added
     */
    public void addAppointment(Doctor doctor, Appointment appointment) {
        if (!doctor.getAppointments().contains(appointment)) {
            doctor.getAppointments().add(appointment);
            System.out.println("Notice: Appointment added to Dr. " + doctor.getName() + "'s schedule.");
        } else {
            System.out.println("Error: Appointment already scheduled.");
        }
    }

    /**
     * Removes an appointment from the doctor's schedule
     * @param doctor the doctor which the appointment is being removed
     * @param appointment the appointment that is going to be removed
     */
    public void removeAppointment(Doctor doctor, Appointment appointment) {
        if (doctor.getAppointments().remove(appointment)) {
            
        } else {
            System.out.println("Error: Appointment not found schedule.");
        }
    }

    /**
     * Retrieve the list of patient that is assigned to the doctor
     * @param doctor the doctor which the patients are assigned to
     * @return a list of patient that the doctor is assigned to
     */
    public List<String> getAssignedPatientIdentifiers(Doctor doctor) {
        return new ArrayList<>(doctor.getAssignedPatientIdentifiers()); 
    }

    /**
     * Adds a diagnosis to a patient's medical record
     * @param patientIdentifier the Identifier of the patient that the diagnosis is being added to
     * @param diagnosisIdentifier the Identifier of diagnosis
     * @param details the information of the diagnosis
     */
    public void addDiagnosis(String patientIdentifier, String diagnosisIdentifier, String details) {
        MedicalRecord record = MedicalRecord.getRecordByPatientIdentifier(patientIdentifier);
        if (record != null) {
            Diagnosis diagnosis = new Diagnosis(diagnosisIdentifier, details, LocalDate.now());
            record.addDiagnosis(diagnosis);
        } else {
            System.out.println("Error: Patient record not found.");
        }
    }
    
    /**
     * Adds a treatment to a patient's medical record
     * @param patientIdentifier the Identifier of the patient that the treatment is being added to
     * @param treatmentIdentifier the Identifier of treatment
     * @param details the information of treatment
     */
    public void addTreatment(String patientIdentifier, String treatmentIdentifier, String details) {
        MedicalRecord record = MedicalRecord.getRecordByPatientIdentifier(patientIdentifier);
        if (record != null) {
            Treatment treatment = new Treatment(treatmentIdentifier, details, LocalDate.now());
            record.addTreatment(treatment);
            System.out.println("Notice: Treatment added successfully.");
        } else {
            System.out.println("Error: Patient record not found.");
        }
    }
    
    /**
     * Adds a prescription to a patient's medical record
     * @param patientIdentifier the Identifier of patient that the treatment is being added to
     * @param prescriptionIdentifier the Identifier of prescription
     * @param medicineHandler manager responsible for medicine-related logic
     */
    public void addPrescription(String patientIdentifier, String prescriptionIdentifier, IMedicineHandler medicineHandler) {
        Scanner scanner = new Scanner(System.in);
        List<Medicine> selectedMedicines = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
    
        medicineHandler.displayInventory();  
    
        while (true) {
            System.out.print("Enter the index of the medicine to add (or -1 to finish): ");
            int index;
            try {
                index = scanner.nextInt();
                scanner.nextLine();  
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                scanner.nextLine();  
                continue;
            }
    
            if (index == -1) {
                break;  
            }
            
            List<Medicine> inventory = medicineHandler.getInventory();
            if (index >= 0 && index < inventory.size()) {
                Medicine selectedMedicine = inventory.get(index);
                System.out.print("Enter quantity for " + selectedMedicine.getName() + ": ");
                int quantity;
                try {
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    if (quantity <= 0) {
                        System.out.println("Error: Quantity must be a positive number.");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter a valid quantity.");
                    scanner.nextLine();
                    continue;
                }
    
                selectedMedicines.add(selectedMedicine);
                quantities.add(quantity);
                System.out.println("Added: " + selectedMedicine.getName() + " (Quantity: " + quantity + ")");
            } else {
                System.out.println("Error: Invalid index. Please select a valid medicine from the inventory.");
            }
        }
    
        if (selectedMedicines.isEmpty()) {
            System.out.println("Error: No medicine selected. Prescription not created.");
            return;
        }
    
        Prescription prescription = new Prescription(prescriptionIdentifier, selectedMedicines, quantities, "Pending");
        MedicalRecord record = MedicalRecord.getRecordByPatientIdentifier(patientIdentifier);
        if (record != null) {
            record.addPrescription(prescription);
            System.out.println("Notice: Prescription added successfully to patient record.");
    
            if (prescriptionHandler != null) {
                prescriptionHandler.addPrescription(prescription);
                System.out.println("Notice: Prescription added to PrescriptionHandler for pharmacist access.");
            } else {
                System.out.println("Error: PrescriptionHandler is not initialized.");
            }
        } else {
            System.out.println("Error: Patient record not found.");
        }
    }
}