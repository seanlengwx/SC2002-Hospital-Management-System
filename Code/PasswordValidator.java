import java.util.regex.Pattern;

public class PasswordValidator {

	public boolean isStrongPassword(String password) {	
		if (password.length() < 8) return false;
		if (!Pattern.compile("[A-Z]").matcher(password).find()) return false; // At least one uppercase letter
		if (!Pattern.compile("[a-z]").matcher(password).find()) return false; // At least one lowercase letter
		if (!Pattern.compile("[0-9]").matcher(password).find()) return false; // At least one digit
		if (!Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) return false; // At least one special character
		return true;
	}
}
