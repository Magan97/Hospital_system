import javax.swing.JFrame;

public class Launcher {
	public static void main(String[] args){
		HFrame a = new MainInterface();
		a.run();
		/*
		OutPatientMedicalAppointment o = new OutPatientMedicalAppointment("MPID_1");
		o.setVisible(true);
        o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        MedicalServiceGUI MedicalServiceGUI1 = new MedicalServiceGUI("SVID_1");
		MedicalServiceGUI1.setVisible(true);
		MedicalServiceGUI1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//db as = new db();
		//as.asd();
	}
}