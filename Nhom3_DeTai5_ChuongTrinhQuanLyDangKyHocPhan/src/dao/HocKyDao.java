package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.HocKy;
import entity.Nam;

public class HocKyDao {
	ArrayList<HocKy> dsHocKy = new ArrayList<HocKy>();
	public HocKyDao() {
		dsHocKy = new ArrayList<HocKy>();
		
	}
	public ArrayList<Integer> getDSHocKy() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<Integer> list = new ArrayList<Integer>();
			String sql = "select * from HocKy";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				int hocKy = rs.getInt(1);
				list.add(hocKy);
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
	public boolean themHK(int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into HocKy values (?)");
			stml.setInt(1, hocKy);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaHK(String hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from HocKy where HocKy=?");
			stml.setString(1, hocKy);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

}
