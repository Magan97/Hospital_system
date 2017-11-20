import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class ViewAll extends JFrame{
	JPanel panel;
	JLabel title,sfor,stext;
	JButton search,refresh,close;
	JTextField searchText;
	JComboBox searchFor;
	ViewAll(){
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("View Doctor Details");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        /*
        String[] columnName = {"DocID", "Firstname", "Lastname","Sex","Home phone",
        		"NIC No","Mobile phone","Address","Qualfications","Specialization",
        		"Type","Visit charge","Channeling Charge","Basic Salary","Notes"};        
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from doctor_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][15];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt1.executeQuery("select * from doctor_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("doctor_id");
					data1[i][1] = rs2.getString("doctor_fname");
					data1[i][2] = rs2.getString("doctor_lname");
					data1[i][3] = rs2.getString("doctor_sex");
					data1[i][4] = rs2.getString("doctor_hphone");
					data1[i][5] = rs2.getString("doctor_NID");
					data1[i][6] = rs2.getString("doctor_mphone");
					data1[i][7] = rs2.getString("doctor_address");
					data1[i][8] = rs2.getString("doctor_qualification");
					data1[i][9] = rs2.getString("doctor_specialization");
					data1[i][10] = rs2.getString("doctor_type");
					data1[i][11] = rs2.getString("doctor_vcharge");
					data1[i][12] = rs2.getString("doctor_ccharge");
					data1[i][13] = rs2.getString("doctor_notes");
					data1[i][14] = rs2.getString("doctor_basic_sal");
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
        table.setPreferredScrollableViewportSize(new Dimension(800,400));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 800, 400);
        panel.add(scrollPane);*/
        
        search = new JButton("Search");
        search.setBounds(270, 510, 80, 30);
        panel.add(search);
        
        refresh = new JButton("Refresh");
        refresh.setBounds(390, 510, 80, 30);
        panel.add(refresh);
        
        close = new JButton("Close");
        close.setBounds(510, 510, 80, 30);
        panel.add(close);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	Doctor doctor = new Doctor("DCID_1");
        		doctor.setVisible(true);
                doctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        
        sfor = new JLabel("Search For");
        sfor.setBounds(150, 560, 80, 30);
        panel.add(sfor);
        
        String[] search1 = {"Doctor Id","First Name","Last Name","Sex","Home Phone",
        		"NIC No","Mobile Phone","Address","Qualfications","Specialization",
        		"Doctor Type","Visiting Charge","Channeling Charge","Basic Salary","Notes","Status"};
        searchFor = new JComboBox<Object>(search1);
        searchFor.setBounds(250, 560, 150, 30);
        panel.add(searchFor);
        
        stext = new JLabel("Search Text");
        stext.setBounds(450, 560, 80, 30);
        panel.add(stext);
        
        searchText = new JTextField("");
        searchText.setBounds(550, 560, 150, 30);
        panel.add(searchText);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	showall();
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	showall();
            }
        });
        showall();

        this.add(panel);
	}
	public void showall()
	{
		String t = searchFor.getSelectedItem().toString();
		//String t = "Doctor ID";
		String seartext = searchText.getText();
		//String seartext = "";
		String selectsql;
		if(seartext.equals(""))
			selectsql = "select * from doctor_details";
		else{
			String type="";
			if(t == "Doctor Id")
				type = "doctor_id";
			if(t == "First Name")
				type = "doctor_fname";
			if(t == "Last Name")
				type = "doctor_lname";
			if(t == "Sex")
				type = "doctor_sex";
			if(t == "Home Phone")
				type = "doctor_hphone";
			if(t == "NIC No")
				type = "doctor_NID";
			if(t == "Mobile Phone")
				type = "doctor_mphone";
			if(t == "Address")
				type = "doctor_address";
			if(t == "Qualfications")
				type = "doctor_qualification";
			if(t == "Specialization")
				type = "doctor_specialization";
			if(t == "Doctor Type")
				type = "doctor_type";
			if(t == "Visiting Charge")
				type = "doctor_vcharge";
			if(t == "Channeling Charge")
				type = "doctor_ccharge";
			if(t == "Basic Salary")
				type = "doctor_basic_sal";
			if(t == "Notes")
				type = "doctor_notes";
			if(t == "Status")
				type = "doctor_status";
			selectsql = "select * from doctor_details where "+type+" like '"+seartext+"'";
		}
		System.out.println(selectsql);
		String[] columnName = {"DocID", "Firstname", "Lastname","Sex","Home phone",
	        		"NIC No","Mobile phone","Address","Qualfications","Specialization",
	        		"Type","Visit charge","Channeling Charge","Basic Salary","Notes","Status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from doctor_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][16];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt1.executeQuery(selectsql);
				while(rs2.next()){
					data1[i][0] = rs2.getString("doctor_id");
					data1[i][1] = rs2.getString("doctor_fname");
					data1[i][2] = rs2.getString("doctor_lname");
					data1[i][3] = rs2.getString("doctor_sex");
					data1[i][4] = rs2.getString("doctor_hphone");
					data1[i][5] = rs2.getString("doctor_NID");
					data1[i][6] = rs2.getString("doctor_mphone");
					data1[i][7] = rs2.getString("doctor_address");
					data1[i][8] = rs2.getString("doctor_qualification");
					data1[i][9] = rs2.getString("doctor_specialization");
					data1[i][10] = rs2.getString("doctor_type");
					data1[i][11] = rs2.getString("doctor_vcharge");
					data1[i][12] = rs2.getString("doctor_ccharge");
					data1[i][13] = rs2.getString("doctor_basic_sal");
					data1[i][14] = rs2.getString("doctor_notes");
					data1[i][15] = rs2.getString("doctor_status");
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
	        table.setPreferredScrollableViewportSize(new Dimension(800,400));
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(50, 100, 800, 400);
	        panel.add(scrollPane);
	}
}
