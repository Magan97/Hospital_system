import java.awt.Button;
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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import com.mysql.jdbc.Statement;
import com.swing.test.calender;


public class Admission_details extends HFrame{
	//JPanel panel;
	JLabel title;
	JComboBox status,pat_id,guar_id,doc_id,room,ward,bed;
	ArrayList<String> did = new ArrayList<String>();
	ArrayList<String> pid = new ArrayList<String>();
	ArrayList<String> gid = new ArrayList<String>();
	ArrayList<String> rid = new ArrayList<String>();
	ArrayList<String> wid = new ArrayList<String>();
	ArrayList<String> bid_r = new ArrayList<String>();
	ArrayList<String> bid_w = new ArrayList<String>();
	JButton ps,ds,save,close,ok,cancel,back1,p_info,g_info,d_info,b_info,r_info,w_info;
	String currentID;
	JTextField text,admin_id,emer,avail;
	JSpinner timein1,timein2;
	int[] move_x = {120,220,540,640};
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
    ButtonGroup bg;
    JRadioButton rb1,rb2;
    Admission_details()
    {
    	this("ADID_1");
    }
	Admission_details(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Admission Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        JLabel l = new JLabel("Admission ID:");
        l.setBounds(100, 100, 100, 20);
        panel.add(l);
        
        admin_id = new JTextField(newid());
        admin_id.setBounds(200, 100, 150, 20);
        panel.add(admin_id);
        admin_id.setEditable(false);
        
        l = new JLabel("-------------------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l.setBounds(80, 120, 800, 10);
        l.setFont(new Font("Dialog",1,15));
        l.setSize(new Dimension(700,20));
        panel.add(l);
        
        l = new JLabel("Patient ID:");
        l.setBounds(100, 140, 100, 20);
        panel.add(l);
        
        getid();
        int size1 = pid.size();
        String[] patid = new String[size1];
        for(int i=0;i<size1;i++){
        	patid[i] = (String)pid.get(i); 
        }
        pat_id = new JComboBox<Object>(patid);
        pat_id.setBounds(200, 140, 150, 20);
        panel.add(pat_id);
        
        p_info = new JButton("in");
        p_info.setBounds(360, 140, 50, 20);
        panel.add(p_info);
        
        p_info.addActionListener(new ActionListener() { //patient table
            @Override
            public void actionPerformed(ActionEvent e) {
            	patient_table();
            }
        });
        
        l = new JLabel("Admission Date:");
        l.setBounds(100, 180, 100, 20);
        panel.add(l);
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(200, 180, 150, 20);
        text.setText("2017-10-11");
        ser.register(text);
        panel.add(text);
        
        l = new JLabel("Emergency:");
        l.setBounds(100, 220, 100, 20);
        panel.add(l);
        
        emer = new JTextField("");
        emer.setBounds(200, 220, 100, 20);
        panel.add(emer);
        
        l = new JLabel("Room / Ward -------------");
        l.setBounds(100, 260, 100, 20);
        panel.add(l);
        
        bg = new ButtonGroup();
        rb1 = new JRadioButton("Room", true);
        rb1.setOpaque(false);
        rb2 = new JRadioButton("Ward",false);
        rb2.setOpaque(false);
        Dimension drb = rb1.getPreferredSize();
        Dimension drb2 = rb2.getPreferredSize();
        rb1.setBounds(100, 280, drb.width, drb.height);
        rb2.setBounds(100, 320, drb2.width, drb2.height);
        bg.add(rb1);
        bg.add(rb2);
        panel.add(rb1);
        panel.add(rb2);
         
        
       
        int size2 = rid.size();
        String[] roid = new String[size2];
        for(int i=0;i<size2;i++){
        	roid[i] = (String)rid.get(i); 
        }
        room = new JComboBox<Object>(roid);
        room.setBounds(200, 280, 150, 20);
        panel.add(room);
        
        r_info = new JButton("in");
        r_info.setBounds(360, 280, 50, 20);
        panel.add(r_info);
        r_info.addActionListener(new ActionListener() { //room table
            @Override
            public void actionPerformed(ActionEvent e) {
            	room_table();
            }
        });
        
        
        int size3 = wid.size();
        String[] waid = new String[size3];
        for(int i=0;i<size3;i++){
        	waid[i] = (String)wid.get(i); 
        }
        ward = new JComboBox<Object>(waid);
        ward.setBounds(200, 320, 150, 20);
        panel.add(ward);
        
        w_info = new JButton("in");
        w_info.setBounds(360, 320, 50, 20);
        panel.add(w_info);
        w_info.addActionListener(new ActionListener() { //ward table
            @Override
            public void actionPerformed(ActionEvent e) {
            	ward_table();
            }
        });
        
        
        l = new JLabel("Guardian ID:");
        l.setBounds(430, 140, 100, 20);
        panel.add(l);
        
        
        int size4 = gid.size();
        String[] guid = new String[size4];
        for(int i=0;i<size4;i++){
        	guid[i] = (String)gid.get(i); 
        }
        guar_id = new JComboBox<Object>(guid);
        guar_id.setBounds(530, 140, 150, 20);
        panel.add(guar_id);
        
        g_info = new JButton("in");
        g_info.setBounds(700, 140, 50, 20);
        panel.add(g_info);
        
        g_info.addActionListener(new ActionListener() { //guardian table
            @Override
            public void actionPerformed(ActionEvent e) {
            	guar_table();
            }
        });
        
        l = new JLabel("Admission Time:");
        l.setBounds(430, 180, 100, 20);
        panel.add(l);
        
        timein1 = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        timein1.setBounds(530, 180, 50, 20);
        panel.add(timein1);
        
        l = new JLabel("hour");
        l.setBounds(580, 180, 30, 20);
        panel.add(l);
        
        timein2 = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        timein2.setBounds(620, 180, 50, 20);
        panel.add(timein2);
        
        l = new JLabel("minute");
        l.setBounds(670, 180, 50, 20);
        panel.add(l);
        
        l = new JLabel("Reffered Doctor:");
        l.setBounds(430, 220, 100, 20);
        panel.add(l);
        
        
        int size5 = did.size();
        String[] doid = new String[size5];
        for(int i=0;i<size5;i++){
        	doid[i] = (String)did.get(i); 
        }
        doc_id = new JComboBox<Object>(doid);
        doc_id.setBounds(530, 220, 150, 20);
        panel.add(doc_id);
        
        d_info = new JButton("in");
        d_info.setBounds(700, 220, 50, 20);
        panel.add(d_info);
        
        d_info.addActionListener(new ActionListener() { //patient table
            @Override
            public void actionPerformed(ActionEvent e) {
            	doc_table();
            }
        });
        
        l = new JLabel("Bed ID:");
        l.setBounds(430, 280, 100, 20);
        panel.add(l);
            
        if(rb1.isSelected())
        {
        	int size6 = bid_r.size();
            String[] beid = new String[size6];
            for(int i=0;i<size6;i++){
            	beid[i] = (String)bid_r.get(i); 
            }
            bed = new JComboBox<Object>(beid);
        }
        if(rb2.isSelected())
        {
        	int size6 = bid_w.size();
            String[] beid = new String[size6];
            for(int i=0;i<size6;i++){
            	beid[i] = (String)bid_w.get(i); 
            }
            bed = new JComboBox<Object>(beid);
        }
        //bed = new JComboBox<Object>();
        bed.setBounds(530, 280, 150, 20);
        panel.add(bed);
        
        
        b_info = new JButton("in");
        b_info.setBounds(700, 280, 50, 20);
        panel.add(b_info);
        
        b_info.addActionListener(new ActionListener() { //bed table
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(rb1.isSelected())  //room
            	{
            		bed_table("RMID");
            	}
            	if(rb2.isSelected())
            	{
            		bed_table("WAID");
            	}
            }
        });
        
        l = new JLabel("Bed Available:");
        l.setBounds(430, 320, 100, 20);
        panel.add(l);
        
        avail = new JTextField("Y");
        avail.setBounds(530, 320, 150, 20);
        panel.add(avail);
        
        l = new JLabel("Status:");
        l.setBounds(100, 360, 100, 20);
        panel.add(l);
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(200, 360, 150, 20);
        panel.add(status);
        
        rb1.addItemListener(new ItemListener() 
        {  
            public void itemStateChanged(ItemEvent e) {
                if (rb1.isSelected()) {
                	bed.removeAllItems();
                	System.out.println("State changed 1");
                	int size6 = bid_r.size();
                    String[] beid = new String[size6];
                    for(int i=0;i<size6;i++){
                    	beid[i] = (String)bid_r.get(i); 
                    	bed.addItem(beid[i]);
                    }     
                    
                } 
            }
          }); 
        rb2.addItemListener(new ItemListener() 
        {  
            public void itemStateChanged(ItemEvent e) {   
            	if(rb2.isSelected()){
            		System.out.println("State changed 2");
                	int size6 = bid_w.size();
                    String[] beid = new String[size6];
                    bed.removeAllItems();
                    for(int i=0;i<size6;i++){
                    	beid[i] = (String)bid_w.get(i); 
                    	bed.addItem(beid[i]);
                    }                                
            	}
            }
         });

       
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
            		currentID = "ADID_"+myid;
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
            		currentID = "ADID_"+myid;
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
        record.setBounds(310, 430, 200, 30);
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
            	Admission_all aa = new Admission_all();
            	aa.setVisible(true);
            	aa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            	
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
            	{
            		move[i].setEnabled(true);
            	}
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
			System.out.println("Can’t load the Driver");
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
        JLabel title1 = new JLabel("Doctor Infomation");
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
			System.out.println("Can’t load the Driver");
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
	public void guar_table() //information
	{
		JFrame l = new JFrame();
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        JLabel title1 = new JLabel("Guardian Infomation");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"guardian id","firstname","lastname","NIC","address","phone","status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from guardian_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][7];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from guardian_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("guardian_id");
					data1[i][1] = rs2.getString("guardian_fname");
					data1[i][2] = rs2.getString("guardian_lname");
					data1[i][3] = rs2.getString("guardian_NIC");
					data1[i][4] = rs2.getString("guardian_address");
					data1[i][5] = rs2.getString("guardian_phone");
					if(rs2.getString("status").indexOf("Y") != -1)
						data1[i][6] = "Y";
					else
						data1[i][6] = "N";
					i++;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(SQLException ex){
			System.out.println("Can’t load the Driver");
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
	public void room_table() //information
	{
		JFrame l = new JFrame();
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        JLabel title1 = new JLabel("Room Infomation");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"room id","type","description","status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from room_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][4];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from room_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("room_id");
					data1[i][1] = rs2.getString("room_rtype");
					data1[i][2] = rs2.getString("room_desc");
					if(rs2.getString("room_status").indexOf("Y") != -1)
						data1[i][3] = "Y";
					else
						data1[i][3] = "N";
					i++;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(SQLException ex){
			System.out.println("Can’t load the Driver");
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
	public void bed_table(String type) //information
	{
		JFrame l = new JFrame();
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        JLabel title1 = new JLabel("Bed Infomation");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"bed id","room/ward id","available","description"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from bed_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][4];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from bed_details where available like 'Y'");
				while(rs2.next()){
					String ty = rs2.getString("room_ward_id");
					String[] sourceArray = ty.split("_");
					String thistype = sourceArray[0];
					System.out.println(thistype+"="+type);
					if(thistype.indexOf(type)!=-1)
					{
						//System.out.println("++++");
						data1[i][0] = rs2.getString("bed_id");
						data1[i][1] = rs2.getString("room_ward_id");
						data1[i][3] = rs2.getString("bed_desc");
						if(rs2.getString("available").indexOf("Y") != -1)
							data1[i][2] = "Y";
						else
							data1[i][2] = "N";
						i++;
					}
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(SQLException ex){
			System.out.println("Can’t load the Driver");
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
	public void ward_table() //information
	{
		JFrame l = new JFrame();
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        JLabel title1 = new JLabel("Ward Infomation");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"ward id","name","rate","description","status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from ward_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][5];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from ward_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("ward_id");
					data1[i][1] = rs2.getString("ward_name");
					data1[i][2] = rs2.getString("ward_rate");
					data1[i][3] = rs2.getString("ward_desc");
					if(rs2.getString("status").indexOf("Y") != -1)
						data1[i][4] = "Y";
					else
						data1[i][4] = "N";
					i++;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(SQLException ex){
			System.out.println("Can’t load the Driver");
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
				ResultSet rs1 = stmt.executeQuery("select * from in_patient_details");
				while(rs1.next()){
					String id = rs1.getString("patient_id");
					pid.add(id);
				}
				ResultSet rs2 = stmt.executeQuery("select * from guardian_details");
				while(rs2.next()){
					String id = rs2.getString("guardian_id");
					gid.add(id);
				}
				ResultSet rs3 = stmt.executeQuery("select * from bed_details");
				while(rs3.next()){
					String id = rs3.getString("bed_id");
					String ty = rs3.getString("room_ward_id");
					String[] sourceArray = ty.split("_");
					String thistype = sourceArray[0];
					/*
					bid_r.add(id);
					bid_w.add(id);*/
					
					if(thistype.indexOf("RMID")!=-1)
						bid_r.add(id);
					else
						bid_w.add(id);
				}
				ResultSet rs4 = stmt.executeQuery("select * from room_details");
				while(rs4.next()){
					
					String id = rs4.getString("room_id");
					rid.add(id);
				}
				ResultSet rs5 = stmt.executeQuery("select * from ward_details");
				while(rs5.next()){
					String id = rs5.getString("ward_id");
					wid.add(id);
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can’t load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from admission_details where admission_id = (SELECT max(admission_id) from admission_details)");
				if(rs.next()){
					String id = rs.getString("admission_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "ADID_"+myid;
					return newid;
				}
				else{
					return "ADID_1";
				}
			}catch(Exception e){
				return "ADID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can’t load the Driver");
		}
		return "";
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
				ResultSet rs = stmt.executeQuery("SELECT * from admission_details where admission_id = (SELECT max(admission_id) from admission_details)");
				while(rs.next()){
					String id = rs.getString("admission_id");
					return id;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can’t load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from admission_details where admission_id = (SELECT min(admission_id) from admission_details)");
				while(rs.next()){
					String id = rs.getString("admission_id");
					return id;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can’t load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from admission_details where admission_id like '"+thisid+"'");
				while(rs.next()){
					admin_id.setText(rs.getString("admission_id"));
					pat_id.setSelectedItem(rs.getString("patient_id"));
					guar_id.setSelectedItem(rs.getString("guardian_id"));
					String id = rs.getString("room_ward_id");
					String[] sourceArray = id.split("_");
					if(sourceArray[0] == "ROID")
					{
						room.setSelectedItem(rs.getString("room_ward_id"));
						//选中组按钮
					}
					else{
						ward.setSelectedItem(rs.getString("room_ward_id"));
					}
					bed.setSelectedItem(rs.getString("bed_id"));
					doc_id.setSelectedItem(rs.getString("refer_doctor"));
					text.setText(rs.getString("admission_date"));
					String t1 = rs.getString("admission_time");
					String[] sourceArray1 = t1.split(":");
					int t11 = Integer.parseInt(sourceArray1[0]);
					timein1.setValue(t11);
					int t12 = Integer.parseInt(sourceArray1[1]);
					timein2.setValue(t12);
					emer.setText(rs.getString("emergency_contact"));
					String ss = rs.getString("status");
					if(ss.indexOf("Y") != -1)
						status.setSelectedItem("Y-available");
					else
						status.setSelectedItem("N-leaving");
				}
			}catch(Exception e){			
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can’t load the Driver");
		}
		String[] sourceArray = currentID.split("_");
        record.setText("          Record  "+Integer.parseInt(sourceArray[1]));
	}
	public void look()
	{
		emer.setEditable(false);
		timein1.setEnabled(false);
		timein2.setEnabled(false);
		avail.setEnabled(false);
	}
	public void add()
	{
		admin_id.setText(newid());
		pat_id.setSelectedIndex(0);
		emer.setEditable(true);
		emer.setText("");
		room.setSelectedIndex(0);
		ward.setSelectedIndex(0);
		guar_id.setSelectedIndex(0);
		timein1.setEnabled(true);
		//timein1.setValue("0");
		timein2.setEnabled(true);
		//timein2.setValue("0");
		doc_id.setSelectedIndex(0);
		bed.setSelectedIndex(0);
		avail.setEditable(true);
		
		for(int i=0;i<4;i++)
		{
    		move[i].setEnabled(false);
    	}
		for(int i=0;i<6;i++)
			action[i].setVisible(false);
		for(int i=6;i<8;i++)
			action[i].setVisible(true);
	}
	public void edit()
	{
		emer.setEditable(true);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
	}
	public void addnew()
	{
		String info[] = new String[10];
		info[0] = admin_id.getText();
		info[1] = pat_id.getSelectedItem().toString();
		info[2] = guar_id.getSelectedItem().toString();
		if(rb1.isSelected())			
			info[3] = room.getSelectedItem().toString();
		else
			info[3] = ward.getSelectedItem().toString();
		info[4] = bed.getSelectedItem().toString();
		info[5] = doc_id.getSelectedItem().toString();
		info[6] = text.getText();
		info[7] = timein1.getValue()+":"+timein2.getValue();
		info[8] = emer.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[9] = "Y";
		else
			info[9] = "N";
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
				int rs = stmt.executeUpdate("insert into admission_details (admission_id, patient_id, guardian_id, room_ward_id, bed_id, refer_doctor, "
						+ "admission_date, admission_time, emergency_contact, status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]
								+"','"+info[7]+"','"+info[8]+"','"+info[9]+"')");
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
			System.out.println("Can’t load the Driver");
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
		String info[] = new String[10];
		info[0] = admin_id.getText();
		info[1] = pat_id.getSelectedItem().toString();
		info[2] = guar_id.getSelectedItem().toString();
		if(rb1.isSelected())			
			info[3] = room.getSelectedItem().toString();
		else
			info[3] = ward.getSelectedItem().toString();
		info[4] = bed.getSelectedItem().toString();
		info[5] = doc_id.getSelectedItem().toString();
		info[6] = text.getText();
		info[7] = timein1.getValue()+":"+timein2.getValue();
		info[8] = emer.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[9] = "Y";
		else
			info[9] = "N";
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
				int rs = stmt.executeUpdate("update admission_details set "
						+ "patient_id='"+info[1]+"',guardian_id='"+info[2]+"',room_ward_id='"+info[3]+"',bed_id='"+info[4]+"',refer_doctor='"+info[5]+"',admission_date='"+info[6]
								+"',admission_time='"+info[7]+"',emergency_contact='"+info[8]+"',status='"+info[9]+"'where admission_id like '"+info[0]+"'");
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
			System.out.println("Can’t load the Driver");
		}
	}
}

