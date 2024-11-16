import java.util.*;

/**
 * Medicine class
 */
public class Medicine {

	private String name;
	private int stock;
	private int alertLevel;
	private static List<Medicine> medicineList = new ArrayList<>();

	/**
	 * Default constructor of a medicine, set stock and alert to 0
	 * @param name the name of medicine
	 */
	public Medicine(String name) {
		this(name, 0, 0); 
		medicineList.add(this);
	}

	/**
	 * constructor for a medicine
	 * @param name the name of medicine
	 * @param stock the stock amount of medicine
	 * @param alertLevel the alert level of the medicine
	 */
	public Medicine(String name, int stock, int alertLevel){
		this.name = name;
		this.stock = stock;
		this.alertLevel = alertLevel;
		medicineList.add(this);
	}

	/**
	 * method to replenish the amount of medicines
	 * @param amount the amount to be added
	 */
	//replenish method
	public void replenish(int amount) {
		this.stock += amount;
		System.out.printf("Stock replenished. %d stocks added to %s. New amount: %d \n", amount, name, stock);
	}

	/**
	 * method to adjust alert level of the medicine
	 * @param newAlertLevel the new alert level to be changed
	 */
	public void adjustAlert(int newAlertLevel) {
		this.alertLevel = newAlertLevel;
		System.out.println("Alert for " + name + " has been modified to " + alertLevel);
		
	}
	
	/**
	 * get method to get the stock
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * set method to set number of stock
	 * updateMedicine stock
	 * @param stock the new stock amount
	 */
	public void setStock(int stock){
		this.stock = stock;
	}

	/**
	 * get method to get the name of the medicine
	 * @return the name of the medicine
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set method to set the alert level of a medicine
	 * @param alertLevel the alert level to be set
	 */
	public void setAlertLevel(int alertLevel) {
        this.alertLevel = alertLevel;
    }

	/**
	 * get method to get the alert level of a medicine
	 * @return the alert level of the mmedicine
	 */
	public int getAlertLevel() {
        return alertLevel;
    }
	
	/**
	 * to check if the medicine requires replenishment
	 * @return a boolean indicating if a medicine requires replenishment
	 */
	public boolean alertReplenishment(){
		return stock < alertLevel;
	}

	/**
	 * get a list of all medicine in the system
	 * @return a list of medicine
	 */
	public static List<Medicine> getAllMedicines() {
        return medicineList;
    }

	/**
	 * Retrieve a medicine by its name
	 * @param name the name of the medicine
	 * @return the medicine
	 */
    public static Medicine findMedicineByName(String name) {
        for (Medicine medicine : medicineList) {
            if (medicine.getName().equalsIgnoreCase(name)) {
                return medicine;
            }
        }
        return null;  //if not found
    }
	
	/**
	 * A method to reduce the amount of stock based on its given quantity
	 * used when we change to dispense and reducing of quantity
	 * @param quantity the amount to be deducted
	 */
	public void deductStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            throw new IllegalArgumentException("Insufficient stock for " + name);
        }
    }

	/**
	 * Medicine string representation
	 */
	@Override
    public String toString() {
		return name;
	}
}