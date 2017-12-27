
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


public class Medicine_product_details extends HFrame{
	//JPanel panel;
	JLabel title,l2,tip1;
	JComboBox status,supp_id,cate_id;
	JButton ps,ds,save,close,ok,cancel,back1;
	String currentID,labels[] = {"Product ID:","Product Name:","Supplier ID:","Category ID:","Utit Price:","Units in stock:","Reordert evel","Status:"};
	JTextField pid,pname,price,stock,level;
	int[] move_x = {120,220,540,640};
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record;
    ArrayList<String> supp_ids = new ArrayList<String>();
    ArrayList<String> cate_ids = new ArrayList<String>();
    Medicine_product_details()
    {
    	this("PDID_1");
    }
	Medicine_product_details(String ID)
	{
		currentID = ID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Product Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        l2 = new JLabel("Product Details--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        l2.setBounds(80, 110, 800, 10);
        l2.setFont(new Font("Dialog",1,15));
        l2.setSize(new Dimension(700,20));
        panel.add(l2);
        
        for(int i=0;i<8;i++)
        {
        	JLabel l= new JLabel(labels[i]);
        	l.setBounds(200, 140+i*38, 100, 20);
        	panel.add(l);
        }
        
        pid = new JTextField("");
        pid.setBounds(400, 140, 150, 20);
        panel.add(pid);
        pid.setEditable(false);
        
        pname = new JTextField("");
        pname.setBounds(400, 178, 150, 20);
        panel.add(pname);
        pname.addKeyListener(new KeyListener(){//only can write char
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
        
        getRtype();
        int size = supp_ids.size();
        String[] srtype = new String[size];
        for(int i=0;i<size;i++){
        	srtype[i] = (String)supp_ids.get(i); 
        }
        supp_id = new JComboBox<Object>(srtype);
        supp_id.setBounds(400, 216, 150, 20);
        panel.add(supp_id);
        
        size = cate_ids.size();
        String[] srtype1 = new String[size];
        for(int i=0;i<size;i++){
        	srtype1[i] = (String)cate_ids.get(i); 
        }
        cate_id = new JComboBox<Object>(srtype1);
        cate_id.setBounds(400, 254, 150, 20);
        panel.add(cate_id);
        
        tip1 = new JLabel("price must bigger than 0");
		tip1.setBounds(570, 292, 200, 20);
		tip1.setForeground(Color.red);
		panel.add(tip1);
		tip1.setVisible(false);
        
        price = new JTextField("");
        price.setBounds(400, 292, 150, 20);
        panel.add(price);
        price.addKeyListener(new KeyListener(){//can write number
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
        
        stock = new JTextField("");
        stock.setBounds(400, 330, 150, 20);
        panel.add(stock);
        stock.addKeyListener(new KeyListener(){//can write number
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
        
        level = new JTextField("");
        level.setBounds(400, 368, 150, 20);
        panel.add(level);
        level.addKeyListener(new KeyListener(){//can write number
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
        status.setBounds(400, 406, 150, 20);
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
            		currentID = "PDID_"+myid;
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
            		currentID = "PDID_"+myid;
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
            	if(truevalue()){
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
            	Product_all rall = new Product_all();
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
            	if(truevalue()){
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
	public int getRtype(){
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
				ResultSet rs = stmt.executeQuery("select * from suppliers");
				while(rs.next()){
					String type = rs.getString("supplier_id");
					supp_ids.add(type);
				}
				ResultSet rs1 = stmt.executeQuery("select * from medicine_category");
				while(rs1.next()){
					String type = rs1.getString("category_id");
					cate_ids.add(type);
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
		return 0;
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
				ResultSet rs = stmt.executeQuery("SELECT * from medicine_details where product_id = (SELECT max(product_id) from medicine_details)");
				if(rs.next()){
					String id = rs.getString("product_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "PDID_"+myid;
					return newid;
				}
				else{
					return "PDID_1";
				}
			}catch(Exception e){
				return "PDID_1";
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
				ResultSet rs = stmt.executeQuery("SELECT * from medicine_details where product_id = (SELECT max(product_id) from medicine_details)");
				while(rs.next()){
					String id = rs.getString("product_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from medicine_details where product_id = (SELECT min(product_id) from medicine_details)");
				while(rs.next()){
					String id = rs.getString("product_id");
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
	public void getInfo(String thipid)
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
				ResultSet rs = stmt.executeQuery("SELECT * from medicine_details where product_id like '"+thipid+"'");
				while(rs.next()){
					pid.setText(thipid);
					pname.setText(rs.getString("product_name"));
					cate_id.setSelectedItem(rs.getString("category_id").toString());
					supp_id.setSelectedItem(rs.getString("supplier_id").toString());
					price.setText(rs.getString("unit_price"));
					stock.setText(rs.getString("units_in_stock"));
					level.setText(rs.getString("recorder_level"));
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
		pname.setEditable(false);
		price.setEditable(false);
		stock.setEditable(false);
		level.setEditable(false);
	}
	public void add()
	{
		pid.setText(newid());
		pname.setEditable(true);
		pname.setText("");
		price.setEditable(true);
		price.setText("");
		stock.setEditable(true);
		stock.setText("");
		level.setEditable(true);
		level.setText("");
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
		pname.setEditable(true);
		price.setEditable(true);
		stock.setEditable(true);
		level.setEditable(true);
	}
	public void addnew()
	{
		String info[] = new String[8];
		info[0] = pid.getText();
		info[1] = pname.getText();
		info[2] = cate_id.getSelectedItem().toString();
		info[3] = supp_id.getSelectedItem().toString();
		info[5] = price.getText();
		info[4] = stock.getText();
		info[6] = level.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[7] = "Y";
		else
			info[7] = "N";
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
				int rs = stmt.executeUpdate("insert into medicine_details (product_id,product_name,category_id,supplier_id,units_in_stock,unit_price,recorder_level,status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]+"','"+info[7]+"')");
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
		String info[] = new String[8];
		info[0] = pid.getText();
		info[1] = pname.getText();
		info[2] = cate_id.getSelectedItem().toString();
		info[3] = supp_id.getSelectedItem().toString();
		info[5] = price.getText();
		info[4] = stock.getText();
		info[6] = level.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[7] = "Y";
		else
			info[7] = "N";
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
				int rs = stmt.executeUpdate("update medicine_details set "
						+ "product_name='"+info[1]+"',category_id='"+info[2]+"',supplier_id='"+info[3]+"',units_in_stock='"+info[4]+"',unit_price='"+info[5]
								+"',recorder_level='"+info[6]+"',status='"+info[7]+"'where product_id like '"+info[0]+"'");
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
		int pri = Integer.parseInt(price.getText());
		if(pri >0){
			tip1.setVisible(false);
			return true;
		}
		else{
			tip1.setVisible(true);
			return false;
		}
	}
}

