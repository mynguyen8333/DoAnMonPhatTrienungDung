package entity;

public class LopHocPhanDaDangKy {
	private String maLop;
	private String tenMonHoc;
	private String nhom;
	private String giangVien;
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public String getNhom() {
		return nhom;
	}
	public void setNhom(String nhom) {
		this.nhom = nhom;
	}
	public String getGiangVien() {
		return giangVien;
	}
	public void setGiangVien(String giangVien) {
		this.giangVien = giangVien;
	}
	public LopHocPhanDaDangKy(String maLop, String tenMonHoc, String nhom, String giangVien) {
		super();
		this.maLop = maLop;
		this.tenMonHoc = tenMonHoc;
		this.nhom = nhom;
		this.giangVien = giangVien;
	}
	@Override
	public String toString() {
		return "LopHocPhanDaDangKy [maLop=" + maLop + ", tenMonHoc=" + tenMonHoc + ", nhom=" + nhom + ", giangVien="
				+ giangVien + "]";
	}
	
}
