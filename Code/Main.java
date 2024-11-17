import java.time.*;
import java.util.*;

import resources.*;
import userclasses.*;
import interfaces.*;
import handlers.*;
/**
 * Initialize and load data from CSV
 * Start application with loginUser() function
 */
public class Main {
    /**
     * onstructor for Main
     */
    public Main() {}

    
    /**
     * Entry of application
     * @param args cli
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Appointment> allAppointments = new ArrayList<>();
        List<User> sharedUserList = new ArrayList<>();
        List<Staff> initialStaffList = new ArrayList<>();
        
        // Initialize managers 
        IMedicineHandler medicineHandler = new MedicineHandler();
        IPrescriptionHandler prescriptionHandler = new PrescriptionHandler(null);
        IPatientHandler patientHandler = new PatientHandler(null);
        IDoctorHandler doctorHandler = new DoctorHandler(null, null);
        IPharmacistHandler pharmacistHandler = new PharmacistHandler(null, null);
        IAppointmentHandler appointmentHandler = new AppointmentHandler(null, null, null);
        UserHandler userHandler = new UserHandler(sharedUserList, doctorHandler, appointmentHandler, medicineHandler, prescriptionHandler);
        IStaffHandler staffHandler = new StaffHandler(initialStaffList, sharedUserList, userHandler, medicineHandler, prescriptionHandler, doctorHandler);

        //Pre defined timeslot's for demonstration purposes
        TimeSlot slot1 = new TimeSlot(LocalDate.now().plusDays(1), LocalTime.of(9, 30));
        TimeSlot slot2 = new TimeSlot(LocalDate.now().plusDays(2), LocalTime.of(9, 30));
        
        //manager dependencies
        prescriptionHandler.setMedicineHandler(medicineHandler);
        patientHandler.setAppointmentHandler(appointmentHandler);
        pharmacistHandler.setMedicineHandler(medicineHandler);
        pharmacistHandler.setPrescriptionHandler(prescriptionHandler);
        appointmentHandler.setAppList(allAppointments);
        appointmentHandler.setDoctorHandler(doctorHandler);
        appointmentHandler.setPatientHandler(patientHandler);
        doctorHandler.setStaffHandler(staffHandler);
        doctorHandler.setPrescriptionHandler(prescriptionHandler);


        //data file paths
        String staffFilePath = "./datafiles/Staff.txt";  
        String patientFilePath = "./datafiles/Patient.txt";  
        String medicineFilePath = "./datafiles/Medicine.txt";  

        //loading of file files
        TxtImport.importStaffData(staffFilePath, staffHandler, medicineHandler, pharmacistHandler, doctorHandler, prescriptionHandler);
        TxtImport.importPatientData(patientFilePath, patientHandler, appointmentHandler);
        TxtImport.importMedicineData(medicineFilePath, medicineHandler);

        //add all users to shared list
        sharedUserList.addAll(staffHandler.getAllStaff());
        sharedUserList.addAll(patientHandler.getAllPatientsInternal());

        //set doctor availability based on previous demo timeslots
        Doctor doctor1 = doctorHandler.findDoctorById("1");
        doctor1.addAvailability(slot1);
        doctor1.addAvailability(slot2);

        //start
        userHandler.startApp();  
    }
}
        