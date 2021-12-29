package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ThongKeLop;

public class ThongKeLopDao {
	ArrayList<ThongKeLop> dsTK =  new ArrayList<ThongKeLop>();
	public ThongKeLopDao() {
		
	}
	
	public  ArrayList<ThongKeLop> ThongKeLop(int hocKy,String nam) {
		Connection con = DataBase.getInstance().getConnection();
		//ArrayList<ThongKeLop> dsLopTK = new ArrayList<ThongKeLop>();		
		String sql = "select mh.MaMHP, mh.TenMHHP ,COUNT(lhp.MaLopHP) as 'số lớp'"
				+ "  from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy =? and lhp.Nam = ? group by mh.MaMHP, mh.TenMHHP";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocKy);
			ps.setString(2, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int soLop = rs.getInt(3);
				ThongKeLop tk = new ThongKeLop(mamon, tenMon, soLop);				
				dsTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	/*
	 * select COUNT(MaLopHP) from LopHocPhan where Nam = ? and HocKy = ?
	 */
	
	public int tongSLLHP(int hocKy,String nam) {
		Connection con = DataBase.getInstance().getConnection();
		int soLop = 0;
		String sql = "select COUNT(MaLopHP) from LopHocPhan where Nam = ? and HocKy = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nam);
			ps.setInt(2, hocKy);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				soLop = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soLop;
	}
	
	public ThongKeLop timThuocTenThuoc(String tenMon) {
		for (ThongKeLop lop : dsTK) {
			if (lop.getTenMon().equalsIgnoreCase(tenMon)) {
				return lop;
			}
		}
		return null;
	}

}
