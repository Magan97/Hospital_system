import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class MainInterface extends HFrame{
	/**
	 *
	 */
	private static final long serialVersionUID = -1395053720340022605L;

	JPanel checkLoginPanel = new JPanel(){	
    	public void paintComponent(Graphics g) {
            ImageIcon icon = new ImageIcon("1.png");
            g.drawImage(icon.getImage(), 0, 0,
            this.getSize().width,
            this.getSize().height,
            this);
        }
    };

	JPanel mainPanel;
	JMenuBar menuBar = new JMenuBar();
	String username;

	LoginInterface LoginFrame = new LoginInterface(this);

	//constructor
	MainInterface(){
		init_Frame();
		init_checkLoginPanel();
		init_mainPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void run(){
		super.run();
		if(!isLogin()){
			this.remove(mainPanel);
			this.add(checkLoginPanel);
		}
		else{
			username = LoginFrame.username;
			init_mainPanel();
			this.remove(checkLoginPanel);
			this.add(mainPanel);
		}
		this.repaint();
		this.revalidate();
	}

	private void init_menubar() {


		JMenu Introduction = new JMenu("Introduction"); menuBar.add(Introduction);
		
		JMenu Doctors = new JMenu("Doctors"); menuBar.add(Doctors);


		JMenu Patients = new JMenu("Patients"); menuBar.add(Patients);


		JMenu Services = new JMenu("Services"); menuBar.add(Services);

		JMenu Medicine = new JMenu("Medicine"); menuBar.add(Medicine);


		JMenu Room_Beds = new JMenu("Room/beds"); menuBar.add(Room_Beds);

		JMenu Bill_payment = new JMenu("Bill payment"); menuBar.add(Bill_payment);
		
		
		//XY
		Room_Beds.add(newMenuItem("Bed details", "bed_details"));
		Introduction.add(newMenuItem("Hospital details", "Hospital_details"));
		Introduction.add(newMenuItem("User details", "user_details"));
		Bill_payment.add(newMenuItem("Inpatient bill details", "inPatient_billdetails"));
		Bill_payment.add(newMenuItem("Inpatient bill", "inPatient_bill"));
		
		//Feng Shuo
		Patients.add(newMenuItem("Admission", "Admission_details"));
		Bill_payment.add(newMenuItem("Doctor appointment bill", "appointment_bill"));
		Patients.add(newMenuItem("Discharge", "discharge_details"));
		Doctors.add(newMenuItem("Doctor appointment", "doctor_appointment_look"));
		Doctors.add(newMenuItem("Doctor schedule", "doctor_schedual"));
		Doctors.add(newMenuItem("Doctors", "Doctor"));
		Patients.add(newMenuItem("Guardian", "Guardian_details"));
		Introduction.add(newMenuItem("Hospital charge", "hospital_charge"));
		Patients.add(newMenuItem("In patient", "inPatient_details"));
		Services.add(newMenuItem("In patient hospital service", "InPatientHospitalService"));
		Medicine.add(newMenuItem("Medicine category", "Medicine_category"));
		Medicine.add(newMenuItem("Medicine product", "Medicine_product_details"));
		Bill_payment.add(newMenuItem("Out patient medicine bill", "outpatient_medicine_bill"));
		Patients.add(newMenuItem("Out patient treatment", "Outpatient_treatments"));
		Patients.add(newMenuItem("Prescription", "prescription_details"));
		Room_Beds.add(newMenuItem("Rooms", "Room_detail"));
		Room_Beds.add(newMenuItem("Room type", "Room_type"));
		Bill_payment.add(newMenuItem("Out patient service appointment bill", "service_appointment_bill"));
		Services.add(newMenuItem("Service appointment", "service_appointment_look"));
		Medicine.add(newMenuItem("Suppliers", "Supplier_details"));
		
		//Xiong Yong
		
		Medicine.add(newMenuItem("Medical Service", "MedicalServiceGUI"));

	}

	private JMenuItem newMenuItem(String text, String className) {
		JMenuItem a = new JMenuItem(text);
		a.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Class cls = Class.forName(className);
					HFrame childFrame = (HFrame)cls.newInstance();
					childFrame.run();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return a;
	}

	private void init_Frame(){
		setLayout(null);
		setSize(900, 700);
		setTitle("Hospital Management System");
		init_menubar();
		setJMenuBar(menuBar);
	}
	
	private void init_mainPanel(){
		//UI
		mainPanel = new JPanel(){	
	    	public void paintComponent(Graphics g) {
	            ImageIcon icon = new ImageIcon("1.png");
	            g.drawImage(icon.getImage(), 0, 0,
	            this.getSize().width,
	            this.getSize().height,
	            this);
	        }
	    };
		mainPanel.setLayout(null);
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFrame.Logout();
				run();
			}
		});logoutButton.setBounds(20, 40, 100, 20);

		mainPanel.setBounds(0, 0, 900, 700);
		mainPanel.add(HFrame.newLabel(("Welcome, " + username), new Rectangle(20, 20, 200, 20)));
		mainPanel.add(logoutButton);
		//login time
		String loginTimeString = "Login time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		mainPanel.add(HFrame.newLabel(loginTimeString, new Rectangle(20, 60, 200, 20)));
	}

	private void init_checkLoginPanel(){

		JLabel loginMessage = new JLabel("Please login");
		loginMessage.setForeground(Color.red);
		checkLoginPanel.add(loginMessage);

		JButton openLoginInterface = new JButton("Login Now");
		openLoginInterface.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFrame.run();
			}
		});
		checkLoginPanel.setBounds(0, 0, 900, 700);
		checkLoginPanel.add(openLoginInterface);
	}

	public boolean isLogin(){
		return this.LoginFrame.isLogin;
	}
}