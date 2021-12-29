package entity;

public class CongNo {
	private String tenMon,  nam;
	private int soChi, hoKy;
	private double tienHoc;
	
	public double getTienHoc() {
		return tienHoc;
	}
	public void setTienHoc(double tienHoc) {
		this.tienHoc = tienHoc;
	}
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	public int getSoChi() {
		return soChi;
	}
	public void setSoChi(int soChi) {
		this.soChi = soChi;
	}
	public int getHoKy() {
		return hoKy;
	}
	public void setHoKy(int hoKy) {
		this.hoKy = hoKy;
	}
	
	@Override
	public String toString() {
		return "CongNo [tenMon=" + tenMon + ", nam=" + nam + ", soChi=" + soChi + ", hoKy=" + hoKy + ", tienHoc="
				+ tienHoc + "]";
	}
	public CongNo(String tenMon, String nam, int soChi, int hoKy, Double tienHoc) {
		super();
		this.tenMon = tenMon;
		this.nam = nam;
		this.soChi = soChi;
		this.hoKy = hoKy;
		this.tienHoc = tienHoc;
	}
	public CongNo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
