package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.LopHocPhan;
import entity.MonHocPhan;
import entity.PhieuDangKyLopHP;



public class PhieuDangKyLopHPDao {
	ArrayList<PhieuDangKyLopHP> dsPDK =  new ArrayList<PhieuDangKyLopHP>();
	public PhieuDangKyLopHPDao() {

	}
	public ArrayList<PhieuDangKyLopHP> layPhieuDK_1_SV(String mssv) {

		Connection con = DataBase.getInstance().getConnection();
		String sql = "select * from PhieuDangKyLHP where MSSV = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, mssv);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String masv = rs.getString(1);
				String maLopHP = rs.getString(2);
				String nhom = rs.getString(3);
				PhieuDangKyLopHP P = new PhieuDangKyLopHP(masv, maLopHP, nhom);
				dsPDK.add(P);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPDK;

	}
	public boolean ThemPhieuDangKy(String masv,String maLop, String maNhom) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement(" insert into PhieuDangKyLHP values (?, ?, ?)");
			stml.setString(1, masv);
			stml.setString(2, maLop);
			stml.setString(3, maNhom);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}

	public boolean xoaPhieuDangKy(String masv,String maLop) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement(" delete from PhieuDangKyLHP where MSSV = ?  and MaLopHP =?");
			stml.setString(1, masv);
			stml.setString(2, maLop);
			//stml.setString(3, maNhom);

			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}


}
