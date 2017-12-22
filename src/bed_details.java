import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class bed_details extends HFrame implements TreeSelectionListener{
	JPanel treePanel = new JPanel();
	JTree tree;
	JPanel dataPanel = new JPanel();
	JTable dataTable = new JTable();

	//constructor
	bed_details(MainInterface mainInterface) {
		super(mainInterface);

		init_treePanel();
	}
	bed_details() {
		setSize(700, 500);
		setLayout(new BorderLayout());

		init_dataPanel();
		init_treePanel();
		
		JLabel title = new JLabel("Bed details", JLabel.CENTER);
		this.add("North", title);
	}

	private void init_treePanel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		tree = new JTree(root);

		DefaultMutableTreeNode wardNode = new DefaultMutableTreeNode("Ward Management");
		DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode("Room Management");

		ArrayList<String> wardNameAL = new ArrayList<String>(); wardNameAL.add("ward_id");
		ArrayList<String> roomNameAL = new ArrayList<String>(); roomNameAL.add("room_id");
		ArrayList<ArrayList<String>> wardNameResult = sql.select("ward_details", wardNameAL);
		ArrayList<ArrayList<String>> roomNameResult = sql.select("room_details", roomNameAL);
		for(int i=0;i<wardNameResult.size();i++) {
			wardNode.add(new DefaultMutableTreeNode(wardNameResult.get(i).get(0)));
		}
		for(int i=0;i<roomNameResult.size();i++) {
			roomNode.add(new DefaultMutableTreeNode(roomNameResult.get(i).get(0)));
		}

		root.add(wardNode);
		root.add(roomNode);
		
		tree.addTreeSelectionListener(this);
		treePanel.add(tree);
		this.add("West", treePanel);
	}
	
	private void init_dataPanel() {
		dataPanel.setPreferredSize(new Dimension(200, 0));
		dataPanel.add(dataTable);
		this.add("Center", dataPanel);
	}
	
	public void valueChanged(TreeSelectionEvent e)
    {
        if(e.getSource()==tree)
        {
            DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
            if(node.isLeaf())
            {
                String str=node.toString();
                String tableName;
                
                if(str.split("_")[0] == "WARD") {
                	tableName = "ward_details";
                }
                else {
                	tableName = "room_details";
                }
                ArrayList<ArrayList<String>> resultSet = sql.selectCondition("bed_details", "room_ward_id", str, 5);
                Object[][] a = new Object[resultSet.size()][5];

                
                for(int i=0;i<resultSet.size();i++) {
                	for(int t=0;t<5;t++) {
                		System.out.println(resultSet.size());
                		a[i][t] = resultSet.get(i).get(t);
                	}
                }
                
                String[] ColumnName = {"bed_id", "room_ward_id", "available", "admission_id", "bed_desc"};
                dataTable = new JTable(a, ColumnName);
            }
            dataPanel.removeAll();
            dataPanel.add(dataTable);
            this.revalidate();
        }
    }
}