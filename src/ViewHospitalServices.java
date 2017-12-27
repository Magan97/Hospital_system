/*import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewHospitalServices extends JFrame{
	private static final long serialVersionUID = 1L;
	Jpanel panel;
	JTable Table;
	JLabel Title;
	DefaultTableModel model;
	JButton Search,Refresh,Close;
	String[] columns={"Service ID", "Medical Service", "Average Duration","Charge","Notes"}; 
	public ViewHospitalServices(){
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,30);
        panel = new Jpanel();
        panel.setLayout(null);
        Title = new JLabel("View Hospital Services Details");
        Title.setBounds(200, 50, 400, 50);
        Title.setFont(new Font("Courier", 1, 35));
        Title.setSize(new Dimension(600,50));
        panel.add(Title);
        setContentPane(panel);
        String[] columnName = {"Service ID", "Medical Service", "Average Duration","Charge","Notes"};
        JTable Table = new JTable(columnName);
        Table.setPreferredScrollableViewportSize(new Dimension(800,400));
        JScrollPane scrollPane = new JScrollPane(Table);
        scrollPane.setBounds(50, 100, 800, 400);
        panel.add(scrollPane);
    
  
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(50, 120, 800, 400);
	    panel.add(scrollPane);
	    defaultTableModel();
		scrollPane.setViewportView(getTable());
		
		
		Search = new JButton("Search");
        Search.setBounds(250, 550, 90, 32);
        Search.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(Search);
        
        Refresh = new JButton("Refresh");
        Refresh.setBounds(370, 550, 90, 32);
        Refresh.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(Refresh);
        
        
        Close = new JButton("Close");
        Close.setBounds(490, 550, 90, 32);
        Close.setFont(new Font("Courier", Font.ITALIC, 15));
        panel.add(Close);
        
		}
		  
		private JTable getTable(){
		
		SqlSon s = new SqlSon();
		Table=new JTable(); 
	    Table.setPreferredScrollableViewportSize(new Dimension(800,400));
		//DefaultTableModel model = new DefaultTableModel();
		
		model.addRow(columns);
		
		String[] argStrings = new String[5];
		ArrayList<ArrayList<String>> AllInformation = s.selectA("services", 5);
		for(int i = 0;i < AllInformation.size();i++)
		{
			
			for(int j = 0;j < AllInformation.get(i).size();j++)
			{
				argStrings[j] = AllInformation.get(i).get(j);
				
			}
			model.addRow(argStrings);
		}
		model.removeRow(0);
		Table.setModel(model);
		
		
		return Table;
	}
		
		private void defaultTableModel(){
			model=new DefaultTableModel(columns,0);
			Table=new JTable(model){
				*//**
				 * 
				 *//*
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row,int column){
					return false;
				}
				};
			}
	}

		
*/


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


public class ViewHospitalServices extends HFrame{
	JLabel title,sfor,stext;
	JButton search,refresh,close;
	JTextField searchText;
	JComboBox searchFor;
	ViewHospitalServices()
	{
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        panel.setLayout(null);
        title = new JLabel("All Services");
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
            	MedicalServiceGUI rd = new MedicalServiceGUI("SVID_1");
        		rd.setVisible(true);
                rd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        
        sfor = new JLabel("Search For");
        sfor.setBounds(150, 560, 80, 30);
        panel.add(sfor);
        
        String[] search1 = {"Service ID", "Medical Service", "Average Duration","Charge","Notes","Status"};
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
			selectsql = "select * from services";
		else{
			String type="";
			if(t == "Service ID")
				type = "service_id";
			if(t == "Medical Service")
				type = "service_name";
			if(t == "Average Duration")
				type = "charge_for_service";
			if(t == "Charge")
				type = "duration_of_service	";
			if(t == "Notes")
				type = "service_notes";
			if(t == "Status")
				type = "status";
			selectsql = "select * from services where "+type+" like '"+seartext+"'";
		}
		System.out.println(selectsql);
		String[] columnName = {"Service ID", "Medical Service", "Average Duration","Charge","Notes","Status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from services");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][6];
				}
				rs1.close();
				ResultSet rs2 = stmt1.executeQuery(selectsql);
				while(rs2.next()){
					data1[i][0] = rs2.getString(1);
					data1[i][1] = rs2.getString(2);
					data1[i][2] = rs2.getInt(4);
					data1[i][3] = rs2.getInt(3);
					data1[i][4] = rs2.getString(5);
					if(rs2.getString("status").indexOf("Y") != -1)
						data1[i][5] = "Y-available";
					else
						data1[i][5] = "N-leaving";
					i++;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(SQLException ex){
			System.out.println("Can��t load the Driver");
		}
	    final JTable table = new JTable(data1, columnName);
        table.setPreferredScrollableViewportSize(new Dimension(800,400));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 800, 400);
        panel.add(scrollPane);
	}
}