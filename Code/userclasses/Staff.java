package userclasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Staff Class
 */
public abstract class Staff extends User {

    /**
     * Constructor for Staff
     * @param userId from superclass
     * @param password from superclass
     * @param name from superclass
     * @param gender from superclass
     * @param role role of staff
     * @param age age of staff
     */
	public Staff(String userId, String password, String name, String gender, String role, int age) {
		super(userId, password, name, gender);  
        this.role = role;
        this.age = age;
        staffList.add(this);
    }
	
	private String role;
	private int age;
    private static List<Staff> staffList = new ArrayList<>();


    /**
     * Get method to get the role of staff member.
     * @return role of the staff member
     */
    public String getRole() {
        return role;
    }

    /**
     * Set method to update the role of the staff member.
     * @param role new role to assign to the staff member
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get method to get the age of the staff member.
     * @return age of the staff member
     */
    public int getAge() {
        return age;
    }

    /**
     * Set method to update the age of the staff member.
     * @param age  age of the staff member
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get method to get list of all staff members.
     * @return list of all staff members
     */
    public static List<Staff> getAllStaff() {
        return staffList;
    }

    /**
     * Abstract class for subclasses to implement their own displayMenu
     */
    @Override
    public abstract void displayMenu();
    
    
}