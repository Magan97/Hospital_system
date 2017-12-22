import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ConstructorProperties;

import javax.swing.*;

public class LoginInterface extends HFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3902707172475963985L;

	JPanel panel = new JPanel();
	JTextField usernameField = new JTextField(15);
	JTextField passwordField = new JTextField(15);
	JButton LoginButton = new JButton("Login");
	JLabel alertMessage = new JLabel("Bad username or password");
	JCheckBox rememberMe = new JCheckBox("Remember Me");

	public String username;
	public boolean isLogin = false;

	//constructor
	LoginInterface(MainInterface mainInterface){
		super(mainInterface);

		setSize(300, 500);
		setTitle("Login");

		init_panel();
	}
	/*  
	LoginInterface(){
		setSize(300, 500);
		setTitle("Login");

		init_panel();
	}
	*/
	public void run(){
		alertMessage.setForeground(Color.red);

		super.run();
	}
	
	private void close(){
		username = usernameField.getText();
		this.setVisible(false);
		if(!rememberMe.isSelected()){
			usernameField.setText("");
			passwordField.setText("");
		}
	}
	
	private void loginError(){
		panel.add(alertMessage);
		panel.revalidate();
	}
	
	private void init_panel(){
		panel.setLayout(new FlowLayout());

		panel.add(new JLabel("Login to Hospital Management System"));
		panel.add(new JLabel("Username: ")); panel.add(usernameField);
		panel.add(new JLabel("Password: ")); panel.add(passwordField);
		panel.add(rememberMe);

		LoginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Login()){
					panel.remove(alertMessage);
					panel.revalidate();
					close();
					mainInterface.run();
				}
				else{
					loginError();
				}
			}
		});
		panel.add(LoginButton);
		this.add(panel);
	}

	private boolean Login(){
		String username = usernameField.getText();
		String password = passwordField.getText();
		//database commands
		String sqlSentence = "SELECT CASE WHEN user_name='" + username + "' AND user_password='" + password + "' THEN true ELSE false END FROM users";
		System.out.println(sqlSentence);
		//isLogin = sql.isTrue(sqlSentence);
		isLogin = true;
		return isLogin;
	}

	public void Logout(){
		isLogin = false;
	}
}
