import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;


public class Doctor extends JFrame{
	JPanel panel;
	JLabel title,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18;
	JTextField id,fn,ln,hp,nn,mp,ad,qu,vc,note,cc,bs,record;
	JComboBox sex,dt,sp,status;
	JButton first,last,next,end,add,edit,save,refresh,viewall,close;
	String currrentID;
	Doctor(String ID)
	{
		currrentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Doctor Details");
        title.setBounds(350, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l1 = new JLabel("Doctor ID:");
        l1.setBounds(100, 100, 150, 20);
        panel.add(l1);
        
        this.add(panel);
        
        id = new JTextField("");
        id.setBounds(250, 100, 150, 20);
        panel.add(id);
                
        l2 = new JLabel("Personal Details--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 120, 800, 20);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        l3 = new JLabel("First Name:");
        l3.setBounds(100, 150, 150, 20);
        panel.add(l3);
        
        fn = new JTextField("");
        fn.setBounds(250, 150, 150, 20);
        panel.add(fn);
        
        fn.addKeyListener(new KeyListener(){//only can write char
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
        
        l4 = new JLabel("Last Name :");
        l4.setBounds(450, 150, 150, 20);
        panel.add(l4);
        
        ln = new JTextField("");
        ln.setBounds(600, 150, 150, 20);
        panel.add(ln);
        
        ln.addKeyListener(new KeyListener(){//only can write char
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
        
        l5 = new JLabel("Sex:");
        l5.setBounds(100, 180, 150, 20);
        panel.add(l5);
        
        String[] sex1 = {"Male","Female"};
        sex = new JComboBox<Object>(sex1);
        sex.setBounds(250, 180, 150, 20);
        panel.add(sex);
        
        l6 = new JLabel("Home Phone:");
        l6.setBounds(450,180,150,20);
        panel.add(l6);
        
        hp = new JTextField("");
        hp.setBounds(600, 180, 150, 20);
        panel.add(hp);
        
        hp.addKeyListener(new KeyListener(){//can write number, +
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
        
        l7 = new JLabel("NIC No:");
        l7.setBounds(100, 210, 150, 20);
        panel.add(l7);
        
        nn = new JTextField("");
        nn.setBounds(250, 210, 150, 20);
        panel.add(nn);
        
        nn.addKeyListener(new KeyListener(){//can write number, 
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
        
        l8 = new JLabel("Mobile Phone:");
        l8.setBounds(450, 210, 150, 20);
        panel.add(l8);
        
        mp = new JTextField("");
        mp.setBounds(600, 210, 150, 20);
        panel.add(mp);
        
        mp.addKeyListener(new KeyListener(){//can write number, +
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
        
        l9 = new JLabel("Address:");
        l9.setBounds(100, 240, 150, 20);
        panel.add(l9);
        
        ad = new JTextField("");
        ad.setBounds(250, 240, 150, 50);
        panel.add(ad);
        
        ad.addKeyListener(new KeyListener(){//can write char,number, -
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
        
        l10 = new JLabel("Qualfications:");
        l10.setBounds(450, 240, 150, 20);
        panel.add(l10);
        
        qu = new JTextField("");
        qu.setBounds(600, 240, 150, 20);
        panel.add(qu);
        
        qu.addKeyListener(new KeyListener(){//can write char
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
        
        l11 = new JLabel("Specialization:");
        l11.setBounds(450, 270, 150, 20);
        panel.add(l11);
        
        String[] dt1 = {"Cardiologist","Dentist","Dermatologist","Neurologist",
        		"Oncologist","ENT","Orthopedic","Plastic surgeon","Pediatrician"};
        sp = new JComboBox<Object>(dt1);
        sp.setBounds(600, 270, 150, 20);
        panel.add(sp);
        
        l12 = new JLabel("Employee Details--------------------------------------------------------"
        		+ "-----------------------------------------------------------");
        l12.setBounds(80, 300, 800, 20);
        l12.setFont(new Font("Dialog",1,15));
        l12.setSize(new Dimension(700,20));
        panel.add(l12);
        
        l13 = new JLabel("Doctor Type:");
        l13.setBounds(100, 330, 150, 20);
        panel.add(l13);
        
        String[] dt2 = {"Permanent", "Temperory"};
        dt = new JComboBox<Object>(dt2);
        dt.setBounds(250, 330, 150, 20);
        panel.add(dt);
        
        l14 = new JLabel("Visiting Charge:");
        l14.setBounds(450, 330, 150, 20);
        panel.add(l14);
        
        vc = new JTextField("");
        vc.setBounds(600, 330, 150, 20);
        panel.add(vc);
        
        vc.addKeyListener(new KeyListener(){//can write number,
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
        
        l15 = new JLabel("Notes:");
        l15.setBounds(100, 360, 150, 20);
        panel.add(l15);
        
        note = new JTextField("");
        note.setBounds(250, 360, 150, 20);
        panel.add(note);
        
        l18 = new JLabel("Status:");
        l18.setBounds(100, 390, 150, 20);
        panel.add(l18);
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(250, 390, 150, 20);
        panel.add(status);
        
        l16 = new JLabel("Channeling Charge:");
        l16.setBounds(450, 360, 150, 20);
        panel.add(l16);
        
        cc = new JTextField("");
        cc.setBounds(600, 360, 150, 20);
        panel.add(cc);
        cc.addKeyListener(new KeyListener(){//can write number, 
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
        
        l17 = new JLabel("Basic Salary:");
        l17.setBounds(450,390,150,20);
        panel.add(l17);
        
        bs = new JTextField("");
        bs.setBounds(600, 390, 150, 20);
        panel.add(bs);
        
        bs.addKeyListener(new KeyListener(){//can write number,
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
        
        first = new JButton("First");
        first.setBounds(120, 440, 80, 30);
        panel.add(first);
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        last = new JButton("Last");
        last.setBounds(220, 440, 80, 30);
        panel.add(last);
        
        
        String[] sourceArray = currrentID.split("_");
        record = new JTextField("                       Record  "+Integer.parseInt(sourceArray[1]));
        record.setBounds(320, 440, 200, 30);
        panel.add(record);
        
        next = new JButton("Next");
        next.setBounds(540, 440 , 80, 30);
        panel.add(next);
        
        end = new JButton("End");
        end.setBounds(640, 440, 80, 30);
        panel.add(end);
        
        add = new JButton("Add");
        add.setBounds(200, 490, 80, 30);
        panel.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	AddDoctor adddoctor = new AddDoctor();
        		adddoctor.setVisible(true);
                adddoctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        
        edit = new JButton("Edit");
        edit.setBounds(320, 490, 80, 30);
        panel.add(edit);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	edit();
            }
        });
        
        save = new JButton("Save");
        save.setBounds(440, 490, 80, 30);
        panel.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	save();
            }
        });
        
        refresh = new JButton("Refresh");
        refresh.setBounds(550, 490, 80, 30);
        panel.add(refresh);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	getInfo("DCID_1");
                look();
            }
        });
        viewall = new JButton("View All");
        viewall.setBounds(320, 540, 80, 30);
        panel.add(viewall);
        viewall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	ViewAll va = new ViewAll();
        		va.setVisible(true);
                va.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        
        close =  new JButton("Close");
        close.setBounds(440, 540, 80, 30);
        panel.add(close);
        
        getInfo(currrentID);
        look();
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String firstid = minID();
            	getInfo(firstid);
                look();
            }
        });
        last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String firstid = minID();
            	String[] sourceArray = currrentID.split("_");
            	if(Integer.parseInt(sourceArray[1])-1>=1){
            		int myid = Integer.parseInt(sourceArray[1])-1;
            		currrentID = "DCID_"+myid;
            	}
            	getInfo(currrentID);
                look();
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String endid = maxID();
            	String[] thisArray = currrentID.split("_");
            	String[] endArray = endid.split("_");
            	if(Integer.parseInt(thisArray[1]) < Integer.parseInt(endArray[1])){
            		int myid = Integer.parseInt(thisArray[1])+1;
            		currrentID = "DCID_"+myid;
            	}
            	System.out.println("current id is"+currrentID);
            	getInfo(currrentID);
                look();
            }
        });
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("end click");
            	String endid = maxID();
            	getInfo(endid);
                look();
            }
        });
	}
	public void getInfo(String theid)
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_details where doctor_id like '"+theid+"'");
				while(rs.next()){
					System.out.println("Get information!");
					id.setText(rs.getString("doctor_id"));
					fn.setText(rs.getString("doctor_fname"));
					ln.setText(rs.getString("doctor_lname"));
					String rs1 = rs.getString("doctor_sex");
					System.out.println(rs1);
					sex.setSelectedItem(rs1);
					hp.setText(rs.getString("doctor_hphone"));
					nn.setText(rs.getString("doctor_NID"));
					mp.setText(rs.getString("doctor_mphone"));
					ad.setText(rs.getString("doctor_address"));
					qu.setText(rs.getString("doctor_qualification"));
					sp.setSelectedItem(rs.getString("doctor_specialization"));
					dt.setSelectedItem(rs.getString("doctor_type"));
					vc.setText(rs.getString("doctor_vcharge"));
					cc.setText(rs.getString("doctor_ccharge"));
					note.setText(rs.getString("doctor_notes"));
					bs.setText(rs.getString("doctor_basic_sal"));
					status.setSelectedItem(rs.getString("doctor_status"));
					
				}
			}catch(Exception e){
				
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		String[] sourceArray = currrentID.split("_");
        record.setText("                       Record  "+Integer.parseInt(sourceArray[1]));
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_details where doctor_id = (SELECT max(doctor_id) from doctor_details)");
				while(rs.next()){
					String id = rs.getString("doctor_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_details where doctor_id = (SELECT min(doctor_id) from doctor_details)");
				while(rs.next()){
					String id = rs.getString("doctor_id");
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
	public void look()
	{
		id.setEditable(false);
		fn.setEditable(false);
		ln.setEditable(false);
		hp.setEditable(false);
		nn.setEditable(false);
		mp.setEditable(false);
		ad.setEditable(false);
		qu.setEditable(false);
		sp.setEditable(false);
		vc.setEditable(false);
		note.setEditable(false);
		cc.setEditable(false);
		bs.setEditable(false);
		record.setEditable(false);
		sex.setEditable(false);
		dt.setEditable(false);		
	}
	public void edit()
	{
		id.setEditable(false);
		fn.setEditable(true);
		ln.setEditable(true);
		hp.setEditable(true);
		nn.setEditable(true);
		mp.setEditable(true);
		ad.setEditable(true);
		qu.setEditable(true);
		sp.setEditable(true);
		vc.setEditable(true);
		note.setEditable(true);
		cc.setEditable(true);
		bs.setEditable(true);
		record.setEditable(false);
		sex.setEditable(true);
		dt.setEditable(true);		
	}
	public void save()
	{
		String id1 = id.getText();
		String fn1 = fn.getText();
		String ln1 = ln.getText();
		String sex1 = sex.getSelectedItem().toString();
		String hp1 = hp.getText();
		String nn1 = nn.getText();
		String mp1 = mp.getText();
		String ad1 = ad.getText();
		String qu1 = qu.getText();
		String sp1 = sp.getSelectedItem().toString();
		String dt1 = dt.getSelectedItem().toString();
		String vc1 = vc.getText();
		String note1 = note.getText();
		String cc1 = cc.getText();
		String bs1 = bs.getText();
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
				int rs = stmt.executeUpdate("update doctor_details set doctor_fname='"+fn1+"',doctor_lname='"+ln1+"',doctor_sex='"+sex1+"',doctor_NID='"+nn1+"'"
						+ ",doctor_hphone='"+hp1+"',doctor_mphone='"+mp1+"',doctor_address='"+ad1+"',doctor_qualification='"+qu1
						+"',doctor_specialization='"+sp1+"',doctor_type='"+dt1+"',doctor_vcharge='"+vc1+"',doctor_ccharge='"+cc1+"',doctor_notes='"+note1+"',doctor_basic_sal='"+bs1
						+"',doctor_status='"+status1+"' where doctor_id like '"+id1+"'");
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
}
