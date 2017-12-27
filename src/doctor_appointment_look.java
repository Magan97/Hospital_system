import java.awt.Button;
import java.awt.Color;
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
import com.swing.test.timetest;


public class doctor_appointment_look extends HFrame{
	//JPanel panel;
	JLabel title,l2,l7,l6,tip1,tip2;
	JComboBox status,pat_id,doc_id;
	JButton ps,ds,save,close,ok,cancel,back1;
	String currentID,labels[] = {"Appointment ID:","Patient ID:","Doctor ID:","Date:","Time:","Status:"};
	JTextField text;
	int[] move_x = {120,220,540,640};;
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close"};
    JTextField record,a_id;
    ArrayList<String> did = new ArrayList<String>();
	ArrayList<String> pid = new ArrayList<String>();
	JSpinner timein1,timein2;
    doctor_appointment_look()
    {
    	this("APID_1");
    }
	doctor_appointment_look(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Add Doctor Appointment");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Appointment Details--------------------------------------------------------");
        l2.setBounds(80, 100, 500, 20);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        for(int i=0;i<6;i++)
        {
        	l7 = new JLabel(labels[i]);
            l7.setBounds(200, 130+50*i, 100, 20);
            panel.add(l7);
        }       
        
        a_id = new JTextField("");
        a_id.setBounds(350, 130, 150, 20);
        panel.add(a_id);
        a_id.setEditable(false);

        getid();
        int size1 = pid.size();
        String[] patid = new String[size1];
        for(int i=0;i<size1;i++){
        	patid[i] = (String)pid.get(i); 
        }
        pat_id = new JComboBox<Object>(patid);
        pat_id.setBounds(350, 180, 150, 20);
        panel.add(pat_id);
        
        ps = new JButton("info");
        ps.setBounds(520, 180, 80, 20);
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
        doc_id.setBounds(350, 230, 150, 20);
        panel.add(doc_id);
        
        ds = new JButton("info");
        ds.setBounds(520, 230, 80, 20);
        panel.add(ds);
        ds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	doc_table();
            }
        });
        
        tip1 = new JLabel("Date must after this day");
		tip1.setBounds(350, 260, 200, 20);
		tip1.setForeground(Color.red);
		panel.add(tip1);
		tip1.setVisible(false);
		
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(350, 280, 150, 20);
        text.setText("2013-10-11");
        ser.register(text);
        panel.add(text);
        
		tip2 = new JLabel("time is not available");
		tip2.setBounds(350, 310, 150, 20);
		tip2.setForeground(Color.red);
		panel.add(tip2);
		tip2.setVisible(false);
        
        timein1 = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        timein1.setBounds(350, 330, 50, 20);
        panel.add(timein1);
        
        l6 = new JLabel("hour");
        l6.setBounds(410, 330, 30, 20);
        panel.add(l6);
        
        timein2 = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        timein2.setBounds(440, 330, 50, 20);
        panel.add(timein2);
        
        l7 = new JLabel("minute");
        l7.setBounds(500, 330, 50, 20);
        panel.add(l7);      
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(350, 380, 150, 20);
        panel.add(status);
       
        int j;
        for(j=0;j<4;j++)
        {
        	move[j] = new JButton(move_content[j]);
        	move[j].setBounds(move_x[j], 430, 70, 30);
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
            		currentID = "APID_"+myid;
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
            		currentID = "APID_"+myid;
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
        action[0].addActionListener(new ActionListener() { //add
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	AddDoctorAppointment a = new AddDoctorAppointment();
            	a.setVisible(true);
            	a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
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
            	if(truevalue()){
            		save();
                	look();
            	}         	
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
            	doctor_appointment_look_all rall = new doctor_appointment_look_all();
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
        table.setPreferredScrollableViewportSize(new Dimension(350,250));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(520, 130, 350, 250);
        panel.add(scrollPane);
	}

	public int correct_date()
	{
		String docid = doc_id.getSelectedItem().toString();
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_appointment where appointment_id = (SELECT max(appointment_id) from doctor_appointment)");
				while(rs.next()){
					String id = rs.getString("appointment_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_appointment where appointment_id = (SELECT min(appointment_id) from doctor_appointment)");
				while(rs.next()){
					String id = rs.getString("appointment_id");
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
	public void getInfo(String thihid)
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_appointment where appointment_id like '"+thihid+"'");
				while(rs.next()){
					a_id.setText(thihid);
					pat_id.setSelectedItem(rs.getString("patient_id"));
					doc_id.setSelectedItem(rs.getString("doctor_id"));
					text.setText(rs.getString("appointment_date"));					
					String time = rs.getString("appointment_time");
					String[] sourceArray = time.split(":");
					System.out.println(sourceArray[0]+":"+sourceArray[1]);
					timein1.setValue(Integer.parseInt(sourceArray[0]));
					timein2.setValue(Integer.parseInt(sourceArray[1]));					
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
		text.setEditable(false);
		timein1.setEnabled(false);
		timein2.setEnabled(false);
	}
	public void edit()
	{
		text.setEditable(false);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
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
		String info[] = new String[6];
		info[0] = a_id.getText();
		info[1] = pat_id.getSelectedItem().toString();
		info[2] = doc_id.getSelectedItem().toString();
		info[3] = text.getText();
		info[4] = timein1.getValue().toString()+timein2.getValue().toString();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[5] = "Y";
		else
			info[5] = "N";
		timetest t = new timetest();
		if(t.aftertoday(text)){
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
					int rs = stmt.executeUpdate("update doctor_appointment set "
							+ "patient_id='"+info[1]+"',doctor_id='"+info[2]+"',appointment_date='"+info[3]+"',appointment_time='"+info[4]+"',status='"+info[5]+"'where appointment_id like '"+info[0]+"'");
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
		}else{
			reminder("fails");
		}
		
	}
	public boolean truevalue()
	{
		timetest t = new timetest();
		if(t.after(text)){
			tip1.setVisible(false);
		}
		else{
			tip1.setVisible(true);
		}
		if(correct_date() == 1)
		{
			tip2.setVisible(false);
		}
		else{
			tip2.setVisible(true);
		}
		if(t.after(text) && correct_date() == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}
}

