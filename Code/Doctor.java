import java.util.ArrayList;
import java.util.List;

/**
 * Doctor class, it extends Staff as its a Staff, and implements IUser for the displayMenu abstract method
 */
public class Doctor extends Staff implements IUser {
    private List<TimeSlot> availability;
    private List<Appointment> appointments;
    private List<String> assignedPatientIdentifiers = new ArrayList<>();
    private IDoctorHandler doctorHandler;

    /**
     * Constructor for doctor class
     * @param userId from superclass
     * @param password from superclass
     * @param name from superclass
     * @param gender from superclass
     * @param role from superclass
     * @param age from superclass
     * @param doctorHandler the manager responsible for doctor-related operations
     */
    public Doctor(String userId, String password, String name, String gender, String role, int age, IDoctorHandler doctorHandler) {
        super(userId, password, name, gender, role, age);
        this.availability = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.assignedPatientIdentifiers = new ArrayList<>();
        this.doctorHandler = doctorHandler; 
    }

    /**
     * get method to get availability of doctor
     * @return a list of available time slots
     */
    public List<TimeSlot> getAvailability() {
        return availability;
    }

    /**
     * get method to get appointments of doctor
     * @return a list of appointments
     */
    public List<Appointment> getAppointments() {
        return this.appointments;
    }

    /**
     * get method to get a list of assigned patient to the doctor
     * this is mainly used to ensure that doctors do not make any unauthorized actions
     * e.g. if the patient is not theirs, they cant just simply update a patient information
     * @return a list of patient that is assigned to the doctor
     */
    public List<String> getAssignedPatientIdentifiers() {
        return assignedPatientIdentifiers;
    }

    /**
     * adds an appointment to the doctor's schedule
     * @param appointment the appointment to be added
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }


    /**
     * removes an appointment from the doctor's schedule
     * @param appointment the appointment to be removed
     */
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
    
    /**
     * method to view the patient medical record specified by the patientIdentifier
     * @param patientIdentifier the Identifier of patient to be retrieved
     */
    public void viewPatientMedicalRecord(String patientIdentifier) {
        doctorHandler.viewPatientRecord(this, patientIdentifier);
    }

    /**
     * adds a new timeslot for the doctor's schedule
     * @param newSlot the time to be added into the doctor's schedule
     */
    public void addAvailability(TimeSlot newSlot) {
        boolean alreadyExists = false;
        for (TimeSlot slot : availability) {
            if (slot.isSameTimeSlot(newSlot)) {
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists) {
            availability.add(newSlot);
        } else {
            System.out.println("Error: Time slot already taken.");
        }
    }
    
    /**
     * removes a timeslot from the doctor's availability
     * @param slot the timeslot to be removed
     */
    public void removeAvailability(TimeSlot slot) {
        if (availability.remove(slot)) {
            //System.out.println("Time slot removed from availability: " + slot);
        } else {
            System.out.println("Error: Time slot not found.");
        }
    }
    
    /**
     * check if a timeslot is available 
     * @param timeSlot the timeslot to be checked
     * @return a boolean indiciating if the timeslot is available or not
     */
    public boolean isAvailable(TimeSlot timeSlot) {
        return availability.contains(timeSlot);
    }
    

    /**
     * retrieves all appointment that are upcoming and confirmed for the doctor
     */
    public void viewUpcomingAppointments() {
        boolean hasUpcomingAppointments = false;
    
        for (Appointment appointment : appointments) {
            if (appointment.isUpcoming() && "Confirmed".equals(appointment.getStatus())) {
                System.out.println(appointment);
                hasUpcomingAppointments = true;
            }
        }
    
        if (!hasUpcomingAppointments) {
            System.out.println("Notice: No upcoming appointments.");
        }
    }

    /**
     * adds a patient into the doctor's assigned list
     * @param patientIdentifier the patient to be added into the doctor assignment
     */
    public void addAssignedPatientIdentifier(String patientIdentifier) {
        if (!assignedPatientIdentifiers.contains(patientIdentifier)) {
            assignedPatientIdentifiers.add(patientIdentifier);
        }
    }
    
    /**
     * the abstract class from IUser, implemented for the doctor class
     */
    @Override
    public void displayMenu() {
        if (isLoggedIn()) {
            System.out.println("\n===========================\n");
            System.out.println("1. View Patient Records");
            System.out.println("2. Update Patient Records");
            System.out.println("3. View Schedule");
            System.out.println("4. Set Availability");
            System.out.println("5. Accept or Decline Appointment ");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Logout");
        } else {
            System.out.println("Error: Not logged in");
        }
    }
}