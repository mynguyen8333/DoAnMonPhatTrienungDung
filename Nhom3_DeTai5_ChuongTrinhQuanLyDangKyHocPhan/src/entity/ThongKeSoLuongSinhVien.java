package entity;

import java.awt.Component;

public class ThongKeSoLuongSinhVien {
	private String maMH;
	private String tenMH;
	private int soLuongSVCan;
	private int soLuonhSVDaDK;
	public String getMaMH() {
		return maMH;
	}
	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}
	public String getTenMH() {
		return tenMH;
	}
	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}
	public int getSoLuongSVCan() {
		return soLuongSVCan;
	}
	public void setSoLuongSVCan(int soLuongSVCan) {
		this.soLuongSVCan = soLuongSVCan;
	}
	public int getSoLuonhSVDaDK() {
		return soLuonhSVDaDK;
	}
	public void setSoLuonhSVDaDK(int soLuonhSVDaDK) {
		this.soLuonhSVDaDK = soLuonhSVDaDK;
	}
	public ThongKeSoLuongSinhVien(String maMH, String tenMH, int soLuongSVCan, int soLuonhSVDaDK) {
		super();
		this.maMH = maMH;
		this.tenMH = tenMH;
		this.soLuongSVCan = soLuongSVCan;
		this.soLuonhSVDaDK = soLuonhSVDaDK;
	}
	public ThongKeSoLuongSinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ThongKeSoLuongSinhVien [maMH=" + maMH + ", tenMH=" + tenMH + ", soLuongSVCan=" + soLuongSVCan
				+ ", soLuonhSVDaDK=" + soLuonhSVDaDK + "]";
	}

	
}
