package userclasses;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import interfaces.*;
import resources.*;
/**
 * Patient class, a subclass of User and implements IUser's abstract methods
 */
public class Patient extends User implements IUser {

    private String patientIdentifier;
    private LocalDate dateOfBirth;
    private String bloodType;
    private String contactInfo;
    private int phoneNumber;
    private List<Appointment> appointments;
    private MedicalRecord medicalRecord;
    private IPatientHandler patientHandler;
    private IAppointmentHandler appointmentHandler;

    /**
     * Patient constructor
     * @param userId from superclass
     * @param password from superclass
     * @param name from superclass
     * @param gender from superclass
     * @param dateOfBirth the dateofbirth of a patient
     * @param bloodType the bloodtype of a patient
     * @param contactInfo the contactinfo (email) of a patient
     * @param phoneNumber the phonenumber of a patient
     * @param patientHandler the manager for patient-related operations
     * @param appointmentHandler the manager for appointments-related operations
     */
    public Patient(String userId, String password, String name, String gender, LocalDate dateOfBirth, String bloodType, String contactInfo, int phoneNumber, IPatientHandler patientHandler, IAppointmentHandler appointmentHandler) {
        super(userId, password, name, gender);
        this.patientIdentifier = userId;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.contactInfo = contactInfo;
        this.phoneNumber = phoneNumber;
        this.appointments = new ArrayList<>();
        this.patientHandler = patientHandler;
        this.appointmentHandler = appointmentHandler;
        
        this.medicalRecord = new MedicalRecord(this);
        MedicalRecord.addRecord(this.medicalRecord);
    }

    /**
     * get method to get a patient Identifier
     * @return patient Identifier
     */
    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    /**
     * get method to get a patient DOB
     * @return patient DOB
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * set method to set patient's DOB
     * @param dateOfBirth the patient's DOB to be set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * get method to get patient's blood type
     * @return patient's blood type
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * get method to get patient's contact info
     * @return patient's contact info
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * set method to set patient's contact info (email)
     * @param contactInfo patient's contact info (email) to be set
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    /**
     * get method to get patient's phone number
     * @return patient's phone number
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * set method to set patient's phone number
     * @param phone the number to be set as patient's phone number
     */
    public void setPhoneNumber(int phone) {
        this.phoneNumber = phone;
    }

    /**
     * get a list of appointment of a patient
     * @return a list of appointment
     */
    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    /**
     * adds an appointment to the patient's list of appointments
     * @param appointment the appointment to be added
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * removes an appointment from the patient's list of appointment
     * @param appointment the appointment to be removed
     */
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    /**
     * retrieve the patient's medical record
     * @return patient's medical record
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    ////////////////////
     
    /**
     * View the patient's medical record
     */
    public void viewMedicalRecord(){
        patientHandler.viewMedicalRecord(this);
    }
    
    /**
     * Update the patient's contact information and phone
     * @param newContactInfo the email of the patient
     * @param phone the phone of the patient
     */
    public void updateContactInfo(String newContactInfo, int phone) {
        patientHandler.updateContactInfo(this, newContactInfo, phone);
    }

    /**
     * Method to view doctor's available slots for appointment
     * @param doctor the doctor to be viewed
     */
    public void viewAvailableAppointmentSlots(Doctor doctor) {
        appointmentHandler.viewAvailableSlots(doctor);
    }

    /**
     * Method to schedule appointment with a doctor and timeslot
     * @param doctor the doctor to schedule appointment with
     * @param timeSlot the timeslot
     */
    public void scheduleAppointment(Doctor doctor, TimeSlot timeSlot) {
        appointmentHandler.scheduleAppointment(this, doctor, timeSlot);
    }

    /**
     * Reschedule an appointment
     * @param appointment the apointment to be rescheduled
     * @param newTimeSlot the timeslot to be rescheduled to
     * @param doctor the doctor associated to the appointment
     */
    public void rescheduleAppointment(Appointment appointment, TimeSlot newTimeSlot, Doctor doctor) {
        appointmentHandler.rescheduleAppointment(this, appointment, newTimeSlot, doctor);
    }

    /**
     * Cancel an appointment
     * @param appointment the appointment to be cancelled
     */
    public void cancelAppointment(Appointment appointment) {
        appointmentHandler.cancelAppointment(appointment, this);
    }

    /**
     * view all scheduled appointment for the patient
     */
    public void viewScheduledAppointments() {
        appointmentHandler.viewAppointments(this);
    }

    /**
     * view all past appointment outcome for the patient
     */
    public void viewPastAppointmentOutcomes() {
        appointmentHandler.viewAppointmentOutcome(this);
    }

    /**
     * the implementation of abstract method of IUser
     */
    public void displayMenu() {
        if (isLoggedIn()) {
            System.out.println("\n===========================");
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Logout");
        } else {
            System.out.println("Error: Not logged in.");
        }
    }
}