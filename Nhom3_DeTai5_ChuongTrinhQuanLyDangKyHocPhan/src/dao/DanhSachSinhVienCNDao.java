package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.DanhSachSVCN;
import entity.SinhVien;


public class DanhSachSinhVienCNDao {
	ArrayList<DanhSachSVCN> dsSV = new ArrayList<DanhSachSVCN>();
	public DanhSachSinhVienCNDao() {
		dsSV = new ArrayList<DanhSachSVCN>();
	}
	
	public ArrayList<SinhVien> chuaCoCN() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<SinhVien> list = new ArrayList<SinhVien>();
			String sql = "select sv1.* from SinhVien sv1"
					+ " where sv1.MSSV in (select sv.MSSV"
					+ " from SinhVien sv EXCEPT (select svn.MSSV"
					+ " from SinhVien_Thuoc_Nganh svn))";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
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
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<SinhVien> SinhVienThuocCN(String maCN) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<SinhVien> dsSV = new ArrayList<SinhVien>();
		String sql = "select sv.* from SinhVien sv join"
				+ " SinhVien_Thuoc_Nganh svn on sv.MSSV = svn.MSSV"
				+ " where svn.MaChuyenNganh = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maCN);
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
	
	public boolean ThemDanhSach(String maSV,String maCN) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("INSERT INTO SinhVien_Thuoc_Nganh values (?,?)");
			stml.setString(1, maSV);
			stml.setString(2, maCN);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaSVTDS(String maSV) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from SinhVien_Thuoc_Nganh where MSSV=?");
			stml.setString(1, maSV);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

}
