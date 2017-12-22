import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sql {
	public Connection conn;
	public java.sql.Statement stmt;

	public void Open() {
		String url = "jdbc:mysql://localhost/hospital_system?characterEncoding=UTF-8&characterSetResults=UTF-8&zeroDateTimeBehavior=convertToNull"; // JDBC��URL
		// ����DriverManager�����getConnection()���������һ��Connection����

		try {
			conn = DriverManager.getConnection(url, "root", "");
			conn.setAutoCommit(false);
			// ����һ��Statement����
			stmt = conn.createStatement(); // ����Statement����

		} catch (SQLException a) {
			a.printStackTrace();
		}
	}

	public void CloseUpdate(String sql) {

		try {
			stmt.executeUpdate(sql);
			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<ArrayList<String>> Closequery(String sql, int amount) {
		try {
			ResultSet rsResultSet = stmt.executeQuery(sql);

			ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

			while (rsResultSet.next()) {
				ArrayList<String> resultSon = new ArrayList<String>();
				for (int i = 0; i < amount; i++) {
					resultSon.add(rsResultSet.getString(i + 1));
				}
				result.add(resultSon);
			}

			stmt.close();
			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String selectAll(String tablenameString) {

		try {
			String count = null;
			String sql = "select count(*) from " + tablenameString;

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = Integer.toString(Integer.valueOf(rs.getString(1)).intValue() + 1);

			stmt.close();
			conn.close();
			return count;
		} catch (SQLException a) {
			a.printStackTrace();
		}
		return null;
	}
	
	public boolean isTrue(String sqlSentence) {

		try {
			ResultSet rs = stmt.executeQuery(sqlSentence);
			rs.next();
			boolean a = rs.getBoolean(1);

			stmt.close();
			conn.close();
			return a;
		} catch (SQLException a) {
			a.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<String> getColumns(String sqlSentence){
		ArrayList<String> result = new ArrayList<String>();
		try {
			ResultSet rsResultSet = stmt.executeQuery(sqlSentence);

			int i=0;
			while (rsResultSet.next()) {
				result.add(rsResultSet.getString("COLUMN_NAME"));
				i++;
			}

			stmt.close();
			conn.close();
		} catch (SQLException a) {
			a.printStackTrace();
		}
		return result;
	}
}
