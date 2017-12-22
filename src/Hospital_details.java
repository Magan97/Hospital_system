import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.*;

public class Hospital_details extends HFrame{
	/*
	String[] text = {"Hospital Name:    TianJin Hospital",
					 "Description:    Good hospital with lots of doctors and nurses",
					 "TEL:    151xxxxxxxx",
					 "E-mail:    tjh@supinfo.com"};*/
	
	Hospital_details(){
		super();

		this.setSize(900, 700);
		ImageIcon image = new ImageIcon("./2.png");
		JLabel l = new JLabel(image);
		l.setBounds(50, 0, 800, 200);
		panel.add(l);
		
		
		JTextArea t = new JTextArea("");
		String s = "Research, prevention as one of the comprehensive level of first-class hospital, is one of the tianjin medical center.Hospital was founded in 1942, formerly known as heaven and hospital, and after ZhongFang hospital, post and telecommunications hospital, light hospital merger, in 1956 changed its name to tianjin first central hospital"+
"Hospital has 1500 beds, 44 clinical professional departments, five national key clinical subject construction project units (intensive medicine, key laboratory, combined Chinese and western medicine, organ transplantation center, clinical laboratory), 3 municipal key specialty (organ transplant center, intensive medicine and otolaryngology head and neck surgery), 6 city-level research institute (organ transplant research institute of tianjin, tianjin institute of emergency medicine, ent research institute, tianjin medical image research institute of tianjin, tianjin institute of thrombosis and hemostasis, tianjin hospital system engineering research institute), with the ministry of health, critical care and emergency medicine key laboratory of clinical medicine postdoctoral workstation.In hospital outpatient more than 2 million people, more than 50000 discharged patients."+
"Hospital, strong technical force, the existing health technical personnel more than 2000 people, including 383 senior titles, master 624 people, the personnel department has 1 millions of talent, the ministry of health of outstanding contribution 1 young and middle-aged experts, enjoy special government allowances 33 people, PhD, master unripe adviser, 81 people";
		t.setText(s);
		t.setLineWrap(true);
		t.setWrapStyleWord(true);
		t.setFont(new Font("ºÚÌå",Font.BOLD,20));		
		
		JScrollPane scroll = new JScrollPane(t); 
		scroll.setBounds(100, 250, 700, 300);
		panel.add(scroll);
		
		JLabel phone = new JLabel("TEL: +86 139123456");
		phone.setBounds(350, 550, 200, 30);
		panel.add(phone);
		JLabel mail = new JLabel("MAIL: hospital@supinfo.com");
		mail.setBounds(350, 580, 200, 30);
		panel.add(mail);
		
		this.add(panel);
	}
}