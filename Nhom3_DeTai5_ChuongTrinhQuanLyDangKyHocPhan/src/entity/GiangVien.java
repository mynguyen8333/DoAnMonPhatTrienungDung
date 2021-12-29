package entity;

import java.sql.Date;

public class GiangVien {
	private String maGV;
	private String tenGV;
	private String diaChi;
	private String gioTinh;
	private Date ngaySinh;
	private String sDT;
	private String maKhoa;
	public String getMaGV() {
		return maGV;
	}
	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}
	public String getTenGV() {
		return tenGV;
	}
	public void setTenGV(String tenGV) {
		this.tenGV = tenGV;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getGioTinh() {
		return gioTinh;
	}
	public void setGioTinh(String gioTinh) {
		this.gioTinh = gioTinh;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getsDT() {
		return sDT;
	}
	public void setsDT(String sDT) {
		this.sDT = sDT;
	}
	public String getMaKhoa() {
		return maKhoa;
	}
	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}
	public GiangVien(String maGV, String tenGV, String diaChi, String gioTinh, Date ngaySinh, String sDT,
			String maKhoa) {
		super();
		this.maGV = maGV;
		this.tenGV = tenGV;
		this.diaChi = diaChi;
		this.gioTinh = gioTinh;
		this.ngaySinh = ngaySinh;
		this.sDT = sDT;
		this.maKhoa = maKhoa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maGV == null) ? 0 : maGV.hashCode());
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
		GiangVien other = (GiangVien) obj;
		if (maGV == null) {
			if (other.maGV != null)
				return false;
		} else if (!maGV.equals(other.maGV))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GiangVien [maGV=" + maGV + ", tenGV=" + tenGV + ", diaChi=" + diaChi + ", gioTinh=" + gioTinh
				+ ", ngaySinh=" + ngaySinh + ", sDT=" + sDT + ", maKhoa=" + maKhoa + "]";
	}
	
	
}
