package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ChiTietLopHocPhan;
import entity.ChiTietLopHocPhan1;
import entity.LopHocPhan;


public class ChiTietLopHocPhanDao {
	ArrayList<ChiTietLopHocPhan> dsChiTiet;
	public ChiTietLopHocPhanDao() {
		dsChiTiet = new ArrayList<ChiTietLopHocPhan>();
	}
	
	public ArrayList<ChiTietLopHocPhan> LayDSChiTiet(String maLop) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ChiTietLopHocPhan> dsLHP = new ArrayList<ChiTietLopHocPhan>();		
		String sql = "select ct.MaNhom,ct.MaLopHP,  ct.TietHoc, ct.NgayHoc, ct.PhongHoc,  gv.HoTen, ct.NgayBatDau from ThoiGian_PhongHoc_GiangVien ct join GiangVien gv on ct.MaGV = gv.MaGV where MaLopHP = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maLop);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maNhom = rs.getString(1);
				String maLop1 = rs.getString(2);
				String tietHoc = rs.getString(3);
				String ngayHoc = rs.getString(4);
				String phongHoc = rs.getString(5);
				String maGV = rs.getString(6);
				Date ngayBD = rs.getDate(7);
				
				ChiTietLopHocPhan ct = new ChiTietLopHocPhan(maNhom, maLop1, tietHoc, ngayHoc, phongHoc, maGV,ngayBD);
				
				dsLHP.add(ct);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLHP;
		
		
	}
	
	public boolean ThemCTLHP(String maNhom,String maLop,String tietHoc,String ngayHoc,String phongHoc,String maGV,String ngayBD) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into ThoiGian_PhongHoc_GiangVien values (?,?,?,?,?,?,?)");
			stml.setString(1, maNhom);
			stml.setString(2, maLop);
			stml.setString(3, tietHoc);
			stml.setString(4, ngayHoc);
			stml.setString(5, phongHoc);
			stml.setString(6, maGV);
			stml.setString(7, ngayBD);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}return n>0;
	}
	
	
	public ArrayList<String> kiemtraLHP(String ngayHoc,String maGV,String phongHoc,String tietHoc,String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<String> dsLHP = new ArrayList<String>();		
		String sql = "select TG.MaNhom from ThoiGian_PhongHoc_GiangVien TG"
				+ " join LopHocPhan lhp on TG.MaLopHP = lhp.MaLopHP"
				+ " where TG.NgayHoc = ? and (TG.MaGV = ? or TG.PhongHoc = ?)"
				+ " and TG.TietHoc = ? and lhp.Nam = ? and lhp.HocKy = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ngayHoc);
			ps.setString(2, maGV);
			ps.setString(3, phongHoc);
			ps.setString(4, tietHoc);
			ps.setString(5,nam);
			ps.setInt(6,hocKy);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maNhom = rs.getString(1);
				dsLHP.add(maNhom);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLHP;
		
		
	}
	
	public ArrayList<ChiTietLopHocPhan1> docTuBang(String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ChiTietLopHocPhan1> dsCT = new ArrayList<ChiTietLopHocPhan1>();		
		String sql = "select lhp.MaMHP,TG.MaLopHP,TG.MaNhom,"
				+ " TG.TietHoc,TG.NgayHoc,TG.PhongHoc,GV.HoTen,TG.NgayBatDau from"
				+ " (ThoiGian_PhongHoc_GiangVien TG join LopHocPhan lhp"
				+ " on TG.MaLopHP = lhp.MaLopHP) join GiangVien gv on"
				+ " TG.MaGV = gv.MaGV where lhp.nam = ? and lhp.HocKy = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,nam);
			ps.setInt(2,hocKy);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maMon = rs.getString(1);
				String maLop = rs.getString(2);
				String maNhom = rs.getString(3);
				String tietHoc = rs.getString(4);
				String ngayHoc = rs.getString(5);
				String phongHoc = rs.getString(6);
				String tenGV = rs.getString(7);
				Date ngayBD = rs.getDate(8);
				ChiTietLopHocPhan1 ct = new ChiTietLopHocPhan1(maMon, maLop, maNhom, tietHoc,ngayHoc, phongHoc, tenGV, ngayBD);
				dsCT.add(ct);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCT;
		
		
	}
	
	public boolean xoaChiTiet(String maLHP,String nhom) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from ThoiGian_PhongHoc_GiangVien where MaNhom = ? and MaLopHP = ?");
			stml.setString(1, nhom);
			stml.setString(2, maLHP);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}return n>0;
	}
	

}
