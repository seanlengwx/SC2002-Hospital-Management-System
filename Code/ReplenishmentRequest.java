import java.util.ArrayList;
import java.util.List;


/**
 * ReplenishmentRequest Class
 */
public class ReplenishmentRequest {
	
	private static List<ReplenishmentRequest> requests = new ArrayList<>(); 

	private String requestIdentifier;
	private Medicine medicine;
	private int requestedAmount;
	private String pharmacistIdentifier;
	private String pharmacistName;
	private boolean isApproved;

	/**
     * Constructor for ReplenishmentRequest
     * @param requestIdentifier the Identifier of replenishment request
     * @param medicine the medicine for request
     * @param requestedAmount the amount requested
     * @param pharmacistIdentifier the pharmacist Identifier
     * @param pharmacistName  the pharmacist Name
     */
	public ReplenishmentRequest(String requestIdentifier, Medicine medicine, int requestedAmount, String pharmacistIdentifier, String pharmacistName) {
        this.requestIdentifier = requestIdentifier;
        this.medicine = medicine;
        this.requestedAmount = requestedAmount;
        this.pharmacistIdentifier = pharmacistIdentifier;
        this.pharmacistName = pharmacistName;
        this.isApproved = false;

        requests.add(this);  
    }

	/**
     * Approves the replenishment request.
     * Updates the approval status and calls the replenish method on the associated medicine.
     */
	public void approve() {
		this.isApproved = true;
		medicine.replenish(requestedAmount);
	}


	/**
     * Get method to retrieve the list of all replenishment requests.
     * @return list of all replenishment requests
     */
	public static List<ReplenishmentRequest> getRequests() {
        return requests;
    }

	/**
     * Get method to get the request Identifier.
     * @return request Identifier
     */
	public String getRequestIdentifier() {
		return requestIdentifier;
	}

	/**
     * Get method to get the medicine associated with the request.
     * @return requested medicine
     */
	public Medicine getMedicine() {
        return medicine;
    }

	/**
     * Get methodto get the requested amount of the medicine.
     * @return requested amount
     */
    public int getRequestedAmount() {
        return requestedAmount;
    }

	/**
     * Get method to get the pharmacist's name who made the request.
     * @return pharmacist's name
     */
	public String getPharmacistName() {  
        return pharmacistName;
    }

	/**
     * Checks if the replenishment request has been approved.
     * @return true if approved, false otherwise
     */
	public boolean isApproved() {
		return isApproved;
	}
	
	/**
     * Provides a string representation of the replenishment request.
     * @return string representation of the request
     */
	public String toString() {
        return "Request Identifier: " + requestIdentifier + ", Medicine: " + medicine.getName() +
               ", Requested Amount: " + requestedAmount + ", Status: " + (isApproved ? "Approved" : "Pending");
    }
 
}