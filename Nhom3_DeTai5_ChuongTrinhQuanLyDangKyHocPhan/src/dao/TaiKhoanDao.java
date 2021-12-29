package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.TaiKhoan;

public class TaiKhoanDao {
	ArrayList<TaiKhoan> dsTK;
	public  TaiKhoanDao() {
		dsTK = new ArrayList<TaiKhoan>();
	}
	
	public ArrayList<TaiKhoan> timKiemTKSV(String maTK,String pass) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<TaiKhoan> tkiemTK1 = new ArrayList<TaiKhoan>();	
		String sql = "select *from TaiKhoanSV as TKSV where TKSV.MaTaiKhoan = ? and TKSV.Pass = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maTK);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String mk = rs.getString(2);
				
				TaiKhoan tk  = new TaiKhoan(ma, mk);
				tkiemTK1.add(tk);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tkiemTK1;
		
		
	}
	
	public ArrayList<TaiKhoan> timKiemTKNV(String maTK,String pass) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<TaiKhoan> tkiemTK = new ArrayList<TaiKhoan>();	
		String sql = "select *from TaiKhoanNV as TKSV "
				+ " where TKSV.MaTaiKhoan = ? and TKSV.Pass = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maTK);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String ma = rs.getString(1);
				String mk = rs.getString(2);
				
				TaiKhoan tk  = new TaiKhoan(ma, mk);
				tkiemTK.add(tk);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tkiemTK;
		
		
	}
	
	public boolean update(String taiKhoan,String matKhau) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update TaiKhoanSV "
										+ " set Pass = ?"
										+ " where MaTaiKhoan = ?");
			stmt.setString(1, matKhau);
			stmt.setString(2, taiKhoan);
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		return n > 0;
	}
}
