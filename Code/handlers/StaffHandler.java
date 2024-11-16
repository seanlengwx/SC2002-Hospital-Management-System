package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import interfaces.*;
import userclasses.*;
/**
 * StaffHandler - logic implementation for Staff class
 */
public class StaffHandler implements IStaffHandler{
    private List<Staff> staffList;
    private List<User> userList;
    private IMedicineHandler inventoryHandler;
    private IPrescriptionHandler prescriptionHandler;
    private IPharmacistHandler pharmacistHandler; 
    private UserHandler userHandler;
    private IDoctorHandler doctorHandler;


    public List<Staff> getAllStaff() {
        return new ArrayList<>(staffList); 
    }

    /**
     * Constructor for StaffHandler
     * @param initialStaffList  initalize a new staffList to populate staff
     * @param userList shared list of all users
     * @param userHandler  the manager for handling user-related operations
     * @param inventoryHandler the manager for handling inventory-related operations
     * @param prescriptionHandler the manager for handling prescrpition-related operations
     * @param doctorHandler the manager for handling doctor-related operations
     */
    public StaffHandler(List<Staff> initialStaffList, List<User> userList, UserHandler userHandler, IMedicineHandler inventoryHandler, IPrescriptionHandler prescriptionHandler, IDoctorHandler doctorHandler) {
        this.staffList = new ArrayList<>(initialStaffList);
        this.userList = userList; 
        this.inventoryHandler = inventoryHandler;
        this.prescriptionHandler = prescriptionHandler;
        this.pharmacistHandler = pharmacistHandler;
        this.userHandler = userHandler;
        this.doctorHandler = doctorHandler;

        for (Staff staff : initialStaffList) {
            this.userList.add(staff);
        }
    }

    /**
     * Load staff data from staff CSV file.
     * @param filePath path to the CSV file
     */
    public void loadStaffFromCSV(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 6) {
                    String userId = data[0];
                    String name = data[1];
                    String password = data[2];
                    String role = data[3];
                    String gender = data[4];
                    int age = Integer.parseInt(data[5]);

                    Staff newStaff;
                    if ("Doctor".equalsIgnoreCase(role)) {
                        newStaff = new Doctor(userId, password, name, gender, role, age, doctorHandler);
                    } else {
                        newStaff = new Pharmacist(userId, password, name, gender, role, age, pharmacistHandler, prescriptionHandler);
                    }

                    addStaff(newStaff);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * Displays the staff management menu.
     * Allows the user to perform actions related to staff management.
     */
    public void displayStaffManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===========================\n");
        System.out.println("1. Add Staff");
        System.out.println("2. Update Staff");
        System.out.println("3. Remove Staff");
        System.out.println("4. View All Staff");
        System.out.println("5. Filter Staff");
        System.out.println("6. Return");
        System.out.print("Enter an action (1-6): ");
        
        
        int action = scanner.nextInt();
        scanner.nextLine();

        if (action == (6)) {
            
            return;
        }

        switch (action) {
            case 1 -> addStaffMenu();
            case 2 -> updateStaff();
            case 3 -> removeStaff(userHandler);
            case 4 -> viewAllStaff();
            case 5 -> filterStaffMenu();
            default -> System.out.println("Error: Invalid action.");
        }
        
    }

    /**
     * Add new staff member to the system.
     * @param staff staff member to add
     */
    public void addStaff(Staff staff) {
        userList.add(staff);
        staffList.add(staff);
    }
    
   
    /**
     * Entering of details of the new staff member
     */
    public void addStaffMenu() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter User Identifier: ");
            String userId = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Gender (Male/Female): ");
            String gender = scanner.nextLine().trim();
            validateGender(gender);

            System.out.print("Enter Role (Doctor/Pharmacist): ");
            String role = scanner.nextLine().trim();
            validateRole(role);

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();

            Staff newStaff;
            if ("Doctor".equalsIgnoreCase(role)) {
                newStaff = new Doctor(userId, password, name, gender, "Doctor", age, doctorHandler);
            } else if ("Pharmacist".equalsIgnoreCase(role)) {
                newStaff = new Pharmacist(userId, password, name, gender, "Pharmacist", age, pharmacistHandler, prescriptionHandler);
            } else {
                
                throw new InvalidRoleException("Error: Invalid role. Only 'Doctor' or 'Pharmacist' is allowed.");
            }

            addStaff(newStaff);
            System.out.println("Notice: Staff member added: " + newStaff.getName() + " (Identifier: " + newStaff.getUserId() + ")");

        } catch (InvalidRoleException | InvalidGenderException e) {
                System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter the correct data type.");
                scanner.nextLine(); // Clear the buffer
        }
        
    }



    /**
     * Updates details of an existing staff member.
     */
    public void updateStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===========================\n");
        System.out.println("\n--- All Staff ---");
        for (int i = 0; i < staffList.size(); i++) {
            Staff staff = staffList.get(i);
            System.out.println(i + ": ID: " + staff.getUserId() + ", Name: " + staff.getName() + ", Role: " + staff.getRole());
        }
    
        System.out.print("Enter the index to Update: ");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a valid number.");
            
            return;
        }
    
        if (index < 0 || index >= staffList.size()) {
            System.out.println("Error: Invalid index. Please select a valid staff member.");
            
            return;
        }
    
        Staff staff = staffList.get(index);
    
        try {
            System.out.print("Enter New Name (SPACE for unchanged): ");
            String name = scanner.nextLine();
    
            System.out.print("Enter New Role (SPACE for unchanged): ");
            String role = scanner.nextLine();
    
            if (!role.isEmpty() && !role.equalsIgnoreCase(staff.getRole())) {
                validateRole(role);
    
                
                Staff newStaff;
                if ("Doctor".equalsIgnoreCase(role)) {
                    newStaff = new Doctor(staff.getUserId(), staff.getPassword(), name.isEmpty() ? staff.getName() : name, staff.getGender(), "Doctor", staff.getAge(), doctorHandler);
                } else if ("Pharmacist".equalsIgnoreCase(role)) {
                    newStaff = new Pharmacist(staff.getUserId(), staff.getPassword(), name.isEmpty() ? staff.getName() : name, staff.getGender(), "Pharmacist", staff.getAge(), pharmacistHandler, prescriptionHandler);
                } else {
                    
                    throw new InvalidRoleException("Error: Invalid role provided.");
                }
    
                staffList.set(index, newStaff);
                userList.set(userList.indexOf(staff), newStaff);
    
                System.out.println("Notice: Role updated. Staff member changed to: " + newStaff.getName() + " (ID: " + newStaff.getUserId() + ", Role: " + newStaff.getRole() + ")");
            }
    
            if (!name.isEmpty()) {
                staff.setName(name);
            }
    
            System.out.print("Enter New Age (SPACE for unchanged): ");
            String ageInput = scanner.nextLine();
            if (!ageInput.isEmpty()) {
                int age = Integer.parseInt(ageInput);
                if (age > 0) {
                    staff.setAge(age);
                }
            }
    
            System.out.println("Notice: Updated staff member: " + staff.getName() + " (ID: " + staff.getUserId() + ")");
        } catch (InvalidRoleException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid age input. Please enter a valid number.");
        }
        
    }
    
    
    
    
    /**
     * Removes a staff member from the system.
     * @param userHandler UserHandler to remove staff from system
     */
    public void removeStaff(UserHandler userHandler) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===========================\n");
        System.out.println("\n--- All Staff ---");
        for (int i = 0; i < staffList.size(); i++) {
            Staff staff = staffList.get(i);
            System.out.println(i + ": ID: " + staff.getUserId() + ", Name: " + staff.getName() + ", Role: " + staff.getRole());
        }
        
        System.out.print("Enter the index to Remove: ");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a valid number.");
            
            return;
        }
        
        if (index < 0 || index >= staffList.size()) {
            System.out.println("Error: Invalid index. Please select a valid staff member.");
            
            return;
        }
        
        Staff staff = staffList.get(index);
        String userId = staff.getUserId();
        
        staffList.remove(index);
        userHandler.getUsers().removeIf(user -> user.getUserId().equals(userId));
    
        System.out.println("Notice: Staff member: " + userId + " removed.");
        
    }
    
    
    /**
     * Displays all staff members in the system.
     */
    public void viewAllStaff() {
        System.out.println("\n===========================\n");
        System.out.println("\n--- All Staff ---");
        for (Staff staff : staffList) {
            System.out.println("ID: " + staff.getUserId() + ", Name: " + staff.getName() + ", Role: " + staff.getRole() + ", Gender: " + staff.getGender() + ", Age: " + staff.getAge());
        }
    }

    /**
     * Filter staff by criteria such as role, gender, or age.
     */
    public void filterStaffMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) { 
            try {
                System.out.println("\nFilter by:");
                System.out.println("1. Role");
                System.out.println("2. Gender");
                System.out.println("3. Age");
                System.out.println("4. Show All");
                System.out.println("5. Return");
                System.out.print("Choose an option (1-5): ");
                
                String input = scanner.nextLine(); 
    
                if (input.equalsIgnoreCase("5")) {
                    
                    return; 
                }
    
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter a number between 1 and 5.");
                    continue; 
                }
    
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter role to filter by (e.g., Doctor, Pharmacist): ");
                        String role = scanner.nextLine();
                        filterStaff(staff -> staff.getRole().equalsIgnoreCase(role), "Role: " + role);
                    }
                    case 2 -> {
                        System.out.print("Enter gender to filter by (Male/Female): ");
                        String gender = scanner.nextLine();
                        
                        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
                            System.out.println("Error: Invalid gender. Please enter 'Male' or 'Female'.");
                            continue; 
                        }
                        filterStaff(staff -> staff.getGender().equalsIgnoreCase(gender), "Gender: " + gender);
                    }
                    case 3 -> {
                        System.out.print("Enter age to filter by: ");
                        int age;
                        try {
                            age = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. Please enter a valid age.");
                            continue; 
                        }
                        filterStaff(staff -> staff.getAge() == age, "Age: " + age);
                    }
                    case 4 -> viewAllStaff();
                    default -> System.out.println("Error: Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
    }
    

    /**
     * Finds a staff member by their user Identifier.
     * @param userId user Identifier to search for
     * @return staff member if found, otherwise null
     */
    public Staff findStaffById(String userId) {
        for (Staff staff : staffList) {
            if (staff.getUserId().equals(userId)) {
                return staff;
            }
        }
        return null;
    }
    
    /**
     * Filters staff based on a specified criterion and displays matching results.
     * @param criteria the filter criterion
     * @param filterDescription name of the filter
     */
    public void filterStaff(Predicate<Staff> criteria, String filterDescription) {
        System.out.println("\nFiltered Staff by " + filterDescription + ":");
        for (Staff staff : staffList) {
            if (criteria.test(staff)) {
                System.out.println("Name: " + staff.getName() + ", Identifier: " + staff.getUserId() +
                                   ", Role: " + staff.getRole() + ", Gender: " + staff.getGender() +
                                   ", Age: " + staff.getAge());
            }
        }
    }

    /**
     * Exception for invalid roles.
     */
    public class InvalidRoleException extends Exception {
        /**
         * Custom exception for role validation
         * @param message the message for exception
         */
        public InvalidRoleException(String message) {
            super(message);
        }
    }
    
    /**
     * Exception for invalid genders.
     */
    public class InvalidGenderException extends Exception {
        /**
         * Custom exception for gender validation
         * @param message the message for exception
         */
        public InvalidGenderException(String message) {
            super(message);
        }
    }

    /**
     * Validates if gender provided is valid.
     * @param gender gender to validate
     * @throws InvalidGenderException if the gender is not valid
     */
    private void validateGender(String gender) throws InvalidGenderException {
        if (!"Male".equalsIgnoreCase(gender) && !"Female".equalsIgnoreCase(gender)) {
            throw new InvalidGenderException("Invalid gender. Only 'Male' or 'Female' is allowed.");
        }
    }

    /**
     * Validates if role provided is valid.
     * @param role role to validate
     * @throws InvalidRoleException if the role is not valid
     */
    private void validateRole(String role) throws InvalidRoleException {
        if (!"Doctor".equalsIgnoreCase(role) && !"Pharmacist".equalsIgnoreCase(role)) {
            throw new InvalidRoleException("Invalid role. Only 'Doctor' or 'Pharmacist' is allowed.");
        }
    }


    
}