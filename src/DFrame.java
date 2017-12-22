import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;

public class DFrame extends HFrame{
	Hashtable<String, Object> objects = new Hashtable<String, Object>();
	Hashtable<String, JButton> buttons = new Hashtable<String, JButton>();
	ArrayList<String> Columns;
	int[] textFields;
	int[] comboBoxs;
}
