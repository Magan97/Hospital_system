import java.util.ArrayList;
import java.util.Dictionary;


public class SqlSon extends Sql {
	public void insert(ArrayList<String> mapArrayList)
	{
		super.Open();
		String tableNameString = null;
		tableNameString = mapArrayList.get(0);
		int count = mapArrayList.size();
		
		String sql = "insert into " + tableNameString + " values ('";
		for(int i = 1;i < count - 1;i++)
		{
			sql = sql + mapArrayList.get(i) + "', '";
		}
		sql = sql + mapArrayList.get(count - 1) + "')";
		//System.out.println(sql);
		super.CloseUpdate(sql);
		
	}
	
	public void delete(String idNameString, String Id, String tablenameString)
	{
		super.Open();
		String sql = "delete from " + tablenameString + " where " + idNameString + " = '" + Id + "'";
		super.CloseUpdate(sql);
	}
	
	public void update(String idnameString, String Id, String tablenameString,ArrayList<String>  mapArrayList,Dictionary<String, String> mapDictionary)
	{
		super.Open();
		String sql = "update " + tablenameString + " set ";
		int count = mapDictionary.size();
		for(int i = 0;i < count - 1;i++)
		{
			sql = sql + mapArrayList.get(i) + " = '" + mapDictionary.get(mapArrayList.get(i)) + "', ";
		}
		sql = sql + mapArrayList.get(count - 1) + " = '" + mapDictionary.get(mapArrayList.get(count - 1)) + "' where " + idnameString + " = '" + Id + "'";
		
		super.CloseUpdate(sql);
        
	}

	public ArrayList<ArrayList<String>> select(String tablenameString, ArrayList<String> checkCoList)
	{
		int amount = checkCoList.size();
		String sql = "select " ;
		for(int i = 0;i < amount - 1;i++){
			sql = sql + checkCoList.get(i) + ", ";
		}
		sql = sql + checkCoList.get(amount - 1) + " from " + tablenameString;
		System.out.println(sql);

		ArrayList<ArrayList<String>> mapArrayList = new ArrayList<ArrayList<String>>();
		super.Open();


		mapArrayList = super.Closequery(sql, amount);
		return mapArrayList;
	}
	
	public ArrayList<ArrayList<String>> selectAllInformation(String tablenameString, int amount)
	{

		String sql = "select * from " + tablenameString ;
		
		ArrayList<ArrayList<String>> mapArrayList = new ArrayList<ArrayList<String>>();
		super.Open();

		mapArrayList = super.Closequery(sql, amount);
		return mapArrayList;
	}

	public String selectAll(String tablenameString) 
	{
		String idString;
		super.Open();
		idString = super.selectAll(tablenameString);
		return idString;
	}
	
	public ArrayList<ArrayList<String>> selectCondition(String tablenameString, String columnNameString, String columnValueString, int column)
	{
		String sql = "select * from " + tablenameString + " where " + columnNameString + " = '" + columnValueString + "'";
		System.out.println(sql);
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		super.Open();

		result = super.Closequery(sql, column);

		return result;
	}

//new
	public boolean isTrue(String sqlSentence){
		super.Open();
		return super.isTrue(sqlSentence);
	}

	public ArrayList<String> getColumns(String sqlSentence){
		super.Open();
		return super.getColumns(sqlSentence);
	}

	public ArrayList<ArrayList<String>> execute(String sqlSentence, int amount){
		super.Open();
		return super.Closequery(sqlSentence, amount);
	}
	
	public String selectOne(String sqlSentence) {
		ArrayList<ArrayList<String>> result = execute(sqlSentence, 1);
		if(result.size()==0) {
			return null;
		}
		else if (result.get(0).size()==0) {
			return null;
		}
		else {
			return result.get(0).get(0);
		}
	}

	public void executeWithoutReturn(String sqlSentence) {
		super.Open();
		super.CloseUpdate(sqlSentence);
	}
}