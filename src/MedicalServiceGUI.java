import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.jdbc.Statement;
    
public class MedicalServiceGUI extends HFrame
{
	private static final long serialVersionUID = 1L;
	//Jpanel panel;
	JLabel Title,label_id,label_Service,label_AR,label_Duration,label_Notes,l14;
	JTextField id,ServiceName,AR,Duration,Notes,record;
	JButton first,last,next,end,add,edit,save,refresh,viewall,close;
	JComboBox status;
	String currentSID="";
	JButton[] action = new JButton[2];
	String action_content[] = {"Update","Back"};
	MedicalServiceGUI()
	{
		this("SVID_1");
	}
	MedicalServiceGUI(String SID)
	{
		currentSID = SID;
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,30);
        //panel = new Jpanel();
        panel.setLayout(null);
        Title = new JLabel("Medical Service");
        Title.setBounds(300, 50, 400, 50);
        Title.setFont(new Font("Courier", 1, 35));
        Title.setSize(new Dimension(450,50));
        panel.add(Title);
        
        label_id = new JLabel("Service ID:");
        label_id.setBounds(100, 150, 480, 28);
        label_id.setFont(new Font("Courier", 0, 18));
        panel.add(label_id);
        
        id = new JTextField();
        id.setBounds(250, 150, 480, 28);
        panel.add(id);
                
        label_Service = new JLabel("Service Name:");
        label_Service.setBounds(100, 200, 480, 28);
        label_Service.setFont(new Font("Courier", 0, 18));
        panel.add(label_Service);
        
        ServiceName = new JTextField();
        ServiceName.setBounds(250, 200, 480, 28);
        panel.add(ServiceName);

        label_AR = new JLabel("Amount/Rate:");
        label_AR.setBounds(100, 250, 480, 28);
        label_AR.setFont(new Font("Courier", 0, 18));
        panel.add(label_AR);
        
        AR = new JTextField();
        AR.setBounds(250, 250, 480, 28);
        panel.add(AR);
        
        label_Duration = new JLabel("Duration(min):");
        label_Duration.setBounds(100, 300, 480, 28);
        label_Duration.setFont(new Font("Courier", 0, 18));
        panel.add(label_Duration);
        
        Duration = new JTextField();
        Duration.setBounds(250, 300, 480, 28);
        panel.add(Duration);
        
        label_Notes = new JLabel("Additional Notes:");
        label_Notes.setBounds(100, 350, 480, 28);
        label_Notes.setFont(new Font("Courier", 0, 18));
        panel.add(label_Notes);
       
        
        l14 = new JLabel("Status:");
        l14.setBounds(100, 400, 480, 28);
        l14.setFont(new Font("Courier", 0, 18));
        panel.add(l14);
               
        
        String[] status1 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status1);
        status.setBounds(250, 400, 100, 28);
        panel.add(status);
        
        Notes = new JTextField();
        Notes.setBounds(250, 350, 480, 28);
        panel.add(Notes);
        
        first = new JButton("First");
        first.setBounds(105, 440, 90, 32);
        first.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(first);
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentSID = minID();
                getInfo(currentSID);
                look();
            }
        });
        
        last = new JButton("Last");
        last.setBounds(205, 440, 90, 32);
        last.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(last);
        last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String firstid = minID();
            	String[] sourceArray = currentSID.split("_");
            	if(Integer.parseInt(sourceArray[1])-1>=1){
            		int myid = Integer.parseInt(sourceArray[1])-1;
            		currentSID = "SVID_"+myid;
            	}
            	getInfo(currentSID);
                look();
            }
        });
        
        
        record = new JTextField("				Record");
        record.setBounds(320, 440, 210, 32);
        record.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(record);
        
        next = new JButton("Next");
        next.setBounds(555, 440 , 90, 32);
        next.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String endid = maxID();
            	String[] thisArray = currentSID.split("_");
            	String[] endArray = endid.split("_");
            	if(Integer.parseInt(thisArray[1]) < Integer.parseInt(endArray[1])){
            		int myid = Integer.parseInt(thisArray[1])+1;
            		currentSID = "SVID_"+myid;
            	}
            	getInfo(currentSID);
                look();
            }
        });
        
        end = new JButton("End");
        end.setBounds(655, 440, 90, 32);
        end.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(end);
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentSID = maxID();
                getInfo(currentSID);
                look();
            }
        });
        
        add = new JButton("Add");
        add.setBounds(200, 490, 90, 32);
        add.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	add();
            	
            }
        });
        
        edit = new JButton("Edit");
        edit.setBounds(320, 490, 90, 32);
        edit.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(edit);
        edit.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	edit();
            }
        });
        
        
        save = new JButton("Save");
        save.setBounds(440, 490, 90, 32);
        save.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	save();
            	look();
            }
        });
        
        refresh = new JButton("Refresh");
        refresh.setBounds(560, 490, 90, 32);
        refresh.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(refresh);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
                getInfo(currentSID);
            }
        });
        
        viewall = new JButton("View All");
        viewall.setBounds(320, 540, 90, 32);
        viewall.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(viewall);
        
        
        viewall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                new ViewHospitalServices().setVisible(true);
                dispose();
              }
           });
        
        
        action[0] = new JButton("Update");
        action[0].setBounds(200, 490, 90, 32);
        action[0].setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(action[0]);
        action[0].addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("22222222");
            	addnew();
            	look();
            	getInfo(currentSID);
            	for(int i=0;i<2;i++)
        			action[i].setVisible(false);
            	add.setEnabled(true);
        		edit.setEnabled(true);
        		refresh.setEnabled(true);
        		viewall.setEnabled(true);
        		close.setEnabled(true);
        		save.setEnabled(true);
        		add.setVisible(true);
        		edit.setVisible(true);
        		refresh.setVisible(true);
        		viewall.setVisible(true);
        		close.setVisible(true);
        		save.setVisible(true);
        	
        		
            }
        });
        
        
        action[1] = new JButton("back");
        action[1].setBounds(560, 490, 90, 32);
        action[1].setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(action[1]);
        action[1].addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(currentSID);
        		for(int i=0;i<2;i++)
        			action[i].setVisible(false);
        		add.setEnabled(true);
        		edit.setEnabled(true);
        		refresh.setEnabled(true);
        		viewall.setEnabled(true);
        		close.setEnabled(true);
        		save.setEnabled(true);
        		add.setVisible(true);
        		edit.setVisible(true);
        		refresh.setVisible(true);
        		viewall.setVisible(true);
        		close.setVisible(true);
        		save.setVisible(true);
            }
        });
        
       
        
       /* add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String idString = id.getText();
		        String nameString = ServiceName.getText();
		        String durationString = Duration.getText();
		        //int durationInt = Integer.valueOf(durationString).intValue();
		        String chargeString = AR.getText();
		        //int chargeInt = Integer.valueOf(chargeString).intValue();
		        String noteString = Notes.getText();
		        
		        ArrayList<String> l = new ArrayList<String>();
		        l.add("services");
		        l.add(idString);
		        l.add(nameString);
		        l.add(durationString);
		        l.add(chargeString);
		        l.add(noteString);
		        
				sql.insert(l);
				
			}
		});
        
        save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String idString = id.getText();
				//sql.save(, IDSelect, "services");
				
			}
		});
        
        edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String idString = id.getText();
		        String nameString = ServiceName.getText();
		        String durationString = Duration.getText();
		        //int durationInt = Integer.valueOf(durationString).intValue();
		        String chargeString = AR.getText();
		        //int chargeInt = Integer.valueOf(chargeString).intValue();
		        String noteString = Notes.getText();
				//sql.Update(idString, nameString, Integer.valueOf(durationString).intValue(), Integer.valueOf(chargeString).intValue(), noteString);
			}
		});*/
        
        close =  new JButton("Close");
        close.setBounds(440, 540, 90, 32);
        close.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(close);
        close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
        
        look();
        getInfo(currentSID);
        this.add(panel);
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
				ResultSet rs = stmt.executeQuery("SELECT * from services where service_id = (SELECT max(service_id) from services)");
				while(rs.next()){
					String id = rs.getString("service_id");
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
	public void addnew()
	{
		String info[] = new String[6];
		info[0] = id.getText();
		info[1] = ServiceName.getText();
		info[2] = AR.getText();
		info[3] = Duration.getText();
		info[4] = Notes.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[5] = "Y";
		else
			info[5] = "N";
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
				int rs = stmt.executeUpdate("insert into services (service_id,service_name,charge_for_service,duration_of_service,service_notes,status) "
						+ "values('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"')");
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
			System.out.println("Can't load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from services where service_id = (SELECT max(service_id) from services)");
				if(rs.next()){
					String id = rs.getString("service_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "SVID_"+myid;
					return newid;
				}
				else{
					return "SVID_1";
				}
			}catch(Exception e){
				return "SVID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can't load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from services where service_id like '"+thisid+"'");
				while(rs.next()){
					id.setText(rs.getString("service_id"));
					ServiceName.setText(rs.getString("service_name"));
					Duration.setText(rs.getString("duration_of_service"));
					AR.setText(rs.getString("charge_for_service"));
					Notes.setText(rs.getString("service_notes"));
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
		String[] sourceArray = currentSID.split("_");
        record.setText("                       Record  "+Integer.parseInt(sourceArray[1]));
	}
	public void save()
	{
		String info[] = new String[6];
		info[0] = id.getText();
		info[1] = ServiceName.getText();
		info[2] = AR.getText();
		info[3] = Duration.getText();	
		info[4] = Notes.getText();
		String status1 = status.getSelectedItem().toString();
		if(status1 == "Y-available")
			info[5] = "Y";
		else
			info[5] = "N";
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
				int rs = stmt.executeUpdate("update services set service_name='"+info[1]+"',charge_for_service='"+info[2]+"',duration_of_service='"+info[3]+"',service_notes='"+info[4]+"',status='"+info[5]+"' where service_id = '"+info[0]+"'");
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
			System.out.println("Can't load the Driver");
		}
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
				ResultSet rs = stmt.executeQuery("SELECT * from services where service_id = (SELECT min(service_id) from services)");
				while(rs.next()){
					String id = rs.getString("service_id");
					return id;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can't load the Driver");
		}
		return "";
	}
	public void add()
	{
		id.setText(newid()); 
		id.setEditable(false);
		ServiceName.setText("");
		ServiceName.setEditable(true);
		AR.setText("");
		AR.setEditable(true);
		Duration.setText("");
		Duration.setEditable(true);
		Notes.setText("");
		Notes.setEditable(true);
		add.setEnabled(false);
		edit.setEnabled(false);
		refresh.setEnabled(false);
		viewall.setEnabled(false);
		close.setEnabled(false);
		save.setEnabled(false);
		add.setVisible(false);
		edit.setVisible(false);
		refresh.setVisible(false);
		viewall.setVisible(false);
		close.setVisible(false);
		save.setVisible(false);
		action[0].setEnabled(true);
		action[0].setVisible(true);
		action[1].setEnabled(true);
		action[1].setVisible(true);
	}
	
	public void look()
    {
        id.setEditable(false);
		ServiceName.setEditable(false);
		AR.setEditable(false);
		Duration.setEditable(false);
		Notes.setEditable(false);
		record.setEditable(false);
    }
	public void edit()
	{
		ServiceName.setEditable(true);
		AR.setEditable(true);
		Duration.setEditable(true);
		Notes.setEditable(true);
		record.setEditable(true);
	}
	
	
}
