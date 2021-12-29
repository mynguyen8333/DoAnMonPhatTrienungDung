package entity;

import java.sql.Date;

public class ChiTietLopHocPhan1 {
	private String maMHP;
	private String maLHP;
	private String maNhom;
	private String tietHoc;
	private String ngayHoc;
	private String phongHoc;
	private String hoTen;
	private Date ngayBD;
	public String getMaMHP() {
		return maMHP;
	}
	public void setMaMHP(String maMHP) {
		this.maMHP = maMHP;
	}
	public String getMaLHP() {
		return maLHP;
	}
	public void setMaLHP(String maLHP) {
		this.maLHP = maLHP;
	}
	public String getMaNhom() {
		return maNhom;
	}
	public void setMaNhom(String maNhom) {
		this.maNhom = maNhom;
	}
	public String getTietHoc() {
		return tietHoc;
	}
	public void setTietHoc(String tietHoc) {
		this.tietHoc = tietHoc;
	}
	public String getNgayHoc() {
		return ngayHoc;
	}
	public void setNgayHoc(String ngayHoc) {
		this.ngayHoc = ngayHoc;
	}
	public String getPhongHoc() {
		return phongHoc;
	}
	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgayBD() {
		return ngayBD;
	}
	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}
	public ChiTietLopHocPhan1(String maMHP, String maLHP, String maNhom, String tietHoc, String ngayHoc,
			String phongHoc, String hoTen, Date ngayBD) {
		super();
		this.maMHP = maMHP;
		this.maLHP = maLHP;
		this.maNhom = maNhom;
		this.tietHoc = tietHoc;
		this.ngayHoc = ngayHoc;
		this.phongHoc = phongHoc;
		this.hoTen = hoTen;
		this.ngayBD = ngayBD;
	}
	public ChiTietLopHocPhan1() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChiTietLopHocPhan1 [maMHP=" + maMHP + ", maLHP=" + maLHP + ", maNhom=" + maNhom + ", tietHoc=" + tietHoc
				+ ", ngayHoc=" + ngayHoc + ", phongHoc=" + phongHoc + ", hoTen=" + hoTen + ", ngayBD=" + ngayBD + "]";
	}
	
	
}
