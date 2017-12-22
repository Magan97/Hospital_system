import javax.swing.JFrame;


public class hospital_main {

	public static void main(String[] args) {
		Doctor d = new Doctor();
		d.setVisible(true);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
		AddDoctorAppointment da = new AddDoctorAppointment("APID_1");
		da.setVisible(true);
        da.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Service_appointment sa = new Service_appointment("APID_1");
        sa.setVisible(true);
        sa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
        
        
        //12-1
		/*
		Outpatient_treatments da = new Outpatient_treatments("OTID_1");
		da.setVisible(true);
        da.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        inPatient_details id = new inPatient_details("IPID_1");
        id.setVisible(true);
        id.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Guardian_details gd = new Guardian_details("GDID_1");
        gd.setVisible(true);
        gd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Admission_details ad = new Admission_details("ADID_1");
		ad.setVisible(true);
        ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
		//12-9
		/*
		Supplier_details gd = new Supplier_details("SPID_1");
        gd.setVisible(true);
        gd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Medicine_category gd1= new Medicine_category("CGID_1");
        gd1.setVisible(true);
        gd1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Medicine_product_details pro = new Medicine_product_details("PDID_1");
        pro.setVisible(true);
        pro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        
        MainInterface m = new MainInterface();
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	}
}
