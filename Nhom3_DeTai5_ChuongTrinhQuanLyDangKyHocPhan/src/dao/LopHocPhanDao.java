package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.LopHocPhan;
import entity.MonHocPhan;

public class LopHocPhanDao {
	ArrayList<LopHocPhan> dsLHP;
	public LopHocPhanDao() {
		dsLHP = new ArrayList<LopHocPhan>();
	}

	public ArrayList<LopHocPhan> doctubang() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql = "select LH.MaLopHP,LH.SiSo,MH.TenMHHP,LH.Nam,LH.HocKy,LH.DaDangKy"
					+ " from LopHocPhan LH join MonHocPhan MH on LH.MaMHP = MH.MaMHP";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				//ma,ten,mamon,hocphanyeucau
				String maLop = rs.getString(1);
				int siSo = rs.getInt(2);
				String maMon = rs.getString(3);
				String nam = rs.getString(4);
				int hocKy = rs.getInt(5);
				int daDK = rs.getInt(6);
				LopHocPhan lhp = new LopHocPhan(maLop, siSo, maMon, nam, hocKy, daDK);
				dsLHP.add(lhp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsLHP;
	}

	public boolean ThemLopPhan(String maLHP,int siSo,String maMon,String nam,int hocky,int dadangKi) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into LopHocPhan values (?,?,?,?,?,?)");
			stml.setString(1, maLHP);
			stml.setInt(2, siSo);
			stml.setString(3, maMon);
			stml.setString(4, nam);
			stml.setInt(5, hocky);
			stml.setInt(6, dadangKi);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

	public boolean xoaLHP(String maLop) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from LopHocPhan where MaLopHP=?");
			stml.setString(1, maLop);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

	public boolean capNhat(String maLop,int siso,String maMHP,int nam,int hocky,int daDK) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update LopHocPhan set SiSo = ?,"
					+ " MaMHP = ?,"
					+ " Nam = ?,"
					+ " HocKy = ?,"
					+ " DaDangKy = ?"
					+ " where MaLopHP = ?");
			stmt.setInt(1, siso);
			stmt.setString(2, maMHP);
			stmt.setInt(3, siso);
			stmt.setInt(4, nam);
			stmt.setInt(5, hocky);
			stmt.setString(6, maLop);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}
		return n > 0;
	}

	public  ArrayList<LopHocPhan> TimTheoTenGV(String ten,String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<LopHocPhan> dsLop = new ArrayList<LopHocPhan>();		
		String sql ="select lhp.*"
				+ " from ((LopHocPhan lhp join ThoiGian_PhongHoc_GiangVien tg on lhp.MaLopHP = tg.MaLopHP)inner join GiangVien gv on gv.MaGV = tg.MaGV)"
				+ " where lhp.Nam = ? and lhp.HocKy = ? and gv.HoTen = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(3, ten);
			ps.setString(1,nam);
			ps.setInt(2,hocKy);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maLop = rs.getString(1);
				int siso = rs.getInt(2);
				String maMon = rs.getString(3);
				String namhoc = rs.getString(4);
				int hocky = rs.getInt(5);
				int daDK = rs.getInt(6);
				LopHocPhan lhp = new LopHocPhan(maLop, siso, maMon, nam, hocky, daDK);
				dsLop.add(lhp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLop;
	}

	public  ArrayList<LopHocPhan> TimTheoThoiGian(String ngayHoc,String tietHoc,String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<LopHocPhan> dsLop = new ArrayList<LopHocPhan>();		
		String sql = "select lhp.* from LopHocPhan lhp,ThoiGian_PhongHoc_GiangVien tg ,GiangVien gv"
				+ " where lhp.MaLopHP = tg.MaLopHP and gv.MaGV = tg.MaGV"
				+ " and lhp.Nam = ? and lhp.HocKy = ? and tg.NgayHoc = ? and tg.TietHoc = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,nam);
			ps.setInt(2,hocKy);
			ps.setString(3, ngayHoc);
			ps.setString(4, tietHoc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maLop = rs.getString(1);
				int siso = rs.getInt(2);
				String maMon = rs.getString(3);
				String namhoc = rs.getString(4);
				int hocky = rs.getInt(5);
				int daDK = rs.getInt(6);
				LopHocPhan lhp = new LopHocPhan(maLop, siso, maMon, nam, hocky, daDK);
				dsLop.add(lhp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLop;
	}

	public  ArrayList<LopHocPhan> TimtheoTenMon(String ten,String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<LopHocPhan> dsLop = new ArrayList<LopHocPhan>();		
		String sql ="select lhp.* from LopHocPhan lhp"
				+ " join MonHocPhan mh on lhp.MaMHP  = mh.MaMHP"
				+ " where lhp.Nam = ? and lhp.HocKy = ? and mh.TenMHHP = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(3, ten);
			ps.setString(1,nam);
			ps.setInt(2,hocKy);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maLop = rs.getString(1);
				int siso = rs.getInt(2);
				String maMon = rs.getString(3);
				String namhoc = rs.getString(4);
				int hocky = rs.getInt(5);
				int daDK = rs.getInt(6);
				//Date ngayBD = rs.getDate(7);

				LopHocPhan lhp = new LopHocPhan(maLop, siso, maMon, nam, hocky, daDK);
				dsLop.add(lhp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLop;
	}

	/*
	 * Của ông tới
	 */

	public ArrayList<LopHocPhan> LayDSLop(String maMHP, int hocky, String Nam) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<LopHocPhan> dsLHP = new ArrayList<LopHocPhan>();		
		String sql = "select * from LopHocPhan where MaMHP = ? and HocKy = ? and Nam = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maMHP);
			ps.setInt(2, hocky);
			ps.setString(3, Nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maLop = rs.getString(1);
				String maMon = rs.getString(3);
				int siSo = rs.getInt(2);
				String nam = rs.getString(4);
				int hocKy = rs.getInt(5);
				int daDK = rs.getInt(6);
				//Date ngayBD = rs.getDate(7);

				LopHocPhan lhp = new LopHocPhan(maLop, siSo, maMon, nam, hocky, daDK);
				dsLHP.add(lhp);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLHP;


	}


	public boolean KTMon_DaDK(String mssv, String maLop) {

		ArrayList<LopHocPhan> dsLHPĐK =  new ArrayList<LopHocPhan>();
		Connection con = DataBase.getInstance().getConnection();
		String sql ="select  DISTINCT lhp.* from PhieuDangKyLHP p "
				+ "join  LopHocPhan lhp on p.MaLopHP = lhp.MaLopHP"
				+ " where   p.MSSV = ?"
				+ " and  lhp.MaMHP ="
				+ " (select MaMHP from LopHocPhan where MaLopHP = ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, mssv);

			ps.setString(2, maLop);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mal= rs.getString(1);
				int  siSo = rs.getInt(2);
				String maMHP = rs.getString(3);
				String nam =  rs.getString(4);
				int hocky = rs.getInt(5);
				int soLuongDK = rs.getInt(6);
				//Date ngayDK = rs.getDate(7);
				LopHocPhan lhp = new LopHocPhan(mal, siSo, maMHP, nam, hocky, soLuongDK);
				if(!dsLHPĐK.contains(lhp))
					dsLHPĐK.add(lhp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if(dsLHPĐK.size()==0)
			return false;
		else 
			return true;
	}
	public int LaySiSoDK(String maLop) {
		Connection con = DataBase.getInstance().getConnection();
		int siso = 0;	
		String sql ="select DaDangKy from LopHocPhan where MaLopHP = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maLop);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				siso= rs.getInt(1);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return siso;


	}
	
	
	public boolean capNhatSiSo(int daDK,String maLop) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update LopHocPhan set DaDangKy = ? where MaLopHP = ?");
			stmt.setInt(1, daDK);
			stmt.setString(2, maLop);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}
		return n > 0;
	}
	/*
	 * Mới
	 */
	
	public  ArrayList<LopHocPhan> LayDSLopTheoMaMon(String nam,int hocKy,String maMonHP) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<LopHocPhan> dsLop = new ArrayList<LopHocPhan>();		
		String sql = "select LH.* from LopHocPhan LH,MonHocPhan MH"
				+ " where LH.MaMHP = MH.MaMHP and"
				+ " LH.Nam = ? and LH.HocKy = ? and MH.MaMHP = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,nam);
			ps.setInt(2,hocKy);
			ps.setString(3, maMonHP);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maLop = rs.getString(1);
				int siso = rs.getInt(2);
				String maMon = rs.getString(3);
				String namhoc = rs.getString(4);
				int hocky = rs.getInt(5);
				int daDK = rs.getInt(6);
				LopHocPhan lhp = new LopHocPhan(maLop, siso, maMon, nam, hocky, daDK);
				dsLop.add(lhp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLop;
	}
	
	public int LayMaTuDong() {
		int stt = 0;
		String sql = "select COUNT(MaLopHP) from LopHocPhan";
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
	/*
	 * Hàm mới ngày 22/12/2020
	 */
	public boolean capNhatLopHocPhan(String maLop,int siSo,String maMon,String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("update LopHocPhan set SiSo = ?,"
					+ " MaMHP = ?,Nam = ?,HocKy = ? where MaLopHP = ?");
			stml.setInt(1, siSo);
			stml.setString(2, maMon);
			stml.setString(3, nam);
			stml.setInt(4, hocKy);
			stml.setString(5, maLop);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}


}
