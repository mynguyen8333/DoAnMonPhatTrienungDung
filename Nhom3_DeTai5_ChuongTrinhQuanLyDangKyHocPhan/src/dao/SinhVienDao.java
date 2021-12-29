package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.SinhVien;


public class SinhVienDao {
	ArrayList<SinhVien> dsMHP =  new ArrayList<SinhVien>();
	public SinhVienDao() {
		
	}
	
	public ArrayList<SinhVien> LaySinhVienTheoMa(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<SinhVien> dsSV = new ArrayList<SinhVien>();
		String sql = "select * from SinhVien where MSSV = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maSV = rs.getString(1);
				String diaChi = rs.getString(2);
				String gioTinh = rs.getString(3);
				String hoTen = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				SinhVien sv = new SinhVien(maSV, diaChi, gioTinh, hoTen, ngaySinh, sdt);
				dsSV.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSV;
	}
	
	public ArrayList<SinhVien> doctubang() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<SinhVien> dsSV = new ArrayList<SinhVien>();
		String sql = "select * from SinhVien";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maSV = rs.getString(1);
				String diaChi = rs.getString(2);
				String gioTinh = rs.getString(3);
				String hoTen = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				SinhVien sv = new SinhVien(maSV, diaChi, gioTinh, hoTen, ngaySinh, sdt);
				dsSV.add(sv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSV;
	}
	
	
	public SinhVien LaySinhVienBangMa(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		SinhVien sv = null;
		String sql = "select * from SinhVien where MSSV = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
				String maSV = rs.getString(1);
				String diaChi = rs.getString(2);
				String gioTinh = rs.getString(3);
				String hoTen = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
			
				sv = new SinhVien(maSV, diaChi, gioTinh, hoTen, ngaySinh, sdt);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sv;
	}
	/*
	 * Hàm mới
	 */
	public boolean ThemSinhVien(String maSV,String diaChi,String gioiTinh,String hoTen,String ngaySinh,String sdt) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("INSERT INTO SinhVien values (?,?,?,?,?,?)"
                    + "INSERT INTO TaiKhoanSV values (?,?)");
			stml.setString(1, maSV);
			stml.setString(2, diaChi);
			stml.setString(3, gioiTinh);
			stml.setString(4, hoTen);
			stml.setString(5, ngaySinh);
			stml.setString(6, sdt);
			stml.setString(7, maSV);
			stml.setString(8,"1");
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaTK(String maTK) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from TaiKhoanSV where MaTaiKhoan=?");
			stml.setString(1, maTK);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaSV(String maSV) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from SinhVien where MSSV=?");
			stml.setString(1, maSV);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	/*
	 * Hàm mới
	 */
	public ArrayList<SinhVien> TimTheoTen(String ten) {
		ArrayList<SinhVien> list= new ArrayList<SinhVien>();
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql="select * from SinhVien where HoTen"
					+ " like N'%"+ten+"' or HoTen like N'"+ten+"%' or HoTen like N'%"+ten+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maSV = rs.getString(1);
				String diaChi = rs.getString(2);
				String gioTinh = rs.getString(3);
				String hoTen = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				SinhVien sv = new SinhVien(maSV, diaChi, gioTinh, hoTen, ngaySinh, sdt);
				list.add(sv);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<SinhVien> TimTheoDiaChi(String diaChi) {
		ArrayList<SinhVien> list= new ArrayList<SinhVien>();
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql="select * from SinhVien where DiaChi like N'%"+diaChi+"' or DiaChi like N'"+diaChi+"%' or DiaChi like N'%"+diaChi+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maSV = rs.getString(1);
				String diaChi1 = rs.getString(2);
				String gioTinh = rs.getString(3);
				String hoTen = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				SinhVien sv = new SinhVien(maSV, diaChi1, gioTinh, hoTen, ngaySinh, sdt);
				list.add(sv);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int LayMaTuDong() {
		int stt = 0;
		String sql = "select COUNT(MSSV) from SinhVien";
		try {
			Connection con = DataBase.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				stt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stt;
	}
	
	public SinhVien KiemTraSDT(String sdtdd) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<SinhVien> dsSV = new ArrayList<SinhVien>();
		String sql = "select * from SinhVien sv where sv.SoDT = ?";
		SinhVien sv = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sdtdd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maSV = rs.getString(1);
				String diaChi = rs.getString(2);
				String gioTinh = rs.getString(3);
				String hoTen = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				sv = new SinhVien(maSV, diaChi, gioTinh, hoTen, ngaySinh, sdt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sv;
	}
	
	
	public ArrayList<SinhVien> TimTheoSDT(String sdt1) {
		ArrayList<SinhVien> list= new ArrayList<SinhVien>();
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql="select * from SinhVien where SoDT like '%"+sdt1+"' or SoDT like '"+sdt1+"%' or SoDT like '%"+sdt1+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maSV = rs.getString(1);
				String diaChi1 = rs.getString(2);
				String gioTinh = rs.getString(3);
				String hoTen = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				SinhVien sv = new SinhVien(maSV, diaChi1, gioTinh, hoTen, ngaySinh, sdt);
				list.add(sv);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * Lệnh mới ngày 21/12/2020
	 */
	
	public boolean capNhatSinhVien(String maSV,String hoTen,String gioiTinh,String ngaySinh,String diaChi,String sdt) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("update SinhVien set HoTen = ?,"
					+ " GioiTinh = ?,"
					+ " NgaySinh = ?, DiaChi = ?, SoDT = ? where MSSV = ?");
			stml.setString(1, hoTen);
			stml.setString(2, gioiTinh);
			stml.setString(3, ngaySinh);
			stml.setString(4, diaChi);
			stml.setString(5, sdt);
			stml.setString(6, maSV);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

}
