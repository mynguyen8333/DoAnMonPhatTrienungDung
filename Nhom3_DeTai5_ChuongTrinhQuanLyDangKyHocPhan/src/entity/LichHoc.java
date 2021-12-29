package entity;

import java.sql.Date;

public class LichHoc {
	private String ngayHoc, tietHoc, tenMonHoc,  phongHoc, nhom;
	private Date ngaybatdau;
	public String getNgayHoc() {
		return ngayHoc;
	}
	public void setNgayHoc(String ngayHoc) {
		this.ngayHoc = ngayHoc;
	}
	public String getTietHoc() {
		return tietHoc;
	}
	public void setTietHoc(String tietHoc) {
		this.tietHoc = tietHoc;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public String getPhongHoc() {
		return phongHoc;
	}
	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}
	public String getNhom() {
		return nhom;
	}
	public void setNhom(String nhom) {
		this.nhom = nhom;
	}
	public Date getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	public LichHoc(String ngayHoc, String tietHoc, String tenMonHoc, String phongHoc, String nhom, Date ngaybatdau) {
		super();
		this.ngayHoc = ngayHoc;
		this.tietHoc = tietHoc;
		this.tenMonHoc = tenMonHoc;
		this.phongHoc = phongHoc;
		this.nhom = nhom;
		this.ngaybatdau = ngaybatdau;
	}
	public LichHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LichHoc [ngayHoc=" + ngayHoc + ", tietHoc=" + tietHoc + ", tenMonHoc=" + tenMonHoc + ", phongHoc="
				+ phongHoc + ", nhom=" + nhom + ", ngaybatdau=" + ngaybatdau + "]";
	}



	
	
	
}
