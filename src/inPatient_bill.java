import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class inPatient_bill extends HFrame{
	Hashtable<String, Object> objects = new Hashtable<String, Object>();
	Hashtable<String, JButton> checks = new Hashtable<String, JButton>();
	ArrayList<String> Names = new ArrayList<String>();
	ArrayList<Rectangle> Positions = new ArrayList<Rectangle>();
	int[] textFields = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	int[] comboBoxs = {0, 1};
	String[] Columns = {"patient_bill_id", "patient_id", "admission_id", "discharge_date", "doctor_charges", "medicine_charges", "service_charges", "room_charges", "hospital_charges", "discount", "net_value", "other_bill_detail"};
	String status = "n";
	
	inPatient_bill(){
		super();

		init_objects();
		init_comboBox();
		init_buttons();
		init_checkButtons();
		
		set_uneditable();
		this.add(panel);
	}
	
	public void init_objects() {
		addObject("combobox", "patient_id", new Rectangle(50, 100, 120, 20));
		addObject("combobox", "admission_id", new Rectangle(50, 140, 120, 20));
		addObject("textfield", "status", new Rectangle(50, 180, 120, 20));
		addObject("textfield", "room_ward_id", new Rectangle(50, 220, 120, 20));
		addObject("textfield", "bed_id", new Rectangle(50, 260, 120, 20));

		addObject("textfield", "doctor_charges", new Rectangle(50, 320, 120, 20));
		addObject("textfield", "service_charges", new Rectangle(50, 360, 120, 20));
		addObject("textfield", "medicine_charges", new Rectangle(50, 400, 120, 20));
		addObject("textfield", "room_charges", new Rectangle(50, 440, 120, 20));
		addObject("textfield", "hospital_charges", new Rectangle(50, 480, 120, 20));

		addObject("textfield", "discharge_date", new Rectangle(450, 180, 120, 20));
		
		
		addObject("textfield", "other_bill_detail", new Rectangle(450, 320, 120, 20));
		addObject("textfield", "total", new Rectangle(450, 360, 120, 20));
		addObject("textfield", "discount", new Rectangle(450, 400, 120, 20));
		addObject("textfield", "net_value", new Rectangle(450, 440, 120, 20));

		for(int i=0;i<Names.size();i++) {
			((Component) objects.get(Names.get(i))).setBounds(Positions.get(i).x + 150, Positions.get(i).y, Positions.get(i).width, Positions.get(i).height);
			panel.add((Component) objects.get(Names.get(i)));
			
			JLabel a = new JLabel(Names.get(i)); a.setBounds(Positions.get(i));
			panel.add(a);
			
		}
	}
	
	public void init_buttons() {
		JButton saveButton = new JButton("Add");
		panel.add(saveButton);
		saveButton.setBounds(new Rectangle(200, 600, 100, 30));
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(status == "e") {
					status = "n";
					save();
					set_uneditable();
				}
				if(status == "n") {
					status = "e";
					set_editable();
				}
			}
		});
	}

	public void init_comboBox() {
		ArrayList<String> coList = new ArrayList<String>();
		coList.add("patient_id");
		ArrayList<ArrayList<String>> itemList = sql.select("in_patient_details", coList);
		for(int i=0;i<itemList.size();i++) {
			((JComboBox<String>) objects.get("patient_id")).addItem(itemList.get(i).get(0));
		}
		
		ArrayList<String> coList2 = new ArrayList<String>();
		coList2.add("admission_id");
		ArrayList<ArrayList<String>> itemList2 = sql.select("admission_details", coList2);
		for(int i=0;i<itemList2.size();i++) {
			((JComboBox<String>) objects.get("admission_id")).addItem(itemList2.get(i).get(0));
		}
	}
	
	public void init_checkButtons() {
		add_CheckButton("patient_details", new Rectangle(350, 100, 80, 20));
		add_CheckButton("admission_details", new Rectangle(350, 140, 80, 20));
		add_CheckButton("doctor_details", new Rectangle(350, 320, 80, 20));
		add_CheckButton("services", new Rectangle(350, 360, 80, 20));
		add_CheckButton("medicine_details", new Rectangle(350, 400, 80, 20));
		add_CheckButton("room_details", new Rectangle(350, 440, 80, 20));
		add_CheckButton("hospital_details", new Rectangle(350, 480, 80, 20));
	}
	
	public void add_CheckButton(String tableName, Rectangle position) {
		checks.put(tableName, new JButton("..."));
		checks.get(tableName).setBounds(position);
		
		checks.get(tableName).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HFrame a = new checkFrame(tableName);
				a.run();
			}
		});
		
		panel.add(checks.get(tableName));
	}
	
	public void save() {
		((JTextComponent)objects.get("discount")).setText("21");
		((JTextComponent)objects.get("net_value")).setText("21");

		String sqlSentence;
		String sqlSentence2;
		sqlSentence = "insert into patient_bill_inpatient (patient_bill_id, ";
		sqlSentence2 = ") values ('" + nextID() +"',";
		for(int i=1;i<Columns.length;i++) {
			sqlSentence = sqlSentence + Columns[i];
			System.out.println(i);
			sqlSentence2 = sqlSentence2 + "'" + getText(i) + "'";
			if(i!=Columns.length-1) {
				sqlSentence = sqlSentence + ",";
				sqlSentence2 = sqlSentence2 + ",";
			}
			else
				sqlSentence = sqlSentence + sqlSentence2 + ")";
		}

		System.out.println(sqlSentence);
		sql.executeWithoutReturn(sqlSentence);
	}
	
	public void set_editable() {
		for(int i=0;i<objects.size();i++) {
			if(isInArray(i, textFields))
				((JTextComponent) objects.get(Names.get(i))).setEditable(true);
		}
	}
	
	public void set_uneditable() {
		for(int i=0;i<objects.size();i++) {
			if(isInArray(i, textFields))
				((JTextComponent) objects.get(Names.get(i))).setEditable(false);
		}
	}

	public String nextID() {
		String sqlSentence = "SELECT max(patient_bill_id) from patient_bill_inpatient";
		String result = sql.selectOne(sqlSentence);
		if(result==null) {
			return "PBID_1";
		}
		return "PBID_" + (Integer.parseInt(result.split("_")[1])+1);
	}
	
	public static boolean isInArray(int a, int[] specialObjects2) {
		for(int i=0;i<specialObjects2.length;i++) {
			if(a == specialObjects2[i]) {
				return true;
			}
		}
		return false;
	}

	public String getText(int index) {
		for(int t=0;t<textFields.length;t++) {
			for(int j=0;j<Names.size();j++) {
				if(Names.get(j) == Columns[index]) {
					if(textFields[t] == j) {
						System.out.println(Names.get(j) + "     " + ((JTextComponent) objects.get(Names.get(j))).getText());
						return ((JTextComponent) objects.get(Names.get(j))).getText();
					}
				}
			}
		}
		for(int t=0;t<comboBoxs.length;t++) {
			for(int j=0;j<Names.size();j++) {
				if(Names.get(j) == Columns[index]) {
					if(comboBoxs[t] == j)
						return (String) ((JComboBox<String>) objects.get(Names.get(j))).getSelectedItem();
				}
			}
		}

		return "";
	}
	
	public void addObject(String Type, String Name, Rectangle Position) {
		if(Type == "combobox") {
			JComboBox<String> a = new JComboBox();
			objects.put(Name, a);
		}
		else if(Type == "textfield") {
			JTextField a = new JTextField();
			objects.put(Name, a);
		}

		Names.add(Name);
		Positions.add(Position);
	}
}
