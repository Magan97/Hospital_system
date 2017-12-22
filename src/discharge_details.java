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
import com.swing.test.timetest;


public class discharge_details extends HFrame{
	//JPanel panel;
	JLabel title,l2,l6,l7;
	JComboBox status,adm_id;
	JButton ps,ds,save,close,ok,cancel,back1;
	String currentID,labels[] = {"Discharge ID:","Admission ID:","Discharge Date:","Discharge Time:","Status:"};
	JTextField did,text;
	JSpinner timein1,timein2;
	int[] move_x = {120,220,540,640};;
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
    ArrayList<String> aid = new ArrayList<String>();
    discharge_details()
    {
    	this("DCID_1");
    }
    discharge_details(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Discharge Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Discharge Details--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 120, 800, 10);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        for(int i=0;i<5;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(200, 140+i*50, 100, 20);
        	panel.add(l);
        }
        
        did = new JTextField("");
        did.setBounds(400, 140, 150, 30);
        panel.add(did);
        did.setEditable(false);
        
        getid();
        int size1 = aid.size();
        String[] admid = new String[size1];
        for(int i=0;i<size1;i++){
        	admid[i] = (String)aid.get(i); 
        }
        adm_id = new JComboBox<Object>(admid);
        adm_id.setBounds(400, 190, 150, 30);
        panel.add(adm_id);
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(400, 240, 150, 30);
        text.setText("2017-12-17");
        ser.register(text);
        panel.add(text);
        
        timein1 = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        timein1.setBounds(400, 290, 50, 30);
        panel.add(timein1);
        
        l6 = new JLabel("hour");
        l6.setBounds(460, 290, 30, 30);
        panel.add(l6);
        
        timein2 = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        timein2.setBounds(490, 290, 50, 30);
        panel.add(timein2);
        
        l7 = new JLabel("minute");
        l7.setBounds(550, 290, 50, 30);
        panel.add(l7);         
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(400, 340, 150, 30);
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
            		currentID = "DCID_"+myid;
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
            		currentID = "DCID_"+myid;
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
            	inpatient_discharge_all rall = new inpatient_discharge_all();
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
				ResultSet rs = stmt.executeQuery("SELECT * from inpatient_discharge where discharge_id = (SELECT max(discharge_id) from inpatient_discharge)");
				if(rs.next()){
					String id = rs.getString("discharge_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "DCID_"+myid;
					return newid;
				}
				else{
					return "DCID_1";
				}
			}catch(Exception e){
				return "DCID_1";
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
				ResultSet rs = stmt.executeQuery("SELECT * from inpatient_discharge where discharge_id = (SELECT max(discharge_id) from inpatient_discharge)");
				while(rs.next()){
					String id = rs.getString("discharge_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from inpatient_discharge where discharge_id = (SELECT min(discharge_id) from inpatient_discharge)");
				while(rs.next()){
					String id = rs.getString("discharge_id");
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
	public void getInfo(String thidid)
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
				ResultSet rs = stmt.executeQuery("SELECT * from inpatient_discharge where discharge_id like '"+thidid+"'");
				while(rs.next()){
					did.setText(thidid);
					adm_id.setSelectedItem(rs.getString("admission_id"));
					text.setText(rs.getString("discharge_date"));
					String t = rs.getString("discharge_time");
					String[] sourceArray = t.split(":");
					timein1.setValue(sourceArray[0].toString());
					timein2.setValue(sourceArray[1].toString());
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
		did.setEditable(false);
		text.setEditable(false);
		timein1.setEnabled(false);
		timein2.setEnabled(false);
	}
	public void add()
	{
		did.setText(newid());
		text.setEditable(true);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
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
		text.setEditable(true);
		timein1.setEnabled(true);
		timein2.setEnabled(true);
	}
	public void addnew()
	{
		String info[] = new String[5];
		info[0] = did.getText();
		info[1] = adm_id.getSelectedItem().toString();
		info[2] = text.getText();
		info[3] = timein1.getValue().toString()+":"+timein2.getValue().toString();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[4] = "Y";
		else
			info[4] = "N";
		timetest t = new timetest();
		if(t.discharge(text, info[1])==true)
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
					int rs = stmt.executeUpdate("insert into inpatient_discharge (discharge_id,admission_id,discharge_date,discharge_time,status) "
							+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"')");
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
		else{
			reminder("fails");
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
		String info[] = new String[5];
		info[0] = did.getText();
		info[1] = adm_id.getSelectedItem().toString();
		info[2] = text.getText();
		info[3] = timein1.getValue().toString()+":"+timein2.getValue().toString();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[4] = "Y";
		else
			info[4] = "N";
		timetest t = new timetest();
		if(t.discharge(text, info[1])==true)
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
					int rs = stmt.executeUpdate("update inpatient_discharge set "
							+ "admission_id='"+info[1]+"',discharge_date='"+info[2]+"',discharge_time='"+info[3]+"',status='"+info[4]+"'where discharge_id like '"+info[0]+"'");
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
		else{
			reminder("fails");
		}
	}
}

