import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for patient's medical records
 */
public class MedicalRecord {

	private Patient patient;
    private List<Diagnosis> diagnoses;
    private List<Treatment> treatments;
    private List<Prescription> prescriptions;
    private List<AppointmentOutcome> pastAppointments;
    private List<LocalDateTime> entryTimestamps;

	private static List<MedicalRecord> allRecords = new ArrayList<>();

	/**
     * Constructor for MedicalRecord
     * @param patient reference to existing patient object 
     */
    public MedicalRecord(Patient patient) {
        this.patient = patient; 
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.pastAppointments = new ArrayList<>();
        this.entryTimestamps = new ArrayList<>();
    }

    /**
     * get method to get patient's name
     * @return patient's name
     */
    public String getPatientName(){
        return patient.getName();
    }

    /**
     * get method to get patient's blood type
     * @return patient's bloodtype
     */
    public String getPatientBloodType(){
        return patient.getBloodType();
    }

    /**
     * get method to get patient's contact information (email)
     * @return patient's contact info
     */
    public String getContactInfo(){
        return patient.getContactInfo();
    }

    /**
     * get method to get patient's dob
     * @return patient's dob
     */    
    public LocalDate getDateOfBirth(){
        return patient.getDateOfBirth();
    }

    /**
     * get method to get patient's phone number
     * @return patient's phone number
     */
    public int getPhoneNumber(){
        return patient.getPhoneNumber();
    }
    
    /**
     * get method to get patient's gender
     * @return patient's gender
     */
    public String getGender(){
        return patient.getGender();
    }

    /**
     * adds a medical record to the list of allrecords
     * @param record the medical record to be added
     */
 	public static void addRecord(MedicalRecord record) {
        allRecords.add(record);
    }

    /**
     * adds an appointment outcome to the medical records
     * @param outcome the outcome to add
     */
    public void addAppointmentOutcome(AppointmentOutcome outcome) {
        if (!pastAppointments.contains(outcome)) {  // Check to prevent duplicates
            pastAppointments.add(outcome);
            System.out.println("Appointment outcome added to medical record for Patient Identifier: " + patient.getPatientIdentifier());
        } else {
            System.out.println("Appointment outcome already exists in the record for Patient Identifier: " + patient.getPatientIdentifier());
        }
    }

    /**
     * adds a diagnosis to medical record
     * @param diagnosis the diagnosis to be added
     */
    public void addDiagnosis(Diagnosis diagnosis) {
        diagnoses.add(diagnosis);
        entryTimestamps.add(LocalDateTime.now());
        System.out.println("Diagnosis added to medical record for Patient Identifier: " + patient.getPatientIdentifier());
    }

    /**
     * adds a prescription to medical record
     * @param prescription the prescription to be added
     */
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        entryTimestamps.add(LocalDateTime.now());
        System.out.println("Prescription added to medical record for Patient Identifier: " + patient.getPatientIdentifier());
    }

    /**
     * adds a treatment to medical record
     * @param treatment the treatment to be added
     */
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
        entryTimestamps.add(LocalDateTime.now());
        System.out.println("Treatment added to medical record for Patient Identifier: " + patient.getPatientIdentifier());
    }

    /**
     * retrieves all appointment outcomes
     * @return a list of appointment outcomes
     */
    public List<AppointmentOutcome> getAppointmentOutcomes() {
        return new ArrayList<>(pastAppointments); 
    }

    /**
     * finds medical record by patient Identifier
     * @param patientIdentifier the Identifier to be retrieved
     * @return the medical record that was retrieved
     */
	public static MedicalRecord getRecordByPatientIdentifier(String patientIdentifier) {
        for (MedicalRecord record : allRecords) {
            if (record.patient.getPatientIdentifier().equals(patientIdentifier)) {
                return record;
            }
        }
        return null;  // null if no record found
    }

    /**
     * Method to return a patient's phone number formatted as string
     * this is use for display purposes, as default when a patient information is added, their phone number is 0
     * @return the patient's phone number if available, else N/A
     */
    public String getPhoneNumberString(){
        if(this.getPhoneNumber() == 0){
            return "N/A";
        }
        else{
            return String.valueOf(this.getPhoneNumber());
        }
    }

    /**
     * Display for medical record
     */
    public void viewMedicalRecord() {
        System.out.println("=== Medical Record ===");
        System.out.println("Patient Identifier: " + patient.getPatientIdentifier());
        System.out.println("Name: " + patient.getName());
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Contact Info: " + patient.getContactInfo());
        System.out.println("Phone Number: " + getPhoneNumberString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("\n--- Diagnoses History ---");
        if (diagnoses.isEmpty()) {
            System.out.println("N/A");
        } else {
            for (int i = 0; i < diagnoses.size(); i++) {
                String formattedTimestamp = entryTimestamps.get(i).format(formatter);
                System.out.println(formattedTimestamp + " - " + diagnoses.get(i));
            }
        }

        System.out.println("\n--- Treatments History ---");
        if (treatments.isEmpty()) {
            System.out.println("N/A");
        } else {
            for (int i = 0; i < treatments.size(); i++) {
                String formattedTimestamp = entryTimestamps.get(i).format(formatter);
                System.out.println(formattedTimestamp + " - " + treatments.get(i));
            }
        }

        System.out.println("\n--- Past Appointments ---");
        if (pastAppointments.isEmpty()) {
            System.out.println("N/A");
        } else {
            for (AppointmentOutcome outcome : pastAppointments) {
                System.out.println(outcome);
            }
        }
        System.out.println("");
    }



}