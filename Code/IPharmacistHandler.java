/**
 * An interface class which manage the pharmacist related operations
 */
public interface IPharmacistHandler {

    /**
     * set nthe manager responsible for handling medicine-related operations.
     * @param medicineHandler the medicine manager to be set
     */
    void setMedicineHandler(IMedicineHandler medicineHandler);

    /**
     * set the  manager responsible for managing prescription records.
     * @param prescriptionHandler the prescription manager to be set
     */
    void setPrescriptionHandler(IPrescriptionHandler prescriptionHandler);

    /**
     * Views all prescription records
     */
    void viewPrescriptionRecords();

    /**
     * Views all pending prescription records
     */
    void viewPendingPrescriptionRecords();

    /**
     * Updates the status of a prescription
     * @param prescriptionIdentifier the unique identifier of the prescription to be updated
     */
    void updatePrescriptionStatus(String prescriptionIdentifier);

    /**
     * Initiates a replenishment request for a medicine in the inventory.
     * @param medicineName the name of the medicine to be replenished
     * @param amt the amount of stock to request for replenishment
     */
    void replenishmentRequest(String medicineName, int amt);

    /**
     * Views the current inventory
     */
    void viewInventory();

    /**
     * Views all replenishment requests submitted
     */
    void viewReplenishmentRequests();
}