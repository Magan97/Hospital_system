import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;
import com.swing.test.calender;


public class OutPatientMedicalAppointment extends HFrame{
	//JPanel panel;
	JLabel title, divide1, divide2;
	JComboBox<Object> status,inpat_id,appo_id,PD,Date,Bank,bstatus;
	JButton ps,ds,save,close,ok,cancel,back1,painfo,trinfo;
	String currenaid;
	String[] labels1 = {"Appointment bill id:","patient Code:","Bill Number:","bill date:"};
	String[] labels2 = {"Bill Amount:","discount:","net value:","status:"};
	String[] labels3 = {"Total Amount Paid:","Balance:","Paying Amount:","Balance Amount:","Bill Status:","Payment Date:"};
	String[] labels4 = {"Check No:","Date:","Bank:"};
	JTextField bid,text,ptext,app_c,hos_c,BillAmount,discount,net,TAP,Balance,Paying,BA,BS,CN;
	int[] move_x = {120,220,540,640};;
    JButton[] move = new JButton[4];
	String move_content[] = {"First","Last","Next","End"};
    int[] action_x = {150,370,590,150,370,590,150,590};
    JButton[] action = new JButton[8];
    String action_content[] = {"Add","Edit","Save","Refresh","View all","Close","Update","Back"};
    JTextField record,date;
    ArrayList<String> pid = new ArrayList<String>();
    ArrayList<String> aid = new ArrayList<String>();
    JRadioButton CashRadioButton,CreditCard,Cheque,Others;
    OutPatientMedicalAppointment()
    {
    	this("MPID_1");
    }
    OutPatientMedicalAppointment(String ID)
	{
		currenaid = ID;
		setSize(900,720);
        setTitle("Hospital management system");
        this.setLocation(150,20);
        //panel = new JPanel();
        panel.setLayout(null);
        title = new JLabel("Outpatient Medicine Bill Payments");
        title.setBounds(200, 50, 400, 50);
        title.setFont(new Font("Dialog", 1, 25));
        title.setSize(new Dimension(450,50));
        panel.add(title);
        
        divide1 = new JLabel("Outpatient Medicine Bill Payments--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        divide1.setBounds(80, 105, 800, 10);
        divide1.setFont(new Font("Dialog",1,15));
        divide1.setSize(new Dimension(700,20));
        panel.add(divide1);
        
        divide2 = new JLabel("Payment Info--------------------------------------------------------"
        		+ "------------------------------------------------------------");
        divide2.setBounds(80, 405, 800, 10);
        divide2.setFont(new Font("Dialog",1,15));
        divide2.setSize(new Dimension(700,20));
        panel.add(divide2);
        
        for(int i=0;i<4;i++)
        {
        	JLabel l= new JLabel(labels1[i]);
        	l.setBounds(100, 140+i*30, 120, 20);
        	panel.add(l);
        	
        	l= new JLabel(labels2[i]);
        	l.setBounds(450, 140+i*30, 120, 20);
        	panel.add(l);
        }
        
        for(int i=0;i<2;i++)
        {
        	JLabel l= new JLabel(labels3[i]);
        	l.setBounds(100+i*350, 380, 120, 20);
        	panel.add(l);
        
        }
        
        for(int i=2;i<6;i++)
        {
        	JLabel l= new JLabel(labels3[i]);
        	l.setBounds(100, 380+25*i, 120, 20);
        	panel.add(l);
        
        }
        
        
        for(int i=0;i<3;i++)
        {
        	JLabel l= new JLabel(labels4[i]);
        	l.setBounds(450, 455+25*i, 120, 20);
        	panel.add(l);
        
        }
        
        
        Paying = new JTextField("");
        Paying.setBounds(220, 430, 150, 20);
        panel.add(Paying);
        Paying.setEditable(false);
        Paying.addKeyListener(new KeyListener(){//only can write char
        	@Override
        	public void keyTyped(KeyEvent e){
        		int temp = e.getKeyChar();
        		//System.out.println(temp);
        		if(temp == 10){
        			//enter
        			int ba = Integer.parseInt(Balance.getText())-Integer.parseInt(Paying.getText());
        			BA.setText(ba+"");
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
        
        CN = new JTextField("");
        CN.setBounds(570, 455, 150, 20);
        panel.add(CN);
        CN.setEditable(false);
        
        BA = new JTextField("");
        BA.setBounds(220, 455, 150, 20);
        panel.add(BA);
        BA.setEditable(false);
        
        
        String[] status1 = {"Y-available","N-leaving"};
        bstatus = new JComboBox<Object>(status1);
        bstatus.setBounds(220, 480, 150, 20);
        panel.add(bstatus);
        /*
        BS = new JTextField("");
        BS.setBounds(220, 480, 150, 20);
        panel.add(BS);
        BS.setEditable(false);*/
        
        
        
        bid = new JTextField("");
        bid.setBounds(220, 140, 150, 20);
        panel.add(bid);
        bid.setEditable(false);
        
        geaid();
        int size1 = pid.size();
        String[] inpaid = new String[size1];
        for(int i=0;i<size1;i++){
        	inpaid[i] = (String)pid.get(i); 
        }
        inpat_id = new JComboBox<Object>(inpaid);
        inpat_id.setBounds(220, 170, 120, 20);
        panel.add(inpat_id);
        inpat_id.addItemListener(new ItemListener(){
        	@Override
        	public void itemStateChanged(ItemEvent e){
        		if(e.getStateChange() == ItemEvent.SELECTED){        			
        			appo_id.removeAllItems();
        			getbillid();
        			int size1 = aid.size();
                    String[] trid = new String[size1];
                    for(int i=0;i<size1;i++){
                    	trid[i] = (String)aid.get(i); 
                    	appo_id.addItem(trid[i]);
                    }
                    System.out.println("should print service appointment"+size1);;
        		}
        	}
        });
        
        calender ser = calender.getInstance();
        date = new JTextField();
        date.setBounds(220, 505, 150, 20);
        date.setText("2017-12-21");
        ser.register(date);
        panel.add(date);
        /*
        PD= new JComboBox<Object>();
        PD.setBounds(220, 505, 150, 20);
        panel.add(PD);*/
        
        calender ser2 = calender.getInstance();
        ptext = new JTextField();
        ptext.setBounds(570, 480, 150, 20);
        ptext.setText("2017-12-21");
        ser2.register(ptext);
        panel.add(ptext);
        /*
        Date = new JComboBox<Object>();
        Date.setBounds(570, 480, 150, 20);
        panel.add(Date);*/
        
        String[] banks = {"Chinese Bank","Aigriculture Bank","Construction Bank"};       
        Bank = new JComboBox<Object>(banks);
        Bank.setBounds(570, 505, 150, 20);
        panel.add(Bank);
        
        
        
        painfo = new JButton("..");
        painfo.setBounds(360, 170, 50, 20);
        panel.add(painfo);
        painfo.addActionListener(new ActionListener() { //room table
            @Override
            public void actionPerformed(ActionEvent e) {
            	patable();
            }
        });
        
        size1 = aid.size();
        String[] trid = new String[size1];
        for(int i=0;i<size1;i++){
        	trid[i] = (String)aid.get(i); 
        }
        appo_id = new JComboBox<Object>(trid);
        appo_id.setBounds(220, 200, 120, 20);
        panel.add(appo_id);
        appo_id.addItemListener(new ItemListener(){
        	@Override
        	public void itemStateChanged(ItemEvent e){
        		if(e.getStateChange() == ItemEvent.SELECTED){        			
        			get_charge();
        			System.out.println("charge");
        		}
        	}
        });
        
        trinfo = new JButton("..");
        trinfo.setBounds(360, 200, 50, 20);
        panel.add(trinfo);
        trinfo.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	trtable();
            }
        });
        
        calender ser1 = calender.getInstance();
        text = new JTextField();
        text.setBounds(220, 230, 150, 20);
        text.setText("2017-12-21");
        ser1.register(text);
        panel.add(text);
        
        BillAmount = new JTextField("");
        BillAmount.setBounds(570, 140, 150, 20);
        panel.add(BillAmount);
        
        discount = new JTextField("");
        discount.setBounds(570, 170, 150, 20);
        panel.add(discount);
        
        net = new JTextField("");
        net.setBounds(570, 200, 150, 20);
        panel.add(net);
        
        TAP = new JTextField("");
        TAP.setBounds(220, 380, 150, 20);
        panel.add(TAP);
        
        Balance = new JTextField("");
        Balance.setBounds(570, 380, 150, 20);
        panel.add(Balance);
        
        String[] status2 = {"Y-available","N-leaving"};
        status = new JComboBox<Object>(status2);
        status.setBounds(570, 230, 150, 20);
        panel.add(status);
        
        CashRadioButton = new JRadioButton("Cash");
        CashRadioButton.setBounds(440, 428, 120, 20);
        panel.add(CashRadioButton);
        
        CreditCard = new JRadioButton("Credit Card");
        CreditCard.setBounds(560, 428, 120, 20);
        panel.add(CreditCard);
        
        Cheque = new JRadioButton("Cheque");
        Cheque.setBounds(680, 428, 120, 20);
        panel.add(Cheque);
        
        Others = new JRadioButton("Others");
        Others.setBounds(800, 428, 80, 20);
        panel.add(Others);
        
        ButtonGroup bg=new ButtonGroup();
        bg.add(CashRadioButton);
        bg.add(CreditCard);
        bg.add(Cheque);
        bg.add(Others);
        
        showall();
       
        int j;
        for(j=0;j<4;j++)
        {
        	move[j] = new JButton(move_content[j]);
        	move[j].setBounds(move_x[j], 540, 70, 30);
        	panel.add(move[j]);
        }
        move[0].addActionListener(new ActionListener() { //first
            @Override
            public void actionPerformed(ActionEvent e) {
            	currenaid = minID();
                getInfo(currenaid);
                look();
            }
        });
        move[1].addActionListener(new ActionListener() { //last
            @Override
            public void actionPerformed(ActionEvent e) {
            	String firsaid = minID();
            	String[] sourceArray = currenaid.split("_");
            	if(Integer.parseInt(sourceArray[1])-1>=1){
            		int myid = Integer.parseInt(sourceArray[1])-1;
            		currenaid = "MPID_"+myid;
            	}
            	getInfo(currenaid);
                look();
            }
        });
        move[2].addActionListener(new ActionListener() { //next
            @Override
            public void actionPerformed(ActionEvent e) {
            	String endid = maxID();
            	String[] thisArray = currenaid.split("_");
            	String[] endArray = endid.split("_");
            	if(Integer.parseInt(thisArray[1]) < Integer.parseInt(endArray[1])){
            		int myid = Integer.parseInt(thisArray[1])+1;
            		currenaid = "MPID_"+myid;
            	}
            	getInfo(currenaid);
                look();
            }
        });
        move[3].addActionListener(new ActionListener() { //end
            @Override
            public void actionPerformed(ActionEvent e) {
            	currenaid = maxID();
                getInfo(currenaid);
                look();
            }
        });
        
        String[] sourceArray = ID.split("_");
        record = new JTextField("          record  "+Integer.parseInt(sourceArray[1]));
        record.setBounds(320, 540, 200, 30);
        record.setEditable(false);
        panel.add(record);              
        
        for(int i=0;i<3;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 580, 80, 30);
        	panel.add(action[i]);
        }
        for(int i=3;i<6;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 620, 80, 30);
        	panel.add(action[i]);
        }
        for(int i=6;i<8;i++)
        {
        	action[i] = new JButton(action_content[i]);
        	action[i].setBounds(action_x[i], 580, 80, 30);
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
            	save();
            	look();
            }
        });
        action[3].addActionListener(new ActionListener() { //refresh
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(currenaid);
            }
        });
        
        action[4].addActionListener(new ActionListener() { //arch
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	//
            	ViewAllOutMedical ViewAllOutService1 = new  ViewAllOutMedical();
            	ViewAllOutService1.setVisible(true);
            	ViewAllOutService1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
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
            	addnew();
            	look();
            	getInfo(currenaid);
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
        action[7].addActionListener(new ActionListener() { //back
            @Override
            public void actionPerformed(ActionEvent e) {
            	look();
            	getInfo(currenaid);
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
        getInfo(currenaid);
        this.add(panel);
	}
    public void geaid()
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
				ResultSet rs = stmt.executeQuery("select * from outpatient_medicine_bill");
				while(rs.next()){
					String id = rs.getString("patient_id");
					pid.add(id);
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
    public void getbillid()
	{
    	String patid = inpat_id.getSelectedItem().toString();
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
				ResultSet rs = stmt.executeQuery("select * from outpatient_medicine_bill where patient_id = '"+patid+"'");
				while(rs.next()){
					String id = rs.getString("medicine_bill_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_medicine_bill_payment where bill_payment_id = (SELECT max(bill_payment_id) from outpatient_medicine_bill_payment)");
				if(rs.next()){
					String id = rs.getString("bill_payment_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "MPID_"+myid;
					return newid;
				}
				else{
					return "MPID_1";
				}
			}catch(Exception e){
				return "MPID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can��t load the Driver");
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_medicine_bill_payment where bill_payment_id = (SELECT max(bill_payment_id) from outpatient_medicine_bill_payment)");
				while(rs.next()){
					String id = rs.getString("bill_payment_id");
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_medicine_bill_payment where bill_payment_id = (SELECT min(bill_payment_id) from outpatient_medicine_bill_payment)");
				while(rs.next()){
					String id = rs.getString("bill_payment_id");
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
	public void getInfo(String thibid)
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
				ResultSet rs = stmt.executeQuery("SELECT * from outpatient_medicine_bill_payment where bill_payment_id like '"+thibid+"'");
				while(rs.next()){
					bid.setText(thibid);
					appo_id.addItem(rs.getString("medicine_bill_id"));
					appo_id.setSelectedItem(rs.getString("medicine_bill_id"));
					Paying.setText(rs.getString("amount_paid"));
					date.setText(rs.getString("paid_date"));
					String type = rs.getString("payment_type");
					if(type.indexOf("Cash") == -1)
						CashRadioButton.setSelected(true);
					else if(type.indexOf("Credit_Card") == -1)
						CreditCard.setSelected(true);
					else if(type.indexOf("Cheque") == -1)
						Cheque.setSelected(true);
					else
						Others.setSelected(true);
					CN.setText(rs.getString("cheque_no"));
					ptext.setText(rs.getString("cheque_date"));
					Bank.setSelectedItem(rs.getString("bank"));
					
				}
			}catch(Exception e){			
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		String[] sourceArray = currenaid.split("_");
        record.setText("          Record  "+Integer.parseInt(sourceArray[1]));
	}
	public void look()
	{
		text.setEditable(false);
		BillAmount.setEditable(false);
		discount.setEditable(false);
		net.setEditable(false);
		TAP.setEditable(false);
		Balance.setEditable(false);
	}
	public void add()
	{
		bid.setText(newid());
		/*
		app_c.setEditable(true);
		hos_c.setEditable(true);*/
		BillAmount.setText("");
		discount.setText("");
		net.setText("");
		for(int i=0;i<4;i++)
    		move[i].setEnabled(false);
		for(int i=0;i<6;i++)
			action[i].setVisible(false);
		for(int i=6;i<8;i++)
			action[i].setVisible(true);
		
		TAP.setEditable(true);
		Balance.setEditable(true);
		Paying.setEditable(true);
		BA.setEditable(true);
		CN.setEditable(true);
		ptext.setEditable(true);
		date.setEditable(true);
	}
	public void edit()
	{
		/*
		app_c.setEditable(true);
		hos_c.setEditable(true);*/
	}
	public void addnew()
	{
		String info[] = new String[8];
		info[0] = bid.getText();
		info[1] = appo_id.getSelectedItem().toString();
		info[2] = Paying.getText();
		info[3] = date.getText();
		if(CashRadioButton.isSelected()){
			info[4] = "Cash";
		}
		else if(CreditCard.isSelected()){
			info[4] = "Credit_Card";
		}
		else if(Cheque.isSelected()){
			info[4] = "Cheque";
		}else{
			info[4] = "Others";
		}
		info[5] =  CN.getText();
		info[6] = ptext.getText();
		info[7] = Bank.getSelectedItem().toString();
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
				int rs = stmt.executeUpdate("insert into outpatient_medicine_bill_payment (bill_payment_id,medicine_bill_id,amount_paid,paid_date,payment_type,cheque_no,cheque_date,bank) "
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
		info[0] = bid.getText();
		info[1] = appo_id.getSelectedItem().toString();
		info[2] = Paying.getText();
		info[3] = date.getText();
		if(CashRadioButton.isSelected()){
			info[4] = "Cash";
		}
		else if(CreditCard.isSelected()){
			info[4] = "Credit_Card";
		}
		else if(Cheque.isSelected()){
			info[4] = "Cheque";
		}else{
			info[4] = "Others";
		}
		info[5] =  CN.getText();
		info[6] = ptext.getText();
		info[7] = Bank.getSelectedItem().toString();
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
				int rs = stmt.executeUpdate("update outpatient_medicine_bill_payment set "
						+ "medicine_bill_id='"+info[1]+"',amount_paid='"+info[2]+"',paid_date='"+info[3]
								+"',payment_type='"+info[4]+"',cheque_no='"+info[5]+"',cheque_date='"+info[6]+"',bank='"+info[7]+"'where bill_payment_id like '"+info[0]+"'");
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
	public void patable()
	{
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel title1 = new JLabel("Out Patients");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
		String[] columnName = {"patient id","name","gender","telephone","status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from patient_details");
				if(rs1.next()){
					int n = rs1.getInt(1);
					num+=n;
				}
				data1 = new Object[num][5];
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from patient_details");
				while(rs2.next()){
					data1[i][0] = rs2.getString("patient_id");
					data1[i][1] = rs2.getString("firstname")+rs2.getString("lastname");
					data1[i][2] = rs2.getString("gender");
					data1[i][3] = rs2.getString("telephone");
					if(rs2.getString("status").indexOf("Y") != -1)
						data1[i][4] = "Y";
					else
						data1[i][4] = "N";
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
	public void trtable()
	{
		JFrame l = new JFrame();
		JPanel panel1;
		l.setLayout(null);
		l.setSize(400,350);
        l.setTitle("Hospital System");
        l.setLocation(350,180);
        panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel title1 = new JLabel("Out Patients");
        title1.setBounds(140, 20, 150, 80);
        l.add(title1);
        String patient = inpat_id.getSelectedItem().toString();
		String[] columnName = {"appointment id","patient id","service id","Date/Time","status"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from service_appointment where patient_id like '"+patient+"'");
				if(rs1.next()){
					int n = rs1.getInt(1);
					num+=n;
				}
				data1 = new Object[num][5];
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from service_appointment where patient_id like '"+patient+"'");
				while(rs2.next()){
					data1[i][0] = rs2.getString("appointment_id");
					data1[i][1] = rs2.getString("patient_id");
					data1[i][2] = rs2.getString("hospital_service_id");
					data1[i][3] = rs2.getString("appointment_date")+ rs2.getString("appointment_time");
					if(rs2.getString("status").indexOf("Y") != -1)
						data1[i][4] = "Y";
					else
						data1[i][4] = "N";
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
	public void get_treatment()
	{
		String patient = inpat_id.getSelectedItem().toString();
		try {
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			int num=0;
			try{
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from outpatient_medicine_bill where patient_id like '"+patient+"'");
				while(rs2.next()){
					String id = rs2.getString("medicine_bill_id");
					aid.add(id);
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
	}
	public void get_charge()
	{
		String bill = appo_id.getSelectedItem().toString();
		try {
			String url = "jdbc:mysql://localhost/hospital_system";
			String login = "root";
			String password = "";
			Connection con;
			con = DriverManager.getConnection(url, login, password);
			con.setAutoCommit(false);
			int i=0;
			int num=0;
			try{
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from outpatient_medicine_bill where medicine_bill_id like '"+bill+"'");
				while(rs2.next()){
					text.setText(rs2.getString("bill_date"));
					BillAmount.setText(rs2.getString("grand_total"));
					discount.setText(rs2.getString("discount"));
					net.setText(rs2.getString("net_value"));	
					TAP.setText(rs2.getString("net_value"));
					
					//need change
					
					Balance.setText(rs2.getString("net_value"));
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
	}
	
	public void showall()
	{
		String selectsql;
		selectsql = "select * from outpatient_medicine_bill_payment";
		String[] columnName = {"NO","AMOUNT PAID","PAID DATE","PAY TYPE","CEDIT CARD/CHEQUE","CHEQUE DATE","BANK"};
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
				ResultSet rs1 = stmt1.executeQuery("select count(*) from outpatient_medicine_bill_payment");
				if(rs1.next()){
					int n = rs1.getInt(1);
					data1 = new Object[n][8];
				}
				Statement stmt2 = (Statement) con.createStatement();
				ResultSet rs2 = stmt1.executeQuery(selectsql);
				while(rs2.next()){
					data1[i][0] = rs2.getString("bill_payment_id");
					data1[i][1] = rs2.getString("medicine_bill_id");
					data1[i][2] = rs2.getString("amount_paid");
					data1[i][3] = rs2.getString("paid_date");
					data1[i][4] = rs2.getString("payment_type");
					data1[i][5] = rs2.getString("cheque_no");
					data1[i][6] = rs2.getString("cheque_date");
					data1[i][7] = rs2.getString("bank");					
					i++;
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}
			
		}catch(SQLException ex){
			System.out.println("Can’t load the Driver");
		}
	    final JTable table = new JTable(data1, columnName);
        table.setPreferredScrollableViewportSize(new Dimension(800,100));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 270, 800, 100);
        panel.add(scrollPane);
	}

}

