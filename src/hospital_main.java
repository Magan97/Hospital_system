import javax.swing.JFrame;


public class hospital_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		doctor_schedual doctor = new doctor_schedual("DSID_1");
		doctor.setVisible(true);
        doctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Doctor doctor1 = new Doctor("DCID_1");
		doctor1.setVisible(true);
        doctor1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        Room_type rt = new Room_type("RMID_1");
		rt.setVisible(true);
        rt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Room_detail rt1 = new Room_detail("RMID_1");
		rt1.setVisible(true);
        rt1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
		AddDoctorAppointment da = new AddDoctorAppointment("APID_1");
		da.setVisible(true);
        da.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Service_appointment sa = new Service_appointment("APID_1");
        sa.setVisible(true);
        sa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
