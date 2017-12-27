package com.swing.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

public class timetest {
	public boolean before(JTextField text)
	{
		String time = text.getText();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String now = df.format(new Date());
		try {
			 Date dt1 = df.parse(time);
			 Date dt2 = df.parse(now);
			 if (dt1.getTime() <= dt2.getTime()) {
				 System.out.println("dt1 在dt2前   ok");
				 return true;
			 } else{
				 System.out.println("dt1在dt2后");
				 text.setText("2017-12-22");
				 return false;
			 } 
		} catch (Exception exception) {
			 exception.printStackTrace();
		}
		return false;
	}
	public boolean after(JTextField text)
	{
		String time = text.getText();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String now = df.format(new Date());
		try {
			 Date dt1 = df.parse(time);
			 Date dt2 = df.parse(now);
			 if (dt1.getTime() >= dt2.getTime()) {
				 System.out.println("dt1 在dt2前   ok");
				 return true;
			 } else{
				 System.out.println("dt1在dt2后");
				 text.setText("2017-12-22");
				 return false;
			 } 
		} catch (Exception exception) {
			 exception.printStackTrace();
		}
		return false;
	}
	public boolean aftertoday(JTextField text)
	{
		String time = text.getText();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String now = df.format(new Date());
		try {
			 Date dt1 = df.parse(time);
			 Date dt2 = df.parse(now);
			 if (dt1.getTime() >= dt2.getTime()) {
				 System.out.println("dt1 在dt2前   ok");
				 return true;
			 } else{
				 System.out.println("dt1在dt2后");
				 text.setText("2017-12-22");
				 return false;
			 } 
		} catch (Exception exception) {
			 exception.printStackTrace();
		}
		return false;
	}
	public boolean discharge(JTextField text, String aid)
	{
		String time = text.getText();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String pid = "";
		String stime = "";
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
				ResultSet rs = stmt.executeQuery("select * from admission_details where admission_id like '"+aid+"'");
				while(rs.next()){
					pid = rs.getString("patient_id");
				}
				ResultSet rs1 = stmt.executeQuery("select * from inpatient_services where inpatient_id like '"+pid+"' and status like 'Y'");
				while(rs1.next()){
					stime = rs1.getString("service_date");
				}
				con.commit();
			}catch(Exception e){
				con.rollback();
				e.printStackTrace();
				System.out.println("failed");
			}			
		}catch(ClassNotFoundException | SQLException ex){
			System.out.println("Can’t load the Driver");
		}
		String now = df.format(new Date());
		try {
			 Date dt1 = df.parse(time);
			 Date dt2 = df.parse(stime);
			 if (dt1.getTime() >= dt2.getTime()) {
				 System.out.println("dt1 在dt2前   ok");
				 return true;
			 } else{
				 System.out.println("dt1在dt2后");
				 text.setText("2017-12-22");
				 return false;
			 } 
		} catch (Exception exception) {
			 exception.printStackTrace();
		}
		return false;
	}
}
