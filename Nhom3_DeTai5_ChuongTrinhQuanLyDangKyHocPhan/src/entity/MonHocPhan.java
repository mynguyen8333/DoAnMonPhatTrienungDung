package entity;

public class MonHocPhan {
	private String maMonHocPhan;
	private String tenMonHocPhan;
	private int soTinChi;
	private String hinhThucThi;
	private String batBuoc;
	private String maKhoa;
	private String hocPhanYeuCau;
	public String getMaMonHocPhan() {
		return maMonHocPhan;
	}
	public void setMaMonHocPhan(String maMonHocPhan) {
		this.maMonHocPhan = maMonHocPhan;
	}
	public String getTenMonHocPhan() {
		return tenMonHocPhan;
	}
	public void setTenMonHocPhan(String tenMonHocPhan) {
		this.tenMonHocPhan = tenMonHocPhan;
	}
	public int getSoTinChi() {
		return soTinChi;
	}
	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
	}
	public String getHinhThucThi() {
		return hinhThucThi;
	}
	public void setHinhThucThi(String hinhThucThi) {
		this.hinhThucThi = hinhThucThi;
	}
	public String getBatBuoc() {
		return batBuoc;
	}
	public void setBatBuoc(String batBuoc) {
		this.batBuoc = batBuoc;
	}
	public String getMaKhoa() {
		return maKhoa;
	}
	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}
	public String getHocPhanYeuCau() {
		return hocPhanYeuCau;
	}
	public void setHocPhanYeuCau(String hocPhanYeuCau) {
		this.hocPhanYeuCau = hocPhanYeuCau;
	}
	public MonHocPhan(String maMonHocPhan, String tenMonHocPhan, int soTinChi, String hinhThucThi, String batBuoc,
			String maKhoa, String hocPhanYeuCau) {
		super();
		this.maMonHocPhan = maMonHocPhan;
		this.tenMonHocPhan = tenMonHocPhan;
		this.soTinChi = soTinChi;
		this.hinhThucThi = hinhThucThi;
		this.batBuoc = batBuoc;
		this.maKhoa = maKhoa;
		this.hocPhanYeuCau = hocPhanYeuCau;
	}
	public MonHocPhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maMonHocPhan == null) ? 0 : maMonHocPhan.hashCode());
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
		MonHocPhan other = (MonHocPhan) obj;
		if (maMonHocPhan == null) {
			if (other.maMonHocPhan != null)
				return false;
		} else if (!maMonHocPhan.equals(other.maMonHocPhan))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MonHocPhan [maMonHocPhan=" + maMonHocPhan + ", tenMonHocPhan=" + tenMonHocPhan + ", soTinChi="
				+ soTinChi + ", hinhThucThi=" + hinhThucThi + ", batBuoc=" + batBuoc + ", maKhoa=" + maKhoa
				+ ", hocPhanYeuCau=" + hocPhanYeuCau + "]";
	}
	
	
	
}
