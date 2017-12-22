import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class HFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MainInterface mainInterface;
	SqlSon sql = new SqlSon();
	
	JButton Close = new JButton("Close");
	
    JPanel panel = new JPanel(){	
    	public void paintComponent(Graphics g) {
            ImageIcon icon = new ImageIcon("1.png");
            g.drawImage(icon.getImage(), 0, 0,
            this.getSize().width,
            this.getSize().height,
            this);
        }
    };

	public static JLabel newLabel(String text, Rectangle r) {
		JLabel a = new JLabel(text);
		a.setBounds(r);
		return a;
	}

	//constructor
	HFrame(){
		super();
		panel.setLayout(null);
		setSize(900,700);
		setTitle("Hospital management system");
	}
	HFrame(MainInterface mainInterface){
		this();
		this.mainInterface = mainInterface;
	}
	
	private void Add(String tableName, String columnName) {
		ArrayList<ArrayList<String>> biggest = sql.execute("SELECT max(" + columnName + ") from " + tableName, 1);
	}

	public void run() {
		this.setVisible(true);
	}
}
