package userclasses;

import java.util.List;

import handlers.*;
import interfaces.*;
import resources.*;

public class Administrator extends Staff implements IUser {
    private IStaffHandler staffHandler;         /**< staff Manager for staff-related logic */
    private IMedicineHandler medicineHandler;   /**< medicine Manager for medicine-related logic */
    private AdministratorHandler adminService;  /**< Admin Manager for admin-related logic */

    /**
     * Constructor for administrator class
     * @param userId            from superlcass
     * @param password          from superlcass
     * @param name              from superlcass
     * @param gender            from superlcass
     * @param role              from superlcass
     * @param age               from superlcass
     * @param staffHandler      staff-related logic
     * @param medicineHandler   medicine-related logic
     */
     
    public Administrator(String userId, String password, String name, String gender, String role, int age, 
                         IStaffHandler staffHandler, IMedicineHandler medicineHandler) {
        super(userId, password, name, gender, role, age);
        this.staffHandler = staffHandler;
        this.medicineHandler = medicineHandler;
        this.adminService = new AdministratorHandler(medicineHandler);
    }

    /**
     * setting StaffHandler 
     * @param sm set staffHandler
     */
    public void setStaffHandler(StaffHandler sm){
        this.staffHandler = sm;
    }

    /**
     * setting MedicineHandler
     * @param mm set medicineHandler
     */
    public void setMedicineHandler(MedicineHandler mm){
        this.medicineHandler = mm;
    }

    /**
     * Display menu for Administrator class
     */
    @Override
    public void displayMenu() {
        if (isLoggedIn()) {
                System.out.println("\n===========================\n");
                System.out.println("\n--- Administrator Menu ---");
                System.out.println("1. Manage Staff");
                System.out.println("2. Manage Medicine Stock");
                System.out.println("3. Approve Replenishments");
                System.out.println("4. View Appointments Details");
                System.out.println("5. Log out");
                System.out.println("\n===========================\n");
        } else {
            System.out.println("Error: Not logged in. (Admin)");
        }
    }

    /**
     * Manages the hospital staff by displaying the staff management menu.
     */
    public void manageHospitalStaff() {
        staffHandler.displayStaffManagementMenu();
    }

    /**
     * Manages the medicine inventory by displaying the medicine management menu.
     */
    public void manageMedicationInventory() {
        medicineHandler.displayMedicineManagementMenu();
    }

    /**
     * Manages the status update for replenishment requests
     */
    public void approveReplenishmentRequests() {
        adminService.approveReplenishmentRequests();
    }

    /**
     * Output to show appointment details
     */
    public void viewAppointmentDetails() {
        List<Appointment> appointments = Appointment.getAllAppointments();
        System.out.println("\n===========================\n");
        System.out.println("Appointments in the System\n");
        
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
    
            //Print out the outcome together with the appointment, if there isnt a outcome 
            //then just leave it as it is
            AppointmentOutcome outcome = appointment.getOutcome();
            if (outcome != null) {
                System.out.println("  --- Appointment Outcome ---");
                System.out.println("  Services Provided: " + outcome.getServices());
                System.out.println("  Additional Notes: " + outcome.getNotes());
                System.out.println("  Prescription Identifier: " + outcome.getPrescriptrion());
                System.out.println("");
            }
        }
        System.out.println("\n===========================\n");
    }
}