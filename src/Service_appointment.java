
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.mysql.jdbc.Statement;
import com.swing.test.calender;

import java.util.Calendar;

public class Service_appointment extends HFrame{
	//JPanel panel;
	JLabel title,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	JComboBox ser_id,pat_id;
	JButton ps,ds,save,close,ok,cancel;
	ArrayList<String> did = new ArrayList<String>();
	ArrayList<String> pid = new ArrayList<String>();
	JSpinner timein1,timein2;
	JTextField text,a_id;
	Service_appointment()
	{
		this("APID_1");
	}
	Service_appointment(String ID)
	{
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Hospital Service Appointment");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Appointment Details--------------------------------------------------------");
        l2.setBounds(80, 100, 500, 20);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        l7 = new JLabel("Appointment ID:");
        l7.setBounds(100, 130, 100, 20);
        panel.add(l7);
        
        a_id = new JTextField(newid());
        a_id.setBounds(220, 130, 150, 20);
        panel.add(a_id);
        a_id.setEditable(false);
        
        l1 = new JLabel("Patient ID:");
        l1.setBounds(100, 160, 100, 20);
        panel.add(l1);
        
        getid();
        int size1 = pid.size();
        String[] patid = new String[size1];
        for(int i=0;i<size1;i++){
        	patid[i] = (String)pid.get(i); 
        }
        pat_id = new JComboBox<Object>(patid);
        pat_id.setBounds(220, 160, 150, 20);
        panel.add(pat_id);
        
        ps = new JButton("info");
        ps.setBounds(380, 160, 80, 20);
        panel.add(ps);
        ps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	patient_table();
            }
        });
        
        int size = did.size();
        String[] docid = new String[size];
        for(int i=0;i<size;i++){
        	docid[i] = (String)did.get(i); 
        }
        ser_id = new JComboBox<Object>(docid);
        ser_id.setBounds(220, 190, 150, 20);
        panel.add(ser_id);
        
        l3 = new JLabel("Service ID:");
        l3.setBounds(100, 190, 100, 20);
        panel.add(l3);
        
        ds = new JButton("info");
        ds.setBounds(380, 190, 80, 20);
        panel.add(ds);
        ds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	doc_table();
            }
        });
        
        l4 = new JLabel("Date:");
        l4.setBounds(100, 220, 100, 20);
        panel.add(l4);
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(220, 220, 200, 20);
        text.setText("2013-10-11");
        ser.register(text);
        panel.add(text);
        
        l5 = new JLabel("Time:");
        l5.setBounds(100, 400, 100, 20);
        panel.add(l5);
        
        timein1 = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        timein1.setBounds(220, 400, 50, 20);
        panel.add(timein1);
        
        l6 = new JLabel("hour");
        l6.setBounds(280, 400, 30, 20);
        panel.add(l6);
        
        timein2 = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        timein2.setBounds(310, 400, 50, 20);
        panel.add(timein2);
        
        l7 = new JLabel("minute");
        l7.setBounds(370, 400, 50, 20);
        panel.add(l7);
        
        save = new JButton("Save");
        save.setBounds(200, 430, 80, 30);
        panel.add(save);
        
        close = new JButton("Close");
        close.setBounds(320, 430, 80, 30);
        panel.add(close);
        
        patient_table();
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();     	
             	service_appointment_look a = new service_appointment_look("APID_1");
         		a.setVisible(true);
                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        doctor_table();
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(correct_date() == 1) //ok
            	{
            		save();
            		//reminder("successsfully");
            	}
            	else{
            		reminder("failed");
            	}
            }
        });
        this.add(panel);        
	}

	public void getid()
	{
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			try{
				Statement stmt = (Statement) con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from services");
				while(rs.next()){
					String id = rs.getString("service_id");
					did.add(id);
				}
				ResultSet rs1 = stmt.executeQuery("select * from patient_details");
				while(rs1.next()){
					String id = rs1.getString("patient_id");
					pid.add(id);
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
	}
	public void patient_table()
	{
		String[] columnName = {"patient_id","firstname","lastname","gender","address","status"};
		Object[][] data1 = null;
		try {
			//Class.forName(com.mysql.jdbc.Driver.class.getName());
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			try{
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select count(*) from patient_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][6];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from patient_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("patient_id");
					data1[i][1] = rs2.getString("firstname");
					data1[i][2] = rs2.getString("lastname");
					data1[i][3] = rs2.getString("gender");
					data1[i][4] = rs2.getString("address");
					if(rs2.getString("status").indexOf("Y") != -1)
						data1[i][5] = "Y";
					else
						data1[i][5] = "N";
					i++;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
	    final JTable table = new JTable(data1, columnName);
        table.setPreferredScrollableViewportSize(new Dimension(350,250));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(520, 130, 350, 250);
        panel.add(scrollPane);
	}
	public void doc_table() //information
	{
		String[] columnName = {"service id","name","duration","charge","note"};
		Object[][] data1 = null;
		try {
			//Class.forName(com.mysql.jdbc.Driver.class.getName());
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			try{
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select count(*) from services");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][5];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from services");
				while(rs2.next()){
					data1[i][0] = rs2.getString("service_id");
					data1[i][1] = rs2.getString("service_name");
					data1[i][2] = rs2.getString("duration_of_service");
					data1[i][3] = rs2.getString("charge_for_service");
					data1[i][4] = rs2.getString("service_notes");
					i++;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
	    final JTable table = new JTable(data1, columnName);
        table.setPreferredScrollableViewportSize(new Dimension(350,250));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(520, 130, 350, 250);
        panel.add(scrollPane);
	}
	public void doctor_table()
	{
		String[] columnName = {"service id","avaldate","time in","time out","schedule_id","note"};
		Object[][] data1 = null;
		try {
			//Class.forName(com.mysql.jdbc.Driver.class.getName());
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			try{
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select count(*) from service_schedule_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][6];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from service_schedule_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("service_id");
					data1[i][1] = rs2.getString("service_avaldate");
					data1[i][2] = rs2.getString("service_start");
					data1[i][3] = rs2.getString("service_end");
					data1[i][4] = rs2.getString("service_schedule_id");
					data1[i][5] = rs2.getString("schedual_notes");
					i++;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
	    final JTable table = new JTable(data1, columnName);
        table.setPreferredScrollableViewportSize(new Dimension(800,120));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 500, 780, 120);
        panel.add(scrollPane);
	}
	public int correct_date()
	{
		String docid = ser_id.getSelectedItem().toString();
		String hour = timein1.getValue().toString();
		String minute = timein2.getValue().toString();
		String avaldate="",tin="",tout="";
		try {
			//Class.forName(com.mysql.jdbc.Driver.class.getName());
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			try{
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from service_schedule_details where service_id like '"+docid+"'");
				while(rs2.next()){
					avaldate = rs2.getString("service_avaldate");
					tin = rs2.getString("service_start");
					tout = rs2.getString("service_end");
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		String date = text.getText();
		String[] sourceArray = date.split("-");
		int year = Integer.parseInt(sourceArray[0]);
		int month = Integer.parseInt(sourceArray[1]);
		int day = Integer.parseInt(sourceArray[2]);
		System.out.println(year+"   "+month+"   "+day);
		int m1 = 0;
		if(month == 5)
			m1 = 0;
		if(month == 8)
			m1 = 1;
		if(month == 2 || month == 3 || month == 11)
			m1 = 2;
		if(month == 6)
			m1 = 3;
		if(month == 9 || month == 12)
			m1 = 4;
		if(month == 4 || month == 7)
			m1 = 5;
		if(month == 1 ||  month == 10)
			m1 = 6;
		if(year % 4 == 0)//ÈòÄê
		{
			if(month == 2)
				m1 = 1;
			if(month == 1)
				m1 = 5;
		}
		int y1;
		int xingqi;
		String s = String.valueOf(year);
		s = s.substring(2, 4);
		System.out.println("-------"+s);
		y1 = Integer.parseInt(s);
		y1 = (y1/4+10)%7;
		
		xingqi = (day+m1+y1)%7;  //0-6
		String xing = "";
		if(xingqi == 0)
			xing = "Sunday";
		if(xingqi == 1)
			xing = "Monday";
		if(xingqi == 2)
			xing = "Tuesday";
		if(xingqi == 3)
			xing = "Wednesday";
		if(xingqi == 4)
			xing = "Thursday";
		if(xingqi == 5)
			xing = "Friday";
		if(xingqi == 6)
			xing = "Saturday";
		System.out.println("week is "+xing);
		
		if(avaldate.contains(xing))
		{
			int hour1 = Integer.parseInt(hour);
			int minite1 = Integer.parseInt(minute);
			String[] sourceArray1 = tin.split(":");
			int tin1 = Integer.parseInt(sourceArray1[0]);//hour
			int tin2 = Integer.parseInt(sourceArray1[1]);//minute
			String[] sourceArray2 = tout.split(":");
			int tout1 = Integer.parseInt(sourceArray2[0]);//hour
			int tout2 = Integer.parseInt(sourceArray2[1]);//minute
			if(hour1 > tin1 && hour1 < tout1)
				return 1;  //ok
			if(hour1 == tin1)
			{
				if(minite1 >= tin2)
					return 1;
				else
					return 2; //error
			}
			if(hour1 == tout2)
			{
				if(minite1 <= tout2)
					return 1;
				else
					return 2;
			}
		}
		return 2;
	}
	public void reminder(String s){
    	JFrame jf = new JFrame();
    	jf.setSize(300,200);
    	jf.setLayout(null);
        jf.setTitle("reminder");
        jf.setLocation(450,180);
        //jp.setLayout(null);;
        jf.setVisible(true);
        JLabel l1 = new JLabel();
        l1.setText("Add "+s+" !");
        l1.setBounds(60, 30, 200, 50);
        JButton b1 = new JButton("ok");
        b1.setBounds(120, 100, 60, 30);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //y();
            	jf.setVisible(false);
		        //rt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        jf.add(l1);
        jf.add(b1);
    }
	public void save()
	{
		String apid = a_id.getText();
		String paid = pat_id.getSelectedItem().toString();
		String seid = ser_id.getSelectedItem().toString();
		String date = text.getText();
		String time = timein1.getValue().toString()+":"+timein2.getValue().toString();
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			try{
				Statement stmt = (Statement) con.createStatement();
				int rs = stmt.executeUpdate("insert into service_appointment (appointment_id,patient_id,hospital_service_id,appointment_date,appointment_time) "
						+ "values('"+apid+"','"+paid+"','"+seid+"','"+date+"','"+time+"')");
				if(rs > 0){
					System.out.println("add success");
					reminder("successsfully");
				}
				else{
					System.out.println("add failed");
					reminder("failed");
				}   				
				con.commit();
			}catch(Exception ex){
				con.rollback();
				ex.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
	}
	public String newid()
	{
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			try{
				Statement stmt = (Statement) con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * from service_appointment where appointment_id = (SELECT max(appointment_id) from service_appointment)");
				if(rs.next()){
					String id = rs.getString("appointment_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "APID_"+myid;
					return newid;
				}
				else{
					return "APID_1";
				}
			}catch(Exception e){
				return "APID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		return "";
	}
}
