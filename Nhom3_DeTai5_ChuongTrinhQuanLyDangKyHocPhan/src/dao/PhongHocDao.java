package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.PhongHoc;

public class PhongHocDao {
	ArrayList<PhongHoc> dsPH;
	public PhongHocDao() {
		dsPH = new ArrayList<PhongHoc>();
	}
	
	public ArrayList<String> getDsPH() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> list = new ArrayList<String>();
			String sql = "select * from PhongHoc";
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
	
	/*
	 * Mới ngày 22/12/2020
	 */
	public boolean themPhong(String phong) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into PhongHoc values (?)");
			stml.setString(1, phong);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaPhong(String phong) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from PhongHoc where PhongHoc=?");
			stml.setString(1, phong);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

}
