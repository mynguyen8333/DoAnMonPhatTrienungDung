package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.LichHoc;
import entity.LopHocPhan;
import entity.MonHocPhan;

public class LichHocDao {
	ArrayList<LichHoc> lichHoc =  new ArrayList<LichHoc>();
	
	public LichHocDao() {
		
	}
	public ArrayList<LichHoc> LayLichHoc (String masv, int hocky, String Nam) {
		Connection con = DataBase.getInstance().getConnection();
		System.out.println("1");
		String sql = "select tg.NgayHoc, tg.TietHoc, m.TenMHHP, tg.PhongHoc, p.Nhom, tg.NgayBatDau  from PhieuDangKyLHP p join LopHocPhan l on p.MaLopHP = l.MaLopHP join ThoiGian_PhongHoc_GiangVien tg on l.MaLopHP = tg.MaLopHP and p.Nhom = tg.MaNhom join MonHocPhan m on l.MaMHP =m.MaMHP where l.HocKy = ? and l.Nam = ? and p.MSSV = ? order by tg.NgayHoc,tg.TietHoc";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocky);
			ps.setString(2, Nam);
			ps.setString(3, masv);
			ResultSet rs = ps.executeQuery();
			System.out.println("1.5");
			while(rs.next()) {
				System.out.println("2");
				String ngayHoc = rs.getString(1);
				String tietHoc = rs.getString(2);
				String tenMonHoc = rs.getString(3);
				String phongHoc = rs.getString(4);
				String nhom = rs.getString(5);
				Date ngaybatdau = rs.getDate(6);
				
				LichHoc lh = new LichHoc(ngayHoc, tietHoc, tenMonHoc, phongHoc, nhom, ngaybatdau);
				System.out.println("lh" + lh);
				lichHoc.add(lh);	
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("3");
		return lichHoc;
		
		
	}
}
