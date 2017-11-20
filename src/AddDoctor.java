import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;


public class AddDoctor extends JFrame{
	JPanel panel;
	JLabel title,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18;
	JTextField id,fn,ln,hp,nn,mp,ad,qu,vc,note,cc,bs,record;
	JComboBox sex,dt,status,sp;
	JButton ok,close;
	AddDoctor()
	{
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
        
        id = new JTextField(addit());
        id.setBounds(250, 100, 150, 20);
        panel.add(id);
        id.setEditable(false);
                
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
        
        String[] sp1 = {"Cardiologist","Dentist","Dermatologist","Neurologist",
        		"Oncologist","ENT","Orthopedic","Plastic surgeon","Pediatrician"};
        sp = new JComboBox<Object>(sp1);
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
        
        String[] dt1 = {"Permanent", "Temperory"};
        dt = new JComboBox<Object>(dt1);
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
        
        ok = new JButton("OK");
        ok.setBounds(320, 490, 80, 30);
        panel.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	getInfo();
            }
        });
                
        close =  new JButton("Close");
        close.setBounds(440, 490, 80, 30);
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
	}
	public String addit()
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
				ResultSet rs = stmt.executeQuery("SELECT * from doctor_details where doctor_id = (SELECT max(doctor_id) from doctor_details)");
				if(rs.next()){
					
					String id = rs.getString("doctor_id");
					String[] sourceArray = id.split("_");
					int myid = Integer.parseInt(sourceArray[1])+1;
					String newid = "DCID_"+myid;
					return newid;
				}
				else{
					return "DCID_1";
				}
			}catch(Exception e){
				return "DCID_1";
			}
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can¡¯t load the Driver");
		}
		return "";
	}
	public void getInfo()
	{
		//JTextField id,fn,ln,hp,nn,mp,ad,qu,sp,vc,note,cc,bs;
		//JComboBox sex,dt;
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
		if(fn1 =="" || ln1=="" || vc1==""|| cc1=="" || bs1 ==""|| mp1=="")
		{
        	System.out.println("not full info");      	
        }
        else{
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
    				int rs = stmt.executeUpdate("insert into doctor_details (doctor_id,doctor_fname,doctor_lname,doctor_sex,doctor_NID,doctor_hphone,doctor_mphone,"
    						+ "doctor_address,doctor_qualification,doctor_specialization,doctor_type,doctor_vcharge,doctor_ccharge,doctor_notes,doctor_basic_sal,doctor_status) "
    						+ "values('"+id1+"','"+fn1+"','"+ln1+"','"+sex1+"','"+nn1+"','"+hp1+"','"+mp1
    						+"','"+ad1+"','"+qu1+"','"+sp1+"','"+dt1+"','"+vc1+"','"+cc1+"','"+note1+"','"+bs1+"','"+status1+"')");
    				Statement stmt1 = (Statement) con.createStatement();
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
	}
	public void reminder(String s){
    	JFrame jf = new JFrame();
    	jf.setSize(300,200);
    	jf.setLayout(null);
        jf.setTitle("reminder");
        jf.setLocation(450,180);
        //jp.setLayout(null);;
        jf.setVisible(true);
        JLabel l1 = new JLabel();
        l1.setText("Add "+s+" !");
        l1.setBounds(60, 30, 200, 50);
        JButton b1 = new JButton("ok");
        b1.setBounds(120, 100, 60, 30);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //dispose();
            	jf.setVisible(false);
		        //rt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        jf.add(l1);
        jf.add(b1);
    }

}

