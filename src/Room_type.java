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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;


public class Room_type extends HFrame{
	//JPanel panel;
	JLabel title,tip1,tip2;
	String currentID,labels[] = {"Room Type:","Room Rates:","Notes:","Status"};
    JTextField[] text = new JTextField[3];
    int[] move_x = {120,220,540,640};
    JButton[] move = new JButton[4];
    String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,260,370,480,590,260,480};
    JButton[] action = new JButton[7];
    String action_content[] = {"Add","Edit","Save","Refresh","Close","Update","Cancel"};
    JTextField record;
    JComboBox<Object> status;
    int NO;
    Room_type()
    {
    	this("1");
    }
	Room_type(String ID){
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Room Type Details");
        title.setBounds(350, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        for(int i=0;i<4;i++)
        {
        	if(i<3)
        	{
        		JLabel l = new JLabel(labels[i]);
            	l.setBounds(150, 150+40*i, 150, 20);
            	panel.add(l);
            	
            	text[i] = new JTextField(""); 
            	text[i].setBounds(420, 150+40*i, 200, 20);
            	panel.add(text[i]);
        	}
        	else
        	{
        		JLabel l = new JLabel(labels[i]);
            	l.setBounds(150, 150+40*i, 150, 20);
            	panel.add(l);
            	
            	String[] status1 = {"Y-available","N-leaving"};
                status = new JComboBox<Object>(status1);
                status.setBounds(420, 150+40*i, 250, 20);
                panel.add(status);
        	}
        }

        tip2 = new JLabel("info can't be empty");
		tip2.setBounds(640, 150, 200, 20);
		tip2.setForeground(Color.red);
		panel.add(tip2);
		tip2.setVisible(false);
		
        tip1 = new JLabel("Rate must > 0");
		tip1.setBounds(640, 190, 200, 20);
		tip1.setForeground(Color.red);
		panel.add(tip1);
		tip1.setVisible(false);
        
        look();
        getInfo(1);
        text[0].addKeyListener(new KeyListener(){//only can write char
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
        text[1].addKeyListener(new KeyListener(){//can write number
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
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
        int j;
        for(j=0;j<4;j++)
        {
        	move[j] = new JButton(move_content[j]);
        	move[j].setBounds(move_x[j], 500, 80, 30);
        	panel.add(move[j]);
        }
        move[0].addActionListener(new ActionListener() { //first
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(1);
            }
        });
        move[1].addActionListener(new ActionListener() { //last
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(2);
            }
        });
        move[2].addActionListener(new ActionListener() { //next
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(3);
            }
        });
        move[3].addActionListener(new ActionListener() { //end
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(4);
            }
        });
        //String[] sourceArray = ID.split("_");
        record = new JTextField("                       Record  "+Integer.parseInt(ID));
        record.setBounds(320, 500, 200, 30);
        record.setEditable(false);
        panel.add(record);
        
        for(int i=0;i<7;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 560, 80, 30);
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
            	getInfo(5);
            	rtype_table();
            }
        });
        action[5].addActionListener(new ActionListener() { //add to db
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(truevalue())
            	{
            		addnew();
                	rtype_table();	
            	}
            	
            }
        });
        action[6].addActionListener(new ActionListener() { //back
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	rtype_table();
            	getInfo(5);
            	for(int i=0;i<4;i++)
        			move[i].setEnabled(true);
        		for(int i=0;i<5;i++)
        			action[i].setVisible(true);
        		for(int i=5;i<7;i++)
        			action[i].setVisible(false);
            }
        });
        rtype_table();
        this.add(panel);
	}
	public void rtype_table()
	{
		String[] columnName = {"Room Type","Room Rates","Notes","Status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from room_type");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][4];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from room_type order by room_type");
				while(rs2.next()){
					data1[i][0] = rs2.getString("room_type");
					data1[i][1] = rs2.getString("room_rate");
					data1[i][2] = rs2.getString("room_notes");
					if(rs2.getString("rtype_status").indexOf("Y") != -1)
						data1[i][3] = "Y-available";
					else
						data1[i][3] = "N-leaving";
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
        table.setPreferredScrollableViewportSize(new Dimension(800,120));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 320, 800, 120);
        panel.add(scrollPane);
	}
	public void addnew()
	{
		String info[] = new String[4];
		for(int i=0;i<3;i++)
		{
			info[i] = text[i].getText();
		}	
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[3] = "Y";
		else
			info[3] = "N";
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
				int rs = stmt.executeUpdate("insert into room_type (room_type,room_rate,room_notes,rtype_status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"')");
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
	public void getInfo(int act)
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
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select count(*) from room_type");
				if(rs1.next()){
					max = rs1.getInt(1);	
				}
				if(act == 1)
					NO = 1;
				if(act == 2 && NO != 1)
					NO--;
				if(act == 3 && NO != max)
					NO++;
				if(act == 4)
				{
					NO = max;
					System.out.println("No is "+NO);
				}
				Statement stmt = (Statement) con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * from room_type order by room_type");
				while(rs.next()){
					if(count == NO){
						text[0].setText(rs.getString("room_type"));
						text[1].setText(rs.getString("room_rate"));
						text[2].setText(rs.getString("room_notes"));
						String ss = rs.getString("rtype_status");
						if(ss.indexOf("Y") != -1)
							status.setSelectedItem("Y-available");
						else
							status.setSelectedItem("N-leaving");
						record.setText("                       Record  "+count);
					}
					count++;
				}
			}catch(Exception e){			
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}   
	}
	public void look()
	{
		for(int i=0;i<3;i++)
		{
			text[i].setEditable(false);
		}
		tip1.setVisible(false);
	}
	public void edit()
	{
		for(int i=1;i<3;i++)
		{
			text[i].setEditable(true);
		}
		tip1.setVisible(false);
		tip2.setVisible(false);
	}
	public void save()
	{
		String info[] = new String[4];
		for(int i=0;i<3;i++)
		{
			info[i] = text[i].getText();
		}	
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[3] = "Y";
		else
			info[3] = "N";
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
				int rs = stmt.executeUpdate("update room_type set room_rate='"+info[1]+"',room_notes='"+info[2]+"',rtype_status='"+info[3]+"'where room_type like '"+info[0]+"'");
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
	public void add()
	{
		for(int i=0;i<3;i++){
			text[i].setText("");
			text[i].setEditable(true);
		}
		for(int i=0;i<4;i++)
			move[i].setEnabled(false);
		for(int i=0;i<5;i++)
			action[i].setVisible(false);
		for(int i=5;i<7;i++)
			action[i].setVisible(true);
	}
	
	public boolean truevalue()
	{
		String t,r,n;
		t = text[0].getText();
		r = text[1].getText();
		n = text[2].getText();
		if(t.equals("")||r.equals("")||n.equals(""))
		{
			tip2.setVisible(true);
			return false;
		}
		else{
			int price = Integer.parseInt(text[1].getText());
			if(price >0){
				tip1.setVisible(false);
				return true;
			}
			else{
				tip1.setVisible(true);
				return false;
			}
		}
		
	}
}
