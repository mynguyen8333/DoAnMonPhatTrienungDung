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
import entity.SinhVien;
import entity.ThongKeLop;
import entity.ThongKeSoLuongSinhVien;

public class MonHocPhanDao {
	ArrayList<MonHocPhan> dsMHP =  new ArrayList<MonHocPhan>();
	public MonHocPhanDao() {
		
	}
	
	public ArrayList<MonHocPhan> doctubang() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql = "Select *from MonHocPhan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maMHP = rs.getString(1);
				String tenMHP = rs.getString(2);
				int soTC = rs.getInt(3);
				String hinhThucThi = rs.getString(4);
				String batBuoc = rs.getString(5);
				String maKhoa = rs.getString(6);
				String monhocyc = rs.getString(7);
				MonHocPhan mhp = new MonHocPhan(maMHP, tenMHP, soTC, hinhThucThi, batBuoc, maKhoa, monhocyc);
				
				dsMHP.add(mhp);		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsMHP;
	}
	
	
	public boolean ThemMHP(String maMHP,String tenMHP,int soTC,String hinThucT,String batBuoc,String maKhoa,String hocPhanYC) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml= null;
		int n=0;
		try {
			stml= con.prepareStatement("insert into MonHocPhan values (?,?,?,?,?,?,?)");
			stml.setString(1, maMHP);
			stml.setString(2, tenMHP);
			stml.setInt(3, soTC);
			stml.setString(4, hinThucT);
			stml.setString(5, batBuoc);
			stml.setString(6, maKhoa);
			stml.setString(7, hocPhanYC);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}return n>0;
	}
	
	public boolean xoaMHP(String maMHP) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("delete from MonHocPhan where MaMHP=?");
			stml.setString(1, maMHP);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}return n>0;
	}
	
	
	public String LayMaMon(String ten) {
		Connection con = DataBase.getInstance().getConnection();
		String ma = null;	
		String sql ="select MaMHP from MonHocPhan where TenMHHP = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ten);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ma = rs.getString(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ma;
	}
	
	public String LayTenMon(String ma) {
		Connection con = DataBase.getInstance().getConnection();
		String ten = null;	
		String sql ="select TenMHHP from MonHocPhan where MaMHP = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ma);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ten = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ten;
	}
		
	public  ArrayList<String> LayTenMHPTheoKi(String nam,int hocky) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<String> dsMHP = new ArrayList<String>();		
		String sql = "select MH.TenMHHP from LopHocPhan LH, MonHocPhan MH"
				+ " where LH.MaMHP = MH.MaMHP and LH.Nam = ? and HocKy = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nam);
			ps.setInt(2, hocky);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String tenmon = rs.getString(1);
				if(!(dsMHP.contains(tenmon)))
					dsMHP.add(tenmon);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMHP;


	}
	
	public  ArrayList<String> LayMaLopTheoTen(String nam,int hocky,String ten) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<String> dsMaLop = new ArrayList<String>();		
		String sql = "select LH.MaLopHP from LopHocPhan LH,MonHocPhan MH"
				+ " where LH.MaMHP = MH.MaMHP and MH.TenMHHP = ?"
				+ " and LH.Nam = ? and HocKy = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ten);
			ps.setString(2, nam);
			ps.setInt(3, hocky);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maLop = rs.getString(1);
				//if(!(dsMHP.contains(tenmon)))
				dsMaLop.add(maLop);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMaLop;


	}
	

	public ArrayList<String> getDsTenMon() {
		try {
			Connection con = DataBase.getInstance().getConnection();
			ArrayList<String> listTen = new ArrayList<String>();
			String sql = "select * from MonHocPhan";
			Statement statement = con.createStatement();
			ResultSet rs  = statement.executeQuery(sql);
			while(rs.next()) {
				String ten = rs.getString(2);
				listTen.add(ten);
			}
			return listTen;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public  ArrayList<MonHocPhan> TimTheoTen(String ten) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<MonHocPhan> dsMon = new ArrayList<MonHocPhan>();		
		String sql = "select * from MonHocPhan where TenMHHP = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ten);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int sotc = rs.getInt(3);
				String hinhThuc = rs.getString(4);
				String batBuoc = rs.getString(5);
				String maKhoa = rs.getString(6);
				String hocPhanYeuCau = rs.getString(7);
				MonHocPhan mh = new MonHocPhan(mamon, tenMon, sotc, hinhThuc, batBuoc, maKhoa, hocPhanYeuCau);
				
				
				dsMon.add(mh);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMon;


	}
	
	public  ArrayList<MonHocPhan> TimTinChi(int soTC) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<MonHocPhan> dsMon = new ArrayList<MonHocPhan>();		
		String sql = "select * from MonHocPhan where SoTinChi = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, soTC);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int sotc = rs.getInt(3);
				String hinhThuc = rs.getString(4);
				String batBuoc = rs.getString(5);
				String maKhoa = rs.getString(6);
				String hocPhanYeuCau = rs.getString(7);
				MonHocPhan mh = new MonHocPhan(mamon, tenMon, sotc, hinhThuc, batBuoc, maKhoa, hocPhanYeuCau);
				
				
				dsMon.add(mh);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMon;


	}
	
	public  ArrayList<MonHocPhan> TimKiemTheoKhoa(String tenKhoa) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<MonHocPhan> dsMon = new ArrayList<MonHocPhan>();		
		String sql = "select mh.* from Khoa k,MonHocPhan mh"
				+ " where k.MaKhoa = mh.MaKhoa and k.TenKhoa = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenKhoa);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int sotc = rs.getInt(3);
				String hinhThuc = rs.getString(4);
				String batBuoc = rs.getString(5);
				String maKhoa = rs.getString(6);
				String hocPhanYeuCau = rs.getString(7);
				MonHocPhan mh = new MonHocPhan(mamon, tenMon, sotc, hinhThuc, batBuoc, maKhoa, hocPhanYeuCau);
				
				
				dsMon.add(mh);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMon;


	}
	
	public  ArrayList<ThongKeLop> ThongKeLop(int hocKy,String nam) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ThongKeLop> dsLopTK = new ArrayList<ThongKeLop>();		
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
				dsLopTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLopTK;
	}
	
	public  ArrayList<String> ThongKeLop1(int hocKy,String nam) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<String> dsLopTK = new ArrayList<String>();		
		String sql = "select mh.TenMHHP from LopHocPhan lhp"
				+ " join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy =? and lhp.Nam = ?"
				+ " group by mh.TenMHHP";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocKy);
			ps.setString(2, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				String tenMon = rs.getString(1);
								
				dsLopTK.add(tenMon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLopTK;
	}
	
	
	public  ArrayList<ThongKeSoLuongSinhVien> ThongKeSoLuongSV(int hocKy,String nam) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ThongKeSoLuongSinhVien> dsTKSL = new ArrayList<ThongKeSoLuongSinhVien>();		
		String sql = "select mh.MaMHP, mh.TenMHHP ,Sum(lhp.SiSo) as"
				+ " 'số lượng sinh viên cần ', Sum(lhp.DaDangKy) as"
				+ " 'số lượng sinh viên đã đăng ký '   from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
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
				dsTKSL.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTKSL;
	}
	
	public  ArrayList<String> ThongKeSoLuongSV1(int hocKy,String nam) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<String> dsTKSL = new ArrayList<String>();		
		String sql = "select mh.TenMHHP from LopHocPhan lhp join"
				+ " MonHocPhan mh on lhp.MaMHP = mh.MaMHP where"
				+ " lhp.HocKy = ? and lhp.Nam = ? group by mh.TenMHHP";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocKy);
			ps.setString(2, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String tenMon = rs.getString(1);		
				dsTKSL.add(tenMon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTKSL;
	}
	
	
	/*
	 * Của ông tới
	 */
	public ArrayList<MonHocPhan> LayCacMonDuocDangKy(String maSV,String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<MonHocPhan> dsMHP = new ArrayList<MonHocPhan>();		
		String sql = "select MHP.* from (LopHocPhan as LHP join MonHocPhan as MHP on LHP.MaMHP = MHP.MaMHP) join ChuongTrinhKhung as CT on CT.MaMHP = LHP.MaMHP   where LHP.Nam =? and LHP.HocKy = ? and  CT.MachuyenNganh = (select SN.MaChuyenNganh from SinhVien_Thuoc_Nganh SN where SN.MSSV = ?) ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(3, maSV);
			ps.setString(1, nam);
			ps.setInt(2, hocKy);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maMHP = rs.getString(1);
				String tenMHP = rs.getString(2);
				int tinchi = rs.getInt(3);
				String hinhThuc = rs.getString(4);
				String batBuoc = rs.getString(5);
				String maKhoa = rs.getString(6);
				String hocPhanYeuCau = rs.getString(7);
				MonHocPhan mh = new MonHocPhan(maMHP, tenMHP, tinchi, hinhThuc, batBuoc, maKhoa, hocPhanYeuCau);
				if(!dsMHP.contains(mh))
					dsMHP.add(mh);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMHP;
		
		
	}
	public boolean ktHocPhanTienQuyet(String maSV, String maHPTQ) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<MonHocPhan> dsMHP = new ArrayList<MonHocPhan>();		
		String sql = "select mh.* from (PhieuDangKyLHP p join  LopHocPhan lhp on p.MaLopHP = lhp.MaLopHP) join MonHocPhan mh on lhp.MaMHP = mh.MaMHP where p.MSSV=? and mh.MaMHP = ?"  ;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maSV);
			ps.setString(2, maHPTQ);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String maMHP = rs.getString(1);
				String tenMHP = rs.getString(2);
				int tinchi = rs.getInt(3);
				String hinhThuc = rs.getString(4);
				String batBuoc = rs.getString(5);
				String maKhoa = rs.getString(6);
				String hocPhanYeuCau = rs.getString(7);
				MonHocPhan mh = new MonHocPhan(maMHP, tenMHP, tinchi, hinhThuc, batBuoc, maKhoa, hocPhanYeuCau);
				if(!dsMHP.contains(mh))
					dsMHP.add(mh);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(dsMHP.size() >0)
			return true;
		else 
			return false;
		
		
	}
	/*
	 * Mới ngày 22/11/2020
	 */
	
	public  ArrayList<ThongKeLop> LopNhieuNhat(String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ThongKeLop> dsLopTK = new ArrayList<ThongKeLop>();		
		String sql = "select  mh.MaMHP, mh.TenMHHP, COUNT(lhp.MaLopHP) as 'số lớp'"
				+ " from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy = ? and lhp.Nam = ?"
				+ " group by mh.MaMHP, mh.TenMHHP having COUNT(lhp.MaLopHP) ="
				+ " ( select top 1 COUNT(lhp.MaLopHP) as 'số lớp'"
				+ " from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy = ? and lhp.Nam = ?"
				+ " group by mh.MaMHP, mh.TenMHHP order by COUNT(lhp.MaLopHP) DESC)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocKy);
			ps.setString(2, nam);
			ps.setInt(3, hocKy);
			ps.setString(4, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int soLop = rs.getInt(3);
				ThongKeLop tk = new ThongKeLop(mamon, tenMon, soLop);				
				dsLopTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLopTK;
	}
	
	public  ArrayList<ThongKeLop> LopItNhat(String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ThongKeLop> dsLopTK = new ArrayList<ThongKeLop>();		
		String sql = "select  mh.MaMHP, mh.TenMHHP , COUNT(lhp.MaLopHP) as 'số lớp'"
				+ " from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy = ? and lhp.Nam = ?"
				+ " group by mh.MaMHP, mh.TenMHHP"
				+ " having COUNT(lhp.MaLopHP) = ( select top 1 COUNT(lhp.MaLopHP) as 'số lớp'"
				+ " from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy = ? and lhp.Nam = ? group by mh.MaMHP, mh.TenMHHP order by COUNT(lhp.MaLopHP) ASC )";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocKy);
			ps.setString(2, nam);
			ps.setInt(3, hocKy);
			ps.setString(4, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int soLop = rs.getInt(3);
				ThongKeLop tk = new ThongKeLop(mamon, tenMon, soLop);				
				dsLopTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLopTK;
	}
	
	public  ArrayList<ThongKeSoLuongSinhVien> MonNhieuNhat(String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ThongKeSoLuongSinhVien> dsmon = new ArrayList<ThongKeSoLuongSinhVien>();		
		String sql = "select mh.MaMHP, mh.TenMHHP ,Sum(lhp.SiSo) as 'số lượng sinh viên cần ',"
				+ " Sum(lhp.DaDangKy) as 'số lượng sinh viên đã đăng ký '"
				+ " from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy =?  and lhp.Nam = ?"
				+ " group by mh.MaMHP, mh.TenMHHP having Sum(lhp.DaDangKy) ="
				+ " (select top 1  Sum(lhp.DaDangKy) from LopHocPhan lhp join MonHocPhan mh"
				+ " on lhp.MaMHP = mh.MaMHP where lhp.HocKy =?  and lhp.Nam = ?"
				+ " group by mh.MaMHP, mh.TenMHHP order by Sum(lhp.DaDangKy) DESC)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocKy);
			ps.setString(2, nam);
			ps.setInt(3, hocKy);
			ps.setString(4, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int soSVCan = rs.getInt(3);
				int soSVDaDK = rs.getInt(4);
				ThongKeSoLuongSinhVien tk = new ThongKeSoLuongSinhVien(mamon, tenMon, soSVCan, soSVDaDK);			
				dsmon.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsmon;
	}
	
	public  ArrayList<ThongKeSoLuongSinhVien> MonItNhat(String nam,int hocKy) {
		Connection con = DataBase.getInstance().getConnection();
		ArrayList<ThongKeSoLuongSinhVien> dsmon = new ArrayList<ThongKeSoLuongSinhVien>();		
		String sql = "select mh.MaMHP, mh.TenMHHP ,Sum(lhp.SiSo) as 'số lượng sinh viên cần ',"
				+ " Sum(lhp.DaDangKy) as 'số lượng sinh viên đã đăng ký '"
				+ " from LopHocPhan lhp join MonHocPhan mh on lhp.MaMHP = mh.MaMHP"
				+ " where lhp.HocKy =?  and lhp.Nam = ?"
				+ " group by mh.MaMHP, mh.TenMHHP having Sum(lhp.DaDangKy) ="
				+ " (select top 1 Sum(lhp.DaDangKy) from LopHocPhan lhp join MonHocPhan mh"
				+ " on lhp.MaMHP = mh.MaMHP where lhp.HocKy =?  and lhp.Nam = ?"
				+ " group by mh.MaMHP, mh.TenMHHP order by Sum(lhp.DaDangKy) ASC)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, hocKy);
			ps.setString(2, nam);
			ps.setInt(3, hocKy);
			ps.setString(4, nam);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String mamon = rs.getString(1);
				String tenMon = rs.getString(2);
				int soSVCan = rs.getInt(3);
				int soSVDaDK = rs.getInt(4);
				ThongKeSoLuongSinhVien tk = new ThongKeSoLuongSinhVien(mamon, tenMon, soSVCan, soSVDaDK);			
				dsmon.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsmon;
	}
	
	public int LayMaTuDong() {
		int stt = 0;
		String sql = "select COUNT(MaMHP) from MonHocPhan";
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
	
	public MonHocPhan KiemTraTenMon(String tenLop) {
		MonHocPhan mh = null;
		try {
			Connection con = DataBase.getInstance().getConnection();
			String sql="select * from MonHocPhan where TenMHHP = N'"+tenLop+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maMHP = rs.getString(1);
				String tenMHP = rs.getString(2);
				int tinchi = rs.getInt(3);
				String hinhThuc = rs.getString(4);
				String batBuoc = rs.getString(5);
				String maKhoa = rs.getString(6);
				String hocPhanYeuCau = rs.getString(7);
				mh = new MonHocPhan(maMHP, tenMHP, tinchi, hinhThuc, batBuoc, maKhoa, hocPhanYeuCau);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mh;
	}
	
	/*
	 *Hàm mới ngày 22/11/2020 
	 */
	public boolean capNhatMonHocPhan(String maMHP,String tenMHP,int soTC,String hinhThucThi,String BatBuoc,String maKhoa,String hPYC) {
		Connection con = DataBase.getInstance().getConnection();
		PreparedStatement stml = null;
		int n =0;
		try {
			stml = con.prepareStatement("update MonHocPhan set TenMHHP = ?,"
					+ " SoTinChi = ?,HinhThucThi = ?,BatBuoc = ?,MaKhoa = ?,"
					+ " HocPhanYeuCau = ? where MaMHP = ?");
			stml.setString(1, tenMHP);
			stml.setInt(2, soTC);
			stml.setString(3, hinhThucThi);
			stml.setString(4, BatBuoc);
			stml.setString(5, maKhoa);
			stml.setString(6, hPYC);
			stml.setString(7, maMHP);
			n = stml.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}return n>0;
	}
	
	
	
	
}
