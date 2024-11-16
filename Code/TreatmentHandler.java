import java.util.ArrayList;
import java.util.List;

/**
 * TreatmentHandler - logic implementation for treatment class
 */
public class TreatmentHandler implements ITreatmentHandler {
    private List<Treatment> treatments;

    /**
     * constructor for TreatmentHandler class
     * initializes an empty list of treatments
     */
    public TreatmentHandler() {
        this.treatments = new ArrayList<>();
    }

    /**
     * method to add treatment to the list
     * @param treatment treatment to add
     */
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
        System.out.println("Notice: Treatment added: " + treatment);
    }

    /**
     * method to remove a treatment from the list by its Identifier
     * @param treatmentIdentifier Id of the treatment to remove
     */
    public void removeTreatment(String treatmentIdentifier) {
        treatments.removeIf(treatment -> treatment.getTreatmentIdentifier().equals(treatmentIdentifier));
        System.out.println("Notice: Treatment " + treatmentIdentifier + " removed.");
    }

    /**
     * method to find a treatment in the list by its Identifier
     * @param treatmentIdentifier Id of the treatment to find
     * @return treatment if found, otherwise null
     */
    public Treatment findTreatmentById(String treatmentIdentifier) {
        for (Treatment treatment : treatments) {
            if (treatment.getTreatmentIdentifier().equals(treatmentIdentifier)) {
                return treatment;
            }
        }
        return null;
    }

    /**
     * get method to get all treatments in the list
     * @return list of all treatments
     */
    public List<Treatment> getAllTreatments() {
        return new ArrayList<>(treatments);
    }
}