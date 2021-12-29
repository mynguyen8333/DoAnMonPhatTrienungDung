package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.LopHocPhanDaDangKy;
import entity.PhieuDangKyLopHP;



public class LopHocPhanDaDangKyDao {
	
	public LopHocPhanDaDangKyDao() {
		
	}
	public ArrayList<LopHocPhanDaDangKy> layDS_DaDK(String mssv, int hk,String nam) {
		
		ArrayList<LopHocPhanDaDangKy> dsLHPĐK =  new ArrayList<LopHocPhanDaDangKy>();
		Connection con = DataBase.getInstance().getConnection();
		String sql = "select DISTINCT lhp.MaLopHP,mhp.TenMHHP, p.Nhom, gv.HoTen"
				+ " from ((((SinhVien sv join PhieuDangKyLHP p on sv.MSSV = p.MSSV)"
				+ " join LopHocPhan lhp on p.MaLopHP = lhp.MaLopHP)"
				+ " join MonHocPhan mhp on lhp.MaMHP = mhp.MaMHP)"
				+ " join ThoiGian_PhongHoc_GiangVien tg on lhp.MaLopHP = tg.MaLopHP)"
				+ " join GiangVien gv on tg.MaGV = gv.MaGV"
				+ " where sv.MSSV=? and lhp.HocKy=? and lhp.Nam = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, mssv);
			ps.setInt(2, hk);
			ps. setString(3, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maLop= rs.getString(1);
				String tenMH = rs.getString(2);
				String nhom = rs.getString(3);
				String giangVien = rs.getString(4);
				LopHocPhanDaDangKy lhp = new LopHocPhanDaDangKy(maLop, tenMH, nhom, giangVien);
				
				if(!dsLHPĐK.contains(lhp))
					dsLHPĐK.add(lhp);
			}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return dsLHPĐK;

}
public boolean KTTG_DaDK(String mssv, int hk, String nam, String tiethoc, String thu) {
		
		ArrayList<LopHocPhanDaDangKy> dsLHPĐK =  new ArrayList<LopHocPhanDaDangKy>();
		Connection con = DataBase.getInstance().getConnection();
//		String sql = "select DISTINCT lhp.MaLopHP,mhp.TenMHHP, p.Nhom, gv.HoTen"
//				+ " from ((((SinhVien sv join PhieuDangKyLHP p on sv.MSSV = p.MSSV)"
//				+ " join LopHocPhan lhp on p.MaLopHP = lhp.MaLopHP)"
//				+ " join MonHocPhan mhp on lhp.MaMHP = mhp.MaMHP)"
//				+ " join ThoiGian_PhongHoc_GiangVien tg on lhp.MaLopHP = tg.MaLopHP)"
//				+ " join GiangVien gv on tg.MaGV = gv.MaGV"
//				+ " where sv.MSSV=? and lhp.HocKy=? and lhp.Nam = ? and tg.TietHoc = ?";
		String sql ="select DISTINCT lhp.MaLopHP,mhp.TenMHHP, p.Nhom, gv.HoTen from ((((SinhVien sv join PhieuDangKyLHP p on sv.MSSV = p.MSSV) join LopHocPhan lhp on p.MaLopHP = lhp.MaLopHP)"
				+ "join MonHocPhan mhp on lhp.MaMHP = mhp.MaMHP) join ThoiGian_PhongHoc_GiangVien tg on lhp.MaLopHP = tg.MaLopHP) "
				+ "join GiangVien gv on tg.MaGV = gv.MaGV"
				+ " where sv.MSSV=? and lhp.HocKy=? and lhp.Nam = ? and tg.TietHoc = ? and tg.NgayHoc = ?";
		
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, mssv);
			ps.setInt(2, hk);
			ps.setString(3, nam);
			ps.setString(4, tiethoc);
			ps.setString(5, thu);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maLop= rs.getString(1);
				String tenMH = rs.getString(2);
				String nhom = rs.getString(3);
				String giangVien = rs.getString(4);
				LopHocPhanDaDangKy lhp = new LopHocPhanDaDangKy(maLop, tenMH, nhom, giangVien);
				System.out.println("LH =" + lhp);
				if(!dsLHPĐK.contains(lhp))
					dsLHPĐK.add(lhp);
			}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	if(dsLHPĐK.size() == 0)
		return false;
	else
		return true;

}

	
}
