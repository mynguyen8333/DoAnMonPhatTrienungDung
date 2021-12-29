package entity;

public class PhieuDangKyLopHP {
	private String mssv;
	private String maLopHP;
	private String nhom;
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getMaLopHP() {
		return maLopHP;
	}
	public void setMaLopHP(String maLopHP) {
		this.maLopHP = maLopHP;
	}
	public String getNhom() {
		return nhom;
	}
	public void setNhom(String nhom) {
		this.nhom = nhom;
	}
	public PhieuDangKyLopHP(String mssv, String maLopHP, String nhom) {
		super();
		this.mssv = mssv;
		this.maLopHP = maLopHP;
		this.nhom = nhom;
	}
	@Override
	public String toString() {
		return "PhieuDangKyLopHP [mssv=" + mssv + ", maLopHP=" + maLopHP + ", nhom=" + nhom + "]";
	}
	
}
