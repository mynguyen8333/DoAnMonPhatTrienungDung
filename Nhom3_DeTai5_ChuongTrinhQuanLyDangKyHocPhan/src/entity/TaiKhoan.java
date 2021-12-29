package entity;

public class TaiKhoan {
	private String maTaiKhoan;
	private String pass;
	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}
	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public TaiKhoan(String maTaiKhoan, String pass) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.pass = pass;
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", pass=" + pass + "]";
	}
	
	

}
