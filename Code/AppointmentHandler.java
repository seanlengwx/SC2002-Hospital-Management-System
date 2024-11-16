import java.util.ArrayList;
import java.util.List;

/**
 * AppointmentHandler, logic for appointments
 */
public class AppointmentHandler implements IAppointmentHandler {

    private IDoctorHandler doctorHandler;       /**< for doctor-related logic */
    private IPatientHandler patientHandler;     /**< for patient-related logic */
    private List<Appointment> allAppointments;  /**< list of appointments hospital wide */

    /**
     * Constructor for appointment manager
     * @param doctorHandler  manager responsible for doctor-related logic
     * @param patientHandler manager responsible for patient-related logic
     * @param allAppointments a list of appointments
     */
    public AppointmentHandler(IDoctorHandler doctorHandler, IPatientHandler patientHandler,List<Appointment> allAppointments) {
        this.doctorHandler = doctorHandler;
        this.patientHandler = patientHandler;    
        this.allAppointments = allAppointments;
    }

    /**
     * setting doctorHandler (to prevent cyclic in Main)
     * @param dm set doctorHandler
     */
    public void setDoctorHandler(IDoctorHandler dm){
        this.doctorHandler = dm;
    }

    /**
     * setting patientHandler (to prevent cyclic in Main)
     * @param pm set patientHandler
     */
    public void setPatientHandler(IPatientHandler pm){
        this.patientHandler = pm;
    }

    /**
     * setting allAppointment (to prevent cyclic in Main)
     * @param app set list
     */
    public void setAppList(List<Appointment> app){
        this.allAppointments = app;
    }

    /**
     * Outputs all available slots for a particular doctor based on the parameters
     * @param doctor specified doctor for this method to output his/her available slots
     */
    public void viewAvailableSlots(Doctor doctor) {
        System.out.println("\n===========================\n");
        System.out.println("Greetings Dr. " + doctor.getName());
        System.out.println("Available Slots:");
        List<TimeSlot> availableSlots = doctorHandler.getAvailability(doctor);
        for (TimeSlot slot : availableSlots) {
            System.out.println(slot);
        }
    }

    /**
     * Method to schedule an appointment between patient and doctor
     * @param patient indicating which patient is involved with the appointment
     * @param doctor indicating which doctor is involved with the appointment
     * @param timeSlot the time which the appointment is scheduled
     */
    public void scheduleAppointment(Patient patient, Doctor doctor, TimeSlot timeSlot) {
        if (doctorHandler.isAvailable(doctor, timeSlot)) {
            String appointmentIdentifier = "APT" + System.currentTimeMillis();
            Appointment appointment = new Appointment(appointmentIdentifier, patient.getUserId(), doctor.getUserId(), timeSlot, "Pending");

            patient.addAppointment(appointment);
            doctor.addAppointment(appointment);
            doctor.removeAvailability(timeSlot);
            allAppointments.add(appointment);
            
        } else {
            System.out.println("Notice: Doctor is unavailable.");
        }
    }

    /**
     * Method to reschedule an appointment between patient and doctor
     * @param patient indicating which patient is involved with the appointment
     * @param appointment indicating which appointment is involved with the rescheduling
     * @param newTimeSlot the chosen new timeslot to be rescheduled
     * @param doctor indicating which doctor is involved with the appointment
     */
    public void rescheduleAppointment(Patient patient, Appointment appointment, TimeSlot newTimeSlot, Doctor doctor) {
        TimeSlot oldTimeSlot = appointment.getTimeSlot();
        if (doctorHandler.isAvailable(doctor, newTimeSlot)) {
            appointment.setTimeSlot(newTimeSlot); //set the appt to new
            doctor.addAvailability(oldTimeSlot); // add the old time to avail
            doctor.removeAvailability(newTimeSlot); // remove the new time from avail
        } else {
            System.out.println("Notice: Unavailable slot selected.");
        }
    }

    /**
     * Retrieves a list of upcoming appointments for a specific patient
     * @param patient the patient whose upcoming appointments are to be retrieved 
     * @return a list of appointments that are scheduled for a future date and time
     */
    public List<Appointment> getUpcomingAppointments(Patient patient) {
        List<Appointment> upcomingAppointments  = new ArrayList<>();
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.isUpcoming()) { 
                upcomingAppointments .add(appointment);
            }
        }
        return upcomingAppointments ;
    }

    /**
     * Displays the upcoming appointments for a specified patient in the console
     * @param patient the patient whose upcoming appointments are to be retrieved 
     */
    public void viewUpcomingAppointments(Patient patient) {
        System.out.println("\n===========================\n");
        System.out.println("\nUpcoming Appointments for Patient: " + patient.getName());
        List<Appointment> upcomingAppointments = getUpcomingAppointments(patient);
    
        if (upcomingAppointments.isEmpty()) {
            System.out.println("Notice: No upcoming appointments found");
        } else {
            for (Appointment appointment : upcomingAppointments) {
                System.out.println("Appointment Identifier: " + appointment.getPatientIdentifier() + 
                                   ", Doctor Identifier: " + appointment.getDoctorIdentifier() + 
                                   ", Time: " + appointment.getTimeSlot().getDate() + " @ " + appointment.getTimeSlot().getTime() + 
                                   ", Status: " + appointment.getStatus());
            }
        }
    }

    /**
     * Retrieves a list of upcoming appointments for a specific doctor
     * @param doctor the doctor whose upcoming appointments are to be retrieved 
     * @return a list of appointments that are scheduled for a future date and time
     */
    //method overloading
    public List<Appointment> getUpcomingAppointments(Doctor doctor) {
        List<Appointment> upcomingAppointments  = new ArrayList<>();
        for (Appointment appointment : doctor.getAppointments()) {
            if (appointment.isUpcoming()) { 
                upcomingAppointments .add(appointment);
            }
        }
        return upcomingAppointments ;
    }

    /**
     * Displays the upcoming appointments for a specified doctor in the console
     * @param doctor the doctor whose upcoming appointments are to be retrieved 
     */
    public void viewUpcomingAppointments(Doctor doctor) {
        System.out.println("\n===========================\n");
        System.out.println("\nUpcoming Appointments for Dr." + doctor.getName());
        List<Appointment> upcomingAppointments = getUpcomingAppointments(doctor);
    
        if (upcomingAppointments.isEmpty()) {
            System.out.println("Notice: No upcoming appointments found.");
        } else {
            for (Appointment appointment : upcomingAppointments) {
                System.out.println("Appointment Identifier: " + appointment.getPatientIdentifier() + 
                                   ", Doctor Identifier: " + appointment.getDoctorIdentifier() + 
                                   ", Time: " + appointment.getTimeSlot().getDate() + " @ " + appointment.getTimeSlot().getTime() + 
                                   ", Status: " + appointment.getStatus());
            }
        }
    }

    /**
     * Retrieves a list of past appointments for a specific patient
     * @param patient the patient whose upcoming appointments are to be retrieved 
     * @return a list of appointments that are scheduled for a past date and time
     */
    public List<Appointment> getPastAppointments(Patient patient) {
        List<Appointment> pastAppointments = new ArrayList<>();
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.isPast() || "Completed".equalsIgnoreCase(appointment.getStatus())) {
                pastAppointments.add(appointment);
            }
        }

        if (pastAppointments.isEmpty()) {
            System.out.println("Notice: No past appointments found.");
        }
        return pastAppointments;
    }

    /**
     * Retrieves a list of past appointments for a specific doctor
     * @param doctor the doctor whose upcoming appointments are to be retrieved 
     * @return a list of appointments that are scheduled for a past date and time
     */
    //method overloading
    public List<Appointment> getPastAppointments(Doctor doctor) {
        List<Appointment> pastAppointments = new ArrayList<>();
        for (Appointment appointment : doctor.getAppointments()) {
            if (appointment.isPast() || "Completed".equalsIgnoreCase(appointment.getStatus())) { 
                pastAppointments.add(appointment);
            }
        }

        if (pastAppointments.isEmpty()) {
            System.out.println("Notice: No past appointments found.");
        }
        return pastAppointments;
    }

    /**
     * Retrieves appointment details
     * @param patient the patient whose appointments are to be retrieved 
     * @param appointmentIdentifier the appointment that is specified for retrival 
     * @return the appointment
     */
    public Appointment getAppointmentDetails(Patient patient, String appointmentIdentifier) {
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.getAppointmentIdentifier().equals(appointmentIdentifier)) {
                return appointment;
            }
        }

        
        return null; 
    }

    /**
     * Method to record a new appointment outcome for an appointment
     * This allows the doctor to record the outcome of a patient's appointment
     * @param doctor the doctor who wrote the appointment outcome
     * @param patientIdentifier patient associated to this appointment outcome
     * @param appointmentIdentifier the appointment associated to this appointment outcome
     * @param services a description of the services provided during the appointment
     * @param notes additional notes regarding the outcome
     * @param prescription prescription issued for the appointment
     */
    public void recordAppointmentOutcome(Doctor doctor, String patientIdentifier, String appointmentIdentifier, String services, String notes, Prescription prescription) {
        Appointment appointment = findAppointmentById(appointmentIdentifier);
        if (appointment == null || !appointment.getDoctorIdentifier().equals(doctor.getUserId())) {
            System.out.println("Error: Invalid appointment or unauthorized access.");
            return;
        }
        
        Patient patient = patientHandler.findPatientById(patientIdentifier);
        if (patient != null) {
            appointment.recordOutcome(services, notes, prescription, patient);
        } else {
            System.out.println("Error: Patient not found.");
        }
    }
    
    /**
     * Finds an appointment based on the appointmentIdentifier
     * @param appointmentIdentifier specified appointmentIdentifier to find
     * @return the appointment with the specified appointmentIdentifier
     */
    public Appointment findAppointmentById(String appointmentIdentifier) {
        for (Appointment appointment : allAppointments) {
            if (appointment.getAppointmentIdentifier().equals(appointmentIdentifier)) {
                return appointment;
            }
        }
        System.out.println("Error: Appointment with Identifier " + appointmentIdentifier + " not found.");
        return null;
    }
    
   /**
    * Views all appointment for a specific patient
    * @param patient the patient appointment to be viewed
    */
    public void viewAppointments(Patient patient) {
        List<Appointment> appointments = patient.getAppointments();

        if (appointments.isEmpty()) {
            System.out.println("Notice: No available appointments found.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }
    
    /**
     Views all appointment for a specific doctor
    * @param doctor the doctor appointment to be viewed
     */
    //Utilizing method overloading to do the same, but for doctor
    public void viewAppointments(Doctor doctor) {
        List<Appointment> appointments = doctor.getAppointments();
        System.out.println("\n===========================\n");
        System.out.println("Scheduled Appointments for Dr. " + doctor.getName());
        if (appointments.isEmpty()) {
            System.out.println("No available appointments for the patient.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }
    
   /**
     * Accepts a specified appointment for a doctor and adding it to the doctor and patient records.
     * @param doctor the doctor accepting the appointment
     * @param appointment the appointment to be accepted
     */
    public void acceptAppointment(Doctor doctor, Appointment appointment) {
        if (appointment.getDoctorIdentifier().equals(doctor.getUserId())) {
            if (!doctor.getAppointments().contains(appointment)) {
                doctor.removeAvailability(appointment.getTimeSlot());
            }
            appointment.confirm();
            if (!allAppointments.contains(appointment)) {
                allAppointments.add(appointment); 
            }
            System.out.println("Notice: Appointment " + appointment.getAppointmentIdentifier() + " accepted.");
            
            Patient patient = patientHandler.findPatientById(appointment.getPatientIdentifier());
            if (patient != null) {
                if (!patient.getAppointments().contains(appointment)) {
                    patient.addAppointment(appointment);
                    System.out.println("Notice: Appointment added to Patient " + patient.getName() + "'s record.");
                }
                
            } else {
                System.out.println("Error: Patient not found.");
            }
            doctor.addAssignedPatientIdentifier(patient.getUserId());
        } else {
            System.out.println("Access Denied: You are not authorized to accept this appointment.");
        }
    }

    /**
     * Declines a specified appointment for a doctor and adding it to the doctor and patient records.
     * @param doctor the doctor declining the appointment
     * @param appointment the appointment to be declined
     */
    public void declineAppointment(Doctor doctor, Appointment appointment) {
        if (appointment.getDoctorIdentifier().equals(doctor.getUserId()) && doctor.getAppointments().contains(appointment)) {
            appointment.setStatus("Declined");

            doctor.addAvailability(appointment.getTimeSlot());
            System.out.println("Appointment " + appointment.getAppointmentIdentifier() + " declined.");
        } else {
            System.out.println("Access Denied: You are not authorized to decline this appointment.");
        }
    }

    /**
     * Cancels a specified appointment and removing it to the doctor and patient records.
     * @param appointment the appointment to be canceled
     * @param caller the user who requested the cancellation (doctor or patient)
     */
    public void cancelAppointment(Appointment appointment, User caller) {
        if (caller instanceof Doctor) {
            Doctor doctor = (Doctor) caller;
            if (appointment.getDoctorIdentifier().equals(doctor.getUserId())) {
                Patient patient = patientHandler.findPatientById(appointment.getPatientIdentifier());
                if (patient != null) {
                    // Cancel appointment for both doctor and patient
                    appointment.cancel();
                    patient.removeAppointment(appointment);
                    doctor.removeAppointment(appointment);
                    doctor.addAvailability(appointment.getTimeSlot());
                    System.out.println("Appointment " + appointment.getAppointmentIdentifier() + "canceled.");
                }
            }
        } else if (caller instanceof Patient) {
            Patient patient = (Patient) caller;
            if (appointment.getPatientIdentifier().equals(patient.getUserId())) {
                Doctor doctor = doctorHandler.findDoctorById(appointment.getDoctorIdentifier());
                if (doctor != null) {
                    // Cancel appointment for both patient and doctor
                    appointment.cancel();
                    patient.removeAppointment(appointment);
                    doctor.removeAppointment(appointment);
                    doctor.addAvailability(appointment.getTimeSlot());
                    System.out.println("Appointment " + appointment.getAppointmentIdentifier() + "canceled.");
                }
            }
        }
    }

    
    /**
     * Views all appointment outcomes in the system, accessible only to pharmacists.
     * 
     * @param caller the staff member requesting access to view all outcomes
     */
    public void viewAllAppointmentOutcome(Staff caller) {
        // Ensure only pharmacists can view all outcomes
        if (caller == null || !"Pharmacist".equalsIgnoreCase(caller.getRole())) {
            System.out.println("Access Denied: Only pharmacists are allowed to view all appointment outcomes.");
            return;
        }
        System.out.println("\n===========================\n");
        System.out.println("--- All Appointment Outcomes ---");
        for (Appointment appointment : allAppointments) {
            AppointmentOutcome outcome = appointment.getOutcome();
    
            if (outcome != null) {
                System.out.println("Appointment: " + appointment.getAppointmentIdentifier() + ", Outcome: ");
                System.out.println(outcome.getDetails());
            } else {
                System.out.println("Appointment: " + appointment.getAppointmentIdentifier() + " has no recorded outcome.");
            }
        }
    }
    
    /**
     * Retrieves a list of appointment outcomes based on their patient Identifier.
     * 
     * @param patientIdentifier the Identifier of the patient
     * @return a list of all recorded outcomes for the specified patient
     */
    public List<AppointmentOutcome> getOutcomesByPatientIdentifier(String patientIdentifier) {
        List<AppointmentOutcome> outcomes = new ArrayList<>();
        for (Appointment appointment : allAppointments) {
            if (appointment.getPatientIdentifier().equals(patientIdentifier) && appointment.getOutcome() != null) {
                outcomes.add(appointment.getOutcome());
            }
        }
        return outcomes;
    }
    
    /**
     * Views all appointment outcomes for a specific patient.
     * 
     * @param patient the patient which appointment outcomes are to be viewed
     */
    public void viewAppointmentOutcome(Patient patient) {
        List<AppointmentOutcome> outcomes = getOutcomesByPatientIdentifier(patient.getUserId());
        if (outcomes.isEmpty()) {
            System.out.println("Notice: No available appointments found.");
        }
        else{
            for (AppointmentOutcome outcome : outcomes) {
            System.out.println(outcome);
            }
        }
    }    
}