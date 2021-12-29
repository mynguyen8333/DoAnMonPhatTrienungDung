package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ThongKeLop;
import entity.ThongKeSoLuongSinhVien;

public class ThongKeSinhVienDao {
	ArrayList<ThongKeSoLuongSinhVien> dsTK =  new ArrayList<ThongKeSoLuongSinhVien>();
	public ThongKeSinhVienDao() {
		
	}
	
	public  ArrayList<ThongKeSoLuongSinhVien> ThongKeSoLuongSV(int hocKy,String nam) {
		Connection con = DataBase.getInstance().getConnection();
		//ArrayList<ThongKeSoLuongSinhVien> dsTKSL = new ArrayList<ThongKeSoLuongSinhVien>();		
		String sql = "select mh.MaMHP, mh.TenMHHP ,Sum(lhp.SiSo) as"
				+ " 'số lượng sinh viên cần ', Sum(lhp.DaDangKy) as"
				+ " 'số lượng sinh viên đã đăng ký ' from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy =? and lhp.Nam = ? group by mh.MaMHP, mh.TenMHHP";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocKy);
			ps.setString(2, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int soSVCan = rs.getInt(3);
				int soSVDaDK = rs.getInt(4);
				ThongKeSoLuongSinhVien tk = new ThongKeSoLuongSinhVien(mamon, tenMon, soSVCan, soSVDaDK);			
				dsTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
	public ThongKeSoLuongSinhVien timMon(String tenMon) {
		for (ThongKeSoLuongSinhVien mon : dsTK) {
			if (mon.getTenMH().equalsIgnoreCase(tenMon)) {
				return mon;
			}
		}
		return null;
	}

}
