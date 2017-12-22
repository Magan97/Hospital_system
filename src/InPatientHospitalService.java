import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class InPatientHospitalService extends HFrame{
	//JPanel panel;
	JLabel title,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	JComboBox adm_id,pat_id,ser_id;
	JButton ps,as,ss,save,close,ok,cancel,back1;
	ArrayList<String> aid = new ArrayList<String>();
	ArrayList<String> pid = new ArrayList<String>();
	ArrayList<String> sid = new ArrayList<String>();
	JSpinner timein1,timein2;
	JTextField text,b_id,p_name,date,desc,ser_n,ser_c;
	String[] labels = {"Patient ID:","Patient Name:","Admission ID:","Treatment Date:","Treatment Time:","Discription:"};
	String[] labels2 = {"Service ID:","Service Name:","Service Charge:","Status:"};
	int[] move_x = {120,210,550,640};
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
    JComboBox status;
    String currentID;
 	InPatientHospitalService()
	{
		this("IPHS_1");
	}
	InPatientHospitalService(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("In Patient Hospital Service");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Patient Details--------------------------------------");
        l2.setBounds(80, 130, 300, 20);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(300,20));
        panel.add(l2);
        
        l8 = new JLabel("Service Details---------------------------------------");
        l8.setBounds(430, 130, 300, 20);
        l8.setFont(new Font("Dialog",1,15));
        l8.setSize(new Dimension(300,20));
        panel.add(l8);
        
        l7 = new JLabel("Bill ID:");
        l7.setBounds(300, 100, 60, 20);
        panel.add(l7);
        
        b_id = new JTextField(newid());
        b_id.setBounds(360, 100, 120, 20);
        panel.add(b_id);
        b_id.setEditable(false);
        
        l1 = new JLabel("Bill Date:");
        l1.setBounds(520, 100, 60, 20);
        panel.add(l1);
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(580, 100, 120, 20);
        text.setText("2017-12-17");
        ser.register(text);
        panel.add(text);
        
        JLabel l;
        for(int i=0;i<6;i++)
        {
        	l = new JLabel(labels[i]);
        	l.setBounds(100, 160+50*i, 100, 20);
        	panel.add(l);
        }
        
        getid();
        int size1 = pid.size();
        String[] patid = new String[size1];
        for(int i=0;i<size1;i++){
        	patid[i] = (String)pid.get(i); 
        }
        pat_id = new JComboBox<Object>(patid);
        pat_id.setBounds(220, 160, 150, 20);
        panel.add(pat_id);
        pat_id.addItemListener(new ItemListener(){
        	@Override
        	public void itemStateChanged(ItemEvent e){
        		if(e.getStateChange() == ItemEvent.SELECTED){
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
        					String pid = pat_id.getSelectedItem().toString();
        					ResultSet rs = stmt.executeQuery("select * from in_patient_details where patient_id like '"+pid+"'");
        					while(rs.next()){
        						String name1 = rs.getString("patient_fname");
        						String name2 = rs.getString("patient_iname");
        						p_name.setText(name1+name2);
        					}
        					con.commit();
        				}catch(Exception e1){
        					con.rollback();
        					e1.printStackTrace();
        					System.out.println("failed");
        				}
        				
        			}catch(ClassNotFoundException | SQLException ex){
        				System.out.println("Can¡¯t load the Driver");
        			}
        		}
        	}
        });
        
        ps = new JButton("..");
        ps.setBounds(380, 160, 50, 20);
        panel.add(ps);
        ps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	patient_table();
            }
        });
        
        p_name = new JTextField("");
        p_name.setBounds(220, 210, 150, 20);
        panel.add(p_name);
        
        int size = aid.size();
        String[] admid = new String[size];
        for(int i=0;i<size;i++){
        	admid[i] = (String)aid.get(i); 
        }
        adm_id = new JComboBox<Object>(admid);
        adm_id.setBounds(220, 260, 150, 20);
        panel.add(adm_id);
        
        as = new JButton("..");
        as.setBounds(380, 260, 50, 20);
        panel.add(as);
        as.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	adm_table();
            }
        });
        
        calender ser1 = calender.getInstance();
        date = new JTextField();
        date.setBounds(220, 310, 150, 20);
        date.setText("2017-12-17");
        ser1.register(date);
        panel.add(date);             
        
        timein1 = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        timein1.setBounds(220, 360, 50, 20);
        panel.add(timein1);
        
        l6 = new JLabel("hour");
        l6.setBounds(280, 360, 30, 20);
        panel.add(l6);
        
        timein2 = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        timein2.setBounds(310, 360, 50, 20);
        panel.add(timein2);
        
        l7 = new JLabel("minute");
        l7.setBounds(370, 360, 50, 20);
        panel.add(l7);
        
        desc = new JTextField("");
        desc.setBounds(220, 410, 150, 20);
        panel.add(desc);
        
        for(int i=0;i<4;i++)
        {
        	l = new JLabel(labels2[i]);
        	l.setBounds(450, 160+50*i, 100, 20);
        	panel.add(l);
        }
      
        int size2 = sid.size();
        String[] serid = new String[size2];
        for(int i=0;i<size2;i++){
        	serid[i] = (String)sid.get(i); 
        }
        ser_id = new JComboBox<Object>(serid);
        ser_id.setBounds(550, 160, 150, 20);
        panel.add(ser_id);
        ser_id.addItemListener(new ItemListener(){
        	@Override
        	public void itemStateChanged(ItemEvent e){
        		if(e.getStateChange() == ItemEvent.SELECTED){
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
        					String sid = ser_id.getSelectedItem().toString();
        					ResultSet rs = stmt.executeQuery("select * from services where service_id like '"+sid+"'");
        					while(rs.next()){
        						String n = rs.getString("service_name");
        						String c = rs.getString("charge_for_service");
        						ser_n.setText(n);
        						ser_c.setText(c);
        					}
        					con.commit();
        				}catch(Exception e1){
        					con.rollback();
        					e1.printStackTrace();
        					System.out.println("failed");
        				}
        				
        			}catch(ClassNotFoundException | SQLException ex){
        				System.out.println("Can¡¯t load the Driver");
        			}
        		}
        	}
        });
        ss = new JButton("..");
        ss.setBounds(720, 160, 50, 20);
        panel.add(ss);
        ss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ser_table();
            }
        });
        
        ser_n = new JTextField("");
        ser_n.setBounds(550, 210, 150, 20);
        panel.add(ser_n);
        
        ser_c = new JTextField("");
        ser_c.setBounds(550, 260, 150, 20);
        panel.add(ser_c);
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(550, 310, 150, 20);
        panel.add(status);
       
        int j;
        for(j=0;j<4;j++)
        {
        	move[j] = new JButton(move_content[j]);
        	move[j].setBounds(move_x[j], 460, 70, 30);
        	panel.add(move[j]);
        }
        move[0].addActionListener(new ActionListener() { //first
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentID = minID();
                getInfo(currentID);
                look();
            }
        });
        move[1].addActionListener(new ActionListener() { //last
            @Override
            public void actionPerformed(ActionEvent e) {
            	String firstid = minID();
            	String[] sourceArray = currentID.split("_");
            	if(Integer.parseInt(sourceArray[1])-1>=1){
            		int myid = Integer.parseInt(sourceArray[1])-1;
            		currentID = "IPHS_"+myid;
            	}
            	getInfo(currentID);
                look();
            }
        });
        move[2].addActionListener(new ActionListener() { //next
            @Override
            public void actionPerformed(ActionEvent e) {
            	String endid = maxID();
            	String[] thisArray = currentID.split("_");
            	String[] endArray = endid.split("_");
            	if(Integer.parseInt(thisArray[1]) < Integer.parseInt(endArray[1])){
            		int myid = Integer.parseInt(thisArray[1])+1;
            		currentID = "IPHS_"+myid;
            	}
            	getInfo(currentID);
                look();
            }
        });
        move[3].addActionListener(new ActionListener() { //end
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentID = maxID();
                getInfo(currentID);
                look();
            }
        });
        
        String[] sourceArray = ID.split("_");
        record = new JTextField("          record  "+Integer.parseInt(sourceArray[1]));
        record.setBounds(320, 460, 200, 30);
        record.setEditable(false);
        panel.add(record);              
        
        for(int i=0;i<3;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 510, 80, 30);
        	panel.add(action[i]);
        }
        for(int i=3;i<6;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 570, 80, 30);
        	panel.add(action[i]);
        }
        for(int i=6;i<8;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 510, 80, 30);
        	panel.add(action[i]);
        }
        action[0].addActionListener(new ActionListener() { //add
            @Override
            public void actionPerformed(ActionEvent e) {
            	add();
            }
        });
        action[1].addActionListener(new ActionListener() { //edit
            @Override
            public void actionPerformed(ActionEvent e) {
            	edit();
            }
        });
        action[2].addActionListener(new ActionListener() { //save
            @Override
            public void actionPerformed(ActionEvent e) {
            	save();
            	look();
            }
        });
        action[3].addActionListener(new ActionListener() { //refresh
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(currentID);
            }
        });
        action[4].addActionListener(new ActionListener() { //search
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	//
            	InPatientHospitalService_all rall = new InPatientHospitalService_all();
            	rall.setVisible(true);
            	rall.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            	
            }
        });
        action[5].addActionListener(new ActionListener() { //back to main
            @Override
            public void actionPerformed(ActionEvent e) {
            	       	dispose();
            }
        });
        action[6].addActionListener(new ActionListener() { //update
            @Override
            public void actionPerformed(ActionEvent e) {
            	addnew();
            	look();
            	getInfo(currentID);
            	for(int i=0;i<4;i++)
            	{
            		move[i].setEnabled(true);
            	}
        		for(int i=0;i<6;i++)
        			action[i].setVisible(true);
        		for(int i=6;i<8;i++)
        			action[i].setVisible(false);
            }
        });
        action[7].addActionListener(new ActionListener() { //back
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(currentID);
            	for(int i=0;i<4;i++)
            		move[i].setEnabled(true);
        		for(int i=0;i<6;i++)
        			action[i].setVisible(true);
        		for(int i=6;i<8;i++)
        			action[i].setVisible(false);
            }
        });
        look();
        getInfo(currentID);
        
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
				ResultSet rs = stmt.executeQuery("select * from admission_details");
				while(rs.next()){
					String id = rs.getString("admission_id");
					aid.add(id);
				}
				ResultSet rs1 = stmt.executeQuery("select * from in_patient_details");
				while(rs1.next()){
					String id = rs1.getString("patient_id");
					pid.add(id);
				}
				ResultSet rs2 = stmt.executeQuery("select * from services");
				while(rs2.next()){
					String id = rs2.getString("service_id");
					sid.add(id);
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
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel title1 = new JLabel("IN Patient Infomation");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from in_patient_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][6];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from in_patient_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("patient_id");
					data1[i][1] = rs2.getString("patient_fname");
					data1[i][2] = rs2.getString("patient_iname");
					data1[i][3] = rs2.getString("patient_sex");
					data1[i][4] = rs2.getString("patient_address");
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
        table.setPreferredScrollableViewportSize(new Dimension(300,150));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 80, 300, 150);
        back1 = new JButton("Back");
        back1.setBounds(160, 260, 70, 30);
        back1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
            	l.setVisible(false);
            }
        });
        l.add(back1);
        l.add(scrollPane);
        l.setVisible(true);
	}
	public void adm_table()
	{
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel title1 = new JLabel("Admin Infomation");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"Admission id","Patient id","guardian id","date","time","status"};
		Object[][] data1 = null;
		try {
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			try{
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select count(*) from admission_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][6];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from admission_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("admission_id");
					data1[i][1] = rs2.getString("patient_id");
					data1[i][2] = rs2.getString("guardian_id");
					data1[i][3] = rs2.getString("admission_date");
					data1[i][4] = rs2.getString("admission_time");
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
        table.setPreferredScrollableViewportSize(new Dimension(300,150));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 80, 300, 150);
        back1 = new JButton("Back");
        back1.setBounds(160, 260, 70, 30);
        back1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
            	l.setVisible(false);
            }
        });
        l.add(back1);
        l.add(scrollPane);
        l.setVisible(true);
	}
	public void ser_table()
	{
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel title1 = new JLabel("Service Infomation");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"service id","service name","duration","charge","notes","status"};
		Object[][] data1 = null;
		try {
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
					data1 = new Object[n][6];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from services");
				while(rs2.next()){
					data1[i][0] = rs2.getString("service_id");
					data1[i][1] = rs2.getString("service_name");
					data1[i][2] = rs2.getString("duration_of_service");
					data1[i][3] = rs2.getString("charge_for_service");
					data1[i][4] = rs2.getString("service_notes");
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
        table.setPreferredScrollableViewportSize(new Dimension(300,150));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 80, 300, 150);
        back1 = new JButton("Back");
        back1.setBounds(160, 260, 70, 30);
        back1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
            	l.setVisible(false);
            }
        });
        l.add(back1);
        l.add(scrollPane);
        l.setVisible(true);
	}
	/*
	public int correct_date()
	{
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
				ResultSet rs2 = stmt2.executeQuery("select * from doctor_schedule_details where doctor_id like '"+docid+"'");
				while(rs2.next()){
					avaldate = rs2.getString("doctor_avaldate");
					tin = rs2.getString("doctor_in");
					tout = rs2.getString("doctor_out");
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
	}*/

	public String maxID(){
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
				ResultSet rs = stmt.executeQuery("SELECT * from inpatient_services where inpatient_service_id = (SELECT max(inpatient_service_id) from inpatient_services)");
				while(rs.next()){
					String id = rs.getString("inpatient_service_id");
					return id;
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
		return "";
	}
	public String minID(){
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
				ResultSet rs = stmt.executeQuery("SELECT * from inpatient_services where inpatient_service_id = (SELECT min(inpatient_service_id) from inpatient_services)");
				while(rs.next()){
					String id = rs.getString("inpatient_service_id");
					return id;
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
		return "";
	}
	public void getInfo(String thicid)
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
				ResultSet rs = stmt.executeQuery("SELECT * from inpatient_services where inpatient_service_id like '"+thicid+"'");
				while(rs.next()){
					b_id.setText(thicid);
					text.setText(rs.getString("bill_date"));
					pat_id.setSelectedItem(rs.getString("inpatient_id"));
					p_name.setText(rs.getString("inpatient_name"));
					adm_id.setSelectedItem(rs.getString("admission_id"));
					date.setText(rs.getString("service_date"));
					String t = rs.getString("service_time");
					String[] sourceArray = t.split(":");
					timein1.setValue(sourceArray[0].toString());
					timein2.setValue(sourceArray[1].toString());
					desc.setText(rs.getString("description"));
					ser_id.setSelectedItem(rs.getString("hospital_service_id"));
					ser_n.setText(rs.getString("service_name"));
					ser_c.setText(rs.getString("service_charge"));
					String ss = rs.getString("status");
					if(ss.indexOf("Y") != -1)
						status.setSelectedItem("Y-available");
					else
						status.setSelectedItem("N-leaving");
				}
			}catch(Exception e){			
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		String[] sourceArray = currentID.split("_");
        record.setText("          Record  "+Integer.parseInt(sourceArray[1]));
	}
	public void look()
	{
		b_id.setEditable(false);
		text.setEditable(false);
		p_name.setEditable(false);
		date.setEditable(false);
		timein1.setEnabled(false);
		timein2.setEnabled(false);
		desc.setEditable(false);
		ser_n.setEditable(false);
		ser_c.setEditable(false);
		date.setEditable(false);
		timein1.setEnabled(false);
		timein2.setEnabled(false);
	}
	public void add()
	{
		b_id.setText(newid());
		b_id.setEditable(false);
		text.setEditable(true);
		p_name.setEditable(false);
		desc.setEditable(true);
		ser_n.setEditable(false);
		ser_c.setEditable(true);
		ser_c.setText("");
		desc.setEditable(true);
		desc.setText("");
		date.setEditable(true);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
		for(int i=0;i<4;i++)
    		move[i].setEnabled(false);
		for(int i=0;i<6;i++)
			action[i].setVisible(false);
		for(int i=6;i<8;i++)
			action[i].setVisible(true);
	}
	public void edit()
	{
		text.setEditable(true);
		date.setEditable(true);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
		desc.setEditable(true);
	}
	public void addnew()
	{
		String info[] = new String[12];
		info[0] = b_id.getText();
		info[1] = text.getText();
		info[2] = pat_id.getSelectedItem().toString();
		info[3] = p_name.getText();
		info[4] = adm_id.getSelectedItem().toString();
		info[5] = date.getText();
		info[6] = timein1.getValue().toString()+":"+timein2.getValue().toString();
		info[7] = desc.getText();
		info[8] = ser_id.getSelectedItem().toString();
		info[9] = ser_n.getText();
		info[10] = ser_c.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[11] = "Y";
		else
			info[11] = "N";
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
				int rs = stmt.executeUpdate("insert into inpatient_services (inpatient_service_id,bill_date,inpatient_id,inpatient_name,"
						+ "admission_id,service_date,service_time,description,hospital_service_id,service_name,service_charge,status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]+"','"+info[7]
								+"','"+info[8]+"','"+info[9]+"','"+info[10]+"','"+info[11]+"')");
				if(rs > 0){
					System.out.println("add success");
					reminder("successsfully");
				}
				else{
					System.out.println("add failed");
					reminder("failed");
				} 
				Statement stmt1 = (Statement) con.createStatement();
				
				int rs1 = stmt1.executeUpdate("insert into patient_bill_inpatient (patient_bill_id,patient_id,admission_id,service_charges,status) "
						+ "values('"+info[0]+"','"+info[2]+"','"+info[4]+"','"+info[10]+"','"+info[11]+"')");
				if(rs1 > 0){
					System.out.println("add success");
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
	public void reminder(String s){
    	JFrame jf = new JFrame();
    	jf.setSize(300,200);
    	jf.setLayout(null);
        jf.setTitle("reminder");
        jf.setLocation(450,180);
        jf.setVisible(true);
        JLabel l1 = new JLabel();
        l1.setText("Add "+s+" !");
        l1.setBounds(60, 30, 200, 50);
        JButton b1 = new JButton("ok");
        b1.setBounds(120, 100, 60, 30);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jf.setVisible(false);
            }
        });
        jf.add(l1);
        jf.add(b1);
    }
	public void save()
	{
		String info[] = new String[12];
		info[0] = b_id.getText();
		info[1] = text.getText();
		info[2] = pat_id.getSelectedItem().toString();
		info[3] = p_name.getText();
		info[4] = adm_id.getSelectedItem().toString();
		info[5] = date.getText();
		info[6] = timein1.getValue().toString()+":"+timein2.getValue().toString();
		info[7] = desc.getText();
		info[8] = ser_id.getSelectedItem().toString();
		info[9] = ser_n.getText();
		info[10] = ser_c.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[11] = "Y";
		else
			info[11] = "N";
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
				int rs = stmt.executeUpdate("update inpatient_services set "
						+ "bill_date='"+info[1]+"',inpatient_id='"+info[2]+"',inpatient_name='"+info[3]+"',admission_id='"+info[4]
								+"',service_date='"+info[5]+"',service_time='"+info[6]+"',description='"+info[7]
										+"',hospital_service_id='"+info[8]+"',service_name='"+info[9]+"',service_charge='"+info[10]+"',status='"+info[11]
												+"'where inpatient_service_id like '"+info[0]+"'");
				if(rs > 0)
					System.out.println("update success and influence "+rs);
				else{
					System.out.println("update failed");
				}
				Statement stmt1 = (Statement) con.createStatement();
				
				int rs1 = stmt1.executeUpdate("update patient_bill_inpatient set "
						+ "patient_id='"+info[2]+"',admission_id='"+info[4]+"',service_charges='"+info[10]+"',status='"+info[11]
												+"'where patient_bill_id like '"+info[0]+"'");
				if(rs1 > 0)
					System.out.println("update success and influence "+rs1);
				else{
					System.out.println("update failed");
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
				ResultSet rs = stmt.executeQuery("SELECT * from inpatient_services where inpatient_service_id = (SELECT max(inpatient_service_id) from inpatient_services)");
				if(rs.next()){
					String id = rs.getString("inpatient_service_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "IPHS_"+myid;
					return newid;
				}
				else{
					return "IPHS_1";
				}
			}catch(Exception e){
				return "IPHS_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		return "";
	}
}
