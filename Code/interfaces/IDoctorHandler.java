package interfaces;

import java.util.List;
import userclasses.Doctor;
import resources.TimeSlot;

/**
 * Interface for managing doctor-related operations
 */
public interface IDoctorHandler {

    /**
     * Finds a doctor by their unique Identifier
     * @param doctorIdentifier the Identifier of the doctor to find
     * @return the doctor with the specified Identifier
     */
    Doctor findDoctorById(String doctorIdentifier);

    /**
     * Retrieves a list of available time slots for a doctor.
     * @param doctor the doctor whose availability is being retrieved
     * @return a list of available time slots for the doctor
     */
    List<TimeSlot> getAvailability(Doctor doctor);

    /**
     * Checks if a specified time slot is available for a doctor.
     * @param doctor the doctor whose availability is being checked
     * @param timeSlot the time slot to check
     * @return a boolean indicating if the timeslot is available
     */
    boolean isAvailable(Doctor doctor, TimeSlot timeSlot);

    /**
     * Views a patient's medical record
     * @param doctor the doctor requesting access to the patient's record
     * @param patientIdentifier the Identifier of the patient whose record is to be viewed
     */
    void viewPatientRecord(Doctor doctor, String patientIdentifier);

    /**
     * Sets the manager responsible for managing staff-related operations.
     * @param staffHandler the staff manager to set
     */
    void setStaffHandler(IStaffHandler staffHandler);

    /**
     * Sets the manager responsible for handling prescription-related operations.
     * @param prescriptionHandler the prescription manager to set
     */
    void setPrescriptionHandler(IPrescriptionHandler prescriptionHandler);

    /**
     * Adds a diagnosis to a patient's medical record.
     * @param selectedPatientIdentifier the Identifier of the patient to add the diagnosis to
     * @param diagnosisIdentifier the Identifier of the diagnosis
     * @param details the details of the diagnosis
     */
    void addDiagnosis(String selectedPatientIdentifier, String diagnosisIdentifier, String details);

    /**
     * Adds a treatment to a patient's medical record.
     * @param selectedPatientIdentifier the Identifier of the patient to add the treatment to
     * @param treatmentIdentifier the Identifier of the treatment
     * @param details the details of the treatment
     */
    void addTreatment(String selectedPatientIdentifier, String treatmentIdentifier, String details);

    /**
     * Adds a prescription to a patient's medical record.
     * @param selectedPatientIdentifier the Identifier of the patient to add the prescription to
     * @param prescriptionIdentifier the Identifier of the prescription
     * @param medicineHandler the medicine manager responsible for handling medicine inventory
     */
    void addPrescription(String selectedPatientIdentifier, String prescriptionIdentifier, IMedicineHandler medicineHandler);

    /**
     * Retrieves a list of all doctors in the system.
     * @return a list of all doctors
     */
    List<Doctor> getAllDoctors();
}