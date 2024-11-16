/**
 * User Class
 */
public abstract class User {

    private String userId;
    private String password;
    private String name;
    private String gender;
    private boolean isLogged;
    private boolean firstLogin = true;

    /**
     * Constructor for User Class
     * @param userId   User Id
     * @param password User password
     * @param name     User full name
     * @param gender   User gender
     */ 
    public User(String userId, String password, String name, String gender) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.isLogged = false;  
    }

    /**
     * Get method to retrieve User Id
     * @return user Id
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * Get method to get User name
     * @return user name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get method to get User gender
     * @return user gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set method to set user gender
     * @param gender gender to set user gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Set method to set user name
     * @param name name to set user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Abstract method for each user menu display
     */
    public abstract void displayMenu();

    /**
     * Set method to set new user password after first login
     * @param newPassword new password after first login
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
        this.firstLogin = false;
    }

    /**
     * Set method to update the user's login 
     * @param status login status where true =logged in, false = logged out
     */
    public void setLoggedIn(boolean status) {
        this.isLogged = status;
    }

    /**
     * Set method to update user's first login status
     * @param firstLogin indication to show whether its user first login
     */
    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    /**
     * Get method to check user first login or not
     * @return if first login true otherwise false
     */
    public boolean isFirstLogin() {
        return firstLogin;
    }

    /**
     * Get method to check if input password matches with user password
     * @param inputPassword password to check
     * @return if match return true otherwise false
     */
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    

    /**
     * Log the user out
     */
    public void logout() {
        if (isLogged) {
            isLogged = false;
            System.out.println("You have been logged out.");
        } else {
            System.out.println("You are not logged in.");
        }
    }

    /**
     * Check if user is logged in
     * @return true if logged in otherwise false
     */
    public boolean isLoggedIn() {
        return isLogged;
    }

    /**
     * Get method to get user password
     * @return user password
     */ 
    public String getPassword(){
        return password;
    }
}