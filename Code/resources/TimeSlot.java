package resources;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * TimeSlot Class 
 */
public class TimeSlot {

	private LocalDate date;
	private LocalTime time;

	/**
	 * Constructor for TimeSlot class
	 * @param date date of time slot
	 * @param time time of time slot
	 */
	public TimeSlot(LocalDate date, LocalTime time){
		this.date = date;
		this.time = time;
	}

	/**
	 * get method to retrieve date of time slot
	 * @return date of time slot
	 */
	public LocalDate getDate(){
		return date;
	}

	/**
	 * set method to update date of time slot
	 * @param date new date to set
	 */
	public void setDate(LocalDate date){
		this.date = date;
	}

	/**
	 * get method to retrieve time of time slot
	 * @return time of time slot
	 */
	public LocalTime getTime(){
		return time;
	}

	/**
	 * set method to update time of time slot
	 * @param time new time to set
	 */
	public void setTime(LocalTime time){
		this.time = time;
	}

	/**
	 * method to check if there is a clash between 2 time slot
	 * @param other other time slot to compare with
	 * @return true if date and time are the same, false otherwise
	 */
	public boolean isSameTimeSlot(TimeSlot other) {
		return this.date.equals(other.date) && this.time.equals(other.time);
	}
		
	/**
	 * method to parses time slot in "yyyy-MM-dd HH:mm"
	 * @param timeSlotString string to parse through
	 * @return time slot object if parsing is successful, otherwise null
	 */
	public static TimeSlot parseTimeSlot(String timeSlotString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timeSlotString, formatter);
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();
            return new TimeSlot(date, time);
        } catch (Exception e) {
            System.out.println("Error: Invalid time slot format (yyyy-MM-dd HH:mm).");
            return null;
        }
    }

	/**
	 * method to return time slot in "date @ time"
	 * @return string containing the date and time of the time slot
	 */
	@Override
	public String toString() {
		return date.toString() + " @ " + time.toString();
	}

}