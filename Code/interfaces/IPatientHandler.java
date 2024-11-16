package interfaces;

import java.util.Collection;

import userclasses.*;
/**
 * An interface class managing patient related operations
 */
public interface IPatientHandler {

    /**
     * Adds a new patient to the system
     * @param newPatient the patient to be added
     */
    void addPatient(Patient newPatient);

    /**
     * Finds a patient based on their Identifier
     * @param patientIdentifier the Identifier of patient to be found
     * @return the patient with the specified Identifier
     */
    Patient findPatientById(String patientIdentifier);

    /**
     * set method for manager responsible for managing patient appointments
     * @param appointmentHandler the appointment manager to be set
     */
    void setAppointmentHandler(IAppointmentHandler appointmentHandler);

    /**
     * Retrieves a collection of all patient for internal use
     * @return a collection of all patient
     */
    Collection<? extends User> getAllPatientsInternal();

    /**
     * Updates a patient contact information
     * @param patient the patient to be updated
     * @param newContactInfo the new contact information to be updated
     * @param phone the phone number to be updated
     */
    void updateContactInfo(Patient patient, String newContactInfo, int phone);

    /**
     * View the medical record for the patient
     * @param patient the patient who record is being retrieved
     */
    void viewMedicalRecord(Patient patient);
}