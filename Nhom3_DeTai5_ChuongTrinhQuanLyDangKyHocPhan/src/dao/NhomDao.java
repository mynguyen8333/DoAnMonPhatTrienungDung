package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NhomDao {
	public ArrayList<String> getDsNhom() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> list = new ArrayList<String>();
			String sql = "select * from Nhom";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ten = rs.getString(1);
				list.add(ten);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
