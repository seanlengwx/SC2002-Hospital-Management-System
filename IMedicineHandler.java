import java.util.List;

/**
 * Interface for managing medicine-related operations
 */
public interface IMedicineHandler {

    /**
     * Retrieves the current inventory of medicines.
     * @return a list of all medicines in the inventory
     */
    List<Medicine> getInventory();

    /**
     * Retrieves a list of pending replenishment requests.
     * @return a list of pending replenishment requests
     */
    List<ReplenishmentRequest> getPendingReplenishmentRequests();

    /**
     * Displays the menu for managing medicines
     */
    void displayMedicineManagementMenu();

    /**
     * Approves a specific replenishment request by its Identifier.
     * @param requestIdentifier the unique identifier of the replenishment request to approve
     */
    void approveReplenishment(String requestIdentifier);

    /**
     * Adds a new medicine to the inventory.
     * @param name the name of the medicine
     * @param stock the initial stock quantity of the medicine
     * @param alertLevel the stock level at which replenishment should be triggered
     */
    void addMedicine(String name, int stock, int alertLevel);

    /**
     * Displays the current inventory of medicines
     */
    void displayInventory();

    /**
     * Finds a medicine in the inventory by its name
     * @param medicineName the name of the medicine to search for
     * @return the medicine with the specified name, or {@code null} if not found
     */
    Medicine findMedicineByName(String medicineName);

    /**
     * Checks if a specific medicine needs replenishment based on its stock level
     * @param name the name of the medicine to check
     * @return a boolean indicating whether if a medicine requires replenishment
     */
    boolean needsReplenishment(String name);

    /**
     * Displays a list of all medicines
     */
    void viewMedicines();
}