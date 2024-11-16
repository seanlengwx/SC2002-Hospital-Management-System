import java.util.*;

/**
 * AdminstratorHandler - logic implementation for Administrator class
 */
public class AdministratorHandler {
    private IMedicineHandler medicineHandler;

    /**
     * Constructor for AdministratorHandler
     * @param medicineHandler manager responsible for medicine-related logic
     */
    public AdministratorHandler(IMedicineHandler medicineHandler) {
        this.medicineHandler = medicineHandler;
    }

    /**
     * Logic for approving replenishment requests.
     * Asks user for an index and updates it from "Pending" to "Approved"
     */
    public void approveReplenishmentRequests() {
        List<ReplenishmentRequest> requests = medicineHandler.getPendingReplenishmentRequests();
        if (requests.isEmpty()) {
            System.out.println("No pending replenishment requests.");
            return;
        }

        System.out.println("Pending Replenishment Requests:");
        for (int i = 0; i < requests.size(); i++) {
            ReplenishmentRequest request = requests.get(i);
            System.out.println(i + ": " + request);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the replenishment request to approve: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 

        if (index >= 0 && index < requests.size()) {
            ReplenishmentRequest selectedRequest = requests.get(index);
            medicineHandler.approveReplenishment(selectedRequest.getRequestIdentifier());
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }
}