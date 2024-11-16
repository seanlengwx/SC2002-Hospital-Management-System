import java.time.LocalDate;

/**
 * Appointment outcome class
 */
public class AppointmentOutcome {
	
	
	private Appointment appointment;
	private String services;
	private String notes;
	private Prescription prescription;
	private LocalDate appointmentDate;

	/**
	 * Constructor for AppointmentOutcome
	 * @param appointment the appointment associated with this outcome
	 * @param services the services provided
	 * @param notes the notes recorded
	 * @param prescription the prescription issued
	 * @param localDate the date of appointment
	 */
	public AppointmentOutcome(Appointment appointment, String services, String notes, Prescription prescription, LocalDate localDate) {
        this.appointment = appointment;
        this.services = services;
        this.notes = notes;
        this.prescription = prescription;
        this.appointmentDate = localDate;

    }

	/**
	 * get method to retrieve appointment
	 * @return the appointment to get
	 */
	public Appointment getAppointment() {
        return appointment;
    }

	
	/**
	 * set method to set services
	 * @param services the services to set
	 */
	public void setServices(String services) {
		this.services = services;
	}

	/**
	 * get method to retrieve services
	 * @return the service to get
	 */
	public String getServices() {
        return services;
    }

	/**
	 * set method to set notes
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * get method to retrieve notes
	 * @return the notes to get
	 */
	public String getNotes() {
        return notes;
    }

	/**
	 * get method for prescription
	 * @return the prescription to get
	 */
	public Prescription getPrescriptrion() {
		return prescription;
	}

	/**
	 * get method for appointmentDate
	 * @return the date of appointment
	 */
	public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

	/**
	 * set method for prescription
	 * @param prescription the prescription to set
	 */
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
	/**
	 * Details of a appointment outcome
	 * @return formatted string with details of outcome
	 */
	public String getDetails() {
		StringBuilder details = new StringBuilder();
		details.append("======= Appointment Outcome =======\n");
		details.append("Appointment Identifier: ").append(appointment.getAppointmentIdentifier()).append("\n");
		details.append("Services: ").append(services != null && !services.isEmpty() ? services : "None").append("\n");
		details.append("Notes: ").append(notes != null && !notes.isEmpty() ? notes : "None").append("\n");
	
		if (prescription != null) {
			details.append("Prescription: ").append(prescription).append("\n");
		} else {
			details.append("Prescription: No Prescription Issued\n");
		}
	
		details.append("Appointment Date: ").append(appointmentDate).append("\n");
		details.append("=================================");
		return details.toString();
	}
	
	

	/**
	 * Generates a representation of appointmentoutcome
	 * @return a string representing appointmentoutcome details
	 */
	public String toString() {
		return "======= Appointment Outcome =======\n" +
				"Appointment Date: " + appointmentDate +
			    "\nAppointment Identifier: " + appointment.getAppointmentIdentifier() +
				"\nServices: " + services +
				"\nPrescription: " + (prescription != null ? prescription : "No Prescription") +
				"\nNotes: " + notes +
				"\n=================================";
	}
}