import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

public class checkFrame extends HFrame{
	JTable dataTable;
	
	checkFrame(String tableName){
		super();
		this.setSize(500, 400);
		
		init_dataPanel(tableName);
		
		this.add(panel);
	}
	
	public void init_dataPanel(String tableName){
		
		ArrayList<String> columns = sql.getColumns("select COLUMN_NAME from information_schema.COLUMNS where table_name = '" + tableName +"'");
		
		ArrayList<ArrayList<String>> result = sql.execute("Select * from " + tableName, columns.size());
		
		Object[][] a = new Object[result.size()][result.get(0).size()];
		
        for(int i=0;i<result.size();i++) {
        	for(int t=0;t<result.get(0).size();t++) {
        		a[i][t] = result.get(i).get(t);
        		System.out.println("" + a.length + "    " + a[i][t]);
        	}
        }
		
        String[] as = new String[columns.size()];
        for(int i=0;i<columns.size();i++) {
        	as[i] = columns.get(i);
        	System.out.println(as[i]);
        }

        dataTable = new JTable(a, as);
        panel.setLayout(new FlowLayout());
        panel.add(dataTable);
        this.revalidate();
	}
}
