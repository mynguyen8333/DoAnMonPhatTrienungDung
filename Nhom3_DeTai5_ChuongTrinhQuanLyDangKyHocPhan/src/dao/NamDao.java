package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Khoa;
import entity.Nam;

public class NamDao {
	ArrayList<String> dsNam = new ArrayList<String>();
	public NamDao() {
		dsNam = new ArrayList<String>();
		
	}
	public ArrayList<String> getDSNAm() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> list = new ArrayList<String>();
			String sql = "select * from Nam";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String nam = rs.getString(1);
				list.add(nam);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Mới ngày 22/12/2020
	 */
	public boolean themNam(String nam) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into Nam values (?)");
			stml.setString(1, nam);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaNam(String nam) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from Nam where Nam=?");
			stml.setString(1, nam);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

}
