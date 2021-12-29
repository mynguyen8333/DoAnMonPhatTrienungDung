package entity;

import java.sql.Date;

public class SinhVien {
	private String maSV;
	private String diaChi;
	private String gioiTinh;
	private String hoTen;
	private Date ngaySinh;
	private String sdt;
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSV == null) ? 0 : maSV.hashCode());
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
		SinhVien other = (SinhVien) obj;
		if (maSV == null) {
			if (other.maSV != null)
				return false;
		} else if (!maSV.equals(other.maSV))
			return false;
		return true;
	}
	public SinhVien(String maSV, String diaChi, String gioiTinh, String hoTen, Date ngaySinh, String sdt) {
		super();
		this.maSV = maSV;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.sdt = sdt;
	}
	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SinhVien [maSV=" + maSV + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", hoTen=" + hoTen
				+ ", ngaySinh=" + ngaySinh + ", sdt=" + sdt + "]";
	}
	
	
	

}
