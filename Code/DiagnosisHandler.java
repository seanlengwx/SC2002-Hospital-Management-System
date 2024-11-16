import java.util.ArrayList;
import java.util.List;

/**
 * DiagnosisHandler class for logic for Diagnosis
 */
public class DiagnosisHandler {
    private List<Diagnosis> diagnoses;

    /**
     * Constructor for DiagnosisHandler
     * There may be more than one diagnosis, so a list is used
     */
    public DiagnosisHandler() {
        this.diagnoses = new ArrayList<>();
    }

    /**
     * method to add diagnosis information into the list 
     * @param diagnosis the diagnosis to be added
     */
    public void addDiagnosis(Diagnosis diagnosis) {
        diagnoses.add(diagnosis);
        System.out.println("Diagnosis added: " + diagnosis);
    }

    /**
     * method to find the diagnosis based on the Identifier
     * @param diagnosisIdentifier the id specified to find the diagnosis
     * @return the diagnosis
     */
    public Diagnosis findDiagnosisById(String diagnosisIdentifier) {
        for (Diagnosis diagnosis : diagnoses) {
            if (diagnosis.getDiagnosisIdentifier().equals(diagnosisIdentifier)) {
                return diagnosis;
            }
        }
        return null;
    }

    /**
     * a method to get all diagnosis inside the list
     * @return the list of diagnosis
     */
    public List<Diagnosis> getAllDiagnoses() {
        return new ArrayList<>(diagnoses);
    }
}