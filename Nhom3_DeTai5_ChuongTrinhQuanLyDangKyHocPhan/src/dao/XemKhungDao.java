package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.ChuongTrinhKhung;
import entity.PhieuDangKyLopHP;


public class XemKhungDao {
	ArrayList<ChuongTrinhKhung> dsM =  new ArrayList<ChuongTrinhKhung>();
	public XemKhungDao() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<ChuongTrinhKhung> layChuongTrinhKhung(String masv){
		Connection con = DataBase.getInstance().getConnection();
		String sql = "select m.MaMHP, m.TenMHHP, m.HocPhanYeuCau, k.HocKy"
				+ " from ChuongTrinhKhung k join MonHocPhan m on k.MaMHP = m.MaMHP  "
				+ "where k.MachuyenNganh = (select MaChuyenNganh from SinhVien_Thuoc_Nganh where MSSV = ?) order by k.HocKy ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, masv);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maHp = rs.getString(1);
				String  tenHp = rs.getString(2);
				String hocPhanTQ = rs.getString(3);
				int  hocKy = rs.getInt(4);
				ChuongTrinhKhung k = new ChuongTrinhKhung(maHp, tenHp, hocPhanTQ, hocKy);
				dsM.add(k);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsM;

	}
	
}
