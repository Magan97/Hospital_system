import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class InPatientHospitalService_all extends HFrame{
	//JPanel panel;
	JLabel title,sfor,stext;
	JButton search,refresh,close;
	JTextField searchText;
	JComboBox searchFor;
	InPatientHospitalService_all()
	{
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("In Patient Hospital Service");
        title.setBounds(300, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
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
            	InPatientHospitalService rd = new InPatientHospitalService("IPHS_1");
        		rd.setVisible(true);
                rd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        
        sfor = new JLabel("Search For");
        sfor.setBounds(150, 560, 80, 30);
        panel.add(sfor);
        
        String[] search1 = {"bill id","bill date","patient id","patient name","admission id","date","time","discription","service id","service name","charge","status"};
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
		String seartext = searchText.getText();
		String selectsql;
		if(seartext.equals(""))
			selectsql = "select * from inpatient_services";
		else{
			String type="";
			if(t == "bill id")
				type = "inpatient_service_id";
			if(t == "bill date")
				type = "bill_date";
			if(t == "patient id")
				type = "inpatient_id";
			if(t == "patient name")
				type = "inpatient_name";
			if(t == "admission id")
				type = "admission_id";
			if(t == "date")
				type = "service_date";
			if(t == "time")
				type = "service_time";
			if(t == "discription")
				type = "description";
			if(t == "service id")
				type = "hospital_service_id";
			if(t == "service name")
				type = "service_name";
			if(t == "charge")
				type = "service_charge";
			if(t == "status")
				type = "status";
			selectsql = "select * from inpatient_services where "+type+" like '"+seartext+"'";
		}
		System.out.println(selectsql);
		String[] columnName = {"bill id","bill date","patient id","patient name","admission id","date","time","discription","service id","service name","charge","status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from inpatient_services");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][12];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(selectsql);
				while(rs2.next()){
					data1[i][0] = rs2.getString("inpatient_service_id");
					data1[i][1] = rs2.getString("bill_date");
					data1[i][2] = rs2.getString("inpatient_id");
					data1[i][3] = rs2.getString("inpatient_name");
					data1[i][4] = rs2.getString("admission_id");
					data1[i][5] = rs2.getString("service_date");
					data1[i][6] = rs2.getString("service_time");
					data1[i][7] = rs2.getString("description");
					data1[i][8] = rs2.getString("hospital_service_id");
					data1[i][9] = rs2.getString("service_name");
					data1[i][10] = rs2.getString("service_charge");					
					if(rs2.getString("status").indexOf("Y") != -1)
						data1[i][11] = "Y-available";
					else
						data1[i][11] = "N-leaving";
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
