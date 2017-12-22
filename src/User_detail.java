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


public class User_detail extends HFrame{
	JPanel panel;
	JLabel title,l2;
	JComboBox status,sex;
	JButton ps,ds,save,close,ok,cancel,back1;
	String currentID,labels[] = {"username:","password:","first name:","last name:","type:","gender:","address:","telephone:","other details:",};
	String labels2[] = {"Lastname:","Sex:","Height(cm):","Address:","Mobile Phone:","Status:"};
	//JTextField text,inpid,fname,lname,dob,weight,blood,nic,hphone,note,height,address,mphone;
	JTextField UserID, username, pwd, fname, lname, type, gender, address, telephone, otherDetails;
	int[] move_x = {120,210,540,630};
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;

    User_detail(){
    	this("UID_1");
    }
    
	public User_detail(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("user Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        JLabel id = new JLabel("UID:");
        id.setBounds(100, 100, 100, 20);
        panel.add(id);

        UserID = new JTextField(newid());
        UserID.setBounds(200, 100, 150, 20);
        panel.add(UserID);
        UserID.setEditable(false);
        
        
        l2 = new JLabel("Personal Details--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 120, 800, 10);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        
        //****************************************************************************
        username = new JTextField("");
        username.setBounds(200, 140, 150, 20);
        panel.add(username);
        
        pwd = new JTextField("");
        pwd.setBounds(200, 180, 150, 20);
        panel.add(pwd);
        
        fname = new JTextField("");
        fname.setBounds(200, 220, 150, 20);
        panel.add(fname);
        
        lname = new JTextField("");
        lname.setBounds(200, 260, 150, 20);
        panel.add(lname);
        
        type = new JTextField("");
        type.setBounds(200, 300, 150, 20);
        panel.add(type);
        
        gender = new JTextField("");
        gender.setBounds(200, 340, 150, 20);
        panel.add(gender);
        
        address = new JTextField("");
        address.setBounds(200, 380, 150, 20);
        panel.add(address);
        
        telephone = new JTextField("");
        telephone.setBounds(200, 420, 150, 20);
        panel.add(telephone);
        
        otherDetails = new JTextField("");
        otherDetails.setBounds(200, 460, 150, 20);
        
        for(int i=0;i<9;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(100, 140+i*40, 100, 20);
        	panel.add(l);
        }
        
        
        /*
        for(int i=0;i<7;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(100, 140+i*40, 100, 20);
        	panel.add(l);
        }
        
        fname = new JTextField("");
        fname.setBounds(200, 140, 150, 20);
        panel.add(fname);
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(200, 180, 150, 20);
        text.setText("2017-10-11");
        ser.register(text);
        panel.add(text);
        
        weight = new JTextField("");
        weight.setBounds(200, 220, 150, 20);
        panel.add(weight);
        
        blood = new JTextField("");
        blood.setBounds(200, 260, 150, 20);
        panel.add(blood);
        
        nic = new JTextField("");
        nic.setBounds(200, 300, 150, 20);
        panel.add(nic);
        
        hphone = new JTextField("");
        hphone.setBounds(200, 340, 150, 20);
        panel.add(hphone);
        
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
        
        String[] sex1 = {"Male","Female"};
        sex = new JComboBox<Object>(sex1);
        sex.setBounds(520, 180, 150, 20);
        panel.add(sex);
        
        height = new JTextField("");
        height.setBounds(520, 220, 150, 20);
        panel.add(height);
        
        address = new JTextField("");
        address.setBounds(520, 260, 150, 20);
        panel.add(address);
        
        mphone = new JTextField("");
        mphone.setBounds(520, 300, 150, 20);
        panel.add(mphone);
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(520, 340, 150, 20);
        panel.add(status);
        */
       
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
            		currentID = "UID_"+myid;
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
            		currentID = "UID_"+myid;
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
        record.setBounds(345, 430, 120, 30);
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
            	inPatient_all rall = new inPatient_all();
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
				ResultSet rs = stmt.executeQuery("SELECT * from USERS where UID = (SELECT max(UID) from users)");
				if(rs.next()){
					String id = rs.getString("UID");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					System.out.println(myid);
					String newid = "UID_"+myid;
					System.out.println(newid);
					return newid;
				}
				else{
					return "UID_1";
				}
			}catch(Exception e){
				return "UID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can��t load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from users where UID = (SELECT max(UID) from users)");
				while(rs.next()){
					String id = rs.getString("UID");
					return id;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can��t load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from users where UID = (SELECT min(UID) from users)");
				while(rs.next()){
					String id = rs.getString("UID");
					return id;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can��t load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from users where UID like '"+thisid+"'");
				while(rs.next()){
					/*
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
					*/
					
					//********************************************************************
					UserID.setText(thisid);
					username.setText(rs.getString("user_name"));
					pwd.setText(rs.getString("user_password"));
					fname.setText(rs.getString("first_name"));
					lname.setText(rs.getString("last_name"));
					type.setText(rs.getString("type"));
					gender.setText(rs.getString("sex"));
					address.setText(rs.getString("address"));
					telephone.setText(rs.getString("telephone"));
					otherDetails.setText(rs.getString("other_details"));
				}
			}catch(Exception e){			
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can��t load the Driver");
		}
		String[] sourceArray = currentID.split("_");
        record.setText("          Record  "+Integer.parseInt(sourceArray[1]));
	}
	public void look()
	{
		/*
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
		*/
		
		username.setEditable(false);
		pwd.setEditable(false);
		fname.setEditable(false);
		lname.setEditable(false);
		type.setEditable(false);
		gender.setEditable(false);
		address.setEditable(false);
		telephone.setEditable(false);
		otherDetails.setEditable(false);
		
	}
	public void add()
	{
		/*
		inpid.setText(newid());
		fname.setEditable(true);
		fname.setText("");
		text.setEditable(true);
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
		*/
		//*****************************************************88
		UserID.setText(newid());
		username.setEditable(true);
		username.setText("");
		pwd.setEditable(true);
		pwd.setText("");
		fname.setEditable(true);
		fname.setText("");
		lname.setEditable(true);
		lname.setText("");
		type.setEditable(true);
		type.setText("");
		gender.setEditable(true);
		gender.setText("");
		address.setEditable(true);
		address.setText("");
		telephone.setEditable(true);
		telephone.setText("");
		otherDetails.setEditable(true);
		otherDetails.setText("");
		
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
		/*
		fname.setEditable(true);
		text.setEditable(true);
		weight.setEditable(true);
		blood.setEditable(true);
		nic.setEditable(true);
		hphone.setEditable(true);
		note.setEditable(true);
		lname.setEditable(true);
		height.setEditable(true);
		address.setEditable(true);
		mphone.setEditable(true);
		*/
		//************************************************************8
		username.setEditable(true);
		pwd.setEditable(true);
		fname.setEditable(true);
		lname.setEditable(true);
		gender.setEditable(true);
		type.setEditable(true);
		address.setEditable(true);
		telephone.setEditable(true);
		otherDetails.setEditable(true);
		
	}
	public void addnew()
	{
		//************************************************88
		String info[] = new String[9];
		info[0] = username.getText();
		info[1] = pwd.getText();
		info[2] = fname.getText();
		info[3] = lname.getText();
		info[4] = type.getText();
		info[5] = gender.getText();
		info[6] = address.getText();
		info[7] = telephone.getText();
		info[8] = otherDetails.getText();

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
				System.out.println(UserID);
				//*************************************************************************************************
				int rs = stmt.executeUpdate("insert into users (UID, user_name, user_password, first_name, last_name, type, sex, address, telephone, other_details) "
						+ "values('"+UserID.getText()+"','"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]+"','"+info[7]+"','"+info[8]+"')");
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
			System.out.println("Can��t load the Driver");
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
		String info[] = new String[9];
		/*
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
		*/
		//*******************************************************************************
		info[0] = username.getText();
		info[1] = pwd.getText();
		info[2] = fname.getText();
		info[3] = lname.getText();
		info[4] = type.getText();
		info[5] = gender.getText();
		info[6] = address.getText();
		info[7] = telephone.getText();
		info[8] = otherDetails.getText();

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
				//***********************************************************************************
				int rs = stmt.executeUpdate("update users set "
						+ "user_name'"+info[0]+"',user_password='"+info[1]+"',first_name='"+info[2]+"',lname='"+info[3]+"',type='"+info[4]+"',sex='"+info[5]
								+"',address='"+info[6]+"',telephone='"+info[7]+"',other_details='"+info[8]+"' where UID='"+currentID+"'");
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
			System.out.println("Can��t load the Driver");
			
		}
	}
}

