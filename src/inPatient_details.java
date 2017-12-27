import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


public class inPatient_details extends HFrame{
	//JPanel panel;
	JLabel title,l2,tip1,tip2,tip3,tip4;
	JComboBox status,sex;
	JButton ps,ds,save,close,ok,cancel,back1,guardian;
	String currentID,labels[] = {"Firstname:","DOB:","Weight(kg):","Blood Group:","NIC Number:","Home Number:","Notes"};
	String labels2[] = {"Lastname:","Sex:","Height(cm):","Address:","Mobile Phone:","Status:"};
	JTextField text,inpid,fname,lname,dob,weight,blood,nic,hphone,note,height,address,mphone;
	int[] move_x = {120,210,440,530};
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
    inPatient_details()
    {
    	this("IPID_1");
    }
	inPatient_details(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("in Patient Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        JLabel id = new JLabel("Patient ID:");
        id.setBounds(100, 100, 100, 20);
        panel.add(id);
        
        inpid = new JTextField(newid());
        inpid.setBounds(200, 100, 150, 20);
        panel.add(inpid);
        inpid.setEditable(false);
        

        tip4 = new JLabel("info can't be empty");
		tip4.setBounds(450, 100, 200, 20);
		tip4.setForeground(Color.red);
		panel.add(tip4);
		tip4.setVisible(false);
        
        l2 = new JLabel("Personal Details--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 120, 800, 10);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        for(int i=0;i<7;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(100, 140+i*40, 100, 20);
        	panel.add(l);
        }
        
        fname = new JTextField("");
        fname.setBounds(200, 140, 150, 20);
        panel.add(fname);
        fname.addKeyListener(new KeyListener(){//only can write char
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        		}
        		else if((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)){
        			//char
        			
        		}
        		else{
        			//no
        			e.consume();
        		}
        	}
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        	@Override
        	public void keyPressed(KeyEvent e){
        		
        	}
        	
        });
        
        tip3 = new JLabel("Should before today");
		tip3.setBounds(200, 160, 150, 20);
		tip3.setForeground(Color.red);
		panel.add(tip3);
		tip3.setVisible(false);
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(200, 180, 150, 20);
        text.setText("2017-10-11");
        ser.register(text);
        panel.add(text);
        
        tip1 = new JLabel("Should large than 0");
		tip1.setBounds(200, 200, 150, 20);
		tip1.setForeground(Color.red);
		panel.add(tip1);
		tip1.setVisible(false);
        
        weight = new JTextField("");
        weight.setBounds(200, 220, 150, 20);
        panel.add(weight);
        weight.addKeyListener(new KeyListener(){//can write number, 
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        		}
        		else if(temp >= 48 && temp <= 57){
        			//number
        		}
        		else{
        			//no
        			e.consume();
        		}
        	}
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        	@Override
        	public void keyPressed(KeyEvent e){
        		
        	}
        	
        });
        
        blood = new JTextField("");
        blood.setBounds(200, 260, 150, 20);
        panel.add(blood);
        
        nic = new JTextField("");
        nic.setBounds(200, 300, 150, 20);
        panel.add(nic);
        nic.addKeyListener(new KeyListener(){//can write number, 
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        		}
        		else if(temp >= 48 && temp <= 57){
        			//number
        		}
        		else{
        			//no
        			e.consume();
        		}
        	}
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        	@Override
        	public void keyPressed(KeyEvent e){
        		
        	}
        	
        });
        
        hphone = new JTextField("");
        hphone.setBounds(200, 340, 150, 20);
        panel.add(hphone);
        hphone.addKeyListener(new KeyListener(){//can write number, +
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        		}
        		else if(temp >= 48 && temp <= 57){
        			//number
        		}
        		else if(temp == 43){
        			//+
        		}
        		else{
        			//no
        			e.consume();
        		}
        	}
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        	@Override
        	public void keyPressed(KeyEvent e){
        		
        	}
        	
        });
        
        note = new JTextField("");
        note.setBounds(200, 380, 150, 20);
        panel.add(note);
        
        for(int i=0;i<6;i++)
        {
        	JLabel l= new JLabel(labels2[i]);
        	l.setBounds(400, 140+i*40, 100, 20);
        	panel.add(l);
        }
        
        lname = new JTextField("");
        lname.setBounds(520, 140, 150, 20);
        panel.add(lname);
        lname.addKeyListener(new KeyListener(){//only can write char
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        		}
        		else if((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)){
        			//char
        			
        		}
        		else{
        			//no
        			e.consume();
        		}
        	}
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        	@Override
        	public void keyPressed(KeyEvent e){
        		
        	}
        	
        });
        
        String[] sex1 = {"Male","Female"};
        sex = new JComboBox<Object>(sex1);
        sex.setBounds(520, 180, 150, 20);
        panel.add(sex);
        
        tip2 = new JLabel("Should large than 0");
		tip2.setBounds(520, 200, 150, 20);
		tip2.setForeground(Color.red);
		panel.add(tip2);
		tip2.setVisible(false);
        
        height = new JTextField("");
        height.setBounds(520, 220, 150, 20);
        panel.add(height);
        height.addKeyListener(new KeyListener(){//can write number, 
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        		}
        		else if(temp >= 48 && temp <= 57){
        			//number
        		}
        		else{
        			//no
        			e.consume();
        		}
        	}
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        	@Override
        	public void keyPressed(KeyEvent e){
        		
        	}
        	
        });
        
        address = new JTextField("");
        address.setBounds(520, 260, 150, 20);
        panel.add(address);
        address.addKeyListener(new KeyListener(){//can write char,number, -
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        		}
        		else if((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)){
        			//char	
        		}
        		else if(temp >= 48 && temp <= 57){
        			//number
        		}
        		else if(temp == 45){
        			//-
        		}
        		else{
        			//no
        			e.consume();
        		}
        	}
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        	@Override
        	public void keyPressed(KeyEvent e){
        		
        	}
        	
        });
        
        mphone = new JTextField("");
        mphone.setBounds(520, 300, 150, 20);
        panel.add(mphone);
        mphone.addKeyListener(new KeyListener(){//can write number, +
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        		}
        		else if(temp >= 48 && temp <= 57){
        			//number
        		}
        		else if(temp == 43){
        			//+
        		}
        		else{
        			//no
        			e.consume();
        		}
        	}
        	@Override
        	public void keyReleased(KeyEvent e){
        		
        	}
        	@Override
        	public void keyPressed(KeyEvent e){
        		
        	}
        	
        });
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(520, 340, 150, 20);
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
            		currentID = "IPID_"+myid;
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
            		currentID = "IPID_"+myid;
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
        
        guardian = new JButton("guardian");
        guardian.setBounds(620,430, 100, 30);
        panel.add(guardian);
        guardian.addActionListener(new ActionListener() { //end
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	Guardian_details gd = new Guardian_details("GDID_1");
            	gd.setVisible(true);
            	gd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            }
        });
        
        String[] sourceArray = ID.split("_");
        record = new JTextField("          record  "+Integer.parseInt(sourceArray[1]));
        record.setBounds(310, 430, 120, 30);
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
            	if(truevalue())
            	{
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
            	inPatient_all rall = new inPatient_all();
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
            	if(truevalue())
            	{
            		addnew();
                	look();
                	getInfo(currentID);
                	for(int i=0;i<4;i++)
                	{
                		move[i].setEnabled(true);
                		guardian.setEnabled(true);
                	}
            		for(int i=0;i<6;i++)
            			action[i].setVisible(true);
            		for(int i=6;i<8;i++)
            			action[i].setVisible(false);
            	}
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
            		guardian.setEnabled(true);
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
				ResultSet rs = stmt.executeQuery("SELECT * from in_patient_details where patient_id = (SELECT max(patient_id) from in_patient_details)");
				if(rs.next()){
					String id = rs.getString("patient_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "IPID_"+myid;
					return newid;
				}
				else{
					return "IPID_1";
				}
			}catch(Exception e){
				return "IPID_1";
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
				ResultSet rs = stmt.executeQuery("SELECT * from in_patient_details where patient_id = (SELECT max(patient_id) from in_patient_details)");
				while(rs.next()){
					String id = rs.getString("patient_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from in_patient_details where patient_id = (SELECT min(patient_id) from in_patient_details)");
				while(rs.next()){
					String id = rs.getString("patient_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from in_patient_details where patient_id like '"+thisid+"'");
				while(rs.next()){
					inpid.setText(rs.getString("patient_id"));
					fname.setText(rs.getString("patient_fname"));
					lname.setText(rs.getString("patient_iname"));
					text.setText(rs.getString("patient_dob"));
					sex.setSelectedItem(rs.getString("patient_sex"));
					nic.setText(rs.getString("patient_NID"));
					hphone.setText(rs.getString("patient_hphone"));
					mphone.setText(rs.getString("patient_mphone"));
					address.setText(rs.getString("patient_address"));
					height.setText(rs.getString("patient_height"));
					weight.setText(rs.getString("patient_weight"));
					blood.setText(rs.getString("patient_blood_group"));
					note.setText(rs.getString("patient_notes"));
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
		fname.setEditable(false);
		text.setEditable(false);
		weight.setEditable(false);
		blood.setEditable(false);
		nic.setEditable(false);
		hphone.setEditable(false);
		note.setEditable(false);
		lname.setEditable(false);
		height.setEditable(false);
		address.setEditable(false);
		mphone.setEditable(false);
		tip1.setVisible(false);
		tip2.setVisible(false);
		tip3.setVisible(false);
	}
	public void add()
	{
		inpid.setText(newid());
		fname.setEditable(true);
		fname.setText("");
		text.setEditable(false);
		weight.setEditable(true);
		weight.setText("");
		blood.setEditable(true);
		blood.setText("");
		nic.setEditable(true);
		nic.setText("");
		hphone.setEditable(true);
		hphone.setText("");
		note.setEditable(true);
		note.setText("");
		lname.setEditable(true);
		lname.setText("");
		height.setEditable(true);
		height.setText("");
		address.setEditable(true);
		address.setText("");
		mphone.setEditable(true);
		mphone.setText("");
		for(int i=0;i<4;i++)
		{
    		move[i].setEnabled(false);
    		guardian.setEnabled(false);
    	}
		for(int i=0;i<6;i++)
			action[i].setVisible(false);
		for(int i=6;i<8;i++)
			action[i].setVisible(true);
	}
	public void edit()
	{
		fname.setEditable(true);
		text.setEditable(false);
		weight.setEditable(true);
		blood.setEditable(true);
		nic.setEditable(true);
		hphone.setEditable(true);
		note.setEditable(true);
		lname.setEditable(true);
		height.setEditable(true);
		address.setEditable(true);
		mphone.setEditable(true);
	}
	public void addnew()
	{
		String info[] = new String[14];
		info[0] = inpid.getText();
		info[1] = fname.getText();
		info[2] = lname.getText();
		info[3] = text.getText();
		info[4] = sex.getSelectedItem().toString();
		info[5] = nic.getText();
		info[6] = hphone.getText();
		info[7] = mphone.getText();
		info[8] = address.getText();
		info[9] = height.getText();
		info[10] = weight.getText();
		info[11] = blood.getText();
		info[12] = note.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[13] = "Y";
		else
			info[13] = "N";
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
				int rs = stmt.executeUpdate("insert into in_patient_details (patient_id,patient_fname,patient_iname,patient_dob,patient_sex,patient_NID,patient_hphone,patient_mphone,patient_address,patient_height,patient_weight,patient_blood_group,patient_notes,status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]+"','"+info[7]+"','"+info[8]+"','"+info[9]+"','"+info[10]+"','"+info[11]+"','"+info[12]+"','"+info[13]+"')");
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
		String info[] = new String[14];
		info[0] = inpid.getText();
		info[1] = fname.getText();
		info[2] = lname.getText();
		info[3] = text.getText();
		info[4] = sex.getSelectedItem().toString();
		info[5] = nic.getText();
		info[6] = hphone.getText();
		info[7] = mphone.getText();
		info[8] = address.getText();
		info[9] = height.getText();
		info[10] = weight.getText();
		info[11] = blood.getText();
		info[12] = note.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[13] = "Y";
		else
			info[13] = "N";
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
				int rs = stmt.executeUpdate("update in_patient_details set "
						+ "patient_fname='"+info[1]+"',patient_iname='"+info[2]+"',patient_dob='"+info[3]+"',patient_sex='"+info[4]+"',patient_NID='"+info[5]+"',patient_hphone='"+info[6]
								+"',patient_mphone='"+info[7]+"',patient_address='"+info[8]+"',patient_height='"+info[9]+"',patient_weight='"+info[10]+"',patient_blood_group='"+info[11]
										+"',patient_notes='"+info[12]+"',status='"+info[13]+"'where patient_id like '"+info[0]+"'");
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
	public boolean truevalue()
	{
		
		timetest t = new timetest();
		if(t.before(text)){
			tip3.setVisible(false);
		}
		else{
			tip3.setVisible(true);
		}
		String f = fname.getText();
		String l = lname.getText();
		String n = nic.getText();
		String hp = hphone.getText();
		String mp = mphone.getText();
		String a = address.getText();
		String he = height.getText();
		String we = weight.getText();
		String b = blood.getText();
		int nonull = 0;
		int w_h = 0;
		if(f.equals("")||l.equals("")||n.equals("")||hp.equals("")||mp.equals("")||a.equals("")||he.equals("")||we.equals("")||b.equals(""))
		{
			tip4.setVisible(true);
			nonull = 0;
		}
		else{
			tip4.setVisible(false);
			nonull = 1;
			int w = Integer.parseInt(weight.getText());
			int h = Integer.parseInt(height.getText());
			if(w > 0){
				tip1.setVisible(false);
				w_h++;
			}
			else{
				tip1.setVisible(true);
			}
			if(h > 0){
				tip2.setVisible(false);
				w_h++;
			}
			else
				tip2.setVisible(true);
		}
		if(t.before(text)&&nonull==1&&w_h==2)
			return true;
		else
			return false;
	}
}

