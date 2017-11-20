import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class doctor_schedual extends JFrame{
	JPanel panel,panel1;
	JLabel title,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18;
	JTextField sid,days,note,cc,bs,record;
	JComboBox did,dt,sp,status;
	JButton first,last,next,end,add,edit,save,refresh,viewall,close,doctor_detail,update,cancel,ok;
	String currentSID,selectdays="";
	JSpinner timein1,timein2,timeout1,timeout2;
	JCheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7;
	ArrayList<JCheckBox> cbs = new ArrayList<JCheckBox>();
	ArrayList<String> mydid = new ArrayList<String>();
	doctor_schedual(String SID)
	{
		currentSID = SID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Doctor Appointment Schedualing");
        title.setBounds(290, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 20));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Schedule Details--------------------------------------------------------"
        		+ "----------------");
        l2.setBounds(80, 100, 500, 20);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        l1 = new JLabel("Schedual ID:");
        l1.setBounds(100, 130, 150, 20);
        panel.add(l1);
        
        sid = new JTextField("");
        sid.setBounds(270, 130, 200, 20);
        panel.add(sid);
        
        l3 = new JLabel("Doctor ID:");
        l3.setBounds(100, 160, 150, 20);
        panel.add(l3);
        
        getdid();
        int size = mydid.size();
        String[] docid = new String[size];
        for(int i=0;i<size;i++){
        	docid[i] = (String)mydid.get(i); 
        }
        did = new JComboBox<Object>(docid);
        did.setBounds(270, 160, 200, 20);
        panel.add(did);
        
        doctor_detail = new JButton("..");
        doctor_detail.setBounds(475, 160, 30, 20);
        panel.add(doctor_detail);
        doctor_detail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	doctor_table();
            }
        });
        
        l4 = new JLabel("Time in:");
        l4.setBounds(100, 190, 200, 20);
        panel.add(l4);
        
        timein1 = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        timein1.setBounds(270, 190, 50, 20);
        panel.add(timein1);
        
        l5 = new JLabel("hour");
        l5.setBounds(330, 190, 30, 20);
        panel.add(l5);
        
        timein2 = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        timein2.setBounds(360, 190, 50, 20);
        panel.add(timein2);
        
        l6 = new JLabel("minute");
        l6.setBounds(420, 190, 50, 20);
        panel.add(l6);
        
        l7 = new JLabel("Time out:");
        l7.setBounds(100, 220, 200, 20);
        panel.add(l7);
        
        timeout1 = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        timeout1.setBounds(270, 220, 50, 20);
        panel.add(timeout1);
        
        l8 = new JLabel("hour");
        l8.setBounds(330, 220, 30, 20);
        panel.add(l8);
        
        timeout2 = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        timeout2.setBounds(360, 220, 50, 20);
        panel.add(timeout2);
        
        l9 = new JLabel("minute");
        l9.setBounds(420, 220, 50, 20);
        panel.add(l9);
        
        l10 = new JLabel("Available Days:");
        l10.setBounds(100, 250, 150, 20);
        panel.add(l10);
        
        days = new JTextField("");
        days.setBounds(270, 250, 200, 20);
        panel.add(days);
        
        l11 = new JLabel("Schedual Notes:");
        l11.setBounds(100, 280, 150, 20);
        panel.add(l11);
        
        note = new JTextField("");
        note.setBounds(270, 280, 200, 20);
        panel.add(note);
        
        l14 = new JLabel("Schedule Status");
        l14.setBounds(100, 310, 150, 20);
        panel.add(l14);
        
                
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(270, 310, 200, 20);
        panel.add(status);
        
        l12 = new JLabel("Available Days------------------");
        l12.setBounds(630, 100, 200, 20);
        l12.setFont(new Font("Dialog",1,15));
        l12.setSize(new Dimension(700,20));
        panel.add(l12);
        
        cb1 = new JCheckBox("Monday");
        cb1.setBounds(660, 130, 100, 18);
        panel.add(cb1);
        
        cb2 = new JCheckBox("Tuesday");
        cb2.setBounds(660, 157, 100, 18);
        panel.add(cb2);
        
        cb3 = new JCheckBox("Wednesday");
        cb3.setBounds(660, 180, 100, 18);
        panel.add(cb3);
        
        cb4 = new JCheckBox("Thursday");
        cb4.setBounds(660, 203, 100, 18);
        panel.add(cb4);
        
        cb5 = new JCheckBox("Friday");
        cb5.setBounds(660, 226, 100, 18);
        panel.add(cb5);
        
        cb6 = new JCheckBox("Saturdayday");
        cb6.setBounds(660, 249, 100, 18);
        panel.add(cb6);
        
        cb7 = new JCheckBox("Sunday");
        cb7.setBounds(660, 272, 100, 18);
        panel.add(cb7);
        
        cblistener();
        
        l13 = new JLabel("------------------------------------------------------"
        		+ "------------------------------------------------------"
        		+ "------------------------------------------");
        l13.setBounds(80, 330, 800, 20);
        l13.setFont(new Font("Dialog",1,15));
        l13.setSize(new Dimension(750,20));
        panel.add(l13);
        schedual_table();
        
        first = new JButton("First");
        first.setBounds(120, 510, 80, 30);
        panel.add(first);
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentSID = minID();
            	nonadd();
                getInfo(currentSID);
                look();
            }
        });
       
        last = new JButton("Last");
        last.setBounds(220, 510, 80, 30);
        panel.add(last);
        last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String firstid = minID();
            	String[] sourceArray = currentSID.split("_");
            	if(Integer.parseInt(sourceArray[1])-1>=1){
            		int myid = Integer.parseInt(sourceArray[1])-1;
            		currentSID = "DSID_"+myid;
            	}
            	nonadd();
            	getInfo(currentSID);
                look();
            }
        });
        
        
        String[] sourceArray = SID.split("_");
        record = new JTextField("                       Record  "+Integer.parseInt(sourceArray[1]));
        record.setBounds(320, 510, 200, 30);
        panel.add(record);
        
        next = new JButton("Next");
        next.setBounds(540, 510 , 80, 30);
        panel.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String endid = maxID();
            	String[] thisArray = currentSID.split("_");
            	String[] endArray = endid.split("_");
            	if(Integer.parseInt(thisArray[1]) < Integer.parseInt(endArray[1])){
            		int myid = Integer.parseInt(thisArray[1])+1;
            		currentSID = "DSID_"+myid;
            	}
            	getInfo(currentSID);
                look();
            }
        });
        
        end = new JButton("End");
        end.setBounds(640, 510, 80, 30);
        panel.add(end);
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentSID = maxID();
            	nonadd();
                getInfo(currentSID);
                look();
            }
        });
        
        add = new JButton("Add");
        add.setBounds(150, 570, 80, 30);
        panel.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addmode();
            	edit();
            }
        });
        
        edit = new JButton("Edit");
        edit.setBounds(260, 570, 80, 30);
        panel.add(edit);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	edit();
            }
        });
        
        save = new JButton("Save");
        save.setBounds(370, 570, 80, 30);
        panel.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	save();
            	schedual_table();
            }
        });
        
        refresh = new JButton("Refresh");
        refresh.setBounds(480, 570, 80, 30);
        panel.add(refresh);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	nonadd();
                getInfo(currentSID);
                schedual_table();
                look();
            }
        });
        
        close =  new JButton("Close");
        close.setBounds(590, 570, 80, 30);
        panel.add(close);
        
        update = new JButton("Update");
        update.setBounds(260, 570, 80, 30);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addnew();
            	nonadd();
            	getInfo(currentSID);
            	schedual_table();
            }
        });
        
        cancel = new JButton("Cancel");
        cancel.setBounds(480, 570, 80, 30);
        panel.add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	nonadd();
            	getInfo(currentSID);
            	schedual_table();
            }
        });
        
        nonadd();
        getInfo(currentSID);
        look();
        this.add(panel);
	}
	public void getdid()
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
					mydid.add(id);
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
	public void schedual_table()
	{
		String[] columnName = {"Schedual ID", "Doctor_ID", "Time in","Time out","Available Days","Notes","Status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from doctor_schedule_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][7];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt1.executeQuery("select * from doctor_schedule_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("schedule_id");
					data1[i][1] = rs2.getString("doctor_id");
					data1[i][2] = rs2.getString("doctor_in");
					data1[i][3] = rs2.getString("doctor_out");
					data1[i][4] = rs2.getString("doctor_avaldate");
					data1[i][5] = rs2.getString("schedual_note");
					if(rs2.getString("schedule_status").indexOf("Y") != -1)
						data1[i][6] = "Y-available";
					else
						data1[i][6] = "N-leaving";
					//data1[i][6] = rs2.getString("schedule_status");
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
        scrollPane.setBounds(50, 360, 800, 120);
        panel.add(scrollPane);
	}
	public void addmode()
	{
		add.setVisible(false);
		edit.setVisible(false);
		save.setVisible(false);
		refresh.setVisible(false);
		close.setVisible(false);
		first.setEnabled(false);
		last.setEnabled(false);
		next.setEnabled(false);
		last.setEnabled(false);
		end.setEnabled(false);
		l12.setVisible(true);
		cb1.setVisible(true);
		cb2.setVisible(true);
		cb3.setVisible(true);
		cb4.setVisible(true);
		cb5.setVisible(true);
		cb6.setVisible(true);
		cb7.setVisible(true);
		update.setVisible(true);
		cancel.setVisible(true);
		sid.setText(newid());
		sid.setEditable(false);
		days.setEditable(false);	
		did.setSelectedIndex(0);
		timein1.setValue(0);
		timein2.setValue(0);
		timeout1.setValue(0);
		timeout2.setValue(0);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
		timeout1.setEnabled(true);
		timeout2.setEnabled(true);
		days.setText("");
		note.setText("");
	}
	public void nonadd()
	{
		add.setVisible(true);
		edit.setVisible(true);
		save.setVisible(true);
		refresh.setVisible(true);
		close.setVisible(true);
		l12.setVisible(false);
		cb1.setVisible(false);
		cb2.setVisible(false);
		cb3.setVisible(false);
		cb4.setVisible(false);
		cb5.setVisible(false);
		cb6.setVisible(false);
		cb7.setVisible(false);
		update.setVisible(false);
		cancel.setVisible(false);
		first.setEnabled(true);
		last.setEnabled(true);
		next.setEnabled(true);
		last.setEnabled(true);
		record.setEditable(false);
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_schedule_details where schedule_id = (SELECT max(schedule_id) from doctor_schedule_details)");
				if(rs.next()){
					String id = rs.getString("schedule_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "DSID_"+myid;
					return newid;
				}
				else{
					return "DSID_1";
				}
			}catch(Exception e){
				return "DSID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		return "";
	}
	public void addnew()
	{
		String sid1 = sid.getText();
		String did1 = did.getSelectedItem().toString();
		String t1 = timein1.getValue()+":"+timein2.getValue();
		System.out.println("timein is "+t1);
		String t2 = timeout1.getValue()+":"+timeout2.getValue();
		String selecteddays = days.getText();
		String note1 = note.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			status1 = "Y";
		else
			status1 = "N";
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
				int rs = stmt.executeUpdate("insert into doctor_schedule_details (schedule_id,doctor_id,doctor_in,doctor_out,doctor_avaldate,schedual_note,schedule_status) "
						+ "values('"+sid1+"','"+did1+"','"+t1+"','"+t2+"','"+selecteddays+"','"+note1+"','"+status1+"')");
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
	public void cblistener()
	{
        cb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cb1.isSelected()){
            		selectdays = selectdays+cb1.getText()+" ";
            	}
            	else{
            		selectdays = selectdays.replaceAll(cb1.getText(), "");
            	}
            	days.setText(selectdays);
            }
        });
        cb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cb2.isSelected()){
            		selectdays = selectdays+cb2.getText()+" ";
            	}
            	else{
            		selectdays = selectdays.replaceAll(cb2.getText(), "");
            	}
            	days.setText(selectdays);
            }
        });
        cb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cb3.isSelected())
            		selectdays = selectdays+cb3.getText()+" ";
            	else
            		selectdays = selectdays.replaceAll(cb3.getText(), "");
            	days.setText(selectdays);
            }
        });
        cb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cb4.isSelected())
            		selectdays = selectdays+cb4.getText()+" ";
            	else
            		selectdays = selectdays.replaceAll(cb4.getText(), "");
            	days.setText(selectdays);
            }
        });
        cb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cb5.isSelected())
            		selectdays = selectdays+cb5.getText()+" ";
            	else
            		selectdays = selectdays.replaceAll(cb5.getText(), "");
            	days.setText(selectdays);
            }
        });
        cb6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cb6.isSelected())
            		selectdays = selectdays+cb6.getText()+" ";
            	else
            		selectdays = selectdays.replaceAll(cb6.getText(), "");
            	days.setText(selectdays);
            }
        });
        cb7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(cb7.isSelected())
            		selectdays = selectdays+cb7.getText()+" ";
            	else
            		selectdays = selectdays.replaceAll(cb7.getText(), "");
            	days.setText(selectdays);
            }
        });
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
                //dispose();
            	jf.setVisible(false);
		        //rt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        jf.add(l1);
        jf.add(b1);
    }
	public void getInfo(String cid)
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_schedule_details where schedule_id like '"+cid+"'");
				while(rs.next()){
					sid.setText(rs.getString("schedule_id"));
					did.setSelectedItem(rs.getString("doctor_id"));
					String t1 = rs.getString("doctor_in");
					String[] sourceArray1 = t1.split(":");
					int t11 = Integer.parseInt(sourceArray1[0]);
					timein1.setValue(t11);
					int t12 = Integer.parseInt(sourceArray1[1]);
					timein2.setValue(t12);
					String t2 = rs.getString("doctor_out");
					String[] sourceArray2 = t2.split(":");
					int t21 = Integer.parseInt(sourceArray2[0]);
					timeout1.setValue(t21);
					int t22 = Integer.parseInt(sourceArray2[1]);
					timeout2.setValue(t22);
					days.setText(rs.getString("doctor_avaldate"));
					note.setText(rs.getString("schedual_note"));
					String ss = rs.getString("schedule_status");
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
		String[] sourceArray = currentSID.split("_");
        record.setText("                       Record  "+Integer.parseInt(sourceArray[1]));
	}
	public void look()
	{
		sid.setEditable(false);
		did.setEditable(false);
		timein1.setEnabled(false);
		timein2.setEnabled(false);
		timeout1.setEnabled(false);
		timeout2.setEnabled(false);
		days.setEditable(false);
		note.setEditable(false);
	}
	public void edit()
	{
		sid.setEditable(false);
		did.setEditable(true);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
		timeout1.setEnabled(true);
		timeout2.setEnabled(true);
		days.setEditable(true);
		note.setEditable(true);
		l12.setVisible(true);
		cb1.setVisible(true);
		cb2.setVisible(true);
		cb3.setVisible(true);
		cb4.setVisible(true);
		cb5.setVisible(true);
		cb6.setVisible(true);
		cb7.setVisible(true);
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_schedule_details where schedule_id = (SELECT max(schedule_id) from doctor_schedule_details)");
				while(rs.next()){
					String id = rs.getString("schedule_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_schedule_details where schedule_id = (SELECT min(schedule_id) from doctor_schedule_details)");
				while(rs.next()){
					String id = rs.getString("schedule_id");
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
	public void save()
	{
		String sid1 = sid.getText();
		String did1 = did.getSelectedItem().toString();
		String t1 = timein1.getValue()+":"+timein2.getValue();
		System.out.println("timein is "+t1);
		String t2 = timeout1.getValue()+":"+timeout2.getValue();
		String selecteddays = days.getText();
		String note1 = note.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			status1 = "Y";
		else
			status1 = "N";
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
				int rs = stmt.executeUpdate("update doctor_schedule_details set doctor_id='"+did1+"',doctor_in='"+t1+"',doctor_out='"+t2+"',doctor_avaldate='"+selecteddays+"'"
						+ ",schedual_note='"+note1+"',schedule_status='"+status1+"'where schedule_id like '"+sid1+"'");
				if(rs > 0)
					System.out.println("add success and influence "+rs);
				else{
					System.out.println("add failed");
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
	public void doctor_table(){
		JFrame l = new JFrame();
		l.setLayout(null);
		l.setSize(300,350);
        l.setTitle("dortors");
        l.setLocation(450,180);
        
        String[] columnName = {"ID", "Name", "Status"};
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
					data1 = new Object[n][3];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from doctor_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("doctor_id");
					data1[i][1] = rs2.getString("doctor_fname")+rs2.getString("doctor_lname");
					data1[i][2] = rs2.getString("doctor_status");
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
        table.setPreferredScrollableViewportSize(new Dimension(260,180));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 30, 260, 180);
        l.add(scrollPane);
        ok = new JButton("OK");
        ok.setBounds(110, 220,60, 30);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
            	l.setVisible(false);
            }
        });
        l.add(ok);
        l.setVisible(true);
	}
}

