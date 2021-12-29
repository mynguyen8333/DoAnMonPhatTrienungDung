package entity;

public class ChuyenNganh {
	private String maChuyenNganh;
	private String tenChuyenNganh;
	private String maKhoa;
	public String getMaChuyenNganh() {
		return maChuyenNganh;
	}
	public void setMaChuyenNganh(String maChuyenNganh) {
		this.maChuyenNganh = maChuyenNganh;
	}
	public String getTenChuyenNganh() {
		return tenChuyenNganh;
	}
	public void setTenChuyenNganh(String tenChuyenNganh) {
		this.tenChuyenNganh = tenChuyenNganh;
	}
	public String getMaKhoa() {
		return maKhoa;
	}
	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}
	public ChuyenNganh(String maChuyenNganh, String tenChuyenNganh, String maKhoa) {
		super();
		this.maChuyenNganh = maChuyenNganh;
		this.tenChuyenNganh = tenChuyenNganh;
		this.maKhoa = maKhoa;
	}
	public ChuyenNganh() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChuyenNganh [maChuyenNganh=" + maChuyenNganh + ", tenChuyenNganh=" + tenChuyenNganh + ", maKhoa="
				+ maKhoa + "]";
	}
	
	
}
