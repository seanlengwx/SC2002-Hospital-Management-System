import java.time.LocalDate;

/**
 * Diagnosis Class
 */
public class Diagnosis {
    private String diagnosisIdentifier;
    private String description;
    private LocalDate date;

    /**
     * Constructor for diagnosis
     * @param diagnosisIdentifier a unique identifier for a diagnosis
     * @param description the description of the diagnosis
     * @param date date of diagnosis
     */
    public Diagnosis(String diagnosisIdentifier, String description, LocalDate date) {
        this.diagnosisIdentifier = diagnosisIdentifier;
        this.description = description;
        this.date = date;
    }

    /**
     * get method to get diagnosis Identifier
     * @return the diagnosis Identifier
     */
    public String getDiagnosisIdentifier() {
        return diagnosisIdentifier;
    }

    /**
     * get method to get description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set method to set the description
     * @param description the info to set the description as
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get method to get date 
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * set method to set the date of diagnosis
     * @param date the date to be set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * a string that represents the diagnosis information
     * @return representation of diagnosis
     */
    @Override
    public String toString() {
        return "Diagnosis Identifier: " + diagnosisIdentifier + ", Notes: " + description;
    }
}