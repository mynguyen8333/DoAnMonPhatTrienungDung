package entity;

public class PhongHoc {
	private String tenPhong;
	private String ghiChu;
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public PhongHoc(String tenPhong, String ghiChu) {
		super();
		this.tenPhong = tenPhong;
		this.ghiChu = ghiChu;
	}
	public PhongHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tenPhong == null) ? 0 : tenPhong.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongHoc other = (PhongHoc) obj;
		if (tenPhong == null) {
			if (other.tenPhong != null)
				return false;
		} else if (!tenPhong.equals(other.tenPhong))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhongHoc [tenPhong=" + tenPhong + ", ghiChu=" + ghiChu + "]";
	}
	
	
}
