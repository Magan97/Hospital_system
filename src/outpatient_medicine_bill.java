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


public class outpatient_medicine_bill extends HFrame{
	//JPanel panel;
	JLabel title,l2;
	JComboBox status,inpat_id,treat_id;
	JButton ps,ds,save,close,ok,cancel,back1,painfo,trinfo;
	String currentID;
	String[] labels1 = {"Medicine bill id:","patient id:","treatment id:","bill date:","medicine charge:"};
	String[] labels2 = {"hospital charge:","grand total:","discount:","net value:","status:"};
	JTextField bid,medi_c,hos_c,text,grand,discount,net;
	int[] move_x = {120,220,540,640};;
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
    ArrayList<String> pid = new ArrayList<String>();
    ArrayList<String> tid = new ArrayList<String>();
    outpatient_medicine_bill()
    {
    	this("MBID_1");
    }
    outpatient_medicine_bill(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Outpatient Medicine Bill");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Outpatient Medicine Bill--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 120, 800, 10);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        for(int i=0;i<5;i++)
        {
        	JLabel l= new JLabel(labels1[i]);
        	l.setBounds(100, 140+i*60, 120, 20);
        	panel.add(l);
        	
        	l= new JLabel(labels2[i]);
        	l.setBounds(450, 140+i*60, 120, 20);
        	panel.add(l);
        }
        
        bid = new JTextField("");
        bid.setBounds(220, 140, 150, 20);
        panel.add(bid);
        bid.setEditable(false);
        
        getid();
        int size1 = pid.size();
        String[] inpaid = new String[size1];
        for(int i=0;i<size1;i++){
        	inpaid[i] = (String)pid.get(i); 
        }
        inpat_id = new JComboBox<Object>(inpaid);
        inpat_id.setBounds(220, 200, 120, 20);
        panel.add(inpat_id);
        inpat_id.addItemListener(new ItemListener(){
        	@Override
        	public void itemStateChanged(ItemEvent e){
        		if(e.getStateChange() == ItemEvent.SELECTED){        			
        			treat_id.removeAllItems();
        			get_treatment();
        			int size1 = tid.size();
                    String[] trid = new String[size1];
                    for(int i=0;i<size1;i++){
                    	trid[i] = (String)tid.get(i); 
                    	treat_id.addItem(trid[i]);
                    }
                    System.out.println("should print treatment"+size1);;
        		}
        	}
        });
        
        painfo = new JButton("..");
        painfo.setBounds(360, 200, 50, 20);
        panel.add(painfo);
        painfo.addActionListener(new ActionListener() { //room table
            @Override
            public void actionPerformed(ActionEvent e) {
            	patable();
            }
        });
        
        size1 = tid.size();
        String[] trid = new String[size1];
        for(int i=0;i<size1;i++){
        	trid[i] = (String)tid.get(i); 
        }
        treat_id = new JComboBox<Object>(trid);
        treat_id.setBounds(220, 260, 120, 20);
        panel.add(treat_id);
        treat_id.addItemListener(new ItemListener(){
        	@Override
        	public void itemStateChanged(ItemEvent e){
        		if(e.getStateChange() == ItemEvent.SELECTED){        			
        			get_charge();
        			System.out.println("charge");
        		}
        	}
        });
        
        trinfo = new JButton("..");
        trinfo.setBounds(360, 260, 50, 20);
        panel.add(trinfo);
        trinfo.addActionListener(new ActionListener() { //room table
            @Override
            public void actionPerformed(ActionEvent e) {
            	trtable();
            }
        });
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(220, 320, 150, 20);
        text.setText("2017-12-19");
        ser.register(text);
        panel.add(text);
        
        medi_c = new JTextField("");
        medi_c.setBounds(220, 380, 150, 20);
        panel.add(medi_c);
        
        hos_c = new JTextField("");
        hos_c.setBounds(570, 140, 150, 20);
        panel.add(hos_c); 
        
        grand = new JTextField("");
        grand.setBounds(570, 200, 150, 20);
        panel.add(grand);
        
        discount = new JTextField("");
        discount.setBounds(570, 260, 150, 20);
        panel.add(discount);
        
        net = new JTextField("");
        net.setBounds(570, 320, 150, 20);
        panel.add(net);
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(570, 380, 150, 20);
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
            		currentID = "MBID_"+myid;
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
            		currentID = "MBID_"+myid;
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
            	//
            	outpatient_medicine_bill_all rall = new outpatient_medicine_bill_all();
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
				ResultSet rs = stmt.executeQuery("select * from patient_details");
				while(rs.next()){
					String id = rs.getString("patient_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_medicine_bill where medicine_bill_id = (SELECT max(medicine_bill_id) from outpatient_medicine_bill)");
				if(rs.next()){
					String id = rs.getString("medicine_bill_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "MBID_"+myid;
					return newid;
				}
				else{
					return "MBID_1";
				}
			}catch(Exception e){
				return "MBID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_medicine_bill where medicine_bill_id = (SELECT max(medicine_bill_id) from outpatient_medicine_bill)");
				while(rs.next()){
					String id = rs.getString("medicine_bill_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_medicine_bill where medicine_bill_id = (SELECT min(medicine_bill_id) from outpatient_medicine_bill)");
				while(rs.next()){
					String id = rs.getString("medicine_bill_id");
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
	public void getInfo(String thibid)
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_medicine_bill where medicine_bill_id like '"+thibid+"'");
				while(rs.next()){
					bid.setText(thibid);
					inpat_id.setSelectedItem(rs.getString("patient_id"));
					treat_id.setSelectedItem(rs.getString("treatment_id"));
					text.setText(rs.getString("bill_date"));
					medi_c.setText(rs.getString("medicine_charge"));
					hos_c.setText(rs.getString("hospital_charge"));
					grand.setText(rs.getString("grand_total"));
					discount.setText(rs.getString("discount"));
					net.setText(rs.getString("net_value"));
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
		medi_c.setEditable(false);
		hos_c.setEditable(false);
		grand.setEditable(false);
		discount.setEditable(false);
		net.setEditable(false);
	}
	public void add()
	{
		bid.setText(newid());
		/*
		medi_c.setEditable(true);
		hos_c.setEditable(true);*/
		medi_c.setText("");
		hos_c.setText("");
		grand.setText("");
		discount.setText("");
		net.setText("");
		for(int i=0;i<4;i++)
    		move[i].setEnabled(false);
		for(int i=0;i<6;i++)
			action[i].setVisible(false);
		for(int i=6;i<8;i++)
			action[i].setVisible(true);
	}
	public void edit()
	{
		/*
		medi_c.setEditable(true);
		hos_c.setEditable(true);*/
	}
	public void addnew()
	{
		String info[] = new String[10];
		info[0] = bid.getText();
		info[1] = inpat_id.getSelectedItem().toString();
		info[2] = treat_id.getSelectedItem().toString();
		info[3] = text.getText();
		info[4] = medi_c.getText();
		info[5] = hos_c.getText();
		info[6] = grand.getText();
		info[7] = discount.getText();
		info[8] = net.getText();
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
				int rs = stmt.executeUpdate("insert into outpatient_medicine_bill (medicine_bill_id,patient_id,treatment_id,bill_date,medicine_charge,hospital_charge,grand_total,discount,net_value,status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]+"','"+info[7]+"','"+info[8]+"','"+info[9]+"')");
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
		String info[] = new String[10];
		info[0] = bid.getText();
		info[1] = inpat_id.getSelectedItem().toString();
		info[2] = treat_id.getSelectedItem().toString();
		info[3] = text.getText();
		info[4] = medi_c.getText();
		info[5] = hos_c.getText();
		info[6] = grand.getText();
		info[7] = discount.getText();
		info[8] = net.getText();
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
				int rs = stmt.executeUpdate("update outpatient_medicine_bill set "
						+ "patient_id='"+info[1]+"',treatment_id='"+info[2]+"',bill_date='"+info[3]
								+"',medicine_charge='"+info[4]+"',hospital_charge='"+info[5]+"',grand_total='"+info[6]+"',discount='"+info[7]+"',net_value='"+info[8]+"',status='"+info[9]+"'where medicine_bill_id like '"+info[0]+"'");
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
	public void patable()
	{
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel title1 = new JLabel("Out Patients");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"patient id","name","gender","telephone","status"};
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
			int num=0;
			try{
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select count(*) from patient_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					num+=n;
				}
				data1 = new Object[num][5];
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from patient_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("patient_id");
					data1[i][1] = rs2.getString("firstname")+rs2.getString("lastname");
					data1[i][2] = rs2.getString("gender");
					data1[i][3] = rs2.getString("telephone");
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
	public void trtable()
	{
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel title1 = new JLabel("Out Patients");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
        String patient = inpat_id.getSelectedItem().toString();
		String[] columnName = {"treatment id","patient id","prescription id","Date","status"};
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
			int num=0;
			try{
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select count(*) from outpatient_treatment where patient_id like '"+patient+"'");
				if(rs1.next()){
					int n = rs1.getInt(1);
					num+=n;
				}
				data1 = new Object[num][5];
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from outpatient_treatment where patient_id like '"+patient+"'");
				while(rs2.next()){
					data1[i][0] = rs2.getString("treatment_id");
					data1[i][1] = rs2.getString("patient_id");
					data1[i][2] = rs2.getString("medicine_bill_id");
					data1[i][3] = rs2.getString("Date");
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
	public void get_treatment()
	{
		String patient = inpat_id.getSelectedItem().toString();
		try {
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			int num=0;
			try{
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from outpatient_treatment where patient_id like '"+patient+"'");
				while(rs2.next()){
					String id = rs2.getString("treatment_id");
					tid.add(id);
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
	}
	public void get_charge()
	{
		String treat = treat_id.getSelectedItem().toString();
		String pre="";
		String med="";
		int price=0;
		int hosc=20;
		int disc=9;
		try {
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			int num=0;
			try{
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from outpatient_treatment where treatment_id like '"+treat+"'");
				while(rs2.next()){
					pre = rs2.getString("prescription_id");
				}
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select * from prescription_details where prescription_id like '"+pre+"'");
				while(rs1.next()){
					med = rs1.getString("medicine_service_id");						
				}
				Statement stmt3 = (Statement) con.createStatement();
				ResultSet rs3 = stmt3.executeQuery("select * from medicine_details where product_id like '"+med+"'");
				while(rs3.next()){
					price = rs3.getInt("unit_price");						
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
		medi_c.setText(price+"");
		hos_c.setText("20");
		grand.setText(price+hosc+"");
		discount.setText(disc+"");
		net.setText(((price+hosc)*disc/10)+"");
	}

}

