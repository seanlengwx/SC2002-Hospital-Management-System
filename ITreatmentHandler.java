import java.util.List;

/**
 * An interface class which manage the treatment related operations
 */
public interface ITreatmentHandler {

    /**
     * Adds a new treatment
     * @param treatment the treatment to be added
     */
    void addTreatment(Treatment treatment);

    /**
     * Removes a treatment from the system by its Identifier.
     * @param treatmentIdentifier the unique identifier of the treatment to be removed
     */
    void removeTreatment(String treatmentIdentifier);

    /**
     * Finds a treatment by its Identifier.
     * @param treatmentIdentifier the unique identifier of the treatment to find
     * @return the treatment with the Identifier
     */
    Treatment findTreatmentById(String treatmentIdentifier);

    /**
     * Retrieves a list of all treatments
     * @return a list of all treatments
     */
    List<Treatment> getAllTreatments();
}