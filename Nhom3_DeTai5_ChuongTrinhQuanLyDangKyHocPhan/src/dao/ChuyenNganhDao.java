package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.ChuyenNganh;



public class ChuyenNganhDao {
	ArrayList<ChuyenNganh> dsCHN = new ArrayList<ChuyenNganh>();
	public ChuyenNganhDao() {
		dsCHN = new ArrayList<ChuyenNganh>();	
	}
	
	public ArrayList<ChuyenNganh> docTuBang() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<ChuyenNganh> list = new ArrayList<ChuyenNganh>();
			String sql = "select * from ChuyenNganh";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String maKh = rs.getString(3);
				ChuyenNganh cn = new ChuyenNganh(ma, ten, maKh);
				list.add(cn);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> LayDSTen() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> list = new ArrayList<String>();
			String sql = "select * from ChuyenNganh";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ten = rs.getString(2);
				list.add(ten);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean ThemChuyenNganh(String maChuyenNganh,String tenChuyenNganh,String maKhoa) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into ChuyenNganh values (?,?,?)");
			stml.setString(1, maChuyenNganh);
			stml.setString(2, tenChuyenNganh);
			stml.setString(3, maKhoa);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaChuyenNganh(String maChuyenNganh) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from ChuyenNganh where MaChuyenNganh=?");
			stml.setString(1, maChuyenNganh);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public String LayMaChuyenNganh(String ten) {
		Connection con = DataBase.getInstance().getConnection();
		String ma = null;	
		String sql ="select MaChuyenNganh from ChuyenNganh where TenChuyenNganh = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ten);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ma = rs.getString(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
	
	public String LayTenCN(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		String ten = null;	
		String sql ="select TenChuyenNganh from ChuyenNganh where MaChuyenNganh = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ten = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ten;
	}
	
	
	/*
	 * Lệnh mới ngày 21/12/2020
	 */
	public boolean capNhatChuyenNganh(String maCN,String tenCN,String maKhoa) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("update ChuyenNganh set TenChuyenNganh = ?,"
					+ " MaKhoa = ? where MaChuyenNganh = ?");
			stml.setString(1, tenCN);
			stml.setString(2, maKhoa);
			stml.setString(3, maCN);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

}
