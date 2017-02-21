package cn.wemart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GetDataDB {
	public ArrayList<String> sql(String dataBaseName, String tableName,String outPutColumnName, String condition) {
		String db = LoadDataBaseInfo.DBurl;
		String port = LoadDataBaseInfo.DBport;
		String userName = LoadDataBaseInfo.DBuserName;
		String userPassword = LoadDataBaseInfo.DBuserPassword;
		
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		ArrayList<String> resultList = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+db+":"+port+"/"+dataBaseName,userName, userPassword);
			st = conn.createStatement();
			String sql = "SELECT " + outPutColumnName + " FROM " + tableName + " " + condition;
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String pd = rs.getString(1);
				resultList.add(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public static void main(String[] args) {
		GetDataDB DB = new GetDataDB();
		ArrayList<String> ABVD = DB.sql("wmplatform_gm","tbl_gm_retailgoodssku","goodssku_id","where goods_id = 'gd103170'");
		for(int i =0;i< ABVD.size();i++){
			System.out.println(ABVD.get(i));
		}
	}
}
