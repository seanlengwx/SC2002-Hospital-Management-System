package interfaces;

import java.util.List;

import userclasses.Staff;
/**
 * An interface class which manage the staff related operations
 */
public interface IStaffHandler {

    /**
     * Retrieves a list of all staff members
     * @return a list of all staff members
     */
    List<Staff> getAllStaff();

    /**
     * Displays the staff management menu
     */
    void displayStaffManagementMenu();

    /**
     * Adds a new staff member
     * @param newStaff the staff member to be added
     */
    void addStaff(Staff newStaff);

}