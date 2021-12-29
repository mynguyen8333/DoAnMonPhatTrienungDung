package entity;

public class ChuongTrinhKhung {
	private String maHp, tenHp, hocPhanTQ;
	private int hocKy;
	public String getMaHp() {
		return maHp;
	}
	public void setMaHp(String maHp) {
		this.maHp = maHp;
	}
	public String getTenHp() {
		return tenHp;
	}
	public void setTenHp(String tenHp) {
		this.tenHp = tenHp;
	}
	public String getHocPhanTQ() {
		return hocPhanTQ;
	}
	public void setHocPhanTQ(String hocPhanTQ) {
		this.hocPhanTQ = hocPhanTQ;
	}
	public int getHocKy() {
		return hocKy;
	}
	public void setHocKy(int hocKy) {
		this.hocKy = hocKy;
	}
	public ChuongTrinhKhung(String maHp, String tenHp, String hocPhanTQ, int hocKy) {
		super();
		this.maHp = maHp;
		this.tenHp = tenHp;
		this.hocPhanTQ = hocPhanTQ;
		this.hocKy = hocKy;
	}
	@Override
	public String toString() {
		return "ChuongTrinhKhung [maHp=" + maHp + ", tenHp=" + tenHp + ", hocPhanTQ=" + hocPhanTQ + ", hocKy=" + hocKy
				+ "]";
	}
	
}
