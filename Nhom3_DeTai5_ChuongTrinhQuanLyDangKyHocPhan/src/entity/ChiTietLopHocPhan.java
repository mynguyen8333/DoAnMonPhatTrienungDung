package entity;

import java.sql.Date;

public class ChiTietLopHocPhan {
	private String maNhom;
	private String maLop;
	private String tietHoc;
	private String ngayHoc;
	private String phongHoc;
	private String maGV;
	private Date ngayBD;
	public String getMaNhom() {
		return maNhom;
	}
	public void setMaNhom(String maNhom) {
		this.maNhom = maNhom;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
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
	public String getMaGV() {
		return maGV;
	}
	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}
	public Date getNgayBD() {
		return ngayBD;
	}
	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}
	public ChiTietLopHocPhan(String maNhom, String maLop, String tietHoc, String ngayHoc, String phongHoc, String maGV,
			Date ngayBD) {
		super();
		this.maNhom = maNhom;
		this.maLop = maLop;
		this.tietHoc = tietHoc;
		this.ngayHoc = ngayHoc;
		this.phongHoc = phongHoc;
		this.maGV = maGV;
		this.ngayBD = ngayBD;
	}
	public ChiTietLopHocPhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChiTietLopHocPhan [maNhom=" + maNhom + ", maLop=" + maLop + ", tietHoc=" + tietHoc + ", ngayHoc="
				+ ngayHoc + ", phongHoc=" + phongHoc + ", maGV=" + maGV + ", ngayBD=" + ngayBD + "]";
	}
	
	
	
}
