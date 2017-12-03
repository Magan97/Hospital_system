
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


public class Guardian_details extends JFrame{
	JPanel panel;
	JLabel title,l2;
	JComboBox status;
	JButton ps,ds,save,close,ok,cancel,back1,patient;
	String currentID,labels[] = {"Guardian ID:","First Name:","Last Name:","NIC Number:","Address:"};
	String labels2[] = {"Phone Number:","Fax Number:","Occupation:","Status:"};
	JTextField gid,fname,lname,nic,address,phone,fax,occu;
	int[] move_x = {120,210,440,530};
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
	public Guardian_details(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Guardian Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Personal Details--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 120, 800, 10);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        for(int i=0;i<5;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(100, 140+i*50, 100, 30);
        	panel.add(l);
        }
        
        gid = new JTextField("");
        gid.setBounds(200, 140, 150, 30);
        panel.add(gid);
        gid.setEditable(false);
        
        fname = new JTextField("");
        fname.setBounds(200, 190, 150, 30);
        panel.add(fname);
        
        lname = new JTextField("");
        lname.setBounds(200, 240, 150, 30);
        panel.add(lname);
        
        nic = new JTextField("");
        nic.setBounds(200, 290, 150, 30);
        panel.add(nic);
        
        address = new JTextField("");
        address.setBounds(200, 340, 150, 30);
        panel.add(address);
        
        for(int i=0;i<4;i++)
        {
        	JLabel l= new JLabel(labels2[i]);
        	l.setBounds(400, 140+i*50, 100, 30);
        	panel.add(l);
        }
        
        phone = new JTextField("");
        phone.setBounds(520, 140, 150, 30);
        panel.add(phone);
        
        fax = new JTextField("");
        fax.setBounds(520, 190, 150, 30);
        panel.add(fax);
        
        occu = new JTextField("");
        occu.setBounds(520, 240, 150, 30);
        panel.add(occu);
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(520, 290, 150, 30);
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
            		currentID = "GDID_"+myid;
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
            		currentID = "GDID_"+myid;
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
        
        patient = new JButton("patient");
        patient.setBounds(620,430, 100, 30);
        panel.add(patient);
        patient.addActionListener(new ActionListener() { //end
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	/*
            	patient_details gd = new patient_details("GDID_1");
            	gd.setVisible(true);
            	gd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); */
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
            	Guardian_all rall = new Guardian_all();
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
            		patient.setEnabled(true);
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
            		patient.setEnabled(true);
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
				ResultSet rs = stmt.executeQuery("SELECT * from guardian_details where guardian_id = (SELECT max(guardian_id) from guardian_details)");
				if(rs.next()){
					String id = rs.getString("guardian_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "GDID_"+myid;
					return newid;
				}
				else{
					return "GDID_1";
				}
			}catch(Exception e){
				return "GDID_1";
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
				ResultSet rs = stmt.executeQuery("SELECT * from guardian_details where guardian_id = (SELECT max(guardian_id) from guardian_details)");
				while(rs.next()){
					String id = rs.getString("guardian_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from guardian_details where guardian_id = (SELECT min(guardian_id) from guardian_details)");
				while(rs.next()){
					String id = rs.getString("guardian_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from guardian_details where guardian_id like '"+thisid+"'");
				while(rs.next()){
					gid.setText(thisid);
					fname.setText(rs.getString("guardian_fname"));
					lname.setText(rs.getString("guardian_lname"));
					nic.setText(rs.getString("guardian_NIC"));
					address.setText(rs.getString("guardian_address"));
					phone.setText(rs.getString("guardian_phone"));
					fax.setText(rs.getString("fax_number"));
					occu.setText(rs.getString("occupation"));
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
		lname.setEditable(false);
		nic.setEditable(false);
		address.setEditable(false);
		phone.setEditable(false);
		fax.setEditable(false);
		occu.setEditable(false);
	}
	public void add()
	{
		gid.setText(newid());
		fname.setEditable(true);
		fname.setText("");
		lname.setEditable(true);
		lname.setText("");
		nic.setEditable(true);
		nic.setText("");
		address.setEditable(true);
		address.setText("");
		phone.setEditable(true);
		phone.setText("");
		fax.setEditable(true);
		fax.setText("");
		occu.setEditable(true);
		occu.setText("");
		for(int i=0;i<4;i++)
		{
    		move[i].setEnabled(false);
    		patient.setEnabled(false);
    	}
		for(int i=0;i<6;i++)
			action[i].setVisible(false);
		for(int i=6;i<8;i++)
			action[i].setVisible(true);
	}
	public void edit()
	{
		fname.setEditable(true);
		lname.setEditable(true);
		nic.setEditable(true);
		address.setEditable(true);
		phone.setEditable(true);
		fax.setEditable(true);
		occu.setEditable(true);
	}
	public void addnew()
	{
		String info[] = new String[9];
		info[0] = gid.getText();
		info[1] = fname.getText();
		info[2] = lname.getText();
		info[3] = nic.getText();
		info[4] = address.getText();
		info[5] = phone.getText();
		info[6] = fax.getText();
		info[7] = occu.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[8] = "Y";
		else
			info[8] = "N";
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
				int rs = stmt.executeUpdate("insert into guardian_details (guardian_id,guardian_fname,guardian_lname,guardian_NIC,guardian_address,guardian_phone,fax_number,occupation,status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]+"','"+info[7]+"','"+info[8]+"')");
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
		String info[] = new String[9];
		info[0] = gid.getText();
		info[1] = fname.getText();
		info[2] = lname.getText();
		info[3] = nic.getText();
		info[4] = address.getText();
		info[5] = phone.getText();
		info[6] = fax.getText();
		info[7] = occu.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[8] = "Y";
		else
			info[8] = "N";
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
				int rs = stmt.executeUpdate("update guardian_details set "
						+ "guardian_fname='"+info[1]+"',guardian_lname='"+info[2]+"',guardian_NIC='"+info[3]+"',guardian_address='"+info[4]+"',guardian_phone='"+info[5]+"',fax_number='"+info[6]
										+"',occupation='"+info[7]+"',status='"+info[8]+"'where guardian_id like '"+info[0]+"'");
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

