package interfaces;

import java.util.List;

import resources.Prescription;

/**
 * An interface class which manage the prescription related operations
 */
public interface IPrescriptionHandler {

    /**
    * set the manager responsible for managing medicine-related operations.
    * @param medicineHandler the medicine manager to be set
    */
    void setMedicineHandler(IMedicineHandler medicineHandler);

    /**
    * Adds a new prescription
    * @param prescription the prescription to be added
    */
    void addPrescription(Prescription prescription);

    /**
    * Retrieves a list of all prescriptions
    * @return a list of all prescriptions
    */
    List<Prescription> getAllPrescriptions();

    /**
    * Retrieves a list of pending prescriptions
    * @return a list of pending prescriptions
    */
    List<Prescription> getPendingPrescriptions();

    /**
    * Updates the status of a specific prescription
    * @param prescriptionIdentifier the unique identifier of the prescription to update
    * @return a boolean indicating whether if it was succesful or not
    */
    boolean updatePrescriptionStatus(String prescriptionIdentifier);
}