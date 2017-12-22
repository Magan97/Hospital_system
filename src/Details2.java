import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Details2 extends HFrame{
	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	JTable Table;
	JLabel titleLabel, DetailsLabel, supplierIdJLabel, companyNameJLabel, middleJLabel, contactNameJLabel, productIdJLabel, productNameJLabel, unitsInStockJLabel, unitsPurchasedJLabel;
	JTextField  companyNameJTextField, productNameJTextField, unitsInStockJTextField, unitsPurchasedJTextField, discountJTextField, amountJTextField;
	JButton addListButton, saveButton, closeButton;
	JComboBox<String> supplierIdBox, productIdBox, billDateJComboBox;
	JLabel ratePerUnitJLabel, discountJLabel, amountJLabel, netAmountJLabel;
	JLabel billNoJLabel, billDateJLabel, grandTotaLabel, discountGivenBottomJLabel, netAmountPayableJLabel;
	JButton supplierIdButton, productIdButton;
	JTextField ratePerUniTextField, netAmounTextField, contactJTextField, billNoJTextField, grandTotalJTextField, discountGivenBottomJTextField, netAmountPayableJTextField;
	
	//add = 1; delete = 2; editButton = 3
	private int mark;
	
	private int position;
	
	private String tablenameString = "patient_details";
	private String idNameString = "patient_id";
	private int tableAmount = 8;
	
	SqlSon SqlTool = new SqlSon();
	
	
	public Details2()
	{
		setSize(900,700);
        setTitle("Hospital management system");
        this.setLocation(150,30);
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        titleLabel = new JLabel("PURCHASE ORDER");
        titleLabel.setBounds(270, 50, 600, 30);
        titleLabel.setFont(new Font("Courier", 1, 35));
        mainPanel.add(titleLabel);
        
        billNoJLabel = new JLabel("Bill No : ");
        billNoJLabel.setBounds(50, 90, 150, 20);
        mainPanel.add(billNoJLabel);
        
        billNoJTextField = new JTextField();
        billNoJTextField.setBounds(220, 90, 200, 20);
        mainPanel.add(billNoJTextField);
        
        billDateJLabel = new JLabel("Bill Date : ");
        billDateJLabel.setBounds(510, 90, 150, 20);
        mainPanel.add(billDateJLabel);
        
        
        billDateJComboBox = new JComboBox<String>();
        billDateJComboBox.setBounds(670, 90, 200, 20);
        mainPanel.add(billDateJComboBox);
        
        supplierIdJLabel = new JLabel("Supplier ID : ");
        supplierIdJLabel.setBounds(100, 130, 150, 20); // column 10 , row 20
        mainPanel.add(supplierIdJLabel);
        
        supplierIdBox = new JComboBox<String>();
        supplierIdBox.setBounds(270, 130, 200, 20);
        mainPanel.add(supplierIdBox);
        
        supplierIdButton = new JButton("...");
        supplierIdButton.setBounds(480, 130, 50, 20);
        mainPanel.add(supplierIdButton);
        
        companyNameJLabel = new JLabel("Company Name : ");
        companyNameJLabel.setBounds(100, 160, 150, 20);
        mainPanel.add(companyNameJLabel);
        
        companyNameJTextField = new JTextField("");
        companyNameJTextField.setBounds(270, 160, 200, 20);
        mainPanel.add(companyNameJTextField);
        
        contactNameJLabel = new JLabel("Contact Name : ");
        contactNameJLabel.setBounds(100, 190, 150, 20);
        mainPanel.add(contactNameJLabel);
        
        contactJTextField = new JTextField();
        contactJTextField.setBounds(270, 190, 200, 20);
        mainPanel.add(contactJTextField);
        
        
        productIdJLabel = new JLabel("Product ID : ");
        productIdJLabel.setBounds(100, 220, 150, 20);
        mainPanel.add(productIdJLabel);
        
        productIdBox = new JComboBox<String>();
        productIdBox.setBounds(270, 220, 200, 20);
        mainPanel.add(productIdBox);
        
        productIdButton = new JButton("...");
        productIdButton.setBounds(480, 220, 50, 20);
        mainPanel.add(productIdButton);
        
        productNameJLabel = new JLabel("Product Name : ");
        productNameJLabel.setBounds(100, 250, 150, 20);
        mainPanel.add(productNameJLabel);
        
        productNameJTextField = new JTextField();
        productNameJTextField.setBounds(270, 250, 200, 20);
        mainPanel.add(productNameJTextField);
        
        unitsInStockJLabel = new JLabel("Units In Stock : ");
        unitsInStockJLabel.setBounds(100, 280, 150, 20);
        mainPanel.add(unitsInStockJLabel);
        
        unitsInStockJTextField = new JTextField();
        unitsInStockJTextField.setBounds(270, 280, 200, 20);
        mainPanel.add(unitsInStockJTextField);
        
        unitsPurchasedJLabel = new JLabel("Units Purchased : ");
        unitsPurchasedJLabel.setBounds(100, 310, 150, 20);
        mainPanel.add(unitsPurchasedJLabel);
        
        unitsPurchasedJTextField = new JTextField();
        unitsPurchasedJTextField.setBounds(270, 310, 200, 20);
        mainPanel.add(unitsPurchasedJTextField);
        
        ratePerUnitJLabel = new JLabel("Rate Per Unit : ");
        ratePerUnitJLabel.setBounds(550, 130, 100, 20);
        mainPanel.add(ratePerUnitJLabel);
        
        ratePerUniTextField = new JTextField();
        ratePerUniTextField.setBounds(670, 130, 200, 20);
        mainPanel.add(ratePerUniTextField);
        
        discountJLabel = new JLabel("Discount : ");
        discountJLabel.setBounds(550, 160, 100, 20);
        mainPanel.add(discountJLabel);
        
        discountJTextField = new JTextField();
        discountJTextField.setBounds(670, 160, 200, 20);
        mainPanel.add(discountJTextField);
        
        amountJLabel = new JLabel("Amount : ");
        amountJLabel.setBounds(550, 190, 100, 20);
        mainPanel.add(amountJLabel);
        
        amountJTextField = new JTextField();
        amountJTextField.setBounds(670, 190, 200, 20);
        mainPanel.add(amountJTextField);
        
        netAmountJLabel = new JLabel("Net Amount : ");
        netAmountJLabel.setBounds(550, 220, 100, 20);
        mainPanel.add(netAmountJLabel);
        
        netAmounTextField = new JTextField();
        netAmounTextField.setBounds(670, 220, 200, 20);
        mainPanel.add(netAmounTextField);
        
        
        
        addListButton = new JButton("Add List");
        addListButton.setBounds(770, 340, 100, 20);
        mainPanel.add(addListButton);
        
                
        middleJLabel = new JLabel("----------------------------------------------------------------------------------------");
        middleJLabel.setFont(new Font("Courier",1,15));
        middleJLabel.setBounds(80, 350, 500, 20);
        mainPanel.add(middleJLabel); 
        
        JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(50, 390, 800, 100);
	    mainPanel.add(scrollPane);
		scrollPane.setViewportView(getTable());
		
		grandTotaLabel = new JLabel("Grand Total : ");
		grandTotaLabel.setBounds(100, 520, 100, 20);
		mainPanel.add(grandTotaLabel);
		
		grandTotalJTextField = new JTextField();
		grandTotalJTextField.setBounds(220, 520, 100, 20);
		mainPanel.add(grandTotalJTextField);
		
		discountGivenBottomJLabel = new JLabel("Discount Given : ");
		discountGivenBottomJLabel.setBounds(340, 520, 100, 20);
		mainPanel.add(discountGivenBottomJLabel);
		
		discountGivenBottomJTextField = new JTextField();
		discountGivenBottomJTextField.setBounds(460, 520, 100, 20);
		mainPanel.add(discountGivenBottomJTextField);
		
		netAmountPayableJLabel = new JLabel("Net Amount Payable : ");
		netAmountPayableJLabel.setBounds(580, 520, 150, 20);
		mainPanel.add(netAmountPayableJLabel);
		
		netAmountPayableJTextField = new JTextField();
		netAmountPayableJTextField.setBounds(750, 520, 100, 20);
		mainPanel.add(netAmountPayableJTextField);
		
		
		
		
        
        closeButton =  new JButton("Close");
        closeButton.setBounds(520, 600, 90, 30);
        closeButton.setFont(new Font("Courier", Font.ITALIC, 15));
        mainPanel.add(closeButton);
        /*closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});*/
        
        
        saveButton = new JButton("Save");
        saveButton.setBounds(280, 600, 90, 30);
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
        
		
        this.add(mainPanel);
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
