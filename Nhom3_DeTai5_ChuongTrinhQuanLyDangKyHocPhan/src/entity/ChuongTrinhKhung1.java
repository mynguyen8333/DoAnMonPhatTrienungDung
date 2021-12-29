package entity;

public class ChuongTrinhKhung1 {
	private String maChuyenNganh;
	private String maMon;
	private int hocKy;
	public String getMaChuyenNganh() {
		return maChuyenNganh;
	}
	public void setMaChuyenNganh(String maChuyenNganh) {
		this.maChuyenNganh = maChuyenNganh;
	}
	public String getMaMon() {
		return maMon;
	}
	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}
	public int getHocKy() {
		return hocKy;
	}
	public void setHocKy(int hocKy) {
		this.hocKy = hocKy;
	}
	public ChuongTrinhKhung1(String maChuyenNganh, String maMon, int hocKy) {
		super();
		this.maChuyenNganh = maChuyenNganh;
		this.maMon = maMon;
		this.hocKy = hocKy;
	}
	public ChuongTrinhKhung1() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChuongTrinhKhung1 [maChuyenNganh=" + maChuyenNganh + ", maMon=" + maMon + ", hocKy=" + hocKy + "]";
	}
	
}
