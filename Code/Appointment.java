import java.util.*;
import java.time.LocalDateTime;

/**
 * Appointment class
 */
public class Appointment {

    private static List<Appointment> allAppointments = new ArrayList<>();

	private String appointmentIdentifier;   /**< appointmentID */
	private String patientIdentifier;       /**< patientID */
	private String doctorIdentifier;        /**< doctorID */
	private TimeSlot timeSlot;              /**< timeslot for appointments */
	private String status;                  /**< status of the appointment (confirmed/cancelled/pending) */
    private AppointmentOutcome outcome;     /**< outcome of the appointment */

    /**
     * Constructor for appointment class
     * @param appointmentIdentifier unique Identifier for appointment
     * @param patientIdentifier get Patient through Identifier
     * @param doctorIdentifier get Doctor through Identifier
     * @param timeSlot TimeSlot for appointment
     * @param status status indicator for the status of appointment
     */
    public Appointment(String appointmentIdentifier, String patientIdentifier, String doctorIdentifier, TimeSlot timeSlot, String status) {
        this.appointmentIdentifier = appointmentIdentifier;
        this.patientIdentifier = patientIdentifier;
        this.doctorIdentifier = doctorIdentifier;
        this.timeSlot = timeSlot;
        this.status = status;
        this.outcome = null;

        allAppointments.add(this);
    }
	
    /**
     * get method for appointmentIdentifier
     * @return the appointmentIdentifier
     */
	public String getAppointmentIdentifier() {
        return appointmentIdentifier;
    }

    /**
     * get method for status
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * get method for patientIdentifier
     * @return the patientIdentifier
     */
    public String getPatientIdentifier() { 
        return patientIdentifier; 
    }

    /**
     * get method for doctorIdentifier
     * @return the patientIdentifier
     */
    public String getDoctorIdentifier(){
        return doctorIdentifier;
    }

    /**
     * get method for timeSlot
     * @return the timelot
     */
    public TimeSlot getTimeSlot(){
        return timeSlot;
    }
    
    /**
     * set method for timeslot
     * @param newTimeSlot gets a timeslot as parameter and set it
     */
    public void setTimeSlot(TimeSlot newTimeSlot) {
        this.timeSlot = newTimeSlot;
    }

    /**
     * set method for status
     * @param status gets a status as parameter and set it
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * sets a record outcome for the patient
     * @param services      services done for the patient
     * @param notes         notes from Doctor
     * @param prescription  prescriptions
     * @param patient       patient the outcome is for
     */
    public void recordOutcome(String services, String notes, Prescription prescription, Patient patient) {
        if (this.outcome == null) {
            this.outcome = new AppointmentOutcome(this, services, notes, prescription, timeSlot.getDate());
        
            MedicalRecord record = MedicalRecord.getRecordByPatientIdentifier(patientIdentifier);
            record.addAppointmentOutcome(outcome);
            }   
        else {
                System.out.println("Error: Existing outcome found.");
        }
    }
    
        
    /**
     * get method for outcome
     * @return the outcome
     */
    public AppointmentOutcome getOutcome() { 
        return outcome; 
    }

    /**
     * set status as confirmed
     */
    public void confirm() {
        this.status = "Confirmed";
    }

    /**
     * set status as cancelled
     */
    public void cancel() {
        this.status = "Cancelled";
    }

    /**
     * retrieves a list of appointment in ther system
     * @return a list of appointments
     */
    public static List<Appointment> getAllAppointments() {
        return allAppointments;  
    }

    /**
     * method for a representation of appointment
     * @return a formatted string containing the appointment Identifier, time, and status
     */
    @Override
    public String toString() {
        return "Appointment Identifier: " + appointmentIdentifier + 
               ", Time: " + timeSlot + 
               ", Status: " + status ;
    }

    /**
     * checks if an appointment is in the past
     * @return a bool indicating if the appointment is in the past or not
     */
    public boolean isPast() {
        LocalDateTime appointmentDateTime = LocalDateTime.of(this.timeSlot.getDate(), this.timeSlot.getTime());
        return appointmentDateTime.isBefore(LocalDateTime.now());
    }
    
    /**
     * checks if an appointment is upcoming (in the future)
     * @return a bool indicating if the appointment is in the future or not
     */
    public boolean isUpcoming() {
        LocalDateTime appointmentDateTime = LocalDateTime.of(this.timeSlot.getDate(), this.timeSlot.getTime());
        return appointmentDateTime.isAfter(LocalDateTime.now());
    }

}