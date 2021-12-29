package entity;

public class DanhSachSVCN {
	private String mssv;
	private String maCN;
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getMaCN() {
		return maCN;
	}
	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}
	public DanhSachSVCN(String mssv, String maCN) {
		super();
		this.mssv = mssv;
		this.maCN = maCN;
	}
	public DanhSachSVCN() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DanhSachSVCN [mssv=" + mssv + ", maCN=" + maCN + "]";
	}
	
	
}
