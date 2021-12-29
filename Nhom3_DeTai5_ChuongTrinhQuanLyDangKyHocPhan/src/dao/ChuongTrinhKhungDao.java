package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.ChuongTrinhKhung;
import entity.ChuongTrinhKhung1;
import entity.LopHocPhan;
import entity.MonHocPhan;


public class ChuongTrinhKhungDao {
	ArrayList<ChuongTrinhKhung1> dsCHN = new ArrayList<ChuongTrinhKhung1>();
	public ChuongTrinhKhungDao() {
		dsCHN = new ArrayList<ChuongTrinhKhung1>();	
	}
	
	public ArrayList<ChuongTrinhKhung1> docTuBang() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<ChuongTrinhKhung1> list = new ArrayList<ChuongTrinhKhung1>();
			String sql = "select * from ChuongTrinhKhung";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				int hocky = rs.getInt(3);
				ChuongTrinhKhung1 ctk = new ChuongTrinhKhung1(ma, ten, hocky);
				list.add(ctk);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean ThemCTK(String maCN,String maMHP,int hocKy ) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into ChuongTrinhKhung values (?,?,?)");
			stml.setString(1, maCN);
			stml.setString(2, maMHP);
			stml.setInt(3, hocKy);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}return n>0;
	}
	
	public boolean xoaCTK(String maCN,String maMH) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from ChuongTrinhKhung where MachuyenNganh = ? and MaMHP = ?");
			stml.setString(1, maCN);
			stml.setString(2, maMH);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}return n>0;
	}
	
//	public ArrayList<> kiemtra(String maCN,String maMH) {
//		Connection con = DataBase.getInstance().getConnection();
//		PreparedStatement stml = null;
//		int n =0;
//		try {
//			stml = con.prepareStatement("delete from ChuongTrinhKhung where MachuyenNganh = ? and MaMHP = ?");
//			stml.setString(1, maCN);
//			stml.setString(2, maMH);
//			n = stml.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			
//		}return n>0;
//	}
	
	public  ArrayList<ChuongTrinhKhung1> kiemtra(String maCN,String maMH) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ChuongTrinhKhung1> list = new ArrayList<ChuongTrinhKhung1>();		
		String sql ="select * from ChuongTrinhKhung where MachuyenNganh = ? and MaMHP = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,maCN);
			ps.setString(2,maMH);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				int hocky = rs.getInt(3);
				ChuongTrinhKhung1 ctk = new ChuongTrinhKhung1(ma, ten, hocky);
				list.add(ctk);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
