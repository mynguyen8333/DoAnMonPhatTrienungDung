package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Khoa;
import entity.SinhVien;
public class KhoaDao {
	ArrayList<Khoa> dsMaKhoa = new ArrayList<Khoa>();
	public KhoaDao() {
		dsMaKhoa = new ArrayList<Khoa>();
		
	}
	public ArrayList<String> getDsMaKhoa() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> list = new ArrayList<String>();
			String sql = "select * from Khoa";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ma = rs.getString(1);
				list.add(ma);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Khoa> docTuBang() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<Khoa> list = new ArrayList<Khoa>();
			String sql = "select * from Khoa";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				Khoa kh = new Khoa(ma, ten);
				list.add(kh);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String LayMaKhoa(String ten) {
		Connection con = DataBase.getInstance().getConnection();
		String ma = null;	
		String sql ="select MaKhoa from Khoa where TenKhoa = ?";
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
	
	
	public ArrayList<String> getDsTen() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> listTen = new ArrayList<String>();
			String sql = "select * from Khoa";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ten = rs.getString(2);
				listTen.add(ten);
			}
			return listTen;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String LayTenKhoa(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		String ten = null;	
		String sql ="select TenKhoa from Khoa where MaKhoa = ?";
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
	
	public boolean ThemKhoa(String maKhoa,String tenKhoa) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into Khoa values (?,?)");
			stml.setString(1, maKhoa);
			stml.setString(2, tenKhoa);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaKhoa(String maKhoa) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from Khoa where MaKhoa=?");
			stml.setString(1, maKhoa);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	
	/*
	 * Lệnh mới ngày 21/12/2020
	 */
	public boolean capNhatKhoa(String maKhoa,String tenKhoa) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("update Khoa set TenKhoa = ? where MaKhoa = ?");
			stml.setString(1, tenKhoa);
			stml.setString(2, maKhoa);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
}
