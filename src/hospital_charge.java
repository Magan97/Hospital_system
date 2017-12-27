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


public class hospital_charge extends HFrame{
	//JPanel panel;
	JLabel title,l2,tip1,tip2,tip3;
	JComboBox status;
	JButton ps,ds,save,close,ok,cancel,back1;
	String currentID,labels[] = {"hospital_id:","hospital_charges:","start_date:","discount:","Status:"};
	JTextField hid,charge,discount,text;
	int[] move_x = {120,220,540,640};;
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
    hospital_charge()
    {
    	this("HPID_1");
    }
	hospital_charge(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Hospital Charge Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Hospital Charge Details--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 120, 800, 10);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        for(int i=0;i<5;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(200, 140+i*50, 130, 30);
        	panel.add(l);
        }
        
        hid = new JTextField("");
        hid.setBounds(400, 140, 150, 30);
        panel.add(hid);
        hid.setEditable(false);
        
        tip3 = new JLabel("charge must > 0");
		tip3.setBounds(570, 190, 200, 30);
		tip3.setForeground(Color.red);
		panel.add(tip3);
		tip3.setVisible(false);
        
        charge = new JTextField("");
        charge.setBounds(400, 190, 150, 30);
        panel.add(charge);
        charge.addKeyListener(new KeyListener(){//only can number 
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
        
        calender ser = calender.getInstance();
        text = new JTextField();
        text.setBounds(400, 240, 150, 30);
        text.setText("2017-10-11");
        ser.register(text);
        panel.add(text);
        
        discount = new JTextField("");
        discount.setBounds(400, 290, 150, 30);
        panel.add(discount); 
        discount.addKeyListener(new KeyListener(){//only can number 
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
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(400, 340, 150, 30);
        panel.add(status);
       
        tip1 = new JLabel("Date must after this day");
		tip1.setBounds(580, 240, 200, 30);
		tip1.setForeground(Color.red);
		panel.add(tip1);
		tip1.setVisible(false);
		
		tip2 = new JLabel("Discount must between 0-9");
		tip2.setBounds(580, 290, 200, 30);
		tip2.setForeground(Color.red);
		panel.add(tip2);
		tip2.setVisible(false);
        
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
            		currentID = "HPID_"+myid;
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
            		currentID = "HPID_"+myid;
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
            	if(truedata()){
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
            	hospital_charge_all rall = new hospital_charge_all();
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
            	if(truedata())
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
				ResultSet rs = stmt.executeQuery("SELECT * from hospital_charges where hospital_id = (SELECT max(hospital_id) from hospital_charges)");
				if(rs.next()){
					String id = rs.getString("hospital_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "HPID_"+myid;
					return newid;
				}
				else{
					return "HPID_1";
				}
			}catch(Exception e){
				return "HPID_1";
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
				ResultSet rs = stmt.executeQuery("SELECT * from hospital_charges where hospital_id = (SELECT max(hospital_id) from hospital_charges)");
				while(rs.next()){
					String id = rs.getString("hospital_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from hospital_charges where hospital_id = (SELECT min(hospital_id) from hospital_charges)");
				while(rs.next()){
					String id = rs.getString("hospital_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from hospital_charges where hospital_id like '"+thihid+"'");
				while(rs.next()){
					hid.setText(thihid);
					charge.setText(rs.getString("hospital_charge"));
					text.setText(rs.getString("start_date"));
					discount.setText(rs.getString("discount"));
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
		charge.setEditable(false);
		text.setEditable(false);
		discount.setEditable(false);
	}
	public void add()
	{
		hid.setText(newid());
		charge.setEditable(true);
		charge.setText("");
		discount.setEditable(true);
		discount.setText("");
		text.setEditable(false);
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
		charge.setEditable(true);
		discount.setEditable(true);
		text.setEditable(false);
	}
	public void addnew()
	{
		truedata();
		String info[] = new String[5];
		info[0] = hid.getText();
		info[1] = charge.getText();
		info[2] = text.getText();
		info[3] = discount.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[4] = "Y";
		else
			info[4] = "N";
		timetest t = new timetest();
		if(t.after(text) == true   && Integer.parseInt(info[3]) < 10)
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
					Statement stmt1 = (Statement) con.createStatement();
					ResultSet rs1 = stmt1.executeQuery("SELECT * from hospital_charges where status = 'Y'");
					if(rs1.next()){
						reminder("fails");
					}
					else{
						Statement stmt = (Statement) con.createStatement();
						int rs = stmt.executeUpdate("insert into hospital_charges (hospital_id,hospital_charge,start_date,discount,status) "
								+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"')");
						if(rs > 0){
							System.out.println("add success");
							reminder("successsfully");
						}
						else{
							System.out.println("add failed");
							reminder("failed");
						}  
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
		truedata();
		String info[] = new String[5];
		info[0] = hid.getText();
		info[1] = charge.getText();
		info[2] = text.getText();
		info[3] = discount.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[4] = "Y";
		else
			info[4] = "N";
		timetest t = new timetest();
		if(t.after(text) == true  && Integer.parseInt(info[3]) < 10)
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
					Statement stmt1 = (Statement) con.createStatement();
					ResultSet rs1 = stmt1.executeQuery("SELECT * from hospital_charges where status = 'Y'");
					if(rs1.next() && info[4] == "Y"){
						reminder("fails");
					}
					else{
						Statement stmt = (Statement) con.createStatement();
						int rs = stmt.executeUpdate("update hospital_charges set "
								+ "hospital_charge='"+info[1]+"',start_date='"+info[2]+"',discount='"+info[3]+"',status='"+info[4]+"'where hospital_id like '"+info[0]+"'");
						if(rs > 0)
							System.out.println("update success and influence "+rs);
						else{
							System.out.println("update failed");
						}
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
	public boolean truedata()
	{		
		timetest t = new timetest();
		if(t.after(text) != true)
		{
			tip1.setVisible(true);
		}
		else{
			tip1.setVisible(false);
		}
		String di = discount.getText();
		if(di.equals("")){
			tip2.setVisible(true);
		}
		else{
			if(Integer.parseInt(di) >= 10)
			{
				tip2.setVisible(true);
			}
			else{
				tip2.setVisible(false);
			}
		}
		if(charge.getText().equals(""))
		{
			tip3.setVisible(true);
		}
		else{
			int cha = Integer.parseInt(charge.getText());
			if(cha > 0)
			{
				tip3.setVisible(false);
			}
			else{
				tip3.setVisible(true);
			}
		}
		
		
		if(t.after(text) == true  && Integer.parseInt(di)<10 && Integer.parseInt(charge.getText()) > 0 && charge.getText().length()>0 && discount.getText().length()>0)	
		{
			return true;
		}
		else{
			return false;
		}
	}
}

