package entity;

import java.sql.Date;

public class LopHocPhan {
	private String maLopHP;
	private int siSo;
	private String maMHP;
	private String nam;
	private int hocKy;
	private int soLuongDK;
	public String getMaLopHP() {
		return maLopHP;
	}
	public void setMaLopHP(String maLopHP) {
		this.maLopHP = maLopHP;
	}
	public int getSiSo() {
		return siSo;
	}
	public void setSiSo(int siSo) {
		this.siSo = siSo;
	}
	public String getMaMHP() {
		return maMHP;
	}
	public void setMaMHP(String maMHP) {
		this.maMHP = maMHP;
	}
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	public int getHocKy() {
		return hocKy;
	}
	public void setHocKy(int hocKy) {
		this.hocKy = hocKy;
	}
	public int getSoLuongDK() {
		return soLuongDK;
	}
	public void setSoLuongDK(int soLuongDK) {
		this.soLuongDK = soLuongDK;
	}
	public LopHocPhan(String maLopHP, int siSo, String maMHP, String nam, int hocKy, int soLuongDK) {
		super();
		this.maLopHP = maLopHP;
		this.siSo = siSo;
		this.maMHP = maMHP;
		this.nam = nam;
		this.hocKy = hocKy;
		this.soLuongDK = soLuongDK;
	}
	public LopHocPhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LopHocPhan [maLopHP=" + maLopHP + ", siSo=" + siSo + ", maMHP=" + maMHP + ", nam=" + nam + ", hocKy="
				+ hocKy + ", soLuongDK=" + soLuongDK + "]";
	}
	
	
	
}
