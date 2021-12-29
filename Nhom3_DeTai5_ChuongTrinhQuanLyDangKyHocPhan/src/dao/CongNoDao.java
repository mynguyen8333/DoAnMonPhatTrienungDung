package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.CongNo;



public class  CongNoDao  {
	
	private final double  tienMoiChi = 790000;
	public CongNoDao()  {
		
	}
	public ArrayList<CongNo> LayCongNo (String masv, int hocky, String Nam) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<CongNo> ListCongNo =  new ArrayList<CongNo>();
		System.out.println("1");
		String sql = "select m.TenMHHP, m.SoTinChi, l.HocKy, l.Nam from (PhieuDangKyLHP p join LopHocPhan l on p.MaLopHP = l.MaLopHP) join MonHocPhan m on l.MaMHP = m.MaMHP where p.MSSV = ? and l.Nam =? and l.HocKy = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(3, hocky);
			ps.setString(2, Nam);
			ps.setString(1, masv);
			ResultSet rs = ps.executeQuery();
			System.out.println("1.5");
			while(rs.next()) {
				System.out.println("2");
				String tenMon = rs.getString(1);
				int soChi= rs.getInt(2);
				int hoKy = rs.getInt(3);
				String nam = rs.getString(4);
				
				double Tien = soChi *tienMoiChi;
				CongNo cn = new CongNo(tenMon, nam, soChi, hoKy, Tien);
				System.out.println(cn);
				ListCongNo.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("3");
		return ListCongNo;
		
		
	}
	public double TongChinhChi(String masv, int hocky, String Nam) {
		double tong = 0;
		Connection con = DataBase.getInstance().getConnection();
		System.out.println("1");
		String sql = "select sum(m.SoTinChi) from (PhieuDangKyLHP p join LopHocPhan l on p.MaLopHP = l.MaLopHP) join MonHocPhan m on l.MaMHP = m.MaMHP where p.MSSV = ? and l.Nam =? and l.HocKy =? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(3, hocky);
			ps.setString(2, Nam);
			ps.setString(1, masv);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
	
			
				int soChi= rs.getInt(1);
				tong =tong + soChi;
				
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tong;
	}
}
