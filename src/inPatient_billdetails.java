import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class inPatient_billdetails extends HFrame{

	Hashtable<String, Object> objects = new Hashtable<String, Object>();
	Hashtable<String, JButton> buttons = new Hashtable<String, JButton>();
	ArrayList<String> Columns;
	ArrayList<String> ButtonNames = new ArrayList<String>();
	int[] textFields = {0, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	int[] comboBoxs = {1, 2};
	Rectangle[] positions = {};
	String status="n";

	inPatient_billdetails(){
		super();

		init_buttons();
		init_interface();
		
		this.add(panel);
		view("PBID_1");
	}

	private void init_interface() {
		Columns = sql.getColumns("select COLUMN_NAME from information_schema.COLUMNS where table_name = 'patient_bill_inpatient'");
		for(int i=0;i<Columns.size();i++) {
			Component item;

			if(isInArray(i, textFields)) {
				item = new JTextField();
			}
			else {// if(isInArray(i, comboBoxs)) {
				item = new JComboBox<String>();
			}
			item.setBounds(new Rectangle(300, 100+i*30, 300, 20));

			JLabel label = new JLabel(Columns.get(i));
			label.setBounds(new Rectangle(50, 100+i*30, 300, 20));

			panel.add(item);
			panel.add(label);

			objects.put(Columns.get(i), item);
		}
		
		init_ComboBox();

		set_uneditable();
		
		//ArrayList<JLabel> Labels = new ArrayList<JLabel>();
		
		/*
		Enumeration<String> e=objects.keys();
		while(e.hasMoreElements()){
			String key=e.nextElement();
			
		}
		*/
	}

	private void init_ComboBox() {
		JComboBox a = (JComboBox) objects.get("patient_id");
		JComboBox b = (JComboBox) objects.get("admission_id");

		ArrayList<String> coList = new ArrayList<String>();
		coList.add("patient_id");
		ArrayList<ArrayList<String>> itemList = sql.select("in_patient_details", coList);

		for(int i=0;i<itemList.size();i++) {
			a.addItem(itemList.get(i).get(0));
		}

		a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if(status=="a") {
	
					ArrayList<String> coList = new ArrayList<String>();
					coList.add("admission_id");
	
					String asd = "Select admission_id from admission_details where patient_id ='" + ((JComboBox<String>) arg0.getSource()).getSelectedItem() +"'";
					ArrayList<ArrayList<String>> itemList_admission = sql.execute("Select admission_id from admission_details where patient_id ='" + ((JComboBox<String>) arg0.getSource()).getSelectedItem() +"'", 1);
					b.removeAllItems();
					for(int i=0;i<itemList_admission.size();i++) {
						b.addItem(itemList_admission.get(i).get(0));
					}
				}
			}
		});
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(status=="a") {
					
					String admission_id = (String) ((JComboBox<String>) arg0.getSource()).getSelectedItem();
					//no inpatient_id
					int total_charge = 0;
					
					((JTextComponent) objects.get("discharge_date")).setText("0000-00-00");
					((JTextComponent) objects.get("hospital_charges")).setText("0");
					((JTextComponent) objects.get("discount")).setText("0");

					String sqlService = "Select service_charge from inpatient_services where admission_id='" + admission_id + "'";
					JTextField sa = (JTextField) objects.get("service_charges");
					sa.setText(sql.selectOne(sqlService)==null?"0":sql.selectOne(sqlService));
					total_charge += getValue(sql.selectOne(sqlService));
					
					String unitPrice = "Select unit_price from inpatients_order_details where order_id=(Select order_id from in_petients_order where admission_id='" + admission_id +"')";
					((JTextComponent) objects.get("medicine_charges")).setText(sql.selectOne(unitPrice)==null?"0":sql.selectOne(unitPrice));
					total_charge += getValue(sql.selectOne(unitPrice));
					
					String doctorCharges = "Select doctor_vcharge from doctor_details where doctor_id=(Select refer_doctor from admission_details where admission_id='" + admission_id +"')";
					((JTextComponent) objects.get("doctor_charges")).setText(sql.selectOne(doctorCharges)==null?"0":sql.selectOne(doctorCharges));
					total_charge += getValue(sql.selectOne(doctorCharges));
					
					String roomid = "select room_ward_id from admission_details where admission_id='" + admission_id +"'";
					String roomCharges;
					if(sql.selectOne(roomid)!=null) {
						if(sql.selectOne(roomid).split("_")[0] == "RMID") {
							roomCharges = "select room_rate from room_type where room_type=(select room_rtype from room_details where room_id='" + sql.selectOne(roomid) +"')";
							((JTextComponent) objects.get("room_charges")).setText(sql.selectOne(roomCharges)==null?"0":sql.selectOne(roomCharges));
						}
						else {
							roomCharges = "select ward_rate from ward_details where ward_id='" + sql.selectOne(roomid) + "'";
							((JTextComponent) objects.get("room_charges")).setText(sql.selectOne(roomCharges)==null?"0":sql.selectOne(roomCharges));
						}
						total_charge += getValue(sql.selectOne(roomCharges));
					}
					
					
					((JTextComponent) objects.get("net_value")).setText("" + total_charge);
					
				}
			}
		});
	}

	private void init_buttons() {
		buttons.put("Add", new JButton("Add")); ButtonNames.add("Add");
		buttons.put("Edit", new JButton("Edit")); ButtonNames.add("Edit");

		buttons.put("Next", new JButton("Next")); ButtonNames.add("Next");
		buttons.put("Last", new JButton("Last")); ButtonNames.add("Last");
		buttons.put("First", new JButton("First")); ButtonNames.add("First");
		buttons.put("End", new JButton("End")); ButtonNames.add("End");

		buttons.put("Save", new JButton("Save")); ButtonNames.add("Save");

		buttons.get("Add").addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				if(status=="a"){
					set_uneditable();
					status = "n";
					view("PBID_1");
				}
				else if(status == "n") {
					set_empty();
					((JTextComponent) objects.get("patient_bill_id")).setText(nextID());
	            	set_editable();
	            	status = "a";
            	}

			}
		});
		buttons.get("Edit").addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				if(status=="e"){
					set_uneditable();
					status = "n";
					view("PBID_1");
				}
				else if(status == "n") {
	            	set_editable();
	            	status = "e";
            	}
			}
		});
		
		buttons.get("Save").addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				if(status!="n") {
					save();
	            	set_uneditable();
            	}
            	status = "n";
			}
		});

		buttons.get("Next").addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
            	view("PBID_" + (Integer.parseInt(((JTextComponent) objects.get("patient_bill_id")).getText().split("_")[1]) + 1));
            	}
		});
		
		buttons.get("Last").addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				view("PBID_" + (Integer.parseInt(((JTextComponent) objects.get("patient_bill_id")).getText().split("_")[1]) - 1));
            	}
		});
		buttons.get("First").addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				view("PBID_1");
            	}
		});
		buttons.get("End").addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				view(sql.selectOne("select patient_bill_id from patient_bill_inpatient where patient_bill_id=(select MAX(patient_bill_id) from patient_bill_inpatient)"));
            	}
		});

		for(int i=0;i<ButtonNames.size();i++) {
			buttons.get(ButtonNames.get(i)).setBounds(new Rectangle(100 + i*100, 600, 80, 30));
			panel.add(buttons.get(ButtonNames.get(i)));
		}
	}
	
	public void save() {
		String sqlSentence="select * from users";
		if(status=="a") {
			String sqlSentence2;
			sqlSentence = "insert into patient_bill_inpatient (";
			sqlSentence2 = ") values (";
			for(int i=0;i<objects.size();i++) {
				sqlSentence = " " + sqlSentence + Columns.get(i);
				sqlSentence2 = " " + sqlSentence2 + "'" + getText(i) + "'";
				if(i!=objects.size()-1) {
					sqlSentence = sqlSentence + ",";
					sqlSentence2 = sqlSentence2 + ",";
				}
				else
					sqlSentence = sqlSentence + sqlSentence2 + ")";
			}
		}
		else if(status=="e") {
			sqlSentence = "update patient_bill_inpatient set ";
			for(int i=0;i<objects.size();i++) {
				sqlSentence = sqlSentence + Columns.get(i) + "='" + getText(i) +"' ";
				if(i!=objects.size()-1) {
					sqlSentence = sqlSentence + ",";
				}
				else
					sqlSentence = sqlSentence + "where admission_id='" + ((JComboBox<String>) objects.get("admission_id")).getSelectedItem() + "'";
			}
		}
		System.out.println(sqlSentence);
		sql.executeWithoutReturn(sqlSentence);
	}
	
	
	public String getText(int index) {
		for(int i=0;i<Columns.size();i++) {
			for(int t=0;t<textFields.length;t++) {
				if(index == textFields[t]) {
					return ((JTextComponent) objects.get(Columns.get(index))).getText();
				}
			}
			for(int t=0;t<comboBoxs.length;t++) {
				if(index == comboBoxs[t]) {
					return (String) ((JComboBox) objects.get(Columns.get(index))).getSelectedItem();
				}
			}
		}
		return "";
	}
	
	public void set_uneditable() {
		for(int i=0;i<Columns.size();i++) {
			if(isInArray(i, textFields)) {
				((JTextComponent) objects.get(Columns.get(i))).setEditable(false);
			}
			else if(isInArray(i, comboBoxs)) {
				((JComboBox) objects.get(Columns.get(i))).setEditable(false);
			}
		}
	}

	public void set_editable() {
		for(int i=0;i<Columns.size();i++) {
			if(i==0) {}
			else if(isInArray(i, textFields)) {
				((JTextComponent) objects.get(Columns.get(i))).setEditable(true);
			}
			else if(isInArray(i, comboBoxs)) {
				((JComboBox) objects.get(Columns.get(i))).setEditable(true);
			}
		}
	}
	
	public void set_empty() {
		for(int i=0;i<Columns.size();i++) {
			if(isInArray(i, textFields)) {
				((JTextComponent) objects.get(Columns.get(i))).setText("");
			}
		}
	}
	
	public int getValue(String xx) {
		if(xx==null || xx=="null") {
			return 0;
		}
		else return Integer.parseInt(xx);
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
	
	public void view(String id) {
		String sqlSentence = "SELECT * from patient_bill_inpatient where patient_bill_id='" + id +"'";
		ArrayList<ArrayList<String>> result = sql.execute(sqlSentence, 12);
		if(result.size()!=0) {
			for(int i=0;i<objects.size();i++) {
				if(isInArray(i, textFields))
					((JTextComponent) objects.get(Columns.get(i))).setText(result.get(0).get(i));
				else if(isInArray(i, comboBoxs))
					((JComboBox<String>) objects.get(Columns.get(i))).addItem(result.get(0).get(i));
			}
		}
	}
}