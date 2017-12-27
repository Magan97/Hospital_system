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


public class  prescription_details extends HFrame{
	//JPanel panel;
	JLabel title,l2,tip1,tip2,tip3;
	JComboBox status,m_s_id;
	JButton ps,ds,save,close,ok,cancel,back1,msinfo;
	String currentID,labels[] = {"Prescription ID:","Medicine/Service ID:","Frequency:","Number of days:","Status:"};
	JTextField cid,freq,days;
	int[] move_x = {120,220,540,640};;
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
    ArrayList<String> aid = new ArrayList<String>();
    prescription_details()
    {
    	this("PSID_1");
    }
    prescription_details(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Prescription Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Prescription Details--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 120, 800, 10);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        for(int i=0;i<5;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(200, 140+i*50, 150, 20);
        	panel.add(l);
        }
        
        cid = new JTextField("");
        cid.setBounds(400, 140, 150, 20);
        panel.add(cid);
        cid.setEditable(false);
        
        getid();
        int size1 = aid.size();
        String[] admid = new String[size1];
        for(int i=0;i<size1;i++){
        	admid[i] = (String)aid.get(i); 
        }
        m_s_id = new JComboBox<Object>(admid);
        m_s_id.setBounds(400, 190, 150, 20);
        panel.add(m_s_id);
        
        msinfo = new JButton("in");
        msinfo.setBounds(570, 190, 50, 20);
        panel.add(msinfo);
        msinfo.addActionListener(new ActionListener() { //room table
            @Override
            public void actionPerformed(ActionEvent e) {
            	mstable();
            }
        });
        
        freq = new JTextField("");
        freq.setBounds(400, 240, 150, 20);
        panel.add(freq);
        freq.addKeyListener(new KeyListener(){//can write number
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
        tip1 = new JLabel("must bigger than 0");
		tip1.setBounds(570, 240, 200, 20);
		tip1.setForeground(Color.red);
		panel.add(tip1);
		tip1.setVisible(false);
        
        days = new JTextField("");
        days.setBounds(400, 290, 150, 20);
        panel.add(days);   
        days.addKeyListener(new KeyListener(){//can write number
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
        tip2 = new JLabel("must bigger than 0");
		tip2.setBounds(570, 290, 200, 20);
		tip2.setForeground(Color.red);
		panel.add(tip2);
		tip2.setVisible(false);
                
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(400, 340, 150, 20);
        panel.add(status);
        

        tip3 = new JLabel("info can't be empty");
		tip3.setBounds(400, 390, 200, 20);
		tip3.setForeground(Color.red);
		panel.add(tip3);
		tip3.setVisible(false);
       
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
            		currentID = "PSID_"+myid;
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
            		currentID = "PSID_"+myid;
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
            	if( truevalue())
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
            	//
            	prescription_all rall = new prescription_all();
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
            	if( truevalue())
            	{
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
				ResultSet rs = stmt.executeQuery("select * from medicine_details");
				while(rs.next()){
					String id = rs.getString("product_id");
					aid.add(id);
				}
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select * from services");
				while(rs1.next()){
					String id = rs1.getString("service_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from prescription_details where prescription_id = (SELECT max(prescription_id) from prescription_details)");
				if(rs.next()){
					String id = rs.getString("prescription_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "PSID_"+myid;
					return newid;
				}
				else{
					return "PSID_1";
				}
			}catch(Exception e){
				return "PSID_1";
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
				ResultSet rs = stmt.executeQuery("SELECT * from prescription_details where prescription_id = (SELECT max(prescription_id) from prescription_details)");
				while(rs.next()){
					String id = rs.getString("prescription_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from prescription_details where prescription_id = (SELECT min(prescription_id) from prescription_details)");
				while(rs.next()){
					String id = rs.getString("prescription_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from prescription_details where prescription_id like '"+thicid+"'");
				while(rs.next()){
					cid.setText(thicid);
					m_s_id.setSelectedItem(rs.getString("medicine_service_id"));
					freq.setText(rs.getString("frequency"));
					days.setText(rs.getString("no_of_days"));
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
		freq.setEditable(false);
		days.setEditable(false);
		tip3.setVisible(false);
		tip1.setVisible(false);
		tip2.setVisible(false);
	}
	public void add()
	{
		cid.setText(newid());
		freq.setEditable(true);
		freq.setText("");
		days.setEditable(true);
		days.setText("");
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
		freq.setEditable(true);
		days.setEditable(true);
	}
	public void addnew()
	{
		String info[] = new String[5];
		info[0] = cid.getText();
		info[1] = m_s_id.getSelectedItem().toString();
		info[2] = freq.getText();
		info[3] = days.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[4] = "Y";
		else
			info[4] = "N";
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
				int rs = stmt.executeUpdate("insert into prescription_details (prescription_id,medicine_service_id,frequency,no_of_days,status) "
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
		info[0] = cid.getText();
		info[1] = m_s_id.getSelectedItem().toString();
		info[2] = freq.getText();
		info[3] = days.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[4] = "Y";
		else
			info[4] = "N";
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
				int rs = stmt.executeUpdate("update prescription_details set "
						+ "frequency='"+info[2]+"',medicine_service_id='"+info[1]+"',no_of_days='"+info[3]+"',status='"+info[4]+"'where prescription_id like '"+info[0]+"'");
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
	public void mstable()
	{
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel title1 = new JLabel("Medicine/Service");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"Medi/Serv ID","name","status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from medicine_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					num+=n;
				}
				Statement stmt3 = (Statement) con.createStatement();
				ResultSet rs3 = stmt3.executeQuery("select count(*) from services");
				if(rs3.next()){
					int n = rs3.getInt(1);
					num+=n;
				}
				data1 = new Object[num][3];
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from medicine_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("product_id");
					data1[i][1] = rs2.getString("product_name");
					if(rs2.getString("status").indexOf("Y") != -1)
						data1[i][2] = "Y";
					else
						data1[i][2] = "N";
					i++;
				}
				Statement stmt4 = (Statement) con.createStatement();
				ResultSet rs4 = stmt4.executeQuery("select * from services");
				while(rs4.next()){
					data1[i][0] = rs4.getString("service_id");
					data1[i][1] = rs4.getString("service_name");
					if(rs4.getString("status").indexOf("Y") != -1)
						data1[i][2] = "Y";
					else
						data1[i][2] = "N";
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
	public boolean truevalue()
	{
		String f = freq.getText();
		String d = days.getText();
		int f_d = 0;
		if(f.equals("") || d.equals(""))
		{
			tip3.setVisible(true);
			
		}
		else{
			int fre = Integer.parseInt(freq.getText());
			int day = Integer.parseInt(days.getText());
			if(fre > 0){
				tip1.setVisible(false);
				f_d++;
			}
			else{
				tip1.setVisible(true);
			}
			if(day > 0)
			{
				tip2.setVisible(false);
				f_d++;
			}
			else{
				tip2.setVisible(true);
			}
		}
		if(f.length()>0 && d.length()>0 && f_d == 2)
		{
			return true;
		}
		else{
			return false;
		}
	}
}

