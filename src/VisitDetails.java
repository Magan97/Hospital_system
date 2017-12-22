import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class VisitDetails extends HFrame{
	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	JTable Table;
	JLabel titleLabel, DetailsLabel, visitIdJLabel, visitDateJLabel, middleJLabel, visitTimeJLabel, doctorIdJLabel, patientIdJLabel, admissionIdJLabel, descriptionJLabel, recordJLabel;
	JTextField visitIdJTextField, visitDateJTextField, visitTimeJTextField, doctorIdJTextField, patientIdJTextField, admissionJTextField, descripitionJTextField;
	JButton addButton, editButton, deleteButton, refreshButton, closeButton, saveButton, cancelButton;
	JButton EndButton, NextButton, LastButton, FirstButton;
	
	//add = 1; delete = 2; editButton = 3
	private int mark;
	
	private int position;
	
	private String tablenameString = "patient_details";
	private String idNameString = "patient_id";
	private int tableAmount = 8;
	
	SqlSon SqlTool = new SqlSon();
	
	
	public VisitDetails()
	{
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,30);
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        titleLabel = new JLabel("VISIT DETAILS");
        titleLabel.setBounds(300, 50, 400, 50);
        titleLabel.setFont(new Font("Courier", 1, 35));
        titleLabel.setSize(new Dimension(450,50));
        mainPanel.add(titleLabel);
        
        DetailsLabel = new JLabel("Visit Details--------------------------------------------------------"
        		+ "----------------");
        DetailsLabel.setBounds(80, 100, 500, 20);
        DetailsLabel.setFont(new Font("Courier",1,15));
        DetailsLabel.setSize(new Dimension(700,20));
        mainPanel.add(DetailsLabel);
        
        visitIdJLabel = new JLabel("Visit ID : ");
        visitIdJLabel.setBounds(100, 130, 150, 20); // column 10 , row 20
        mainPanel.add(visitIdJLabel);
        
        visitIdJTextField = new JTextField("");
        visitIdJTextField.setBounds(270, 130, 200, 20);
        mainPanel.add(visitIdJTextField);
        
        
        visitDateJLabel = new JLabel("Visit Date : ");
        visitDateJLabel.setBounds(100, 160, 150, 20);
        mainPanel.add(visitDateJLabel);
        
        visitDateJTextField = new JTextField("");
        visitDateJTextField.setBounds(270, 160, 200, 20);
        mainPanel.add(visitDateJTextField);
        
        visitTimeJLabel = new JLabel("Visit Time : ");
        visitTimeJLabel.setBounds(100, 190, 150, 20);
        mainPanel.add(visitTimeJLabel);
        
        visitTimeJTextField = new JTextField("");
        visitTimeJTextField.setBounds(270, 190, 200, 20);
        mainPanel.add(visitTimeJTextField);
        
        doctorIdJLabel = new JLabel("Doctor : ");
        doctorIdJLabel.setBounds(100, 220, 150, 20);
        mainPanel.add(doctorIdJLabel);
        
        doctorIdJTextField = new JTextField();
        doctorIdJTextField.setBounds(270, 220, 200, 20);
        mainPanel.add(doctorIdJTextField);
        
        patientIdJLabel = new JLabel("Patient ID : ");
        patientIdJLabel.setBounds(100, 250, 150, 20);
        mainPanel.add(patientIdJLabel);
        
        patientIdJTextField = new JTextField();
        patientIdJTextField.setBounds(270, 250, 200, 20);
        mainPanel.add(patientIdJTextField);
        
        admissionIdJLabel = new JLabel("Admission ID : ");
        admissionIdJLabel.setBounds(100, 280, 150, 20);
        mainPanel.add(admissionIdJLabel);
        
        admissionJTextField = new JTextField();
        admissionJTextField.setBounds(270, 280, 200, 20);
        mainPanel.add(admissionJTextField);
        
        descriptionJLabel = new JLabel("Descripition : ");
        descriptionJLabel.setBounds(100, 310, 150, 20);
        mainPanel.add(descriptionJLabel);
        
        descripitionJTextField = new JTextField();
        descripitionJTextField.setBounds(270, 310, 200, 20);
        mainPanel.add(descripitionJTextField);
        
                
        middleJLabel = new JLabel("----------------------------------------------------------------------------------------");
        middleJLabel.setFont(new Font("Courier",1,15));
        middleJLabel.setBounds(80, 350, 500, 20);
        mainPanel.add(middleJLabel); 
        
        JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(50, 390, 800, 100);
	    mainPanel.add(scrollPane);
		scrollPane.setViewportView(getTable());
        
        addButton = new JButton("Add");
        addButton.setBounds(150, 530, 90, 30);
        addButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(addButton);
        /*addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
            	patientIdLabel.setVisible(true);
            	patientIdTextField.setVisible(true);
            	visibleTrue();
            	
            	patientIdTextField.setText(SqlTool.selectAll(tablenameString));
            	patientIdTextField.setEnabled(false);
            	freshText();
            	editButtonMore();
            	mark = 1;
            	
            	recordJLabel.setText("                Record : ");
            }
        });*/
        
        editButton = new JButton("Edit");
        editButton.setBounds(260, 530, 90, 30);
        editButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(editButton);
        /*editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	patientIdLabel.setVisible(true);
            	patientIdTextField.setVisible(true);
            	visibleTrue();
            	
            	editButtonMore();
            	
            	bottomButtonShow();
        		
            	mark = 3;
            	
            	ArrayList<ArrayList<String>> allInformationArrayList = new ArrayList<ArrayList<String>>();
            	allInformationArrayList = SqlTool.selectAllInformation(tablenameString, tableAmount);
            	patientIdTextField.setText(allInformationArrayList.get(0).get(0));
            	visitDateJTextField.setText(allInformationArrayList.get(0).get(1));
            	visitTimeJTextField.setText(allInformationArrayList.get(0).get(2));
            	doctorIdJTextField.setText(allInformationArrayList.get(0).get(3));
            	patientIdJTextField.setText(allInformationArrayList.get(0).get(4));
            	admissionJTextField.setText(allInformationArrayList.get(0).get(5));
            	descripitionJTextField.setText(allInformationArrayList.get(0).get(6));
            	notesTextArea.setText(allInformationArrayList.get(0).get(7));
            	
            	patientIdTextField.setEnabled(false);
            	
            	
            	position = 1;
            	recordJLabel.setText("                Record : " + Integer.toString(position));
            }
        });*/
        
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(370, 530, 90, 30);
        deleteButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(deleteButton);
        /*deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				visibleFalse();
				
				patientIdTextField.setEnabled(true);
				patientIdTextField.setText("");
            	freshText();
            	
				editButtonMore();
				
				mark = 2;
				
				recordJLabel.setText("                Record : ");
			}
		});*/
        
        
        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(480, 530, 90, 30);
        refreshButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(refreshButton);
        /*refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//scrollPane.setViewportView(getTable());
            	patientIdTextField.setText("");
            	freshText();
            	recordJLabel.setText("                Record : ");
            }
        });*/
        
        closeButton =  new JButton("Close");
        closeButton.setBounds(590, 530, 90, 30);
        closeButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(closeButton);
        /*closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});*/
        
        
        saveButton = new JButton("Save");
        saveButton.setBounds(260, 530, 90, 30);
        saveButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(saveButton);
        /*saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(mark == 1)
            	{
            		String patientIdString = patientIdTextField.getText();
                	String firstNameString = visitDateJTextField.getText();
                	String lastNameString = visitTimeJTextField.getText();
                	String genderString = doctorIdJTextField.getText();
                	String telephoneString = patientIdJTextField.getText();
                	String addressString = admissionJTextField.getText();
                	String statusString = descripitionJTextField.getText();
                	String noteString = notesTextArea.getText();
                	
                	ArrayList<String> mapArrayList = new ArrayList<String>();
                	mapArrayList.add(tablenameString);
                	mapArrayList.add(patientIdString);
                	mapArrayList.add(firstNameString);
                	mapArrayList.add(lastNameString);
                	mapArrayList.add(genderString);
                	mapArrayList.add(telephoneString);
                	mapArrayList.add(addressString);
                	mapArrayList.add(statusString);
                	mapArrayList.add(noteString);
                	
                	SqlTool.insert(mapArrayList);
                	
            	}
            	else if(mark == 2)
            	{
            		
    				String idString = patientIdTextField.getText();
    				
    				
    				SqlTool.delete(idNameString, idString, tablenameString);
				}
            	else if(mark == 3)
            	{
            		
            		String patientIdString = patientIdTextField.getText();
            		String firstNameString = visitDateJTextField.getText();
            		String lastNameString = visitTimeJTextField.getText();
            		String genderString = doctorIdJTextField.getText();
            		String telephoneString = patientIdJTextField.getText();
            		String addressString = admissionJTextField.getText();
            		String statusString = descripitionJTextField.getText();
            		String notesString = notesTextArea.getText();
            		
            		ArrayList<String> mapArrayList = new ArrayList<String>();
            		mapArrayList.add("firstname");
            		mapArrayList.add("lastname");
            		mapArrayList.add("gender");
            		mapArrayList.add("address");
            		mapArrayList.add("telephone");
            		mapArrayList.add("status");
            		mapArrayList.add("notes");
            		
            		Dictionary<String, String> mapDictionary = new Hashtable<>();
            		mapDictionary.put("firstname", firstNameString);
            		mapDictionary.put("lastname", lastNameString);
            		mapDictionary.put("gender", genderString);
            		mapDictionary.put("address", addressString);
            		mapDictionary.put("telephone", telephoneString);
            		mapDictionary.put("status", statusString);
            		mapDictionary.put("notes", notesString);
            		
					SqlTool.update(idNameString, patientIdString, tablenameString, mapArrayList, mapDictionary);
            	}
            	mark = 0;
            	updateAfter();
            	visibleTrue();
            	patientIdTextField.setText("");
            	freshText();
            	updateAfter();
            	recordJLabel.setText("                Record : ");
            }
        });*/
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(480, 530, 90, 30);
        cancelButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(cancelButton);
        /*cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	patientIdTextField.setText("");
            	freshText();
            	updateAfter();
            	bottomButtonHide();
            	recordJLabel.setText("                Record : ");
            }
        });*/
        
        FirstButton = new JButton("First");
        FirstButton.setBounds(100, 580, 90, 30);
        FirstButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(FirstButton);
        /*FirstButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ArrayList<ArrayList<String>> allInformationArrayList = new ArrayList<ArrayList<String>>();
            	allInformationArrayList = SqlTool.selectAllInformation(tablenameString, tableAmount);
            	patientIdTextField.setText(allInformationArrayList.get(0).get(0));
            	visitDateJTextField.setText(allInformationArrayList.get(0).get(1));
            	visitTimeJTextField.setText(allInformationArrayList.get(0).get(2));
            	doctorIdJTextField.setText(allInformationArrayList.get(0).get(3));
            	patientIdJTextField.setText(allInformationArrayList.get(0).get(4));
            	admissionJTextField.setText(allInformationArrayList.get(0).get(5));
            	descripitionJTextField.setText(allInformationArrayList.get(0).get(6));
            	notesTextArea.setText(allInformationArrayList.get(0).get(7));
            	
            	patientIdTextField.setEnabled(false);
            	
            	
            	position = 1;
            	recordJLabel.setText("                Record : " + Integer.toString(position));
			}
		});*/
        
        LastButton = new JButton("Last");
        LastButton.setBounds(210, 580, 90, 30);
        LastButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(LastButton);
        /*LastButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				position -= 1;
				
				ArrayList<ArrayList<String>> allInformationArrayList = new ArrayList<ArrayList<String>>();
            	allInformationArrayList = SqlTool.selectCondition(tablenameString, idNameString, Integer.toString(position), tableAmount);
            	patientIdTextField.setText(allInformationArrayList.get(0).get(0));
            	visitDateJTextField.setText(allInformationArrayList.get(0).get(1));
            	visitTimeJTextField.setText(allInformationArrayList.get(0).get(2));
            	doctorIdJTextField.setText(allInformationArrayList.get(0).get(3));
            	patientIdJTextField.setText(allInformationArrayList.get(0).get(4));
            	admissionJTextField.setText(allInformationArrayList.get(0).get(5));
            	descripitionJTextField.setText(allInformationArrayList.get(0).get(6));
            	notesTextArea.setText(allInformationArrayList.get(0).get(7));
            	
            	patientIdTextField.setEnabled(false);
            	
            	recordJLabel.setText("                Record : " + Integer.toString(position));
			}
		});*/
        
        recordJLabel = new JLabel("                Record : ");
        recordJLabel.setBounds(320, 580, 180, 30);
        recordJLabel.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(recordJLabel);
        
        NextButton = new JButton("Next");
        NextButton.setBounds(520, 580, 90, 30);
        NextButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(NextButton);
        /*NextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				position += 1;
				
				ArrayList<ArrayList<String>> allInformationArrayList = new ArrayList<ArrayList<String>>();
            	allInformationArrayList = SqlTool.selectCondition(tablenameString, idNameString, Integer.toString(position), tableAmount);
            	patientIdTextField.setText(allInformationArrayList.get(0).get(0));
            	visitDateJTextField.setText(allInformationArrayList.get(0).get(1));
            	visitTimeJTextField.setText(allInformationArrayList.get(0).get(2));
            	doctorIdJTextField.setText(allInformationArrayList.get(0).get(3));
            	patientIdJTextField.setText(allInformationArrayList.get(0).get(4));
            	admissionJTextField.setText(allInformationArrayList.get(0).get(5));
            	descripitionJTextField.setText(allInformationArrayList.get(0).get(6));
            	notesTextArea.setText(allInformationArrayList.get(0).get(7));
            	
            	patientIdTextField.setEnabled(false);
            	            	
            	recordJLabel.setText("                Record : " + Integer.toString(position));
			}
		});*/
        
        EndButton = new JButton("End");
        EndButton.setBounds(630, 580, 90, 30);
        EndButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(EndButton);
        /*EndButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				position = Integer.valueOf(SqlTool.selectAll(tablenameString)).intValue() - 1;
				//System.out.println(position);
				
				ArrayList<ArrayList<String>> allInformationArrayList = new ArrayList<ArrayList<String>>();
            	allInformationArrayList = SqlTool.selectCondition(tablenameString, idNameString, Integer.toString(position), tableAmount);
            	patientIdTextField.setText(allInformationArrayList.get(0).get(0));
            	visitDateJTextField.setText(allInformationArrayList.get(0).get(1));
            	visitTimeJTextField.setText(allInformationArrayList.get(0).get(2));
            	doctorIdJTextField.setText(allInformationArrayList.get(0).get(3));
            	patientIdJTextField.setText(allInformationArrayList.get(0).get(4));
            	admissionJTextField.setText(allInformationArrayList.get(0).get(5));
            	descripitionJTextField.setText(allInformationArrayList.get(0).get(6));
            	notesTextArea.setText(allInformationArrayList.get(0).get(7));
            	
            	patientIdTextField.setEnabled(false);
            	
            	recordJLabel.setText("                Record : " + Integer.toString(position));
			}
		});*/
        
        bottomButtonHide();
		
        this.add(mainPanel);
	}
	
	private void editButtonMore()
	{
		
		addButton.setVisible(false);
		editButton.setVisible(false);
		deleteButton.setVisible(false);
		refreshButton.setVisible(false);
		closeButton.setVisible(false);
		
		saveButton.setVisible(true);
		cancelButton.setVisible(true);
		
		FirstButton.setEnabled(false);
		LastButton.setEnabled(false);
		NextButton.setEnabled(false);
		EndButton.setEnabled(false);
	}
	
	private void updateAfter()
	{
		addButton.setVisible(true);
		editButton.setVisible(true);
		deleteButton.setVisible(true);
		refreshButton.setVisible(true);
		closeButton.setVisible(true);
		
		saveButton.setVisible(false);
		cancelButton.setVisible(false);
		
		FirstButton.setEnabled(true);
		LastButton.setEnabled(true);
		NextButton.setEnabled(true);
		EndButton.setEnabled(true);
	}
	
	private void bottomButtonHide()
	{
		FirstButton.setEnabled(false);
		LastButton.setEnabled(false);
		NextButton.setEnabled(false);
		EndButton.setEnabled(false);
	}
	
	private void bottomButtonShow()
	{
		FirstButton.setEnabled(true);
		LastButton.setEnabled(true);
		NextButton.setEnabled(true);
		EndButton.setEnabled(true);
	}
	
	private void freshText()
	{
		visitDateJTextField.setText("");
		visitTimeJTextField.setText("");
		doctorIdJTextField.setText("");
		patientIdJTextField.setText("");
		admissionJTextField.setText("");
		descripitionJTextField.setText("");
	}
	
	private void visibleTrue() 
	{
    	visitDateJTextField.setVisible(true);
    	visitTimeJLabel.setVisible(true);
    	visitTimeJTextField.setVisible(true);
    	doctorIdJLabel.setVisible(true);
    	doctorIdJTextField.setVisible(true);
    	admissionIdJLabel.setVisible(true);
    	admissionJTextField.setVisible(true);
    	patientIdJLabel.setVisible(true);
    	patientIdJTextField.setVisible(true);
    	descriptionJLabel.setVisible(true);
    	descripitionJTextField.setVisible(true);
	}
	
	private void visibleFalse()
	{
    	visitDateJTextField.setVisible(false);
    	visitTimeJLabel.setVisible(false);
    	visitTimeJTextField.setVisible(false);
    	doctorIdJLabel.setVisible(false);
    	doctorIdJTextField.setVisible(false);
    	admissionIdJLabel.setVisible(false);
    	admissionJTextField.setVisible(false);
    	patientIdJLabel.setVisible(false);
    	patientIdJTextField.setVisible(false);
    	descriptionJLabel.setVisible(false);
    	descripitionJTextField.setVisible(false);
	}
	
	private JTable getTable(){
		
		Table = new JTable();
		Table.setPreferredScrollableViewportSize(new Dimension(800,200));
		String[] columns={"Visit_ID", "Visit_Date", "Visit_Time", "Doctor_ID", "Admission_ID", "Patient_ID", "Description", "Prescription_ID"}; 
		DefaultTableModel model=new DefaultTableModel(columns,0);
		model=new DefaultTableModel(columns,0);
		SqlSon s = new SqlSon();
		
	    /*if(mark == 1)
	    {
	    	String[] argStrings = new String[8];
			ArrayList<ArrayList<String>> AllInformation = s.selectAllInformation(tableNameString, tableAmount);
			//System.out.println(AllInformation.size());
			//System.out.println(AllInformation.get(0).size());
			for(int i = 0;i < AllInformation.size();i++)
			{
				
				for(int j = 0;j < AllInformation.get(i).size();j++)
				{
					argStrings[j] = AllInformation.get(i).get(j);
					
				}
				
				model.addRow(argStrings);
				
			}
	    }
	    else if(mark == 2)
	    {
	    	String[] argStrings = new String[8];
			ArrayList<ArrayList<String>> AllInformation = s.selectCondition(tableNameString, columnItemString, searchTextString, tableAmount);
			//System.out.println(columnItemString);
			//System.out.println(searchTextString);
			
			for(int i = 0;i < AllInformation.size();i++)
			{
				for(int j = 0;j < AllInformation.get(i).size();j++)
				{
					argStrings[j] = AllInformation.get(i).get(j);
				}
				
				model.addRow(argStrings);
				
			}
	    }*/
		
		Table.setModel(model);
		
		return Table;
	}
	
}
