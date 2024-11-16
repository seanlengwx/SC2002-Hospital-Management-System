/**
 * Pharmacist class, which is a subclass of Staff, and implements the abstract methods of IUser
 */
public class Pharmacist extends Staff implements IUser {

    private IPharmacistHandler pharmacistHandler;
    private IPrescriptionHandler prescriptionHandler;

    /**
     * Constructor for pharmacist
     * @param userId from superclass
     * @param password from superclass
     * @param name from superclass
     * @param gender from superclass
     * @param role from superclass
     * @param age from superclass
     * @param pharmacistHandler the manager instance for managing pharmacist-specific operations
     * @param prescriptionHandler the manager instance for managing prescription-specific operations
     */
    public Pharmacist(String userId, String password, String name, String gender, String role, int age, 
                      IPharmacistHandler pharmacistHandler, IPrescriptionHandler prescriptionHandler) {
        super(userId, password, name, gender, role, age);
        this.pharmacistHandler = pharmacistHandler;
        this.prescriptionHandler = prescriptionHandler; 
    }

    /**
     * get method to get the prescription manager
     * @return the prescription manager
     */
    public IPrescriptionHandler getPrescriptionHandler() {
        return this.prescriptionHandler;
    }

    /**
     * set method to set the manager that manages the pharmacist-specific operations
     * @param pm the pharmacist manager
     */
    public void setPharmacistHandler(PharmacistHandler pm){
        this.pharmacistHandler = pm;
    }

    /**
     * Retrieves all the prescription record to be viewed
     */
    public void viewPrescriptionRecords() {
        pharmacistHandler.viewPrescriptionRecords();
    }

    /**
     * Retrieves all the pending prescription record to be viewed
     */
    public void viewPendingPrescriptionRecords() {
        pharmacistHandler.viewPendingPrescriptionRecords();
    }

    /**
     * update a status of a prescription 
     * @param prescriptionIdentifier the Identifier of the prescription to be updated
     */
    public void updatePrescriptionStatus(String prescriptionIdentifier) {
        pharmacistHandler.updatePrescriptionStatus(prescriptionIdentifier);
    }

    /**
     * Submits a replenishment request for a specified medicine and its amount
     * @param medicineName the medicine name
     * @param amt the amount to be replenished
     */
    public void replenishmentRequest(String medicineName, int amt) {
        pharmacistHandler.replenishmentRequest(medicineName, amt);
    }

    /**
     * View the current medication inventory
     */
    public void viewInventory() {
        pharmacistHandler.viewInventory();
    }

    /**
     * View all pending replensihment request
     */
    public void viewReplenishmentRequests() {
        pharmacistHandler.viewReplenishmentRequests();
    }

    /**
     * the implementation of abstract method of IUser
     */
    public void displayMenu() {
        if (isLoggedIn()) {
            System.out.println("\n===========================\n");
            System.out.println("1. View Prescription Records");
            System.out.println("2. View Pending Prescription Records");
            System.out.println("3. Update Prescription Status");
            System.out.println("4. View Inventory");
            System.out.println("5. Submit Replenishment Request");
            System.out.println("6. View Replenishment Requests");
            System.out.println("7. Logout");
        } else {
            System.out.println("Error: Not logged in.");
        }
    }
}