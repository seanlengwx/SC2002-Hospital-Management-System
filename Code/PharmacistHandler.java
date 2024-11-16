import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handler class which manages the pharmacist-related operations
 */
class PharmacistHandler implements IPharmacistHandler {

    private IPrescriptionHandler prescriptionHandler;
    private IMedicineHandler medicineHandler;
    private List<ReplenishmentRequest> replenishmentRequests;

    /**
     * Constructor for PharmacistHandler
     * @param prescriptionHandler the manager used for managing prescription records
     * @param medicineHandler the manager used for managing medicine records
     */
    public PharmacistHandler(IPrescriptionHandler prescriptionHandler, IMedicineHandler medicineHandler) {
        this.prescriptionHandler = prescriptionHandler;
        this.medicineHandler = medicineHandler;
        this.replenishmentRequests = new ArrayList<>();
    }

    /**
     * set method to set prescriptionmanager
     * @param pm the prescription manager
     */
    public void setPrescriptionHandler(IPrescriptionHandler pm){
        this.prescriptionHandler = pm;   
    }

    /**
     * set method to set medicine manager
     * @param mm the medicine manager
     */
    public void setMedicineHandler(IMedicineHandler mm){
        this.medicineHandler = mm;
    }
    
    /**
     * display all prescription record
     */
    public void viewPrescriptionRecords() {
        System.out.println("\nPrescription Records:");
        List<Prescription> prescriptions = prescriptionHandler.getAllPrescriptions();

        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions available.");
        } else {
            for (Prescription prescription : prescriptions) {
                System.out.println(prescription);
            }
        }
    }

    /**
     * display all pending prescription record
     */
    public void viewPendingPrescriptionRecords() {
        System.out.println("\nPending Prescription Records:");
        List<Prescription> pendingPrescriptions = prescriptionHandler.getPendingPrescriptions();

        if (pendingPrescriptions.isEmpty()) {
            System.out.println("No pending prescriptions available.");
        } else {
            for (Prescription prescription : pendingPrescriptions) {
                System.out.println(prescription);
            }
        }
    }

    /**
     * update a prescription record status
     * @param prescriptionIdentifier the Identifier of the prescription to be udpated
     */
    public void updatePrescriptionStatus(String prescriptionIdentifier) {
        if (!prescriptionHandler.updatePrescriptionStatus(prescriptionIdentifier)) {
            System.out.println("Prescription with Identifier " + prescriptionIdentifier + " not found.");
        }
    }

    /**
     * submit a replenishment request for a medicine
     * @param medicineName the name of the medicine to be replenished
     * @param amt the amount to be replenished
     */
    public void replenishmentRequest(String medicineName, int amt) {
        Medicine medicine = medicineHandler.findMedicineByName(medicineName);

        if (medicine == null) {
            System.out.println("Medicine " + medicineName + " not found in inventory.");
            return;
        }

        if (medicineHandler.needsReplenishment(medicine.getName())) {
            boolean alreadyRequested = false;
            for (ReplenishmentRequest request : replenishmentRequests) {
                if (request.getMedicine().equals(medicine) && !request.isApproved()) {
                    alreadyRequested = true;
                    break;
                }
            }

            if (alreadyRequested) {
                System.out.println("A replenishment request for " + medicine.getName() + " is already pending.");
                return;
            }

            // creating a new request
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMHHmmss");
            String requestIdentifier = "R" + LocalDateTime.now().format(formatter);
            ReplenishmentRequest request = new ReplenishmentRequest(requestIdentifier, medicine, amt, "pharmacistIdentifier", "pharmacistName");

            replenishmentRequests.add(request);  // Track this request locally for the pharmacist
            System.out.println("Replenishment Request Submitted: " + amt + " units of " + medicine.getName());
        } else {
            System.out.println("Replenishment is not needed for " + medicine.getName() + ".");
        }
    }


    /**
     * Method to view all the inventory in the system
     */
    public void viewInventory() {
        medicineHandler.viewMedicines();
    }

    /**
     * Display all replenishment request made by pharmacist
     */
    public void viewReplenishmentRequests() {
        System.out.println("Replenishment Requests by Pharmacist:");
        for (ReplenishmentRequest request : replenishmentRequests) {
            String status = request.isApproved() ? "Approved" : "Pending";
            System.out.println("Request Identifier: " + request.getRequestIdentifier() + ", Medicine: " 
                               + request.getMedicine().getName() + ", Amount: " + request.getRequestedAmount()
                               + ", Status: " + status);
        }
    }
}