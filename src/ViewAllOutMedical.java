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


public class ViewAllOutMedical extends HFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JPanel panel;
	JLabel title,sfor,stext;
	JButton search,refresh,close;
	JTextField searchText;
	JComboBox<Object> searchFor;
	ViewAllOutMedical()
	{
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("All Out Patient Medical Appointment Bill Payments");
        title.setBounds(150, 50, 800, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(600,50));
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
            	OutPatientMedicalAppointment o = new OutPatientMedicalAppointment("MPID_1");
        		o.setVisible(true);
                o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        
        sfor = new JLabel("Search For");
        sfor.setBounds(150, 560, 80, 30);
        panel.add(sfor);
        
        String[] search1 = {"NO","Amount Paid","Paid Date","Pay Type","CERDIT CARD/CHEQUE NO","Cheque Date","Bank"};
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
			selectsql = "select * from outpatient_medicine_bill_payment";
		else{
			String type="";
			if(t == "NO")
				type = "bill_payment_id";
			if(t == "Amount Paid")
				type = "amount_paid";
			if(t == "Paid Date")
				type = "paid_date";
			if(t == "Pay Type")
				type = "payment_type";
			if(t == "CERDIT CARD/CHEQUE NO")
				type = "cheque_no";
			if(t == "DD Date")
				type = "cheque_date";
			if(t == "Bank")
				type = "bank";
			selectsql = "select * from outpatient_medicine_bill_payment where "+type+" like '"+seartext+"'";
		}
		System.out.println(selectsql);
		String[] columnName = {"NO","Amount Paid","Paid Date","Pay Type","CERDIT CARD/CHEQUE NO","Cheque Date","Bank"};
		Object[][] data1 = null;
		try {
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			try{
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select count(*) from outpatient_medicine_bill_payment");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][7];
				}
				ResultSet rs2 = stmt1.executeQuery(selectsql);
				while(rs2.next()){
					data1[i][0] = rs2.getString("bill_payment_id");
					data1[i][1] = rs2.getInt("amount_paid");
					data1[i][2] = rs2.getString("paid_date");
					data1[i][3] = rs2.getString("payment_type");
					data1[i][4] = rs2.getInt("cheque_no");
					data1[i][5] = rs2.getString("cheque_date");
					data1[i][6] = rs2.getString("bank");
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

