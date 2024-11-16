import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that manages patient related operations
 */
public class PatientHandler implements IPatientHandler {

    private List<Patient> patientList;
    private IAppointmentHandler appointmentHandler;

    /**
     * Constructor for PatientHandler
     * Construct a new patient list and assigns the appointment manager
     * @param appointmentHandler the manager used for managing appointment-related operations
     */
    public PatientHandler(AppointmentHandler appointmentHandler) {
        this.patientList = new ArrayList<>();
        this.appointmentHandler = appointmentHandler;
    }

    /**
     * set the appointment manager
     * @param am the manager to set
     */
    public void setAppointmentHandler(IAppointmentHandler am){
        this.appointmentHandler = am;
    }
    
    /**
     * Display medical record of a patient
     * @param patient the patient which medical record is being retrieved
     */
    public void viewMedicalRecord(Patient patient) {
        MedicalRecord record = MedicalRecord.getRecordByPatientIdentifier(patient.getUserId());
        if (record != null) {
            record.viewMedicalRecord();
        } else {
            System.out.println("No medical record found for Patient Identifier: " + patient.getUserId());
        }
    }

    /**
     * update a contact information of a patient
     * @param patient the patient to be updated
     * @param newContactInfo the contact information of the patient (email)
     * @param phone the phone number of the patient
     */
    public void updateContactInfo(Patient patient, String newContactInfo, int phone) {
        if (newContactInfo != null && !newContactInfo.trim().isEmpty()) {
            patient.setContactInfo(newContactInfo);
            patient.setPhoneNumber(phone);
            System.out.println("Updated contact information.");
        } else {
            System.out.println("Invalid contact information.");
        }
    }
    
    /**
     * Retrieve a list of all patient
     * @param caller the caller must be either a doctor or administrator
     * @return a list of patient
     */
    public List<Patient> getAllPatients(Staff caller) { //
    if ("Doctor".equals(caller.getRole()) || "Administrator".equals(caller.getRole())) {
        return new ArrayList<>(patientList);
    } else {
        System.out.println("Access denied. Only doctors or administrators can access all patient records.");
        return Collections.emptyList();
    }
    }

    /**
     * retrieves a list of all patients (used internally, no restrictions)
     * @return a list of patients
     */
    public List<Patient> getAllPatientsInternal() {
        return patientList;
    }

    /**
     * Finds a patient based on its Identifier
     * @param patientIdentifier the Identifier of the patient to find
     * @return the patient
     */
    public Patient findPatientById(String patientIdentifier) {
        for (Patient patient : getAllPatientsInternal()) {
            if (patient.getPatientIdentifier().equals(patientIdentifier)) {
                return patient;
            }
        }
        return null;
    }
    
    /**
     * Adds a new patient to the patient list
     * @param patient the patient to be added
     */
    public void addPatient(Patient patient) {
        if (!patientList.contains(patient)) {
            patientList.add(patient);
            //System.out.println("Patient added: " + patient.getName() + " (Identifier: " + patient.getUserId() + ")");
        }
    }
    
}