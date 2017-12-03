import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
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


public class Outpatient_treatments extends JFrame{
	JPanel panel,panel1;
	JLabel title,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,title1;
	JComboBox doc_id,pat_id,status;
	JButton ps,ds,save,close,ok,cancel,back1;
	String currentID,labels[] = {"Treatment ID:","Patient ID:","Doctor ID:","Date","Time","Descriptin","Prescription","Status"};
	JSpinner timein1,timein2;
	JTextField text,a_id,tid,des,pre;
	ArrayList<String> did = new ArrayList<String>();
	ArrayList<String> pid = new ArrayList<String>();
	int[] move_x = {120,220,540,640};
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
	public Outpatient_treatments(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Treatment Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        for(int i=0;i<8;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(150, 100+i*40, 150, 20);
        	panel.add(l);
        }
        
        tid = new JTextField(newid());
        tid.setBounds(380, 100, 150, 20);
        panel.add(tid);
        tid.setEditable(false);
        
        getid();
        int size1 = pid.size();
        String[] patid = new String[size1];
        for(int i=0;i<size1;i++){
        	patid[i] = (String)pid.get(i); 
        }
        pat_id = new JComboBox<Object>(patid);
        pat_id.setBounds(380, 140, 150, 20);
        panel.add(pat_id);
        
        ps = new JButton("info");
        ps.setBounds(560,140, 80, 20);
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
        doc_id = new JComboBox<Object>(docid);
        doc_id.setBounds(380, 180, 150, 20);
        panel.add(doc_id);
        ds = new JButton("info");
        ds.setBounds(560,180, 80, 20);
        panel.add(ds);
        ds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	doc_table();
            }
        });
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(380, 220, 150, 20);
        text.setText("2017-10-11");
        ser.register(text);
        panel.add(text);
        
        timein1 = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        timein1.setBounds(380, 260, 50, 20);
        panel.add(timein1);
        
        l6 = new JLabel("hour");
        l6.setBounds(430, 260, 30, 20);
        panel.add(l6);
        
        timein2 = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        timein2.setBounds(470, 260, 50, 20);
        panel.add(timein2);
        
        l7 = new JLabel("minute");
        l7.setBounds(520, 260, 50, 20);
        panel.add(l7);
        
        des = new JTextField("");
        des.setBounds(380, 300, 150, 30);
        panel.add(des);
        
        pre = new JTextField("");
        pre.setBounds(380, 340, 150, 30);
        panel.add(pre);
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(380, 380, 150, 20);
        panel.add(status);
        
        int j;
        for(j=0;j<4;j++)
        {
        	move[j] = new JButton(move_content[j]);
        	move[j].setBounds(move_x[j], 430, 80, 30);
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
            		currentID = "OTID_"+myid;
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
            		currentID = "OTID_"+myid;
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
        record = new JTextField("                       Record  "+Integer.parseInt(sourceArray[1]));
        record.setBounds(320, 430, 200, 30);
        record.setEditable(false);
        panel.add(record);
        
        for(int i=0;i<3;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 490, 80, 30);
        	panel.add(action[i]);
        }
        for(int i=3;i<6;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 550, 80, 30);
        	panel.add(action[i]);
        }
        for(int i=6;i<8;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 490, 80, 30);
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
            	outpatient_treatment_all rall = new outpatient_treatment_all();
            	rall.setVisible(true);
            	rall.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            	
            }
        });
        action[5].addActionListener(new ActionListener() { //back to main
            @Override
            public void actionPerformed(ActionEvent e) {
            	       	
            }
        });
        action[6].addActionListener(new ActionListener() { //update
            @Override
            public void actionPerformed(ActionEvent e) {
            	addnew();
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_treatment where treatment_id = (SELECT max(treatment_id) from outpatient_treatment)");
				if(rs.next()){
					String id = rs.getString("treatment_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "OTID_"+myid;
					return newid;
				}
				else{
					return "OTID_1";
				}
			}catch(Exception e){
				return "OTID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		return "";
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
				ResultSet rs = stmt.executeQuery("select * from doctor_details");
				while(rs.next()){
					String id = rs.getString("doctor_id");
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
		System.out.print("show patient table ");
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        title1 = new JLabel("Patient Infomation");
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
	public void doc_table() //information
	{
		JFrame l = new JFrame();
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        title1 = new JLabel("Doctor Infomation");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"doctor id","firstname","lastname","qualification","specialization","status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from doctor_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][6];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from doctor_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("doctor_id");
					data1[i][1] = rs2.getString("doctor_fname");
					data1[i][2] = rs2.getString("doctor_lname");
					data1[i][3] = rs2.getString("doctor_qualification");
					data1[i][4] = rs2.getString("doctor_specialization");
					if(rs2.getString("doctor_status").indexOf("Y") != -1)
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
        l.add(scrollPane);
        back1 = new JButton("Back");
        back1.setBounds(160, 260, 70, 30);
        back1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
            	l.setVisible(false);
            }
        });
        l.add(back1);
        l.setVisible(true);
	}
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_treatment where treatment_id = (SELECT max(treatment_id) from outpatient_treatment)");
				while(rs.next()){
					String id = rs.getString("treatment_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_treatment where treatment_id = (SELECT min(treatment_id) from outpatient_treatment)");
				while(rs.next()){
					String id = rs.getString("treatment_id");
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
	public void getInfo(String thisid)
	{
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int count = 1;
			int max = 0;
			try{
				Statement stmt = (Statement) con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_treatment where treatment_id like '"+thisid+"'");
				while(rs.next()){
					tid.setText(rs.getString("treatment_id"));
					pat_id.setSelectedItem(rs.getString("patient_id"));
					doc_id.setSelectedItem(rs.getString("doctor_id"));
					text.setText(rs.getString("Date"));
					String t1 = rs.getString("Time");
					String[] sourceArray1 = t1.split(":");
					int t11 = Integer.parseInt(sourceArray1[0]);
					timein1.setValue(t11);
					int t12 = Integer.parseInt(sourceArray1[1]);
					timein2.setValue(t12);
					des.setText(rs.getString("description"));
					pre.setText(rs.getString("prescription"));
					String ss = rs.getString("Status");
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
        record.setText("                       Record  "+Integer.parseInt(sourceArray[1]));
	}
	public void look()
	{
		text.setEditable(false);
		timein1.setEnabled(false);
		timein2.setEnabled(false);
		des.setEditable(false);
		pre.setEditable(false);
	}
	public void add()
	{
		tid.setText(newid()); 
		tid.setEditable(false);
		pat_id.setSelectedIndex(0);
		doc_id.setSelectedIndex(0);
		text.setEditable(true);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
		des.setText("");
		des.setEditable(true);
		pre.setText("");
		pre.setEditable(true);
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
		timein1.setEnabled(true);
		timein2.setEnabled(true);
		des.setEditable(true);
		pre.setEditable(true);
	}
	public void addnew()
	{
		String info[] = new String[8];
		info[0] = tid.getText();
		info[1] = pat_id.getSelectedItem().toString();
		info[2] = doc_id.getSelectedItem().toString();
		info[3] = text.getText();
		info[4] = timein1.getValue().toString()+":"+timein2.getValue().toString();
		info[5] = des.getText();
		info[6] = pre.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[7] = "Y";
		else
			info[7] = "N";
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
				int rs = stmt.executeUpdate("insert into outpatient_treatment (treatment_id,patient_id,doctor_id,Date,Time,description,prescription,Status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]+"','"+info[7]+"')");
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
		String info[] = new String[8];
		info[0] = tid.getText();
		info[1] = pat_id.getSelectedItem().toString();
		info[2] = doc_id.getSelectedItem().toString();
		info[3] = text.getText();
		info[4] = timein1.getValue().toString()+":"+timein2.getValue().toString();
		info[5] = des.getText();
		info[6] = pre.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[7] = "Y";
		else
			info[7] = "N";
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
				int rs = stmt.executeUpdate("update outpatient_treatment set "
						+ "patient_id='"+info[1]+"',doctor_id='"+info[2]+"',Date='"+info[3]+"',Time='"+info[4]+"',description='"+info[5]+"',prescription='"+info[6]+"',Status='"+info[7]+"'where treatment_id like '"+info[0]+"'");
				if(rs > 0)
					System.out.println("update success and influence "+rs);
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
}
