package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.GiangVien;
import entity.SinhVien;

public class GiangVienDao {
	ArrayList<GiangVien> dsGV;
	public GiangVienDao() {
		dsGV = new ArrayList<GiangVien>();
	}
	
	public ArrayList<String> getDsMaGV() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> list = new ArrayList<String>();
			String sql = "select * from GiangVien";
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
	
	
	public ArrayList<String> getDsTenGV() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> list = new ArrayList<String>();
			String sql = "select * from GiangVien";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ten = rs.getString(2);
				if(!(list.contains(ten)))
					list.add(ten);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> LayMaGV(String ten) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<String> list = new ArrayList<String>();
		String sql ="select MaGV from GiangVien where HoTen = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ten);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				list.add(ma);
			}
			return list;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public ArrayList<GiangVien> doctubang() {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<GiangVien> dsGV = new ArrayList<GiangVien>();
		String sql = "select gv.MaGV,gv.HoTen,gv.DiaChi,"
				+ " gv.GioiTinh,gv.NgaySinh,gv.SoDt,kh.TenKhoa"
				+ " from GiangVien gv join Khoa kh on gv.Makhoa = kh.MaKhoa";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maGV = rs.getString(1);
				String hoTen = rs.getString(2);
				String diaChi = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				String maKhoa = rs.getString(7);
				GiangVien gv = new GiangVien(maGV, hoTen, diaChi, gioiTinh, ngaySinh, sdt, maKhoa);
				dsGV.add(gv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsGV;
	}
	
	public int LayMaTuDong() {
		int stt = 0;
		String sql = "select COUNT(MaGV) from GiangVien";
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
	
	public GiangVien KiemTraSDT(String sdtdd) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<GiangVien> dsSV = new ArrayList<GiangVien>();
		String sql = "select * from GiangVien gv where gv.SoDt = ?";
		GiangVien gv = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sdtdd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maGV = rs.getString(1);
				String hoTen = rs.getString(2);
				String diaChi = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				String maKhoa = rs.getString(7);
				gv = new GiangVien(maGV, hoTen, diaChi, gioiTinh, ngaySinh, sdt, maKhoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gv;
	}
	
	public boolean ThemGiangVien(String maGV,String diaChi,String gioiTinh,String hoTen,String ngaySinh,String sdt,String maKhoa) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("INSERT INTO GiangVien values (?,?,?,?,?,?,?)");
			stml.setString(1, maGV);
			stml.setString(2, hoTen);
			stml.setString(3, diaChi);
			stml.setString(4, gioiTinh);
			stml.setString(5, ngaySinh);
			stml.setString(6, sdt);
			stml.setString(7, maKhoa);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public boolean xoaGV(String maGV) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from GiangVien where MaGV=?");
			stml.setString(1, maGV);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	public ArrayList<GiangVien> TimTheoTen(String ten) {
		ArrayList<GiangVien> list= new ArrayList<GiangVien>();
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql="select * from GiangVien where HoTen"
					+ " like N'%"+ten+"' or HoTen like N'"+ten+"%' or HoTen like N'%"+ten+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maGV = rs.getString(1);
				String hoTen = rs.getString(2);
				String diaChi = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				String maKhoa = rs.getString(7);
				GiangVien gv = new GiangVien(maGV, hoTen, diaChi, gioiTinh, ngaySinh, sdt, maKhoa);
				list.add(gv);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<GiangVien> TimTheoDiaChi(String diaChi) {
		ArrayList<GiangVien> list= new ArrayList<GiangVien>();
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql="select * from GiangVien where DiaChi"
					+ " like N'%"+diaChi+"' or DiaChi like N'"+diaChi+"%' or DiaChi like N'%"+diaChi+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maGV = rs.getString(1);
				String hoTen = rs.getString(2);
				String diaChi1 = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				String maKhoa = rs.getString(7);
				GiangVien gv = new GiangVien(maGV, hoTen, diaChi1, gioiTinh, ngaySinh, sdt, maKhoa);
				list.add(gv);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<GiangVien> TimTheoSDT(String sdt1) {
		ArrayList<GiangVien> list= new ArrayList<GiangVien>();
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql="select * from GiangVien where SoDt"
					+ " like N'%"+sdt1+"' or SoDt like N'"+sdt1+"%' or SoDt like N'%"+sdt1+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maGV = rs.getString(1);
				String hoTen = rs.getString(2);
				String diaChi1 = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				String maKhoa = rs.getString(7);
				GiangVien gv = new GiangVien(maGV, hoTen, diaChi1, gioiTinh, ngaySinh, sdt, maKhoa);
				list.add(gv);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<GiangVien> TimTheoTenKhoa(String tenKhoa) {
		ArrayList<GiangVien> list= new ArrayList<GiangVien>();
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql="select gv.* from GiangVien gv"
					+ " join Khoa kh on gv.Makhoa = kh.MaKhoa"
					+ " where kh.TenKhoa like N'%"+tenKhoa+"' or kh.TenKhoa like N'"+tenKhoa+"%' or kh.TenKhoa like N'%"+tenKhoa+"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maGV = rs.getString(1);
				String hoTen = rs.getString(2);
				String diaChi1 = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				String sdt = rs.getString(6);
				String maKhoa = rs.getString(7);
				GiangVien gv = new GiangVien(maGV, hoTen, diaChi1, gioiTinh, ngaySinh, sdt, maKhoa);
				list.add(gv);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * Lệnh mới ngày 21/12/2020
	 */
	public boolean capNhatGiangVien(String maGV,String hoTen,String gioiTinh,String ngaySinh,String diaChi,String sdt,String maKhoa) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("update GiangVien set HoTen = ?,"
					+ " GioiTinh = ?, NgaySinh = ?, DiaChi = ?, SoDt = ?,"
					+ " Makhoa = ? where MaGV = ?");
			stml.setString(1, hoTen);
			stml.setString(2, gioiTinh);
			stml.setString(3, ngaySinh);
			stml.setString(4, diaChi);
			stml.setString(5, sdt);
			stml.setString(6, maKhoa);
			stml.setString(7, maGV);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
}
